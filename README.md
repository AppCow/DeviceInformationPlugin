# DeviceInformation plugin for Cordova & React Native #

This plugin allows you to retrieve most information about your Android devices that are available through Android's Telephony Manager and Account Manager classes from your Cordova or React Native application:

1. Your unique Device ID
- Phone Number (if it is stored in your SIM card)
- Country ISO of your phone network provider
- Name of your network provider
- Your SIM Card Serial number
- Country ISO of your SIM card
- Name of your SIM card mobile operator
- E-mail/Phone number used by apps listed in your Settings > Accounts & Sync list
- Device model
- Board ID
- Manufacturer Name
- User Agent String
- Android Version Number
- Google Ad ID

## Adding the Plugin to your project ##

You can use this plugin with Cordova projects on the Android platform only. iOS and other platforms are not implemented.

You can also use this plugin with React Native Android by using the amazing [React Native Cordova Plugin](https://github.com/axemclion/react-native-cordova-plugin).

### Install for React Native ###
`./node_modules/.bin/cordova-plugin add cordova-plugin-deviceinformation`

### Install for Cordova ###
If you have installed Cordova CLI, run the following code from the command line:
   <pre>cordova plugin add cordova-plugin-deviceinformation</pre>

Otherwise,

1. To install the plugin, copy the www/deviceinformation.js file to your project's www folder and include a reference to it in your html file after cordova.js.
   <pre>
    &lt;script type="text/javascript" charset="utf-8" src="cordova.js"&gt;&lt;/script&gt;
    &lt;script type="text/javascript" charset="utf-8" src="deviceinformation.js"&gt;&lt;/script&gt;
   </pre>

2. Create a directory within your project called "src/com/upchannel/cordova/plugins" and copy src/com/upchannel/cordova/plugins/DeviceInformation.java into it.

3. In your res/xml/config.xml file add the following line:
   <pre>
    &lt;plugin name="DeviceInformation" value="com.upchannel.cordova.plugins.DeviceInformation"/&gt;
   </pre>


## Using the plugin ##

Create a new object that represents the plugin using cordova.require. Then you can call the 'get' method on that object providing a success callback which will be called with a result value that is a JSON object of all the information about your devices.

<pre>
  /**
    * Get the devices information.
    */
  get(success, failure)
</pre>

Sample use:

    const deviceInfo = cordova.require("cordova-plugin-deviceinformation.DeviceInformation");
    deviceInfo.get(function(result) {
            console.log("result = " + result);
            const resultJson = JSON.parse(result);
        }, function() {
            console.log("error");
        });


## RELEASE NOTES ##

## BUGS AND CONTRIBUTIONS ##


## LICENSE ##

This plugin is available under the MIT License (2008).
The text of the MIT license is reproduced below.

---

### The MIT License

Copyright (c) 2013 Veronica Liesaputra

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in
 all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 THE SOFTWARE.
