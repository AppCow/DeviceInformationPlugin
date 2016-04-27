var exec = require("cordova/exec");

var DeviceInformationLoader = function (require, exports, module) {

    function DeviceInformation () {}

    DeviceInformation.prototype.get = function (successFunc, failFunc) {
        exec(successFunc, failFunc, "DeviceInformation","get",[]);
    };

    var deviceInformation = new DeviceInformation();
    module.exports = deviceInformation;
};

DeviceInformationLoader(require, exports, module);

cordova.define("cordova/plugin/DeviceInformation", DeviceInformationLoader);
