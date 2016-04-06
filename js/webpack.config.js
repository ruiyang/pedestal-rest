"use strict";

var webpack = require('webpack');
var ExtractTextPlugin = require('extract-text-webpack-plugin');
var path = require('path');

var nodeModulesPath = path.resolve(__dirname, 'node_modules');

var config = require('./make-webpack-config')({
  entry: {
    // https://github.com/webpack/webpack/issues/300
    "index": [path.resolve(__dirname, 'app/index.js')]
  },

  output: {
    path: path.join(__dirname, '../public/assets/js'),
    filename: '[name].js',
    publicPath: '/public'
  },

  devServer: {
    colors: true,
    port: 4000,
    hot: true,
    inline: true,
    proxy: {
      '/api/*': {
        target: 'http://localhost:3001',
        secure: false
      }
    }
  },

  devtool: 'inline-source-map',

  plugins: [
    new webpack.DefinePlugin({
      'process.env': {
        SERVICE_API_URL: JSON.stringify(process.env.BUDGETING_API_URL || 'http://localhost:9292')
      }
    }),
    new webpack.HotModuleReplacementPlugin(),
    new webpack.NoErrorsPlugin()
  ]
});

module.exports = config;
