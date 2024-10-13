const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,

  pluginOptions: {
    vuetify: {
			// https://github.com/vuetifyjs/vuetify-loader/tree/next/packages/vuetify-loader
		}
  },

  outputDir: '../scm_api/src/main/resources/static',
  devServer: {
    proxy: 'http://localhost:8080/api'
  }
})
