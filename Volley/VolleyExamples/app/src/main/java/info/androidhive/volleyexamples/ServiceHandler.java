package info.androidhive.volleyexamples;

import android.content.Context;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import info.androidhive.volleyexamples.app.AppController;
import info.androidhive.volleyexamples.utils.Const;

import static info.androidhive.volleyexamples.app.AppController.getInstance;

/**
 * Created by technicise on 6/3/17.
 */

public class ServiceHandler
{
    public interface VolleyCallback
    {
        void onSuccessResponse(String result);
    }
    public void getResponse(String url, final VolleyCallback callback, Context context)
    {
        final TransparentProgressDialog pd = new TransparentProgressDialog(context);
        pd.show();
        StringRequest strreq = new StringRequest(Request.Method.GET, url, new Response.Listener < String > ()
        {
            @Override
            public void onResponse(String Response)
            {
                callback.onSuccessResponse(Response);
                pd.dismiss();
            }

        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError e)
            {
                callback.onSuccessResponse("VOLLEY_NETWORK_ERROR");
                pd.dismiss();
            }
        })
        {
            /**
             * Passing some request headers
             * */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError
            {
                return createBasicAuthHeader("mycuratioApiUSer", "m3h3@lth2013");
            }

            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Username", "mycuratioApiUSer");
                params.put("Password", "m3h3@lth2013");
                params.put("grant_type", "password");

                return params;
            }
        };
        MySingleton.getInstance(getInstance()).addToRequestQueue(strreq);
    }
    private HashMap<String, String> createBasicAuthHeader(String username, String password)
    {
        HashMap<String, String> headerMap = new HashMap<String, String>();

        String credentials = username + ":" + password;
        String base64EncodedCredentials =
                Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
        headerMap.put("Authorization", "Basic " + base64EncodedCredentials);

        return headerMap;
    }
    public void postResponce(JSONObject params, String url, final VolleyCallback callback, Context context)
    {
        try
        {
            final TransparentProgressDialog pd = new TransparentProgressDialog(context);
            pd.show();
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            final String requestBody = params.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
            {
                @Override
                public void onResponse(String response)
                {
                    callback.onSuccessResponse(response);
                    Log.i("POST_", response);
                    pd.dismiss();
                }
            }, new Response.ErrorListener()
            {
                @Override
                public void onErrorResponse(VolleyError error)
                {
                    callback.onSuccessResponse("POST_ERROR");
                    Log.e("POST_", error.toString());
                    pd.dismiss();
                }
            })
            {
                /**
                 * Passing some request headers
                 * */
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError
                {
                    return createBasicAuthHeader("mycuratioApiUSer", "m3h3@lth2013");
                }
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError
                {
                    try
                    {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    }
                    catch (UnsupportedEncodingException uee)
                    {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response)
                {
                    String responseString = "post_error";
                    if (response != null)
                    {
                        try
                        {
                            responseString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            requestQueue.add(stringRequest);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
