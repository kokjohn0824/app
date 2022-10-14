const path = require("path");

module.exports = {
    "mode": "production",
    "entry": "./src/jsx/hi.jsx",
    "output": {
        "path": __dirname+'/static',
        "filename": "[name].[chunkhash:8].js"
    },
    "module": {
        "rules": [
            {
                "test": /\.(js|jsx)$/,
                "exclude": /node_modules/,
                "use": {
                    "loader": "babel-loader",
                    "options": {
                        "presets": [
                            "env",
                            "react"
                        ]
                    }
                }
            }
        ]
    }
};