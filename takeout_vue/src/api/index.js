import request from '../utils/request';

export const UserLogin = query => {
    return request({
        url: '/user/login',
        method: 'post',
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

export const GetUser = query => {
    return request({
        url: '/user/list',
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
export const MachineData = query => {
    // console.log(query)
    return request({
        // url: './table.json',
        url: '/device',
        method: 'get',
        params: query

    });
};
export const MachineEdit = query => {
    // console.log(query)
    return request({
        // url: './table.json',
        url: '/device/update',
        method: 'put',
        data: query

    });
};
export const GroupData = query => {
    // console.log(query)
    return request({
        // url: './table.json',
        url: '/deviceGroup',
        method: 'get',
        params: query

    });
};
export const MaterialData = query => {
    // console.log(query)
    return request({
        // url: './table.json',
        url: '/material/page',
        method: 'get',
        params: query

    });
};
export const MaterialDelete = query => {
    // console.log(query)
    return request({
        // url: './table.json',
        url: '/material/delete',
        method: 'put',
        data: { id: query }

    });
};
export const ProgramData = query => {
    // console.log(query)
    return request({
        // url: './table.json',
        url: '/programs',
        method: 'get',
        params: query

    });
};
export const MaterialCreate = query => {
    // console.log(query)
    return request({
        // url: './table.json',
        url: '/programs',
        method: 'post',
        data: query

    });
};
export const NoticeData = query => {
    // console.log(query)
    return request({
        // url: './table.json',
        url: '/notice',
        method: 'get',
        params: query

    });
};
export const NoticeCreate = query => {
    // console.log(query)
    return request({
        // url: './table.json',
        url: '/notice',
        method: 'post',
        data: query

    });
};
export const imgPub = query => {
    // console.log(query)
    return request({
        // url: './table.json',
        url: '/android/operation',
        method: 'post',
        data: query

    });
};
