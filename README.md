# Android UI controller
With android SDK, develper could use uiautomator to do ui test, such as clicking or scrolling. But for NDK developer (or other developer without SDK,) doing ui audo test is not easy. This project try to implement a web server (in android service) to do ui test according to client's command.   
    
This project use [Nanohttpd](https://github.com/NanoHttpd/nanohttpd) to establish web server, and the default listening port is *12125*.    

At the beginning, I only support bash as control script language. Actually, since there is a httpd, other language could connect to this project, too.   
   
Using this project is very easy. First, build this project in android NDK. And then you could control android UI by bash. The example could be found in *BashWrapper/example.sh*.