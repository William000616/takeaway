import request from './request.js';

export const UserLogin = query => {
    return request({
        url: '/user/login',
        method: 'post',
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

export const UpdateAddressById = query => {
    return request({
        url: '/address/update',
        method: 'put',
        data: query
    });
};

export const CreateAddress = query => {
    return request({
        url: '/address/add',
        method: 'post',
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

export const GetCategory = query => {
    return request({
        url: '/category/list',
        method: 'get',
        params: query
    });
};

export const GetGood = query => {
    return request({
        url: '/good/listGood',
        method: 'get',
        params: query
    });
};

export const getOrderInfo = query => {
    return request({
        url: '/order/listById',
        method: 'get',
        params: query
    });
};

export const updatePassword = query => {
    return request({
        url: '/user/updatePassword',
        method: 'put',
        data: query
    });
};

export const CreateOrder = query => {
    return request({
        url: '/order/createOrder',
        method: 'post',
        data: query
    });
};

export const GetAllShop = query => {
    return request({
        url: '/shop/list',
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