let listing=function(urling){
	return new Promise((resolve,reject)=>{
		uni.request({
			url:urling,
			method:'GET',
		})
		.then((res)=>{
			resolve(res)
		})
		.catch((err)=>{
			reject(err)
		})
	})
}
export{listing}