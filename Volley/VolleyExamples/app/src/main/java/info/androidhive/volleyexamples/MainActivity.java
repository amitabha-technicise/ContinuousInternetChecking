package info.androidhive.volleyexamples;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener
{
	private Button btnJson, btnString;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnString = (Button) findViewById(R.id.btnStringRequest);
		btnJson = (Button) findViewById(R.id.btnJsonRequest);

		// button click listeners
		btnString.setOnClickListener(this);
		btnJson.setOnClickListener(this);
	}


	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.btnStringRequest:
			startActivity(new Intent(MainActivity.this,
					GETrequestActivity.class));
			break;
		case R.id.btnJsonRequest:
			startActivity(new Intent(MainActivity.this,
					POSTrequestActivity.class));

		default:
			break;
		}
	}
}
