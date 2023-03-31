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