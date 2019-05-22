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
var __weex_script__ = __webpack_require__(3)

__weex_define__('@weex-component/0764912d7c1a6325f0c3077a3e1871f4', [], function(__weex_require__, __weex_exports__, __weex_module__) {

    __weex_script__(__weex_module__, __weex_exports__, __weex_require__)
    if (__weex_exports__.__esModule && __weex_exports__.default) {
      __weex_module__.exports = __weex_exports__.default
    }

    __weex_module__.exports.template = __weex_template__

    __weex_module__.exports.style = __weex_style__

})

__weex_bootstrap__('@weex-component/0764912d7c1a6325f0c3077a3e1871f4',undefined,undefined)

/***/ }),
/* 1 */
/***/ (function(module, exports) {

module.exports = {
  "type": "div",
  "classList": [
    "wrapper"
  ],
  "children": [
    {
      "type": "div",
      "classList": [
        "login"
      ],
      "children": [
        {
          "type": "div",
          "classList": [
            "input-wrapper"
          ],
          "children": [
            {
              "type": "input",
              "events": {
                "change": "onchangeUserNumber"
              },
              "classList": [
                "input"
              ],
              "attr": {
                "type": "text",
                "placeholder": "手机号",
                "autofocus": "true",
                "value": ""
              }
            },
            {
              "type": "image",
              "classList": [
                "input-img"
              ],
              "attr": {
                "src": "resources/img/login_input_user_img.png"
              }
            }
          ]
        },
        {
          "type": "div",
          "classList": [
            "input-wrapper"
          ],
          "children": [
            {
              "type": "input",
              "events": {
                "change": "onchangeUserPassword"
              },
              "classList": [
                "input"
              ],
              "attr": {
                "type": "password",
                "placeholder": "密码",
                "value": ""
              }
            },
            {
              "type": "image",
              "classList": [
                "input-img"
              ],
              "attr": {
                "src": "resources/img/login_input_pass_img.png"
              }
            }
          ]
        },
        {
          "type": "div",
          "classList": [
            "input-wrapper"
          ],
          "children": [
            {
              "type": "div",
              "classList": [
                "input-login"
              ],
              "events": {
                "click": "login"
              },
              "children": [
                {
                  "type": "text",
                  "classList": [
                    "input-login-text"
                  ],
                  "attr": {
                    "value": "登录"
                  }
                }
              ]
            }
          ]
        },
        {
          "type": "div",
          "classList": [
            "input-wrapper"
          ],
          "children": [
            {
              "type": "text",
              "classList": [
                "input-forget"
              ],
              "events": {
                "click": "findPassword"
              },
              "attr": {
                "value": "找回密码"
              }
            },
            {
              "type": "text",
              "classList": [
                "input-register"
              ],
              "events": {
                "click": "register"
              },
              "attr": {
                "value": "立即注册"
              }
            }
          ]
        }
      ]
    },
    {
      "type": "toast",
      "id": "toast"
    }
  ]
}

/***/ }),
/* 2 */
/***/ (function(module, exports) {

module.exports = {
  "wrapper": {
    "position": "absolute",
    "top": 0,
    "right": 0,
    "bottom": 0,
    "left": 0
  },
  "login": {
    "marginTop": "180px"
  },
  "input-wrapper": {
    "width": "550px",
    "marginLeft": "100px",
    "marginRight": "100px",
    "marginBottom": "30px"
  },
  "input": {
    "fontSize": "30px",
    "height": "80px",
    "width": "550px",
    "paddingLeft": "90px",
    "paddingTop": "15px",
    "paddingBottom": "15px",
    "borderWidth": "1px",
    "borderColor": "#48c9bf",
    "borderRadius": "10px",
    "outline": "none"
  },
  "input-img": {
    "position": "absolute",
    "top": "10px",
    "left": "15px",
    "width": "60px",
    "height": "60px"
  },
  "input-login": {
    "height": "80px",
    "width": "550px",
    "backgroundColor": "#48c9bf",
    "borderRadius": "10px",
    "marginTop": "40px"
  },
  "input-login-text": {
    "height": "80px",
    "width": "550px",
    "textAlign": "center",
    "lineHeight": "80px",
    "color": "#FFFFFF",
    "fontSize": "35px"
  },
  "input-forget": {
    "position": "absolute",
    "left": "30px",
    "fontSize": "30px"
  },
  "input-register": {
    "position": "absolute",
    "right": "30px",
    "fontSize": "30px"
  }
}

/***/ }),
/* 3 */
/***/ (function(module, exports) {

module.exports = function(module, exports, __weex_require__){'use strict';

module.exports = {
    data: function () {return {
        userNumber: '',
        userPassword: ''
    }},
    methods: {
        onchangeUserNumber: function onchangeUserNumber(event) {
            this.userNumber = event.value;
        },
        onchangeUserPassword: function onchangeUserPassword(event) {
            this.userPassword = event.value;
        },

        findPassword: function findPassword() {
            this.$vm('toast').$emit('toast', 'Hello,找回密码暂时未开发，后续我们会进行完善。');
        },

        register: function register() {
            this.$vm('toast').$emit('toast', 'Hello,注册暂时未开发，后续我们会进行完善。');
        },

        login: function login() {
            if (this.userNumber.length < 1) {
                this.$vm('toast').$emit('toast', '请输入手机号');
                return;
            } else if (this.userPassword.length < 1) {
                this.$vm('toast').$emit('toast', '请输入密码');
                return;
            }
            this.$vm('toast').$emit('toast', "登录成功");
        }
    }
};}
/* generated by weex-loader */


/***/ })
/******/ ]);