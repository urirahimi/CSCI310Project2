const webpack = require('webpack');
const path = require('path');

const dir = path.resolve('.', 'WebContent', 'js');
const srcDir = path.resolve(dir, 'backend');
const distDir = dir;
const entryFilename = 'index.js';
const outputFilename = 'backend.js';

module.exports = {
  entry: path.resolve(srcDir, entryFilename),
  output: {
    path: distDir,
    filename: outputFilename,
  },

  module: {
   loaders: [
     {
       test: /\.js$/,
       loader: 'babel-loader',
       include: srcDir,
       exclude: /node_modules/,
     },
    ],
    rules: [
      {
        test: require.resolve(path.resolve(srcDir, entryFilename)),
        use: [
          {
            loader: 'expose-loader',
            options: 'FirebaseDB',
          },
        ],
      },
    ],
  },

  plugins: [
    // new webpack.optimize.UglifyJsPlugin(),
  ],

  stats: {
    colors: true
  },

  // devtool: 'source-map',
  cache: false,
};
