#!/usr/bin/env bash

function _send_curl_cmd() {
    curl -d "${1}" localhost:12125
}

function getDeviceInfo() {
     _send_curl_cmd "{'cmd':'getDeviceInfo' }"
}

function press() {
    _send_curl_cmd "{'cmd':'press', 'key':'${1}'}"
}

function clickText() {
    _send_curl_cmd "{'cmd':'clickText', 'text':'${1}'}"
}

function longClickText() {
    _send_curl_cmd "{'cmd':'longClickText', 'text':'${1}'}"
}

function click() {
    _send_curl_cmd "{'cmd':'click', 'coordinate':'${1}_${2}'}"
}

function longClick() {
    _send_curl_cmd "{'cmd':'longClick', 'coordinate':'${1}_${2}'}"
}

function swipe() {
    _send_curl_cmd "{'cmd':'swipe', 'coordinates':'${1}_${2}_${3}_${4}'}"
}

function scroll() {
    _send_curl_cmd "{'cmd':'scroll', 'direction':'${1}'}"
}

function scrollAndClickText() {
    _send_curl_cmd "{'cmd':'scrollAndClickText', 'text':'${1}'}"
}

function turnScreen() {
    _send_curl_cmd "{'cmd':'turnScreen', 'action':'${1}'}"
}

function isScreenOn() {
    _send_curl_cmd "{'cmd':'isScreenOn'}"
}

function screenshot() {
    _send_curl_cmd "{'cmd':'screenshot'}"
    adb pull /data/screenshot.png
    adb shell rm /data/screenshot.png
}

function setText() {
    _send_curl_cmd "{'cmd':'setText', 'target':'${1}', 'text':'${2}'}"
}

function gesture() {
    if [ ${1} = 'two' ]; then
        if [ ${2} = 'swipe' ]; then
            _send_curl_cmd "{'cmd':'gesture_two_swipe', 'coordinatesfrom':'${4}_${5}_${6}_${7}', 'coordinatesto':'${9}_${10}_${11}_${12}'}"
        fi
    elif [ ${1} = 'three' ]; then
        ls
    elif [ ${1} = 'four' ]; then
        ls
    fi
}

function errCommand() {
    _send_curl_cmd "{'cmd':'O_Q'}"
}

function init_connection() {
    adb remount
    adb shell rm /system/app/UiTestService.apk
    adb push $AO/system/app/UiTestService.apk  /system/app/UiTestService.apk
    adb push $AO/data/local/tmp/UiAutomatorController.jar /data/local/tmp/UiAutomatorController.jar
    adb shell busybox pkill com.test.uitestservice
    adb shell am startservice com.test.uitestservice/com.test.uitestservice.UiTestService
    adb forward tcp:12125 tcp:12125
    adb shell busybox chmod 6777 /data/local/tmp/ -R
}

