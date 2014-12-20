package com.test.uitestservice;

import java.util.HashMap;
import java.util.Map;
import android.util.Log;
import java.io.IOException;
import org.json.JSONObject;
import org.json.JSONException;

public class UiAutoTestServer extends NanoHTTPD {
    static final String TAG = "UiAutoTestServer";

    public UiAutoTestServer() {
        super(12125);
    }

    @Override public Response serve(IHTTPSession session) {
        Method method = session.getMethod();
        String uri = session.getUri();
        Map<String, String> files = new HashMap<String, String>();
        String msg = "undefined";

        if (Method.PUT.equals(method) || Method.POST.equals(method)) {
            try {
                session.parseBody(files);
            } catch (IOException ioe) {
                return new Response(Response.Status.INTERNAL_ERROR, MIME_PLAINTEXT, "SERVER INTERNAL ERROR: IOException: " + ioe.getMessage());
            } catch (ResponseException re) {
                return new Response(re.getStatus(), MIME_PLAINTEXT, re.getMessage());
            }
        }

        // get the POST body
        String postBody = session.getQueryParameterString();

        try {
            CommandParser cmd_handler = new CommandParser();
            msg = null;
            msg = cmd_handler.executeCommand(new JSONObject(postBody));
        } catch (JSONException e) {
        }

        return new NanoHTTPD.Response(msg + "\n");
    }


    public static void main(String[] args) {
        ServerRunner.run(UiAutoTestServer.class);
    }
}
