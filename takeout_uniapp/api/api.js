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

let publicing=function(urling,data){
	return new Promise((resolve,reject)=>{
		uni.request({
			url:urling,
			method:'POST',
			data:data
		})
		.then((res)=>{
			resolve(res)
		})
		.catch((err)=>{
			reject(err)
		})
	})
}
export{listing,publicing}