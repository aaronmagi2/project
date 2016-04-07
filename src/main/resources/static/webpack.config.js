var path = require('path');

var node_dir = __dirname + '/node_modules';

module.exports = {
    entry: './app.js',
    devtool: 'sourcemaps',
    // TODO FOR DEV set back to cache: true,
    cache: false,
    debug: true,
    resolve: {
        alias: {
            'stompjs': node_dir + '/stompjs/lib/stomp.js',
            'when': node_dir + '/rest/node_modules/when/when.js'
        }
    },
    output: {
        path: __dirname,
        filename: './built/bundle.js'
    },
    module: {
        loaders: [
            {
                test: path.join(__dirname, '.'),
                exclude: /(node_modules)/,
                // TODO remove for DEV
                // loader: 'babel-loader'
                loaders: ["react-hot", "babel-loader"]
            }
        ]
    }
};
