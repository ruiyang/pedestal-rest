"use strict";

var ExtractTextPlugin = require("extract-text-webpack-plugin");
var CompressionPlugin = require("compression-webpack-plugin");

var defaultLoaders = [
  {test: /\.jsx$/, exclude: /node_modules/, loaders: ['react-hot', 'babel']},
  {test: /\.js?$/, exclude: /node_modules/, loader: 'babel'},
  {test: /\.woff(2)?(\?)?(.+)?$/, loader: 'url?limit=10000&mimetype=application/font-woff&name=/fonts/[name].[ext]'},
  {test: /\.ttf(\?)?(.+)?$/, loader: 'url?limit=10000&mimetype=application/octet-stream&name=/fonts/[name].[ext]'},
  {test: /\.eot(\?)?(.+)?$/, loader: 'file?name=/fonts/[name].[ext]'},
  {test: /\.svg(\?)?(.+)?$/, loader: 'url?limit=10000&mimetype=image/svg+xml&name=/fonts/[name].[ext]'},
  {test: /\.(png|cur)$/, loader: 'file?name=/images/[name].[ext]'}
];

module.exports = function(options) {
  var entry = options.entry;
  var output = options.output;
  var devServer = options.devServer;
  var loaders = defaultLoaders;
  var plugins = options.plugins;

  if (options.separateStylesheet) {
    loaders = loaders.concat(
      {test: /\.scss$/, exclude: /node_modules/, loader: ExtractTextPlugin.extract('css?sourceMap!sass?sourceMap')},
      {test: /\.css$/, loader: ExtractTextPlugin.extract('style', 'css')}
    );
  } else {
    loaders = loaders.concat(
      {test: /\.scss$/, exclude: /node_modules/, loader: 'style!css!sass'},
      {test: /\.css$/, loader: 'style!css'}
    );
  }

  if (options.compress) {
    plugins = plugins.concat(new CompressionPlugin({
      asset: "{file}.gz",
      algorithm: "gzip",
      minRatio: 10
    }));
  }

  var module = {
    preLoaders: [
      {test: /\.js?$/, loader: 'eslint-loader', exclude: /node_modules/}
    ],

    loaders: loaders,

    noParse: []
  };

  var resolve = {
    extensions: ['', '.js'],
    alias: options.alias
  };

  var devtool = options.devtool;

  var addVendor = function (name, path) {
    this.resolve.alias[name] = path;
    this.module.noParse.push(new RegExp(path));
  };

  return {
    entry: entry,
    output: output,
    devServer: devServer,
    module: module,
    resolve: resolve,
    devtool: devtool,
    plugins: plugins,
    addVendor: addVendor
  };
};
