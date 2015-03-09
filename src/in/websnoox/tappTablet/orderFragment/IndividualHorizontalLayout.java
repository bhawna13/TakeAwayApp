package in.websnoox.tappTablet.orderFragment;

import in.websnoox.tappTablet.BaseFragmentActivity;
import in.websnoox.tappTablet.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class IndividualHorizontalLayout extends LinearLayout{

	Context context;
	private View individualOrderView;
	LinearLayout layout;
	private HorizontalScrollView hsv;
	private RelativeLayout transparentwhiteReceipt;
	private LinearLayout layoutchild;
	private int position;

	public IndividualHorizontalLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context=context;
	}
	public IndividualHorizontalLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context=context;
	}

	@SuppressLint("NewApi")
	public IndividualHorizontalLayout(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		this.context=context;
	}
	public void addcustomView(LinearLayout view, HorizontalScrollView hsv,int position)
	{
		//addView(child, index);
		addView(AddIndividualReceipt());
		this.layout=view;

		this.hsv=hsv;
		this.position=position;
		//hsv.indexOfChild(child)

	}
	private int currenntTag;
	private View AddIndividualReceipt() {
		LayoutInflater mInflater ;
		mInflater = LayoutInflater.from(context);
		individualOrderView = mInflater.inflate(R.layout.notification_orderreceipt_layout, layout, false);
		LayoutParams params=new LayoutParams(420, LayoutParams.MATCH_PARENT);
		params.rightMargin=20;
		params.topMargin=20;
		individualOrderView.setLayoutParams(params);
		final RelativeLayout transparentwhiteReceipt = (RelativeLayout)
				individualOrderView.findViewById(R.id.transparentwhite_orderReceipt);
		layoutchild = (LinearLayout)individualOrderView.findViewById(R.id.linearCheck);
		layoutchild.setOnClickListener(new OnClickListener() {



			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				transparentwhiteReceipt.setVisibility(View.VISIBLE);
				//currenntTag=(int) layoutchild.getTag();
				//inflateTransparentLayout();
				//layoutchild.setBackgroundColor(context.getResources().getColor(R.color.transparentwhite));

				((BaseFragmentActivity) context).OnIndiviDualReciptTwiceClick(arg0);
			}
		});
		LinearLayout bottomLayout = (LinearLayout)individualOrderView.findViewById(R.id.linear_orderReciptbottom);
		bottomLayout.setVisibility(View.GONE);

		//layoutchild.setTag(position);
		return individualOrderView;
	}
	protected void inflateTransparentLayout() {
		// TODO Auto-generated method stub
		int count=((IndividualHorizontalLayout) hsv.getChildAt(0))
				.getChildCount();



		for(int i=0;i<count;i++){

			final View child = layout.getChildAt(i);
			
			if(child.equals(individualOrderView)){
				transparentwhiteReceipt.setVisibility(View.VISIBLE);
			}
			else{
				transparentwhiteReceipt.setVisibility(View.INVISIBLE);
			}
		}
	}

}
