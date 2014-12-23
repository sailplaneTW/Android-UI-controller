package com.test.uitestcontroller;

import android.util.Log;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android.graphics.Point;
import android.os.RemoteException;
import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.core.UiWatcher;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class UiAutomatorTestCaseController extends UiAutomatorTestCase {
    static final private String TAG = "UiAutomatorTestCaseController";

    static final private String RESULT_FILE = "/data/local/tmp/uiautotest.txt";

    private void writeReturnData(final String data) {
        if((new File(RESULT_FILE)).exists() == true) {
            (new File(RESULT_FILE)).delete();
        }

        try {
            (new File(RESULT_FILE)).createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(RESULT_FILE, true));
            bw.write(data);
            bw.flush();
            bw.close();
        } catch(IOException e) {
        }
    }

    private UiObject getObjectByText(final String txt) {
        return new UiObject(new UiSelector().text(txt));
    }

    /* --------------------------------------------------- */

    public boolean press() {
        final String key = getParams().getString("para1");
        boolean ret = true;

        if(key.equals("home")) {
            getUiDevice().pressHome();
        }
        else if(key.equals("back")) {
            getUiDevice().pressBack();
        }
        else if(key.equals("left")) {
            getUiDevice().pressDPadLeft();
        }
        else if(key.equals("right")) {
            getUiDevice().pressDPadRight();
        }
        else if(key.equals("up")) {
            getUiDevice().pressDPadUp();
        }
        else if(key.equals("down")) {
            getUiDevice().pressDPadDown();
        }
        else if(key.equals("center")) {
            getUiDevice().pressDPadCenter();
        }
        else if(key.equals("menu")) {
            getUiDevice().pressMenu();
        }
        else if(key.equals("enter")) {
            getUiDevice().pressEnter();
        }
        else if(key.equals("del")) {
            getUiDevice().pressDelete();
        }
        else if(key.equals("recent")) {
            try {
                getUiDevice().pressRecentApps();
            } catch (RemoteException e) {
            }
        }
        else if(key.equals("volume_up")) {
            getUiDevice().pressKeyCode(24);
        }
        else if(key.equals("volume_down")) {
            getUiDevice().pressKeyCode(25);
        }
        else if(key.equals("power")) {
            getUiDevice().pressKeyCode(26);
        }
        else {
            Log.e(TAG, "unknown key [" + key + "]");
            ret = false;
        }

        return ret;
    }

    public boolean turnScreen() {
        final String action = getParams().getString("para1");
        boolean ret = true;

        try {
            if(action.equals("on"))
                getUiDevice().wakeUp();
            else if (action.equals("off"))
                getUiDevice().sleep();
            else {
                Log.e(TAG, "unknown touchScreen action [" + action + "]");
                ret = false;
            }
        } catch(RemoteException e) {
        }

        return ret;
    }

    public boolean clickText() {
        try {
            getObjectByText(getParams().getString("para1")).click();
        } catch(UiObjectNotFoundException e) {
        }

        return true;
    }

    public boolean longClickText() {
        try {
            getObjectByText(getParams().getString("para1")).longClick();
        } catch(UiObjectNotFoundException e) {
        }

        return true;
    }

    public boolean click() {
        int x = 0, y = 0;
        x = Integer.parseInt(getParams().getString("para1").split("_")[0]);
        y = Integer.parseInt(getParams().getString("para1").split("_")[1]);
        getUiDevice().click(x, y);

        return true;
    }

    public boolean longClick() {
        int x = 0, y = 0;
        x = Integer.parseInt(getParams().getString("para1").split("_")[0]);
        y = Integer.parseInt(getParams().getString("para1").split("_")[1]);
        getUiDevice().swipe(x, y, x, y, 100);

        return true;
    }

    public boolean swipe() {
        int sx = 0, sy = 0, ex = 0, ey = 0;
        sx = Integer.parseInt(getParams().getString("para1").split("_")[0]);
        sy = Integer.parseInt(getParams().getString("para1").split("_")[1]);
        ex = Integer.parseInt(getParams().getString("para1").split("_")[2]);
        ey = Integer.parseInt(getParams().getString("para1").split("_")[3]);
        getUiDevice().swipe(sx, sy, ex, ey, 1);

        return true;
    }

    public boolean scroll() {
        String direction = getParams().getString("para1");
        UiScrollable listView = new UiScrollable(new UiSelector().className(android.widget.ListView.class.getName()));

        try {
            if(direction.equals("backward"))
                listView.scrollBackward();
            else if(direction.equals("forward"))
                listView.scrollForward();
        } catch (UiObjectNotFoundException e) {
        }

        return true;
    }

    public boolean scrollAndClickText() {
        String text = getParams().getString("para1");
        UiScrollable listView = new UiScrollable(new UiSelector().className(android.widget.ListView.class.getName()));

        try {
            listView.setMaxSearchSwipes(100);
            listView.scrollTextIntoView(text);
            listView.waitForExists(5000);
            listView.getChildByText(new UiSelector().className(android.widget.TextView.class.getName()), text).click();
        } catch (UiObjectNotFoundException e) {
        }

        return true;
    }

    public boolean isScreenOn() {
        String is_screen_on = "true";
        try {
            is_screen_on = (getUiDevice().isScreenOn())?"true":"false";
        } catch(RemoteException e) {
        }
        writeReturnData(is_screen_on);
        return true;
    }

    public boolean gestureSwipe() {
        final int num_of_fingers = Integer.parseInt(getParams().getString("para1"));
        final String[] points_from_string = getParams().getString("para2").split("_");
        final String[] points_to_string = getParams().getString("para3").split("_");

        Point[] from = new Point[num_of_fingers];
        Point[] to = new Point[num_of_fingers];

        for(int i=0; i<num_of_fingers ; i++) {
            from[i] = new Point(Integer.parseInt(points_from_string[i * 2 + 0]), Integer.parseInt(points_from_string[i * 2 + 1]));

            to[i] = new Point(Integer.parseInt(points_to_string[i * 2 + 0]), Integer.parseInt(points_to_string[i * 2 + 1]));
        }

        if(num_of_fingers == 2) {
            (new UiObject(new UiSelector().className("android,widget.FrameLayout"))).performTwoPointerGesture(
                from[0], from[1], to[0], to[1], 10);
        }

        return true;
    }

    public boolean setText() {
        try {
            (new UiObject(new UiSelector().descriptionStartsWith(getParams().getString("para1")))).setText(getParams().getString("para2"));
        } catch(UiObjectNotFoundException e) {
        }

        return true;
    }

    public boolean getDeviceInfo() {
        String device_info = "";

        device_info += "product name = " + getUiDevice().getProductName();
        device_info += ", height = " + getUiDevice().getDisplayHeight();
        device_info += ", width = " + getUiDevice().getDisplayWidth();
        writeReturnData(device_info);

        return true;
    }
}
