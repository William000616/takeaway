<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-cascades"></i>用户管理/店铺账号管理
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <span>账户名：</span>
                <el-input v-model="params.username" size="small" placeholder="请输入账户名称" class="handle-input mr10">
                </el-input>
                <span>手机号码：</span>
                <el-input v-model="params.phone" size="small" placeholder="请输入手机号码" class="handle-input mr10">
                </el-input>
                <el-button @click="handleClean">重置</el-button>
                <el-button type="primary" @click="handleSearch">搜索</el-button>
            </div>
            <el-table :data="tableData" border class="table" ref="multipleTable" header-cell-class-name="table-header">
                <el-table-column prop="username" label="账户名"></el-table-column>
                <el-table-column prop="name" label="真实姓名"></el-table-column>
                <el-table-column prop="phone" label="手机号"></el-table-column>
                <el-table-column prop="identity_number" label="身份证号"></el-table-column>
                <el-table-column prop="stat" label="状态">
                    <template #default="scope">
                        <el-tag @click="handleState(scope.$index, scope.row)" :type="
                            scope.row.stat === 1
                                ? 'success'
                                : 'danger'
                        ">{{ scope.row.stat === 1 ? '启用' : '停用' }}</el-tag>

                    </template>
                </el-table-column>
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
        <el-dialog title="编辑" v-model="editVisible" width="30%">
            <el-form label-width="70px">
                <el-form-item label="账户名">
                    <el-input v-model="form.username"></el-input>
                </el-form-item>
                <el-form-item label="密码">
                    <el-input v-model="form.password" type="password" placeholder="Please input password" show-password />
                </el-form-item>
                <el-form-item label="真实姓名">
                    <el-input v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item label="身份证号">
                    <el-input v-model="form.identity_number"></el-input>
                </el-form-item>
                <el-form-item label="手机号">
                    <el-input v-model="form.phone"></el-input>
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
            <el-form label-width="70px">
                <el-form-item label="账户名">
                    <el-input v-model="New.username"></el-input>
                </el-form-item>
                <el-form-item label="密码">
                    <el-input v-model="New.password" type="password" placeholder="Please input password" show-password />
                </el-form-item>
                <el-form-item label="真实姓名">
                    <el-input v-model="New.name"></el-input>
                </el-form-item>
                <el-form-item label="身份证号">
                    <el-input v-model="New.identity_number"></el-input>
                </el-form-item>
                <el-form-item label="手机号">
                    <el-input v-model="New.phone"></el-input>
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
import { GetManager, managerAdd, managerEdit, managerDelete } from "../api/index";
export default {
    name: "basetable",
    setup() {
        const params = reactive({
            username: "",
            phone: "",
            pageNum: 1,
            pageSize: 10,
        });
        const tableData = ref([]);
        const pageTotal = ref(0);
        // 获取表格数据
        const getData = () => {
            GetManager(params).then((res) => {
                if (res.code === '200') {
                    var list = res.data.list
                    tableData.value = list;
                    pageTotal.value = res.data.total || 50;
                }

            });
        };
        getData();
        // 查询操作
        const handleClean = () => {
            params.username = "";
            params.phone = "";
            getData();
        };
        const handleSearch = () => {
            GetManager(params).then((res) => {
                if (res.code === '200') {
                    var list = res.data.list
                    tableData.value = list;
                    pageTotal.value = res.data.total || 50;
                }
            });
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
                    ElMessage.success("删除成功");
                    managerDelete(row);
                    getData();
                })
                .catch(() => { });
        };

        // 表格编辑时弹窗和保存
        const editVisible = ref(false);
        //新建账户弹窗和保存
        const createVisible = ref(false);
        let New = reactive({
            username: "",
            password: "",
            name: "",
            identity_number: "",
            phone: "",

        });
        let form = reactive({
            s_m_id: "",
            username: "",
            password: "",
            identity_number: "",
            stat: "",
            name: "",
            phone: "",

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
                managerEdit(row).then((res) => {
                    ElMessage.success(`状态修改成功`);
                    getData();
                });

            }
            else {
                row.stat = 1
                managerEdit(row).then((res) => {
                    ElMessage.success(`状态修改成功`);
                    getData();
                });
            }
        }
        const handleCreate = () => {
            createVisible.value = true;

        };
        const saveCreate = (query) => {
            if (query.username === '' || query.password === '' || query.name === '' || query.phone === '' || query.score === '') {
                ElMessage.error(`新用户详情不能为空`);
            } else {
                managerAdd(query).then((res) => {
                    if (res.code === "200") {
                        ElMessage.success(`新用户创建成功`);
                        createVisible.value = false;
                        getData();
                        New.identity_number = '';
                        New.username = '';
                        New.name = '';
                        New.password = '';
                        New.phone = '';
                    } else {
                        ElMessage.error(`创建失败`);
                    }

                });
            }

        }
        const saveEdit = (query) => {
            if (query.username === '' || query.password === '' || query.name === '' || query.phone === '' || query.score === '') {
                ElMessage.error(`用户详情不能修改为空`);
            } else {
                managerEdit(query).then((res) => {
                    if (res.code === "200") {
                        editVisible.value = false;
                        ElMessage.success(`修改成功`);
                        getData();
                    } else {
                        ElMessage.error(`修改失败`);
                    }

                });
            }
        };

        return {
            tableData,
            pageTotal,
            editVisible,
            createVisible,
            form,
            New,
            params,
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
