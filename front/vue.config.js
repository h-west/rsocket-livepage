module.exports = {
  outputDir: `${__dirname}/../src/main/resources/static`,
  devServer: {
    proxy: 'http://localhost:8080'
  },
  pages: {
    index: {
      entry: 'src/index.js',
      template: 'public/index.html',
      filename: 'index.html'
    },
    page: {
      entry: 'src/page.js',
      template: 'public/page.html',
      filename: 'page.html'
    }
  }
}