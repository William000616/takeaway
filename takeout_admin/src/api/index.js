import request from '../utils/request';

export const UserLogin = query => {
    return request({
        url: '/user/login',
        method: 'post',
        data: query
    });
};

export const updatePassword = query => {
    return request({
        url: '/user/updatePassword',
        method: 'put',
        data: query
    });
};

export const ManagerLogin = query => {
    return request({
        url: '/manager/login',
        method: 'post',
        data: query
    });
};

export const updateManagerPassword = query => {
    return request({
        url: '/manager/updatePassword',
        method: 'put',
        data: query
    });
};

export const UserRegister = query => {
    return request({
        url: '/user/add',
        method: 'post',
        data: query
    });
};

export const shopManagerRegister = query => {
    return request({
        url: '/manager/add',
        method: 'post',
        data: query
    });
};

export const shopRegister = query => {
    return request({
        url: '/shop/add',
        method: 'post',
        data: query
    });
};


export const GetUser = query => {
    return request({
        url: '/user/list',
        method: 'get',
        params: query
    });
};
export const GetAllUser = query => {
    return request({
        url: '/user/listAll',
        method: 'get',
        params: query
    });
};
export const userAdd = query => {
    return request({
        url: '/user/add',
        method: 'post',
        data: query
    });
};
export const userDelete = query => {
    return request({
        url: '/user/delete',
        method: 'delete',
        params: query
    });
};
export const userEdit = query => {
    return request({
        url: '/user/update',
        method: 'put',
        data: query

    });
};
export const userPicSrcEdit = query => {
    return request({
        url: '/user/updatePicSrc',
        method: 'put',
        data: query

    });
};
export const GetManager = query => {
    return request({
        url: '/manager/list',
        method: 'get',
        params: query
    });
};
export const managerAdd = query => {
    return request({
        url: '/manager/add',
        method: 'post',
        data: query
    });
};
export const managerDelete = query => {
    return request({
        url: '/manager/delete',
        method: 'delete',
        params: query
    });
};
export const managerEdit = query => {
    return request({
        url: '/manager/update',
        method: 'put',
        data: query

    });
};

export const GetShop = query => {
    return request({
        url: '/shop/list',
        method: 'get',
        params: query
    });
};

export const GetShopOpen = query => {
    return request({
        url: '/shop/listOpen',
        method: 'get',
        params: query
    });
};
export const GetShopById = query => {
    return request({
        url: '/shop/listById',
        method: 'get',
        params: query
    });
};
export const GetShopAdd = query => {
    return request({
        url: '/shop/listAdd',
        method: 'get',
        params: query
    });
};
export const UpdateShop = query => {
    return request({
        url: '/shop/update',
        method: 'put',
        data: query
    });
};

export const GetCategory = query => {
    return request({
        url: '/category/list',
        method: 'get',
        params: query
    });
};
export const categoryAdd = query => {
    return request({
        url: '/category/add',
        method: 'post',
        data: query
    });
};
export const categoryDelete = query => {
    return request({
        url: '/category/delete',
        method: 'delete',
        params: query
    });
};
export const GetGood = query => {
    return request({
        url: '/good/list',
        method: 'get',
        params: query
    });
};
export const goodAdd = query => {
    return request({
        url: '/good/add',
        method: 'post',
        data: query
    });
};
export const goodDelete = query => {
    return request({
        url: '/good/delete',
        method: 'delete',
        params: query
    });
};
export const goodEdit = query => {
    return request({
        url: '/good/update',
        method: 'put',
        data: query
    });
};
export const GetAddress = query => {
    return request({
        url: '/address/list',
        method: 'get',
        params: query
    });
};
export const GetAddressById = query => {
    return request({
        url: '/address/listById',
        method: 'get',
        params: query
    });
};
export const addAddress = query => {
    return request({
        url: '/address/add',
        method: 'post',
        data: query
    });
};
export const deleteAddress = query => {
    return request({
        url: '/address/delete',
        method: 'delete',
        params: query
    });
};
export const updateAddress = query => {
    return request({
        url: '/address/update',
        method: 'put',
        data: query
    });
};
export const GetOrder = query => {
    return request({
        url: '/order/list',
        method: 'get',
        params: query
    });
};
export const GetOrderAdd = query => {
    return request({
        url: '/order/listAdd',
        method: 'get',
        params: query
    });
};
export const GetOrderNew = query => {
    return request({
        url: '/order/listNew',
        method: 'get',
        params: query
    });
};
export const pickOrder = query => {
    return request({
        url: '/order/shopPickOrder',
        method: 'put',
        data: query
    });
};
export const changeOrderState = query => {
    return request({
        url: '/order/changeOrderState',
        method: 'put',
        data: query
    });
};
export const getOrderInfo = query => {
    return request({
        url: '/order/listById',
        method: 'get',
        params: query
    });
};

export const GetComment = query => {
    return request({
        url: '/comment/list',
        method: 'get',
        params: query
    });
};

