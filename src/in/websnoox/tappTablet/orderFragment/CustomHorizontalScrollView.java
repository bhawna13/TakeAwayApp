package in.websnoox.tappTablet.orderFragment;

import in.websnoox.tappTablet.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class CustomHorizontalScrollView extends HorizontalScrollView{

	Context context;
	int prevIndex = 0;
	private Object individualOrderView;

	public CustomHorizontalScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		this.setSmoothScrollingEnabled(true);

	}

	public void setAdapter(Context context, NewOrderReceiptLayout mAdapter) {

		try {

			fillViewWithAdapter(mAdapter);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	private void fillViewWithAdapter(NewOrderReceiptLayout mAdapter)
			throws Exception {
		if (getChildCount() == 0) {
			throw new Exception(
					"CenterLockHorizontalScrollView must have one child");
		}
		if (getChildCount() == 0 || mAdapter == null)
			return;

		ViewGroup parent = (ViewGroup) getChildAt(0);


		parent.removeAllViews();

		for (int i = 0; i < mAdapter.getCount(); i++) {
			DisplayMetrics metrics = new DisplayMetrics();
			((Activity) context).getWindowManager().getDefaultDisplay()
			.getMetrics(metrics);
			int smallestWidth ;
		
			
			int density = metrics.densityDpi;
			if(density==213){
				smallestWidth=600;
				
			}
			else{
				smallestWidth=500;
			}

			View view = mAdapter.getView(i, null, parent);
			LayoutParams params=new LayoutParams(smallestWidth, LayoutParams.MATCH_PARENT);
			params.rightMargin=20;
			ScrollView chilScroll=(ScrollView)view.findViewById(R.id.childscroll);

			view.setLayoutParams(params);


			parent.setOnTouchListener(new View.OnTouchListener() {

				public boolean onTouch(View v, MotionEvent event) {
					Log.v("PARENT", "PARENT TOUCH");
					findViewById(R.id.childscroll).getParent()
					.requestDisallowInterceptTouchEvent(false);
					return false;
				}
			});
			chilScroll.setOnTouchListener(new View.OnTouchListener() {

				public boolean onTouch(View v, MotionEvent event) {
					Log.v("CHILD", "CHILD TOUCH");
					// Disallow the touch request for parent scroll on touch of
					// child view
					v.getParent().requestDisallowInterceptTouchEvent(true);
					return false;
				}
			});
			view.setTag(i);
			parent.addView(view);

		}
	}

	public void setCenter(int index) {

		ViewGroup parent = (ViewGroup) getChildAt(0);

		View preView = parent.getChildAt(prevIndex);
		preView.setBackgroundColor(Color.parseColor("#64CBD8"));
		android.widget.LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		lp.setMargins(5, 5, 5, 5);
		preView.setLayoutParams(lp);

		View view = parent.getChildAt(index);
		view.setBackgroundColor(Color.RED);

		int screenWidth = ((Activity) context).getWindowManager()
				.getDefaultDisplay().getWidth();

		int scrollX = (view.getLeft() - (screenWidth / 2))
				+ (view.getWidth() / 2);
		this.smoothScrollTo(scrollX, 0);
	}

	/* private View AddIndividualReceipt() {
			LayoutInflater mInflater ;
			mInflater = LayoutInflater.from(context);
			individualOrderView = mInflater.inflate(R.layout.notification_orderreceipt_layout, layout, false);
			LayoutParams params=new LayoutParams(420, LayoutParams.MATCH_PARENT);
			params.rightMargin=20;
			params.topMargin=20;
			individualOrderView.setLayoutParams(params);
	    }*/
}
