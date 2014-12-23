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
    #clickText Settings
    #scrollAndClickText Location
    #press down
    #longClickText Settings
    #screenshot /tmp/QQQ.png
    #setText Rename ANDROID_BT_SAM_2
    #errCommand
    #gesture two swipe from 200 200 200 250 to 800 200 800 250 steps 10
    #gesture three swipe from 200 200 200 250 200 300 to 500 200 500 250 500 300 steps 2
    #gesture four swipe from 300 200 400 300 500 300 600 300 to 300 500 400 500 500 500 600 500 steps 15
    # ----------------------------------- #
}
main
