package in.websnoox.tappTablet.orderFragment;

import in.websnoox.tappTablet.BaseFragmentActivity;
import in.websnoox.tappTablet.R;
import in.websnoox.tappTablet.entity.Order;
import in.websnoox.tappTablet.util.Util;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class OrderFragment extends Fragment implements OnClickListener,
OnTouchListener,UpdateViews,
OnDragListener,OnItemClickListener{
	private LayoutInflater inflater;
	private Context context;
	private Button ChaneorderViewButton;
	float smallestWidth;
	int density;
	boolean menuOut = false;
	private IndividualHorizontalLayout baseOrderReceiptOrderView;
	private View newOrderreceiptLayout;
	private View tableorderitemPart;
	private HorizontalScrollView hsv;
	private RelativeLayout individualOrderreceipt;
	private LinearLayout VoidButton;
	private LinearLayout ReadyButton;
	private LinearLayout ReprintButton;
	private LinearLayout DoneButton;
	private View readyorderitemPart;
	private ListView list_readyOrders;
	private LinearLayout raedyOrderIndividualReceipt;
	private RelativeLayout transparentLayout;
	private View finalView;
	private Button dragButton;
	NewOrderReceiptLayout adapter_newReceipt;
	private CustomHorizontalScrollView cutomHorizontalView;
	private LinearLayout linear_baseOfHorizontalScroll;
	private LinearLayout orderIdsList;
	View individualOrderView;
	private boolean adapterInflate;
	private PopupWindow popupWindow;
	private TableLayout tableLayout;
	private StringBuilder builder;
	private RelativeLayout transparentwhiteReceipt;
	private EditText edtCalculator;
	private TextView amountTobePiad;
	private ArrayList<Order> newOrderItemList;
	@Override
	public View onCreateView(final LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {

		final View view = inflater.inflate(R.layout.order_fragment_view,
				container, false);

		this.inflater = inflater;
		// this.itemAddOn = itemAddOn;
		initView(view);


		return view;
	}

	private RelativeLayout transparentwhite_relativeLayout;
	private float heightDp;
	private ImageView arrowRight;
	private ImageView arrowleft;
	private Button dragButton1;
	private Button dragButton2;

	private void initView(View view) {
		// TODO Auto-generated method stub
		finalView=view;
		ChaneorderViewButton=(Button)view.findViewById(R.id.ChaneorderViewButton);
		arrowRight=(ImageView)view.findViewById(R.id.arrowRight);
		arrowleft=(ImageView)view.findViewById(R.id.arrowleft);
		newOrderreceiptLayout=view.findViewById(R.id.newOrderreceiptLayout_orderView);
		readyorderitemPart=view.findViewById(R.id.readyorderitemPart);
		orderIdsList=(LinearLayout)view.findViewById(R.id.bottom_orderIds);
		VoidButton=(LinearLayout)view.findViewById(R.id.VoidButtonOrderfragment);
		ReadyButton=(LinearLayout)view.findViewById(R.id.ReadyButtonOrderfragment);
		ReprintButton=(LinearLayout)view.findViewById(R.id.ReprintButtonOrderfragment);
		DoneButton=(LinearLayout)view.findViewById(R.id.DoneButtonOrderfragment);
		dragButton=(Button)view.findViewById(R.id.dragCheck);
		dragButton.setOnTouchListener(this);
		dragButton1=(Button)view.findViewById(R.id.dragCheck1);
		dragButton1.setOnTouchListener(this);
		dragButton2=(Button)view.findViewById(R.id.dragCheck2);
		dragButton2.setOnTouchListener(this);

		transparentLayout=(RelativeLayout)view.
				findViewById(R.id.transparent_forCalculator);
		list_readyOrders=(ListView)view.findViewById(R.id.list_readyOrders);
		raedyOrderIndividualReceipt=(LinearLayout)view.findViewById
				(R.id.linear_raedyOrderIndividualReceipt);
		if(Util.readyOrdersList!=null&&Util.readyOrdersList.size()>0){
			list_readyOrders.setVisibility(View.VISIBLE);
		}

		cutomHorizontalView=(CustomHorizontalScrollView)view.findViewById(R.id.scrollView);
		linear_baseOfHorizontalScroll=(LinearLayout)view.findViewById(R.id.linear_baseOfHorizontalScroll);
		linear_baseOfHorizontalScroll.setOnDragListener(this);

		raedyOrderIndividualReceipt.setOnClickListener(this);
		arrowRight.setOnClickListener(this);
		arrowleft.setOnClickListener(this);

		//AddBottomIds();
		AddhorizontalView();
		GetDeviceDimesions();
		
		ChaneorderViewButton.setOnClickListener(this);
		list_readyOrders.setOnItemClickListener(this);

		VoidButton.setOnClickListener(this);
		ReadyButton.setOnClickListener(this);
		ReprintButton.setOnClickListener(this);
		DoneButton.setOnClickListener(this);
	}

	private void GetDeviceDimesions() {
		DisplayMetrics metrics = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay()
		.getMetrics(metrics);
		density = metrics.densityDpi;

		int widthPixels = metrics.widthPixels;
		int heightPixels = metrics.heightPixels;
		float scaleFactor = metrics.density;
		float widthDp = widthPixels / scaleFactor;
		heightDp = heightPixels / scaleFactor;

		smallestWidth = Math.min(widthDp, heightDp);
		
	}

	private void AddBottomIds() {
		for(int i=0;i<6;i++){
			View triangleView = inflater.inflate
					(R.layout.triangle_demo, null);
			LayoutParams params=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);

			params.topMargin=20;
			triangleView.setLayoutParams(params);
			orderIdsList.addView(triangleView);
		}


	}
	protected void startListAnimation() {
		Animation animswipe;
		LayoutParams ParamsorderitemPart = new LayoutParams(0,
				LayoutParams.MATCH_PARENT);
		LayoutParams ParamstakeawayPart = new LayoutParams(0,
				LayoutParams.MATCH_PARENT);


		animswipe = AnimationUtils.loadAnimation(getActivity(),
				R.anim.slide_right_noalpha);
		//menuOut = false;
		ChaneorderViewButton.setBackgroundResource(R.drawable.right_arrow);


		if (density == 213) {
			ParamsorderitemPart.weight = 5.5f;
			ParamstakeawayPart.weight = 4.5f;
		}
		else if (density == 160) {
			if(heightDp==600){
				ParamsorderitemPart.weight = 5.5f;
				ParamstakeawayPart.weight = 4.5f;
			}
			else{
				ParamsorderitemPart.weight = 4.0f;
				ParamstakeawayPart.weight = 6.0f;
			}

		}
		else{
			ParamsorderitemPart.weight = 4.0f;
			ParamstakeawayPart.weight = 6.0f;
		}

		readyorderitemPart.setLayoutParams(ParamsorderitemPart);
		newOrderreceiptLayout.setLayoutParams(ParamstakeawayPart);
		animswipe.setDuration(100);
		animswipe.setFillAfter(true);
		ChaneorderViewButton.startAnimation(animswipe);
		newOrderreceiptLayout.startAnimation(animswipe);

	}

	ProgressDialog dialog;
	private void AddhorizontalView() {


		newOrderItemList=new ArrayList<Order>();
		for(int i=0;i<6;i++){
			Order newOrder=new Order();
			newOrder.orderId=100+i;
			newOrder.Username="Bhawna";
			newOrder.OrderTime="09:15";
			newOrder.isSelectedToDelete=false;
			newOrderItemList.add(newOrder);
		}

		adapter_newReceipt=new NewOrderReceiptLayout(context, newOrderItemList,"withDelete",OrderFragment.this);

		 dialog=ProgressDialog.show(context, "Show", "Fetching Orders");
		/*Runnable r1=new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				cutomHorizontalView.setAdapter(context, adapter_newReceipt);
				Thread.sleep(3000);
				handler.sendEmptyMessage(0);
				//cutomHorizontalView.remo
			}
		};*/
		
	new Thread(new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			cutomHorizontalView.setAdapter(context, adapter_newReceipt);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			handler.sendEmptyMessage(0);
		}
	}).start();
		
		/*Handler h1=new Handler();
		h1.postDelayed(r1, 200);*/
		
	}
	
	
	 Handler handler = new Handler() {

		  @Override
		  public void handleMessage(Message msg) {
			 dialog.dismiss();

		  }
		 };
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		this.context=activity;
	}
	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.ChaneorderViewButton:
			startAnimation();
			break;
		case R.id.VoidButtonOrderfragment:
			((BaseFragmentActivity) context).HighLiGhtButtons("Void");
			break;
		case R.id.ReadyButtonOrderfragment:
			((BaseFragmentActivity) context).HighLiGhtButtons("Ready");
			break;
		case R.id.ReprintButtonOrderfragment:
			((BaseFragmentActivity) context).HighLiGhtButtons("Reprint");
			break;
		case R.id.DoneButtonOrderfragment:
			((BaseFragmentActivity) context).HighLiGhtButtons("Done");
			break;
		case R.id.linear_raedyOrderIndividualReceipt:
			transparentwhite_relativeLayout.setVisibility(View.VISIBLE);
			break;
		case R.id.arrowleft:
			cutomHorizontalView.scrollTo((int) cutomHorizontalView.getScrollX() - 120, (int) cutomHorizontalView.getScrollY());
			break;
		case R.id.arrowRight:
			cutomHorizontalView.scrollTo((int) cutomHorizontalView.getScrollX() + 120, (int) cutomHorizontalView.getScrollY());
			break;
		default:
			break;
		}

	}


	public void startAnimation() {
		Animation animswipe;
		LayoutParams ParamsorderitemPart = new LayoutParams(0,
				LayoutParams.MATCH_PARENT);
		LayoutParams ParamstakeawayPart = new LayoutParams(0,
				LayoutParams.MATCH_PARENT);

		if (menuOut) {
			animswipe = AnimationUtils.loadAnimation(getActivity(),
					R.anim.slide_right_noalpha);
			menuOut = false;
			ChaneorderViewButton.setBackgroundResource(R.drawable.right_arrow);
			if(Util.readyOrdersList.size()>0){
				ParamsorderitemPart.weight = 1.0f;
				ParamstakeawayPart.weight = 9.0f;
			}
			else{
				ParamsorderitemPart.weight = 0.0f;
				ParamstakeawayPart.weight = 10.0f;
			}


		} else {
			animswipe = AnimationUtils.loadAnimation(getActivity(),
					R.anim.slide_left_noalpha);
			menuOut = true;
			ChaneorderViewButton.setBackgroundResource(R.drawable.left_arrow);
			raedyOrderIndividualReceipt.setVisibility(View.GONE);

			if (smallestWidth >= 720) {

				if(Util.readyOrdersList.size()>0){
					ParamsorderitemPart.weight = 1.0f;
					ParamstakeawayPart.weight = 9.0f;
				}
				else{
					ParamsorderitemPart.weight = 0.0f;
					ParamsorderitemPart.setMargins(
							0,
							0,
							(int) context.getResources().getDimension(
									R.dimen.left_popup_margin), 0);
					ParamstakeawayPart.weight = 10.0f;
				}


			} else {
				if(Util.readyOrdersList.size()>0){
					ParamsorderitemPart.weight = 3.0f;
					ParamstakeawayPart.weight = 7.0f;
				}
				else{
					ParamsorderitemPart.weight = 1.0f;
					ParamstakeawayPart.weight = 9.0f;
				}



			}



		}
		readyorderitemPart.setLayoutParams(ParamsorderitemPart);
		newOrderreceiptLayout.setLayoutParams(ParamstakeawayPart);
		animswipe.setDuration(100);
		animswipe.setFillAfter(true);
		ChaneorderViewButton.startAnimation(animswipe);
		newOrderreceiptLayout.startAnimation(animswipe);



	}




	public void HighlIghtButtons(){
		VoidButton.setBackgroundColor(Color.parseColor("#333333"));
	}

	public void inflateReadyOrdersAdapter(){

		readyOrderAdapter adapter_readyOrders=new readyOrderAdapter(context, Util.readyOrdersList);
		list_readyOrders.setAdapter(adapter_readyOrders);
		adapterInflate=true;
		startAnimation();

	}

	public void inflateCalculator(View v){
		transparentLayout.setVisibility(View.VISIBLE);
		edtCalculator = (EditText) finalView.findViewById(R.id.edtCalcCashier);
		tableLayout = (TableLayout) finalView.findViewById(R.id.tableCashier);
		amountTobePiad=(TextView)finalView.findViewById(R.id.totalAmountTobePaid);
		/// txtCalculateValue = (CustomTextview) view .findViewById(R.id.txtCalculateValue);

		int rowCount = tableLayout.getChildCount();
		edtCalculator.setSelection(edtCalculator.getText().length());

		builder = new StringBuilder("");
		for (int i = 0; i < rowCount; i++) {
			TableRow rowView = (TableRow) tableLayout.getChildAt(i);
			int child = rowView.getChildCount();

			for (int j = 0; j < child; j++) {

				View childView = rowView.getChildAt(j);
				childView.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {

						try {
							String tag = (String) v.getTag();
							if(tag.equalsIgnoreCase("Ok")){
								String valueToCalculate = edtCalculator
										.getText().toString();

								String netAmountString = amountTobePiad
										.getText().toString();

								float enteredValue = Float
										.parseFloat(valueToCalculate);
								Float netValue = enteredValue
										- Float.parseFloat(netAmountString);
								edtCalculator.setText(String.valueOf(netValue));
								builder.delete(0, builder.length());
							}
							else if(tag.equalsIgnoreCase("Cancel")){

							}
							else if(tag.equalsIgnoreCase(".")){

							}
							else if(tag.equalsIgnoreCase("Card")){

							}
							else if(tag.equalsIgnoreCase("Cash")){

							}
							else {
								builder.append(tag);
								edtCalculator.setText(builder.toString());
							}


						} catch (Exception e) {
							Toast.makeText(context, "Equation is not valid.",
									Toast.LENGTH_LONG).show();
						}

					}
				});
			}
		}
	}
	private static final String LOGCAT = null;
	@Override
	public boolean onDrag(View layoutview, DragEvent dragevent) {
		int action = dragevent.getAction();
		View view = (View) dragevent.getLocalState();


		RelativeLayout bottomLayout;
		switch (action) {
		case DragEvent.ACTION_DRAG_STARTED:
			break;
		case DragEvent.ACTION_DRAG_ENTERED:
			break;
		case DragEvent.ACTION_DRAG_EXITED:
			break;
		case DragEvent.ACTION_DROP:


			View viewc = inflater.inflate
			(R.layout.new_order_recipt_layout, null);
			bottomLayout=(RelativeLayout)viewc.findViewById(R.id.relative_bottom);
			bottomLayout.setVisibility(View.GONE);
			LayoutParams params=new LayoutParams(500, LayoutParams.MATCH_PARENT);
			viewc.setLayoutParams(params);
			Rect scrollBounds = new Rect();
			//int i=(int) ((ViewGroup) layoutview).get;
			/*View focusedchild = ((ViewGroup) layoutview).getFocusedChild();
			int i=(int) focusedchild.getTag();*/
			// int IndexOfVisibleChild = 0;
			cutomHorizontalView.getDrawingRect(scrollBounds);
			int value = scrollBounds.right;
			int IndexOfVisibleChild=Util.calculateIndexOfchild(density, heightDp,value);
			
			
			((ViewGroup) layoutview).addView(viewc, IndexOfVisibleChild);
			/*if(value>=1000&&value<1000)
			
			Rect childBounds = new Rect();      
			for (int i = 0; i < ((ViewGroup) layoutview).getChildCount(); i++) {
				((ViewGroup) layoutview).getChildAt(i).getHitRect(childBounds);
			    if(scrollBounds.contains(childBounds)) {
			        int IndexOfVisibleChild = i;
			       
			    }
			}*/
			//((ViewGroup) layoutview).addView(viewc, 1);
			 
           /*  owner.removeView(view);
             LinearLayout container = (LinearLayout) layoutview;
             ((ViewGroup) layoutview).addView(view);
             view.setVisibility(View.VISIBLE);
             if(container.getId()==R.id.linear_baseOfHorizontalScroll){
                 view.setOnTouchListener(null);
                 owner.setOnDragListener(null);
             }*/
			break;
		case DragEvent.ACTION_DRAG_ENDED:
			Log.d(LOGCAT, "Drag ended");
			if (dropEventNotHandled(dragevent)){
				view.setVisibility(View.VISIBLE);
			}
			break;
		default:
			break;
		}
		return true;
	}
	private boolean dropEventNotHandled(DragEvent dragEvent) {
		return !dragEvent.getResult();
	}
	@Override
	public boolean onTouch(View view, MotionEvent motionEvent) {
		if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
			DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
			view.startDrag(null, shadowBuilder, view, 0);
			view.setVisibility(View.INVISIBLE);
			return true;
		} else {
			return false;
		}
	}
	@Override
	public void UpdatehorizontalViews(ArrayList<Order> items) {
		//cutomHorizontalView.removeAllViews();
		adapter_newReceipt=new NewOrderReceiptLayout(context, items,"withDelete",OrderFragment.this);
		cutomHorizontalView.setAdapter(context, adapter_newReceipt);

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		raedyOrderIndividualReceipt.setVisibility(View.VISIBLE);
		startListAnimation();
		View orderreceipt_layout = inflater.inflate
				(R.layout.new_order_recipt_layout, null);
		RelativeLayout UpperPart_Layout = (RelativeLayout)orderreceipt_layout.findViewById(R.id.relative_upperPart);
		RelativeLayout bottomLayout = (RelativeLayout)orderreceipt_layout.findViewById(R.id.relative_bottom);
		bottomLayout.setVisibility(View.GONE);
		UpperPart_Layout.setBackgroundColor(getResources().getColor(R.color.purple_OrderStatus_selected));

		transparentwhite_relativeLayout = (RelativeLayout)orderreceipt_layout.
				findViewById(R.id.transparentwhite_orderReceipt);
		LayoutParams params=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
		if(density==213)
			params.topMargin=27;

		else
			params.topMargin=20;

		orderreceipt_layout.setLayoutParams(params);

		raedyOrderIndividualReceipt.addView(orderreceipt_layout);
		
	}


}
