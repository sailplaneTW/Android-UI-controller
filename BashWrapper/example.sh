#!/usr/bin/env bash

source $(dirname $0)/env.sh

function main() {
    init_connection

    # ---------- real commands ---------- #
    #getDeviceInfo
    #clickText Settings
    #pressBack
    #press home
    #press volume_up 
    #press power
    #turnScreen off
    #turnScreen on
    #isScreenOn
    #click 200 200
    #longClick 400 400
    #swipe 650 430 550 430
    #scroll backward
    clickText Settings
    scrollAndClickText Location
    #press down
    #longClickText Settings
    #screenshot /tmp/QQQ.png
    #setText Rename ANDROID_BT_SAM_2
    #errCommand
    # ----------------------------------- #
}
main
