package in.websnoox.tappTablet.dataReport;

import in.websnoox.tappTablet.R;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class DeleteDialog extends Dialog{

	Context context;
	ICallBackDelete backDelete;
	
	public DeleteDialog(Context context,ICallBackDelete  backDelete) 
	
	{
		super(context);
		
		this.backDelete=backDelete;
		this.context=context;
		
	}

	@Override  
	protected void onCreate(Bundle savedInstanceState) {  
		super.onCreate(savedInstanceState);

		getWindow().setBackgroundDrawable(
				new ColorDrawable(android.graphics.Color.TRANSPARENT));
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	     setContentView(R.layout.delete_dialog_layout);
	     
	     Button yes=(Button)findViewById(R.id.deleteButton);
	     Button no=(Button)findViewById(R.id.cancelButton);
	     
	     yes.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dismiss();
			
				backDelete.deleteRows(true);
			}
			
		});
	     
	     no.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dismiss();
				backDelete.deleteRows(false);
			}
		});
	}
	
	
}
