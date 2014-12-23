package com.test.uitestservice;

import android.util.Log;
import org.json.JSONObject;
import org.json.JSONException;

public class CommandParser {
    private String TAG = "CommandParser";
    private UiController uicontroller = new UiController();

    public String executeCommand(final JSONObject jObject) {
        String cmd = null;
        String ret = "";

        cmd = (getJsonString(jObject, "cmd") != null) ? getJsonString(jObject, "cmd") : "<undefined cmd>";

        if(cmd.equals("getDeviceInfo")) {
            ret = "{'cmd':'getDeviceInfo', 'content':'" + uicontroller.getDeviceInfo() + "', 'status':'ok'}";
        }
        else if(cmd.equals("press")) {
            uicontroller.press(getJsonString(jObject, "key"));
            ret = "{'cmd':'pressBack', 'status':'ok' }";
        }
        else if(cmd.equals("turnScreen")) {
            uicontroller.turnScreen(getJsonString(jObject, "action"));
            ret = "{'cmd':'turnScreen', 'status':'ok' }";
        }
        else if(cmd.equals("isScreenOn")) {
            ret = "{'cmd':'isScreenOn', 'status':'" + uicontroller.isScreenOn() + "', 'status':'ok'}";
        }
        else if(cmd.equals("click")) {
            uicontroller.click(getJsonString(jObject, "coordinate"));
            ret = "{'cmd':'click', 'status':'ok' }";
        }
        else if(cmd.equals("longClick")) {
            uicontroller.longClick(getJsonString(jObject, "coordinate"));
            ret = "{'cmd':'longClick', 'status':'ok' }";
        }
        else if(cmd.equals("gesture_two_swipe")) {
            uicontroller.gestureSwipe(2, getJsonString(jObject, "coordinatesfrom"), getJsonString(jObject, "coordinatesto"));
            ret = "{'cmd':'gesture_two_swipe', 'status':'ok' }";
        }
        else if(cmd.equals("swipe")) {
            uicontroller.swipe(getJsonString(jObject, "coordinates"));
            ret = "{'cmd':'swipe', 'status':'ok' }";
        }
        else if(cmd.equals("scroll")) {
            uicontroller.scroll(getJsonString(jObject, "direction"));
            ret = "{'cmd':'scroll', 'status':'ok' }";
        }
        else if(cmd.equals("scrollAndClickText")) {
            uicontroller.scrollAndClickText(getJsonString(jObject, "text"));
            ret = "{'cmd':'scrollAndClickText', 'status':'ok' }";
        }
        else if(cmd.equals("isScreenOn")) {
            ret = "{'cmd':'isScreenOn', 'status':'" + uicontroller.isScreenOn() + "', 'status':'ok'}";
        }
        else if(cmd.equals("clickText")) {
            uicontroller.clickText(getJsonString(jObject, "text"));
            ret = "{'cmd':'clickText', 'status':'ok' }";
        }
        else if(cmd.equals("longClickText")) {
            uicontroller.longClickText(getJsonString(jObject, "text"));
            ret = "{'cmd':'longClickText', 'status':'ok' }";
        }
        else if(cmd.equals("setText")) {
            uicontroller.setText(getJsonString(jObject, "target"), getJsonString(jObject, "text"));
            ret = "{'cmd':'setText', 'status':'ok' }";
        }
        else if(cmd.equals("screenshot")) {
            uicontroller.screenshot();
            ret = "{'cmd':'screenshot', 'status':'ok' }";
        }
        else {
            Log.e(TAG, cmd);
            ret = "{'cmd':'" + cmd + "', 'status':'ok' }";
        }

        return new String(ret);
    }

    private String getJsonString(final JSONObject jObject, final String key) {
        String str = null;

        try {
            str = jObject.getString(key);
        } catch (JSONException e) {
        }

        return str;
    }
}

