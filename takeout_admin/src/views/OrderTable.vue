<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-cascades"></i>订单管理/已接订单
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <span>订单号：</span>
                <el-input v-model="params.order_Number" size="small" placeholder="请输入订单号" class="handle-input mr10">
                </el-input>
                <el-button @click="handleClean">重置</el-button>
                <el-button type="primary" @click="handleSearch">搜索</el-button>
            </div>
            <el-table :data="tableData" border class="table" ref="multipleTable" header-cell-class-name="table-header">
                <el-table-column prop="order_Number" label="订单号"></el-table-column>
                <el-table-column prop="address" label="送达地址"></el-table-column>
                <el-table-column prop="create_time" label="创建时间"></el-table-column>
                <el-table-column prop="order_time" label="接单时间"></el-table-column>
                <el-table-column prop="arrive_time" label="完成时间"></el-table-column>
                <el-table-column prop="order_Stat" label="订单状态">
                    <template #default="scope">
                        <el-tag :type="'success'">
                            {{ scope.row.order_Stat === 2
                                ? '已接单'
                                : scope.row.order_Stat === 3
                                    ? '配送中'
                                    : scope.row.order_Stat === 4
                                        ? '待评价'
                                        : '已完成' }}
                        </el-tag>
                    </template>
                </el-table-column>
                <el-table-column label="操作" width="180" align="center">
                    <template #default="scope">
                        <el-button type="text" @click="handleDelete(scope.$index, scope.row)">订单详情</el-button>
                        <el-button type="text" v-if="scope.row.order_Stat !== 4 && scope.row.order_Stat !== 5"
                            @click="handleEdit(scope.$index, scope.row)">修改状态</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="pagination">
                <el-pagination background layout="total, prev, pager, next" :current-page="params.pageNum"
                    :page-size="params.pageSize" :total="pageTotal" @current-change="handlePageChange"></el-pagination>
            </div>
        </div>

        <!-- 编辑弹出框 -->
        <el-dialog title="修改订单状态" v-model="editVisible" width="40%">
            <el-form :model="form" :rules="rules" ref="Edit" label-width="70px">
                <el-form-item label="类别" prop="category">
                    <el-select v-model="form.order_Stat" class="m-2" placeholder="类别" size="large">
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
        <el-dialog title="订单详情" v-model="createVisible" width="30%">
            <div>
                <el-table :data="tableList">
                    <el-table-column width="200" property="good_name" label="商品名称" align="center"></el-table-column>
                    <el-table-column width="200" property="count" label="数量" align="center"> </el-table-column>
                </el-table>
                <div style="margin: 5px;color: #409EFF">总价：￥{{ New.total_price }}</div>
            </div>
        </el-dialog>
    </div>
</template>

<script>
import { ref, reactive } from "vue";
import { ElMessage, ElMessageBox } from "element-plus";
import { GetOrderAdd, goodAdd, getOrderInfo, changeOrderState, goodEdit } from "../api/index";
export default {
    name: "basetable",
    setup() {
        const options = ref([
            {
                label: "已接单",
                value: 2
            },
            {
                label: "配送中",
                value: 3
            },
            {
                label: "已送达",
                value: 4
            },
            {
                label: "已完成",
                value: 5
            },
        ]);
        const params = reactive({
            order_Number: "",
            s_id: "",
            pageNum: 1,
            pageSize: 10,
        });
        const rules = {
            // username: [
            //     {
            //         required: true,
            //         message: "请输入用户名",
            //         trigger: "blur",
            //     },
            // ],
            // password: [
            //     { required: true, message: "请输入密码", trigger: "blur" },
            // ],
            // phone: [
            //     { required: true, message: "请输入手机号码", trigger: "blur" },
            // ],
            // score: [
            //     { required: true, message: "请输入星级", trigger: "blur" },
            //     { type: 'number', message: '积分必须是数字' },
            // ],
            // name: [
            //     { required: true, message: "请输入姓名", trigger: "blur" },
            // ],
        };
        const tableData = ref([]);
        const tableList = ref([]);
        const pageTotal = ref(0);
        // 获取表格数据
        const getData = () => {
            params.s_id = localStorage.getItem("s_id");
            GetOrderAdd(params).then((res) => {
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
        getData();
        // 查询操作
        const handleClean = () => {
            params.order_Number = "";
            getData();
        };
        const handleSearch = () => {
            params.s_id = localStorage.getItem("s_id");
            GetOrderAdd(params).then((res) => {
                if (res.code === '200') {
                    var list = res.data.list
                    tableData.value = list;
                    pageTotal.value = res.data.total || 0;
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
            idx = index;
            Object.keys(New).forEach((item) => {

                New[item] = row[item];
            });
            console.log(New)
            getOrderInfo(New).then((res) => {
                if (res.code === '200') {
                    tableList.value = res.data.list
                    console.log(tableList)
                }
            })
            createVisible.value = true;
        };

        // 表格编辑时弹窗和保存
        const editVisible = ref(false);
        //新建账户弹窗和保存
        const createVisible = ref(false);
        const Create = ref(null);
        const Edit = ref(null);
        let New = reactive({
            o_id: "",
            total_price: ""

        });
        let form = reactive({
            order_Stat: "",
            o_id: "",
            arrive_time: ""

        });

        let idx = -1;
        const handleEdit = (index, row) => {
            idx = index;
            Object.keys(form).forEach((item) => {

                form[item] = row[item];
            });
            console.log(form)
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
            // Create.value.validate((valid) => {
            //     if (valid) {
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
                    ElMessage.error(`创建失败`);
                }

            });
            //     }
            // })

        }
        const saveEdit = (query) => {
            // Edit.value.validate((valid) => {
            //     if (valid) {
            changeOrderState(query).then((res) => {
                if (query.order_Stat === 5) {
                    ElMessage.error(`请确保订单先送达！`);
                } else {
                    if (res.code === "200") {
                        editVisible.value = false;
                        ElMessage.success(`修改成功`);
                        getData();
                    } else {
                        ElMessage.error(`修改失败`);
                    }
                }


            });
            //     }
            // })
        };

        return {
            tableData,
            tableList,
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
