// { "framework": "Vue"} 

/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};
/******/
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/
/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId]) {
/******/ 			return installedModules[moduleId].exports;
/******/ 		}
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			i: moduleId,
/******/ 			l: false,
/******/ 			exports: {}
/******/ 		};
/******/
/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);
/******/
/******/ 		// Flag the module as loaded
/******/ 		module.l = true;
/******/
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/
/******/
/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;
/******/
/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;
/******/
/******/ 	// define getter function for harmony exports
/******/ 	__webpack_require__.d = function(exports, name, getter) {
/******/ 		if(!__webpack_require__.o(exports, name)) {
/******/ 			Object.defineProperty(exports, name, {
/******/ 				configurable: false,
/******/ 				enumerable: true,
/******/ 				get: getter
/******/ 			});
/******/ 		}
/******/ 	};
/******/
/******/ 	// getDefaultExport function for compatibility with non-harmony modules
/******/ 	__webpack_require__.n = function(module) {
/******/ 		var getter = module && module.__esModule ?
/******/ 			function getDefault() { return module['default']; } :
/******/ 			function getModuleExports() { return module; };
/******/ 		__webpack_require__.d(getter, 'a', getter);
/******/ 		return getter;
/******/ 	};
/******/
/******/ 	// Object.prototype.hasOwnProperty.call
/******/ 	__webpack_require__.o = function(object, property) { return Object.prototype.hasOwnProperty.call(object, property); };
/******/
/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";
/******/
/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(__webpack_require__.s = 0);
/******/ })
/************************************************************************/
/******/ ([
/* 0 */
/***/ (function(module, exports, __webpack_require__) {

var __weex_template__ = __webpack_require__(1)
var __weex_style__ = __webpack_require__(2)

__weex_define__('@weex-component/400987328a1108733c2dd19c12fac749', [], function(__weex_require__, __weex_exports__, __weex_module__) {

    __weex_module__.exports.template = __weex_template__

    __weex_module__.exports.style = __weex_style__

})

__weex_bootstrap__('@weex-component/400987328a1108733c2dd19c12fac749',undefined,undefined)

/***/ }),
/* 1 */
/***/ (function(module, exports) {

module.exports = {
  "type": "div",
  "classList": [
    "container"
  ],
  "children": [
    {
      "type": "div",
      "classList": [
        "cell"
      ],
      "children": [
        {
          "type": "image",
          "classList": [
            "thumb"
          ],
          "attr": {
            "src": "http://t.cn/RGE3AJt"
          }
        },
        {
          "type": "text",
          "classList": [
            "title"
          ],
          "attr": {
            "value": "Hello Weex"
          }
        }
      ]
    }
  ]
}

/***/ }),
/* 2 */
/***/ (function(module, exports) {

module.exports = {
  "cell": {
    "marginTop": 10,
    "marginLeft": 10,
    "flexDirection": "row"
  },
  "thumb": {
    "width": 200,
    "height": 200
  },
  "title": {
    "textAlign": "center",
    "flex": 1,
    "color": "#808080",
    "fontSize": 50
  }
}

/***/ })
/******/ ]);