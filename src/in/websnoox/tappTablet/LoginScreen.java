package in.websnoox.tappTablet;

import in.websnoox.tappTablet.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class LoginScreen extends Activity implements OnClickListener{
	private Button loginButton;
	private Button forgotButton;
	private Button registerButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login_sccreen);
		GetIds();
	}

	private void GetIds() {
		loginButton=(Button)findViewById(R.id.btn_login);
		forgotButton=(Button)findViewById(R.id.btn_forgotPassword);
		registerButton=(Button)findViewById(R.id.btn_register);
		loginButton.setOnClickListener(this);
		forgotButton.setOnClickListener(this);
		registerButton.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_forgotPassword:

			break;
		case R.id.btn_login:
			Intent i = new Intent(LoginScreen.this, BaseFragmentActivity.class);
			LoginScreen.this.startActivity(i);
			LoginScreen.this.finish();
			break;
		case R.id.btn_register:

			break;

		default:
			break;
		}

	}
}
