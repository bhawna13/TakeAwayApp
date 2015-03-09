package in.websnoox.tappTablet.orderFragment;

import in.websnoox.tappTablet.R;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout.LayoutParams;

public class Individual_Order_Receipt_Dialog extends Dialog implements android.view.View.OnClickListener{

	Context context;
	public Individual_Order_Receipt_Dialog(Context context) {
		super(context);
		this.context=context;
	
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setBackgroundDrawable(
				new ColorDrawable(android.graphics.Color.TRANSPARENT));
		
		getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setCanceledOnTouchOutside(true);
		setCancelable(true);
		setContentView(R.layout.individual_order_status_screen);

		

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		

		default:
			break;

		}

	}

}
