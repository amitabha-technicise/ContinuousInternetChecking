package info.androidhive.volleyexamples;

import info.androidhive.volleyexamples.app.AppController;
import info.androidhive.volleyexamples.utils.Const;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GETrequestActivity extends Activity {

	private String TAG = GETrequestActivity.class.getSimpleName();
	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_call);
		tv = (TextView) findViewById(R.id.tv);

		myWebServiceFun();
	}

	public void myWebServiceFun()
	{

//		String url = "http://webservice.mycuratio.co/webservice/code/index.php?/indivoconnect/careCircleListByRecordId/2166/e5479ae1-a5a1-434a-ba71-66760a630fca";
		String url = "http://webservice.mycuratio.com/webservice2.1/code/index.php?/indivoconnect/vitalSignListByUserIdRecordIdVitalType/1937/fdfe6a28-633e-4798-9536-e17e00eb572e/bp";
//		String url = "http://webservice.mycuratio.com/webservice2.1/code/index.php?/chartsIndivo/getMedicationDetails";
		ServiceHandler serviceHandler = new ServiceHandler();
		serviceHandler.getResponse(url,
				new ServiceHandler.VolleyCallback()
				{
					@Override
					public void onSuccessResponse(String result)
					{
						Log.d("LOG_DATA", result+"");
						tv.setText(result+"");
						if(result.matches("VOLLEY_NETWORK_ERROR"))
						{

						}
						else
						{

						}
					}
				}, this);
	}
}
