package com.test.uitestcontroller;

import android.util.Log;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android.graphics.Point;
import android.os.RemoteException;
import android.view.MotionEvent.PointerCoords;
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
        final int steps = Integer.parseInt(getParams().getString("para4"));

        if(num_of_fingers == 2) {
            Point[] from = new Point[num_of_fingers];
            Point[] to = new Point[num_of_fingers];

            for(int i=0; i<num_of_fingers ; i++) {
                from[i] = new Point(Integer.parseInt(points_from_string[i * 2 + 0]), Integer.parseInt(points_from_string[i * 2 + 1]));
                to[i] = new Point(Integer.parseInt(points_to_string[i * 2 + 0]), Integer.parseInt(points_to_string[i * 2 + 1]));
            }
            (new UiObject(new UiSelector().className("android,widget.FrameLayout"))).performTwoPointerGesture(
                from[0], from[1], to[0], to[1], steps);
        }
        else if (num_of_fingers == 3) {
            Point[] from = new Point[num_of_fingers];
            Point[] to = new Point[num_of_fingers];

            for(int i=0; i<num_of_fingers ; i++) {
                from[i] = new Point(Integer.parseInt(points_from_string[i * 2 + 0]), Integer.parseInt(points_from_string[i * 2 + 1]));
                to[i] = new Point(Integer.parseInt(points_to_string[i * 2 + 0]), Integer.parseInt(points_to_string[i * 2 + 1]));
            }

            performThreePointerGesture(from[0], from[1], from[2], to[0], to[1], to[2], steps);
        }
        else if (num_of_fingers == 4) {
            Point[] from = new Point[num_of_fingers];
            Point[] to = new Point[num_of_fingers];

            for(int i=0; i<num_of_fingers ; i++) {
                from[i] = new Point(Integer.parseInt(points_from_string[i * 2 + 0]), Integer.parseInt(points_from_string[i * 2 + 1]));
                to[i] = new Point(Integer.parseInt(points_to_string[i * 2 + 0]), Integer.parseInt(points_to_string[i * 2 + 1]));
            }

            performFourPointerGesture(from[0], from[1], from[2], from[3], to[0], to[1], to[2], to[3], steps);
        }
        else {
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

    private boolean performThreePointerGesture(Point startPoint1, Point startPoint2, Point startPoint3, Point endPoint1,
            Point endPoint2, Point endPoint3, int steps) {
        // avoid a divide by zero
        if(steps == 0)
            steps = 1;

        final float stepX1 =  (float) (endPoint1.x - startPoint1.x) / steps;
        final float stepY1 =  (float) (endPoint1.y - startPoint1.y) / steps;
        final float stepX2 =  (float) (endPoint2.x - startPoint2.x) / steps;
        final float stepY2 =  (float) (endPoint2.y - startPoint2.y) / steps;
        final float stepX3 =  (float) (endPoint3.x - startPoint3.x) / steps;
        final float stepY3 =  (float) (endPoint3.y - startPoint3.y) / steps;

        float eventX1, eventY1, eventX2, eventY2;
        float eventX3, eventY3;
        eventX1 = startPoint1.x;
        eventY1 = startPoint1.y;
        eventX2 = startPoint2.x;
        eventY2 = startPoint2.y;
        eventX3 = startPoint3.x;
        eventY3 = startPoint3.y;

        // allocate for steps plus first down and last up
        PointerCoords[] points1 = new PointerCoords[steps + 2];
        PointerCoords[] points2 = new PointerCoords[steps + 2];
        PointerCoords[] points3 = new PointerCoords[steps + 2];

        // Include the first and last touch downs in the arrays of steps
        for (int i = 0; i < steps + 1; i++) {
            PointerCoords p1 = new PointerCoords();
            p1.x = eventX1;
            p1.y = eventY1;
            p1.pressure = 1;
            p1.size = 1;
            points1[i] = p1;

            PointerCoords p2 = new PointerCoords();
            p2.x = eventX2;
            p2.y = eventY2;
            p2.pressure = 1;
            p2.size = 1;
            points2[i] = p2;

            PointerCoords p3 = new PointerCoords();
            p3.x = eventX3;
            p3.y = eventY3;
            p3.pressure = 1;
            p3.size = 1;
            points3[i] = p3;

            eventX1 += stepX1;
            eventY1 += stepY1;
            eventX2 += stepX2;
            eventY2 += stepY2;
            eventX3 += stepX3;
            eventY3 += stepY3;
        }

        // ending pointers coordinates
        PointerCoords p1 = new PointerCoords();
        p1.x = endPoint1.x;
        p1.y = endPoint1.y;
        p1.pressure = 1;
        p1.size = 1;
        points1[steps + 1] = p1;

        PointerCoords p2 = new PointerCoords();
        p2.x = endPoint2.x;
        p2.y = endPoint2.y;
        p2.pressure = 1;
        p2.size = 1;
        points2[steps + 1] = p2;

        PointerCoords p3 = new PointerCoords();
        p3.x = endPoint3.x;
        p3.y = endPoint3.y;
        p3.pressure = 1;
        p3.size = 1;
        points3[steps + 1] = p3;

        return (new UiObject(new UiSelector().className("android,widget.FrameLayout"))).performMultiPointerGesture(
                points1, points2, points3);
    }

    private boolean performFourPointerGesture(Point startPoint1, Point startPoint2, Point startPoint3, Point startPoint4,
                                              Point endPoint1, Point endPoint2, Point endPoint3, Point endPoint4, int steps)
    {
        // avoid a divide by zero
        if(steps == 0)
            steps = 1;

        final float stepX1 =  (float) (endPoint1.x - startPoint1.x) / steps;
        final float stepY1 =  (float) (endPoint1.y - startPoint1.y) / steps;
        final float stepX2 =  (float) (endPoint2.x - startPoint2.x) / steps;
        final float stepY2 =  (float) (endPoint2.y - startPoint2.y) / steps;
        final float stepX3 =  (float) (endPoint3.x - startPoint3.x) / steps;
        final float stepY3 =  (float) (endPoint3.y - startPoint3.y) / steps;
        final float stepX4 =  (float) (endPoint4.x - startPoint4.x) / steps;
        final float stepY4 =  (float) (endPoint4.y - startPoint4.y) / steps;

        float eventX1, eventY1, eventX2, eventY2;
        float eventX3, eventY3;
        float eventX4, eventY4;

        eventX1 = startPoint1.x;
        eventY1 = startPoint1.y;
        eventX2 = startPoint2.x;
        eventY2 = startPoint2.y;
        eventX3 = startPoint3.x;
        eventY3 = startPoint3.y;
        eventX4 = startPoint4.x;
        eventY4 = startPoint4.y;

        // allocate for steps plus first down and last up
        PointerCoords[] points1 = new PointerCoords[steps + 2];
        PointerCoords[] points2 = new PointerCoords[steps + 2];
        PointerCoords[] points3 = new PointerCoords[steps + 2];
        PointerCoords[] points4 = new PointerCoords[steps + 2];

        // Include the first and last touch downs in the arrays of steps
        for (int i = 0; i < steps + 1; i++) {
            PointerCoords p1 = new PointerCoords();
            p1.x = eventX1;
            p1.y = eventY1;
            p1.pressure = 1;
            p1.size = 1;
            points1[i] = p1;

            PointerCoords p2 = new PointerCoords();
            p2.x = eventX2;
            p2.y = eventY2;
            p2.pressure = 1;
            p2.size = 1;
            points2[i] = p2;

            PointerCoords p3 = new PointerCoords();
            p3.x = eventX3;
            p3.y = eventY3;
            p3.pressure = 1;
            p3.size = 1;
            points3[i] = p3;

            PointerCoords p4 = new PointerCoords();
            p4.x = eventX4;
            p4.y = eventY4;
            p4.pressure = 1;
            p4.size = 1;
            points4[i] = p4;

            eventX1 += stepX1;
            eventY1 += stepY1;
            eventX2 += stepX2;
            eventY2 += stepY2;
            eventX3 += stepX3;
            eventY3 += stepY3;
            eventX4 += stepX4;
            eventY4 += stepY4;
        }

        // ending pointers coordinates
        PointerCoords p1 = new PointerCoords();
        p1.x = endPoint1.x;
        p1.y = endPoint1.y;
        p1.pressure = 1;
        p1.size = 1;
        points1[steps + 1] = p1;

        PointerCoords p2 = new PointerCoords();
        p2.x = endPoint2.x;
        p2.y = endPoint2.y;
        p2.pressure = 1;
        p2.size = 1;
        points2[steps + 1] = p2;

        PointerCoords p3 = new PointerCoords();
        p3.x = endPoint3.x;
        p3.y = endPoint3.y;
        p3.pressure = 1;
        p3.size = 1;
        points3[steps + 1] = p3;

        PointerCoords p4 = new PointerCoords();
        p4.x = endPoint4.x;
        p4.y = endPoint4.y;
        p4.pressure = 1;
        p4.size = 1;
        points4[steps + 1] = p4;

        return (new UiObject(new UiSelector().className("android,widget.FrameLayout"))).performMultiPointerGesture(
                points1, points2, points3, points4);
    }
}
