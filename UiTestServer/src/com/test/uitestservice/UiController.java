package com.test.uitestservice;

import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.FileReader;
import java.lang.StringBuilder;

public class UiController {
    static private String TAG = "UiController";

    static final private String RESULT_FILE = "/data/local/tmp/uiautotest.txt";

    private String executeUiTestCommand(final String cmd) {
        try {
            String command = "/system/bin/uiautomator runtest UiAutomatorController.jar -c com.test.uitestcontroller.UiAutomatorTestCaseController#" + cmd;
            Log.e(TAG, "<1> executeUiTestCommand command ["+command+"]");
            Process proc = Runtime.getRuntime().exec(command);
            String line = null;

            InputStreamReader osr = new InputStreamReader(proc.getInputStream());
            BufferedReader obr = new BufferedReader(osr);
            while ((line = obr.readLine()) != null) {
                Log.e(TAG, "<1> executeUiTestCommand result \t--> ["+line+"]");
            }

            proc.waitFor();
        } catch (Exception e) {
        }

        return new String("");
    }

    private String executeUiTestCommand(final String cmd, final String text) {
        try {
            String command = "/system/bin/uiautomator runtest UiAutomatorController.jar -c com.test.uitestcontroller.UiAutomatorTestCaseController#" + cmd + " -e para1 " + text;
            Log.e(TAG, "<2> executeUiTestCommand command ["+command+"]");
            Process proc = Runtime.getRuntime().exec(command);
            String line = null;

            InputStreamReader osr = new InputStreamReader(proc.getInputStream());
            BufferedReader obr = new BufferedReader(osr);
            while ((line = obr.readLine()) != null) {
                Log.e(TAG, "<2> executeUiTestCommand result \t--> ["+line+"]");
            }

            proc.waitFor();
        } catch (Exception e) {
        }

        return new String("");
    }

    private String executeUiTestCommand(final String cmd, final String text1, final String text2) {
        try {
            String command = "/system/bin/uiautomator runtest UiAutomatorController.jar -c com.test.uitestcontroller.UiAutomatorTestCaseController#" + cmd + " -e para1 " + text1 + " -e para2 " + text2;
            Log.e(TAG, "<3> executeUiTestCommand command ["+command+"]");
            Process proc = Runtime.getRuntime().exec(command);
            String line = null;

            InputStreamReader osr = new InputStreamReader(proc.getInputStream());
            BufferedReader obr = new BufferedReader(osr);
            while ((line = obr.readLine()) != null) {
                Log.e(TAG, "<3> executeUiTestCommand result \t--> ["+line+"]");
            }

            proc.waitFor();
        } catch (Exception e) {
        }

        return new String("");
    }

    private String executeUiTestCommand(final String cmd, final String text1, final String text2, final String text3) {
        try {
            String command = "/system/bin/uiautomator runtest UiAutomatorController.jar -c com.test.uitestcontroller.UiAutomatorTestCaseController#" + cmd + " -e para1 " + text1 + " -e para2 " + text2 + " -e para3 " + text3;
            Log.e(TAG, "<4> executeUiTestCommand command ["+command+"]");
            Process proc = Runtime.getRuntime().exec(command);
            String line = null;

            InputStreamReader osr = new InputStreamReader(proc.getInputStream());
            BufferedReader obr = new BufferedReader(osr);
            while ((line = obr.readLine()) != null) {
                Log.e(TAG, "<4> executeUiTestCommand result \t--> ["+line+"]");
            }

            proc.waitFor();
        } catch (Exception e) {
        }

        return new String("");
    }

    private String executeUiTestCommand(final String cmd,
                                        final String text1,
                                        final String text2,
                                        final String text3,
                                        final String text4)
    {
        try {
            String command = "/system/bin/uiautomator runtest UiAutomatorController.jar -c com.test.uitestcontroller.UiAutomatorTestCaseController#" + cmd + " -e para1 " + text1 + " -e para2 " + text2 + " -e para3 " + text3 + " -e para4 " + text4;
            Log.e(TAG, "<5> executeUiTestCommand command ["+command+"]");
            Process proc = Runtime.getRuntime().exec(command);
            String line = null;

            InputStreamReader osr = new InputStreamReader(proc.getInputStream());
            BufferedReader obr = new BufferedReader(osr);
            while ((line = obr.readLine()) != null) {
                Log.e(TAG, "<5> executeUiTestCommand result \t--> ["+line+"]");
            }

            proc.waitFor();
        } catch (Exception e) {
        }

        return new String("");
    }

    private String executeShellCommand(final String cmd) {
        try {
            String command = cmd;
            Log.e(TAG, "<1> executeShellCommand command ["+command+"]");
            Process proc = Runtime.getRuntime().exec(command);
            String line = null;

            InputStreamReader osr = new InputStreamReader(proc.getInputStream());
            BufferedReader obr = new BufferedReader(osr);
            while ((line = obr.readLine()) != null) {
                Log.e(TAG, "<1> executeShellCommand result \t--> ["+line+"]");
            }

            proc.waitFor();
        } catch (Exception e) {
        }

        return new String("");
    }

    private String readReturnData() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(RESULT_FILE));
            try {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    sb.append(line);
                    line = br.readLine();
                }
                return sb.toString();
            } finally {
                br.close();
            }
        } catch (IOException e) {
        }

        return new String("");
    }


    /* ---------------------------------------------------------- */
    public boolean press(final String key) {
        executeUiTestCommand("press", key);
        return true;
    }

    public boolean turnScreen(final String action) {
        executeUiTestCommand("turnScreen", action);
        return true;
    }

    public String isScreenOn() {
        executeUiTestCommand("isScreenOn");
        return readReturnData();
    }

    public boolean click(final String coordinate) {
        executeUiTestCommand("click", coordinate);
        return true;
    }

    public boolean longClick(final String coordinate) {
        executeUiTestCommand("longClick", coordinate);
        return true;
    }

    public boolean swipe(final String coordinates) {
        executeUiTestCommand("swipe", coordinates);
        return true;
    }

    public boolean gestureSwipe(final int num_of_fingers,
                                final String coordinatesfrom,
                                final String coordinatesto,
                                final String steps)
    {
        executeUiTestCommand("gestureSwipe", Integer.toString(num_of_fingers), coordinatesfrom, coordinatesto, steps);
        return true;
    }

    public boolean scroll(final String direction) {
        executeUiTestCommand("scroll", direction);
        return true;
    }

    public boolean scrollAndClickText(final String text) {
        executeUiTestCommand("scrollAndClickText", text);
        return true;
    }

    public boolean clickText(final String text) {
        executeUiTestCommand("clickText", text);
        return true;
    }

    public boolean longClickText(final String text) {
        executeUiTestCommand("longClickText", text);
        return true;
    }

    public String getDeviceInfo() {
        executeUiTestCommand("getDeviceInfo");
        return readReturnData();
    }

    public boolean screenshot() {
        executeShellCommand("/system/bin/screencap -p /data/screenshot.png");
        return true;
    }

    public boolean setText(final String target, final String text) {
        executeUiTestCommand("setText", target, text);
        return true;
    }
}

