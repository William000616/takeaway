<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-cascades"></i>店铺管理/菜品管理
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <span>商品名：</span>
                <el-input v-model="params.good_name" size="small" placeholder="请输入账户名称" class="handle-input mr10">
                </el-input>
                <el-button @click="handleClean">重置</el-button>
                <el-button type="primary" @click="handleSearch">搜索</el-button>
                <el-button type="primary" @click="handleCreate">新增菜品</el-button>
            </div>
            <el-table :data="tableData" border class="table" ref="multipleTable" header-cell-class-name="table-header">
                <el-table-column prop="good_name" label="商品名"></el-table-column>
                <el-table-column label="图片(查看大图)" align="center">
                    <template #default="scope">
                        <el-image class="table-td-thumb" :src="scope.row.good_pic" :preview-src-list="[scope.row.good_pic]">
                        </el-image>
                    </template>
                </el-table-column>
                <el-table-column prop="price" label="价格"></el-table-column>
                <el-table-column prop="category_name" label="类别"></el-table-column>
                <el-table-column prop="sales" label="销量"></el-table-column>
                <el-table-column label="操作" width="180" align="center">
                    <template #default="scope">
                        <el-button type="text" @click="handleEdit(scope.$index, scope.row)">编辑
                        </el-button>
                        <el-button type="text" class="red" @click="handleDelete(scope.$index, scope.row)">下架</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="pagination">
                <el-pagination background layout="total, prev, pager, next" :current-page="params.pageNum"
                    :page-size="params.pageSize" :total="pageTotal" @current-change="handlePageChange"></el-pagination>
            </div>
        </div>

        <!-- 编辑弹出框 -->
        <el-dialog title="编辑" v-model="editVisible" width="40%">
            <el-form :model="form" :rules="rules" ref="Edit" label-width="70px">
                <el-form-item label="商品名" prop="good_name">
                    <el-input v-model="form.good_name"></el-input>
                </el-form-item>
                <el-form-item label="图片" prop="good_pic">
                    <el-upload class="avatar-uploader" action="http://localhost:3000/file/upload" :show-file-list="false"
                        :on-success="handleAvatarSuccessEdit" :before-upload="beforeAvatarUpload">
                        <img v-if="form.good_pic" :src="form.good_pic" class="avatar" />
                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                    </el-upload>
                </el-form-item>
                <el-form-item label="价格(￥)" prop="price">
                    <el-input v-model="form.price"></el-input>
                </el-form-item>
                <el-form-item label="类别" prop="category">
                    <el-select v-model="form.c_id" class="m-2" placeholder="类别" size="large">
                        <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value" />
                    </el-select>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="editVisible = false">取 消</el-button>
                    <el-button type="primary" @click="saveEdit(form)">确 定</el-button>
                </span>
            </template>
        </el-dialog>

        <!-- 新建弹出框 -->
        <el-dialog title="新增菜品" v-model="createVisible" width="30%">
            <el-form :model="New" :rules="rules" ref="Create" label-width="90px">
                <el-form-item label="商品名" prop="good_name">
                    <el-input v-model="New.good_name"></el-input>
                </el-form-item>
                <el-form-item label="图片" prop="good_pic">
                    <el-upload class="avatar-uploader" action="http://localhost:3000/file/upload" :show-file-list="false"
                        :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
                        <img v-if="New.good_pic" :src="New.good_pic" class="avatar" />
                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                    </el-upload>
                </el-form-item>
                <el-form-item label="价格(￥)" prop="price">
                    <el-input v-model="New.price"></el-input>
                </el-form-item>
                <el-form-item label="类别" prop="category">
                    <el-select v-model="New.c_id" class="m-2" placeholder="类别" size="large">
                        <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value" />
                    </el-select>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="createVisible = false">取 消</el-button>
                    <el-button type="primary" @click="saveCreate(New)">确 定</el-button>
                </span>
            </template>
        </el-dialog>
    </div>
</template>

<script>
import { ref, reactive } from "vue";
import { ElMessage, ElMessageBox } from "element-plus";
import { GetGood, goodAdd, goodDelete, GetCategory, goodEdit } from "../api/index";
export default {
    name: "basetable",
    setup() {
        const options = ref([]);
        const params = reactive({
            good_name: "",
            pageNum: 1,
            pageSize: 10,
        });
        const rules = {
            good_name: [
                {
                    required: true,
                    message: "商品名称不能为空",
                    trigger: "blur",
                },
            ],
            price: [
                { required: true, message: "商品价格不能为空", trigger: "blur" },
            ],
            good_pic: [
                { required: true, message: "商品照片不能为空", trigger: "blur" },
            ],
        };
        const tableData = ref([]);
        const pageTotal = ref(0);
        // 获取表格数据
        const getData = () => {
            params.s_id = localStorage.getItem("s_id");
            GetGood(params).then((res) => {
                if (res.code === '200') {
                    var list = res.data.list
                    list.map((e) => {
                        options.value.map((item) => {
                            if (item.value === e.c_id) {
                                e.category_name = item.label;
                            }
                        })
                    })
                    tableData.value = list;
                    pageTotal.value = res.data.total || 0;
                }

            });
        };
        const getCategory = () => {
            params.s_id = localStorage.getItem("s_id");
            GetCategory(params).then((res) => {
                if (res.code === '200') {
                    let list = res.data;
                    let newList = [];
                    list.map((e) => {
                        let obj = {
                            label: e.category_name,
                            value: e.c_id
                        }
                        newList.push(obj)
                        options.value = newList;
                    })
                }

            });
        };
        getCategory();
        getData();
        // 查询操作
        const handleAvatarSuccess = (res) => {
            if (res.code === '200') {
                New.good_pic = res.data
            }
        };
        const handleAvatarSuccessEdit = (res) => {
            if (res.code === '200') {
                form.good_pic = res.data
            }
        };
        const beforeAvatarUpload = (rawFile) => {
            if (rawFile.type !== 'image/jpeg' && rawFile.type !== 'image/png') {//只能为图片类型
                // imageUrl=rawFile
                ElMessage.error('请选择图片类型的文件！')
                return false
            } else if (rawFile.size / 1024 / 1024 > 2) {
                ElMessage.error('大小不能超过2MB!')//不能大于2MB
                return false
            }
            return true;
        }
        const handleClean = () => {
            params.good_name = "";
            getData();
        };
        const handleSearch = () => {
            getData();
        };
        // 分页导航
        const handlePageChange = (val) => {
            params.pageNum = val;
            getData();
        };

        // 删除操作
        const handleDelete = (index, row) => {
            // 二次确认删除
            ElMessageBox.confirm("确定要下架吗？", "提示", {
                type: "warning",
            })
                .then(() => {

                    goodDelete(row).then((res) => {
                        if (res.code === '200') {
                            ElMessage.success("下架成功");
                            getData();
                        }
                    })

                })
                .catch(() => { });
        };

        // 表格编辑时弹窗和保存
        const editVisible = ref(false);
        //新建账户弹窗和保存
        const createVisible = ref(false);
        const Create = ref(null);
        const Edit = ref(null);
        let New = reactive({
            good_name: "",
            good_pic: "",
            price: "",
            c_id: "",
            s_id: "",

        });
        let form = reactive({
            g_id: "",
            good_name: "",
            good_pic: "",
            price: "",
            c_id: "",
            s_id: "",

        });

        let idx = -1;
        const handleEdit = (index, row) => {
            idx = index;
            Object.keys(form).forEach((item) => {

                form[item] = row[item];
            });
            editVisible.value = true;
        };
        const handleState = (index, row) => {
            if (row.stat == 1) {
                row.stat = 0
                userEdit(row).then((res) => {
                    ElMessage.success(`状态修改成功`);
                    getData();
                });

            }
            else {
                row.stat = 1
                userEdit(row).then((res) => {
                    ElMessage.success(`状态修改成功`);
                    getData();
                });
            }
        }
        const handleCreate = () => {
            createVisible.value = true;

        };
        const saveCreate = (query) => {
            Create.value.validate((valid) => {
                if (valid) {
                    query.s_id = localStorage.getItem("s_id");
                    goodAdd(query).then((res) => {
                        if (res.code === "200") {
                            ElMessage.success(`创建成功`);
                            createVisible.value = false;
                            getData();
                            New.good_name = '';
                            New.good_pic = '';
                            New.price = '';
                            New.c_id = '';
                            New.s_id = '';
                        } else {
                            ElMessage.error(`创建失败，请检查填写内容`);
                        }

                    });
                }
            })

        }
        const saveEdit = (query) => {
            Edit.value.validate((valid) => {
                if (valid) {
                    goodEdit(query).then((res) => {
                        if (res.code === "200") {
                            editVisible.value = false;
                            ElMessage.success(`修改成功`);
                            getData();
                        } else {
                            ElMessage.error(`修改失败`);
                        }

                    });
                }
            })
        };

        return {
            tableData,
            pageTotal,
            editVisible,
            createVisible,
            form,
            New,
            params,
            rules,
            Create,
            Edit,
            options,
            handleSearch,
            handlePageChange,
            handleDelete,
            handleEdit,
            saveEdit,
            handleCreate,
            handleState,
            handleClean,
            saveCreate,
            handleAvatarSuccess,
            beforeAvatarUpload,
            handleAvatarSuccessEdit,
        };
    },
};
</script>

<style>
.handle-box {
    margin-bottom: 20px;
}

.handle-select {
    width: 130px;
}

.handle-input {
    width: 130px;
    display: inline-block;
}

.table {
    width: 100%;
    font-size: 14px;
}

.red {
    color: #ff0000;
}

.mr10 {
    margin-right: 10px;
}

.table-td-thumb {
    display: block;
    margin: auto;
    width: 40px;
    height: 40px;
}

.avatar-uploader .el-upload {
    border: 1px dashed var(--el-border-color);
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    transition: var(--el-transition-duration-fast);
    width: 178px;
}

.avatar-uploader .el-upload:hover {
    border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    text-align: center;
}

.avatar-uploader .avatar {
    width: 178px;
    height: 178px;
    display: block;
}

.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    width: 178px;
}

.avatar-uploader .el-upload:hover {
    border-color: #409EFF;
}

.avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
}

.avatar {
    width: 178px;
    height: 178px;
    display: block;
}

.el-upload--text {
    width: 178px;
    height: 180px;

}
</style>
