<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-cascades"></i>我的地址
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-button type="primary" @click="handleCreate">新增</el-button>
            </div>
            <el-table :data="tableData" border class="table" ref="multipleTable" header-cell-class-name="table-header">
                <el-table-column prop="name" label="收货人" align="center"></el-table-column>
                <el-table-column prop="tel" label="手机号" align="center"></el-table-column>
                <el-table-column prop="address" label="地址" width="700" align="center"></el-table-column>
                <el-table-column label="操作" width="180" align="center">
                    <template #default="scope">
                        <el-button type="text" @click="handleEdit(scope.$index, scope.row)">编辑
                        </el-button>
                        <el-button type="text" class="red" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
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
                <el-form-item label="收货人" prop="name">
                    <el-input v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item label="手机号" prop="tel">
                    <el-input v-model="form.tel"></el-input>
                </el-form-item>
                <el-form-item label="地址" prop="address">
                    <el-input v-model="form.address"></el-input>
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
        <el-dialog title="新建账户" v-model="createVisible" width="30%">
            <el-form :model="New" :rules="rules" ref="Create" label-width="70px">
                <el-form-item label="收货人" prop="name">
                    <el-input v-model="New.name"></el-input>
                </el-form-item>
                <el-form-item label="手机号" prop="tel">
                    <el-input v-model="New.tel"></el-input>
                </el-form-item>
                <el-form-item label="地址" prop="address">
                    <el-input v-model="New.address"></el-input>
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
import { GetAddress, addAddress, deleteAddress, updateAddress } from "../api/index";
export default {
    name: "basetable",
    setup() {
        const params = reactive({
            u_id: ""
        });
        const rules = {
            username: [
                {
                    required: true,
                    message: "请输入用户名",
                    trigger: "blur",
                },
            ],
            password: [
                { required: true, message: "请输入密码", trigger: "blur" },
            ],
            phone: [
                { required: true, message: "请输入手机号码", trigger: "blur" },
            ],
            score: [
                { required: true, message: "请输入星级", trigger: "blur" },
                { type: 'number', message: '积分必须是数字' },
            ],
            name: [
                { required: true, message: "请输入姓名", trigger: "blur" },
            ],
        };
        const tableData = ref([]);
        const pageTotal = ref(0);
        // 获取表格数据
        const getData = () => {
            params.u_id = localStorage.getItem("u_id");
            GetAddress(params).then((res) => {
                if (res.code === '200') {
                    var list = res.data
                    tableData.value = list;
                    pageTotal.value = list.length || 50;
                }

            });
        };
        getData();
        // 查询操作
        const handleClean = () => {
            // params.username = "";
            // params.phone = "";
            // getData();
        };
        const handleSearch = () => {
            // GetUser(params).then((res) => {
            //     if (res.code === '200') {
            //         var list = res.data.list
            //         tableData.value = list;
            //         pageTotal.value = res.data.total || 50;
            //     }
            // });
        };
        // 分页导航
        const handlePageChange = (val) => {
            params.pageNum = val;
            getData();
        };

        // 删除操作
        const handleDelete = (index, row) => {
            // 二次确认删除
            ElMessageBox.confirm("确定要删除吗？", "提示", {
                type: "warning",
            })
                .then(() => {
                    deleteAddress(row).then((res) => {
                        if (res.code === '200') {
                            ElMessage.success("删除成功");
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
            u_id: "",
            address: "",
            name: "",
            tel: "",

        });
        let form = reactive({
            u_id: "",
            a_id: "",
            address: "",
            name: "",
            tel: "",

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
            // if (row.stat == 1) {
            //     row.stat = 0
            //     userEdit(row).then((res) => {
            //         ElMessage.success(`状态修改成功`);
            //         getData();
            //     });

            // }
            // else {
            //     row.stat = 1
            //     userEdit(row).then((res) => {
            //         ElMessage.success(`状态修改成功`);
            //         getData();
            //     });
            // }
        }
        const handleCreate = () => {
            createVisible.value = true;

        };
        const saveCreate = (query) => {
            // Create.value.validate((valid) => {
            //     if (valid) {
            query.u_id = localStorage.getItem("u_id");
            addAddress(query).then((res) => {
                if (res.code === "200") {
                    ElMessage.success(`新用户创建成功`);
                    createVisible.value = false;
                    getData();
                    New.tel = '';
                    New.name = '';
                    New.address = '';
                } else {
                    ElMessage.error(`创建失败`);
                }

            });
            //     }
            // })

        }
        const saveEdit = (query) => {
            // Edit.value.validate((valid) => {
            //     if (valid) {
            updateAddress(query).then((res) => {
                if (res.code === "200") {
                    editVisible.value = false;
                    ElMessage.success(`修改成功`);
                    getData();
                } else {
                    ElMessage.error(`修改失败`);
                }

            });
            //     }
            // })
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
            handleSearch,
            handlePageChange,
            handleDelete,
            handleEdit,
            saveEdit,
            handleCreate,
            handleState,
            handleClean,
            saveCreate
        };
    },
};
</script>

<style scoped>
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
</style>
