// const thispath=window.location.href
//
// //本地用这个字符串截取
// const thisport=Number(thispath.substring(17,21))
// //服务器用这个字符串截取
// // var thisport=thispath.slice(14,4)
//
// const newport=String(thisport+1010)
// console.log(newport)

// 跨域配置
module.exports = {
    devServer: {                //记住，别写错了devServer//设置本地默认端口  选填
        port: 8080,
        proxy: {                 //设置代理，必须填
            '/api': {              //设置拦截器  拦截器格式   斜杠+拦截器名字，名字可以自己定
                target: 'http://82.157.124.157:9090',     //代理的目标地址
                changeOrigin: true,              //是否设置同源，输入是的
                pathRewrite: {                   //路径重写
                    '^/api': ''                     //选择忽略拦截器里面的内容
                }
            }
        }
    }
}

