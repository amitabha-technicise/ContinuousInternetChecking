package info.androidhive.volleyexamples;

import android.app.Activity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class POSTrequestActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_post);

		try
		{
			String URL = "http://webservice.mycuratio.com/webservice2.1/code/index.php?/indivoconnect/addNewImmunizationByRecordId";
			JSONObject params = new JSONObject();
			params.put("userId","2234");
			params.put("recordId","49d35543-17d8-4fb3-9b83-5312fcb648d5");

			params.put("product_name_title", "typhoid, oral");
			params.put("product_name_system", "http://www2a.cdc.gov/nip/IIS/IISStandards/vaccines.asp?rpt=cvx#");
			params.put("product_name_identifier", "54");
			params.put("date", "2017-3-3");
			params.put("provider_fullname","VOLAaaaaaaaaaaaa aaa");
			params.put("provider_npi","1376627745");


			ServiceHandler serviceHandler = new ServiceHandler();
			serviceHandler.postResponce(params, URL, new ServiceHandler.VolleyCallback()
			{
				@Override
				public void onSuccessResponse(String result)
				{
					Log.d("LOG_DATA_POST", result+"");
//					tv.setText(result+"");
					if(result.matches("VOLLEY_NETWORK_ERROR"))
					{

					}
					else
					{

					}
				}
			}, this);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
