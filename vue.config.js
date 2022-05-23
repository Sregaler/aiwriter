module.exports = {
    pages: {
      index: {
        entry: 'src/main.js',
      },
    },
    lintOnSave:false,
    publicPath:'/',
    devServer: {
      port:8000,
      proxy: 'http://localhost:8080'
    }
    // devServer: {
    //   port:8002,
    //   proxy: {
    //     '/aiwriter':{
    //       target:'http://localhost:8080',
    //       pathRewrite:{'^/aiwriter':''},
    //     }
    //   }
    // }
  }