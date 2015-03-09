package in.websnoox.tappTablet.notificationFragment;

import in.websnoox.tappTablet.R;
import in.websnoox.tappTablet.entity.Order;
import in.websnoox.tappTablet.wheelwidget.WheelView;
import in.websnoox.tappTablet.wheelwidget.Adapters.NumericWheelAdapter;

import java.util.ArrayList;
import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class NotificationFragment extends Fragment implements OnClickListener,
OnItemClickListener,UpdatePagerAdapter{
	private LayoutInflater inflater;
	private FrameLayout notificationBase_framelayout;
	private View notificationReceiptWithdelete;
	private TextView accept_linear;
	private TextView reject_linear;
	private View select_deliverytimeLAyout;
	private View select_reasonLAyout;
	private FrameLayout rightSideContainer;
	private View horizontalScroll;
	private LinearLayout mygallery;
	Context context;
	private View notification_orderreceipt_layout;
	ArrayList<Order> newOrderItemList;
	NewOrdersAdapter adapter_newOrders;
	private ListView list_newOrders;
	private LinearLayout scrollUpButton;
	private LinearLayout scrollDownButton;
	private View notificationLAyoutToDeleteOrders;
	private TextView deleteNewOrdersButton;
	private LinearLayout baseNewOrderView;
	private View individualNewOrderView;
	private ListView firstListViewPager;
	private ListView secondListViewPager;
	private ViewPager viewPager;
	private MyPagerAdapter myPagerAdapter;
	private LinearLayout scrollLeftButton;
	private LinearLayout scrollRightButton;
	private TextView deleteNewOrders;
	private TextView CancelDeletePopup;
	private NewOrdersAdapter 		adapter_newOrderPager;
	private LinearLayout deliveryTimeFirst;
	private LinearLayout deliveryTimeSecond;
	private LinearLayout deliveryTimeThird;
	private LinearLayout deliveryTimeFourth;
	private LinearLayout deliveryTimeFifth;
	private LinearLayout deliveryTimeSixth;
	private RelativeLayout topHeader_OrderReceipt;
	private TextView select_alldeleteNewOrders;
	static int x=5;
	static int y=15;
	//private NewOrdersAdapter adapter_newOrdersPager;
	@Override
	public View onCreateView(final LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {

		final View view = inflater.inflate(R.layout.notification_fragment_base,
				container, false);

		this.inflater = inflater;

		// this.itemAddOn = itemAddOn;
		initView(view);


		return view;
	}
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		this.context=activity;
	}
	private void initView(View view) {
		notificationBase_framelayout=(FrameLayout)view.findViewById(R.id.notificationBase_framelayout);
		notificationReceiptWithdelete = inflater.inflate(R.layout.notification_with_delete_layout, null);
		notificationLAyoutToDeleteOrders = inflater.inflate(R.layout.notification_with_all_button_layout, null);
		notification_orderreceipt_layout= inflater.inflate(R.layout.new_order_recipt_layout, null);

		notificationBase_framelayout.addView(notificationReceiptWithdelete);
		rightSideContainer=(FrameLayout)notificationReceiptWithdelete
				.findViewById(R.id.container_for_orderReceiptNotificationBase);
		deleteNewOrdersButton=(TextView)notificationReceiptWithdelete.
				findViewById(R.id.deleteNewOrdersLinear);
		LayoutParams ParamsorderitemPart = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		notification_orderreceipt_layout.setLayoutParams(ParamsorderitemPart);
		rightSideContainer.addView(notification_orderreceipt_layout);
		scrollUpButton=(LinearLayout)notificationReceiptWithdelete.
				findViewById(R.id.linear_scrollUpButton);
		scrollDownButton=(LinearLayout)notificationReceiptWithdelete.
				findViewById(R.id.linear_scrollDownButton);
		scrollLeftButton=(LinearLayout)notificationLAyoutToDeleteOrders.
				findViewById(R.id.linear_scrollLeftButton);
		scrollRightButton=(LinearLayout)notificationLAyoutToDeleteOrders.
				findViewById(R.id.linear_scrollRightButton);
		deleteNewOrders=(TextView)notificationLAyoutToDeleteOrders.
				findViewById(R.id.linear_deleteButton);
		CancelDeletePopup=(TextView)notificationLAyoutToDeleteOrders.
				findViewById(R.id.linear_CancelButton);
		
		select_alldeleteNewOrders=(TextView)notificationLAyoutToDeleteOrders.
		findViewById(R.id.alldeleteNewOrders);
		list_newOrders=(ListView)notificationReceiptWithdelete.
				findViewById(R.id.list_newOrders_notificationBaseWithDelete);
		accept_linear=(TextView)notificationReceiptWithdelete.findViewById(R.id.accept_linear);
		reject_linear=(TextView)notificationReceiptWithdelete.findViewById(R.id.reject_linear);
		newOrderItemList=new ArrayList<Order>();
		for(int i=0;i<5;i++){
			Order newOrder=new Order();
			newOrder.orderId=100+i;
			newOrder.Username="Bhawna";
			newOrder.OrderTime="09:15";
			newOrder.isSelectedToDelete=false;
			newOrderItemList.add(newOrder);
		}
		adapter_newOrders=new NewOrdersAdapter(context, newOrderItemList,"withDelete");
		list_newOrders.setAdapter(adapter_newOrders);
		list_newOrders.setOnItemClickListener(this);

		accept_linear.setOnClickListener(this);
		reject_linear.setOnClickListener(this);
		scrollUpButton.setOnClickListener(this);
		scrollDownButton.setOnClickListener(this);
		scrollLeftButton.setOnClickListener(this);
		scrollRightButton.setOnClickListener(this);
		deleteNewOrdersButton.setOnClickListener(this);
		deleteNewOrders.setOnClickListener(this);
		CancelDeletePopup.setOnClickListener(this);
		select_alldeleteNewOrders.setOnClickListener(this);
	}
	@SuppressLint("NewApi")
	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.accept_linear:
			inflateSelectDeliveryTime();

			break;
		case R.id.reject_linear:
			inflateSelectReason();

			break;
		case R.id.linear_scrollDownButton:
			if(x<newOrderItemList.size()){
				list_newOrders.smoothScrollToPositionFromTop(x, y);
				x=x+5;
			}

			break;
		case R.id.linear_scrollUpButton:
			
			if(x>0){
				list_newOrders.smoothScrollToPosition(x-5);
				x=x-5;
			}

			break;
		case R.id.linear_scrollLeftButton:
			viewPager.setCurrentItem(getItem(-1), true);

			break;
		case R.id.linear_scrollRightButton:
			viewPager.setCurrentItem(getItem(+1), true);


			break;

		case R.id.deleteNewOrdersLinear:
			notificationBase_framelayout.removeView(notificationReceiptWithdelete);
			notificationBase_framelayout.addView(notificationLAyoutToDeleteOrders);
			viewPager = (ViewPager)notificationLAyoutToDeleteOrders.findViewById(R.id.myviewpager);
			myPagerAdapter = new MyPagerAdapter(context,newOrderItemList,NotificationFragment.this);
			viewPager.setAdapter(myPagerAdapter);
			
			break;

		case R.id.linear_deleteButton:

			if(newOrderItemList.size()>0){
				for(int i=newOrderItemList.size()-1;i>=0;i--)
				{
					if(newOrderItemList.get(i).isSelectedToDelete){
						newOrderItemList.remove(i);
						myPagerAdapter.notifyAdapter();
						//adapter_newOrdersPager.notifyDataSetChanged();
						viewPager.setAdapter(myPagerAdapter);
					}
				}
			}

			break;
		case R.id.linear_CancelButton:
			notificationBase_framelayout.removeView(notificationLAyoutToDeleteOrders);
			notificationBase_framelayout.addView(notificationReceiptWithdelete);
			break;

		case R.id.deliveryTimeFirst:
			break;
		case R.id.deliveryTimeSecond:
			break;
		case R.id.deliveryTimeThird:
			break;
		case R.id.deliveryTimeFourth:
			break;
		case R.id.deliveryTimeFifth:
			break;
		case R.id.deliveryTimeSixth:
			break;
			
		case R.id.alldeleteNewOrders:
			
			for(int i=0;i<newOrderItemList.size();i++){
				newOrderItemList.get(i).isSelectedToDelete=true;
				myPagerAdapter.notifyAdapter();
				//adapter_newOrdersPager.notifyDataSetChanged();
				viewPager.setAdapter(myPagerAdapter);
			}
			
			
			break;
		default:
			break;
		}

	}

	private void inflateSelectReason() {
		select_reasonLAyout = inflater.inflate(R.layout.select_reason, null);
		rightSideContainer.addView(select_reasonLAyout);
		TextView OkButton = (TextView)select_reasonLAyout.findViewById(R.id.okButton_selectReason);
		TextView cancelButton = (TextView)select_reasonLAyout.findViewById(R.id.cancelButton_selectReason);

		TextView tv_tooBusy = (TextView)select_reasonLAyout.findViewById(R.id.text_toobusy);
		TextView tv_toofar = (TextView)select_reasonLAyout.findViewById(R.id.text_toofar);
		TextView tv_toooutofstock = (TextView)select_reasonLAyout.findViewById(R.id.text_toooutofStock);

		tv_tooBusy.setOnClickListener(SelectReasonOnclick());
		tv_toofar.setOnClickListener(SelectReasonOnclick());
		tv_toooutofstock.setOnClickListener(SelectReasonOnclick());
		OkButton.setOnClickListener(SelectReasonOnclick());
		cancelButton.setOnClickListener(SelectReasonOnclick());

		
	}


	private OnClickListener SelectReasonOnclick() {
		// TODO Auto-generated method stub
		return new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				switch (arg0.getId()) {
				case R.id.text_toobusy:

					break;
				case R.id.text_toofar:

					break;
				case R.id.text_toooutofStock:

					break;
				case R.id.okButton_selectReason:
					rightSideContainer.removeView(select_reasonLAyout);
					break;
				case R.id.cancelButton_selectReason:
					rightSideContainer.removeView(select_reasonLAyout);
					break;

				default:
					break;
				}

			}
		};
	}
	private void inflateSelectDeliveryTime() {
		select_deliverytimeLAyout = inflater.inflate(R.layout.select_deliverytime, null);
		rightSideContainer.addView(select_deliverytimeLAyout);
		TextView OkButton = (TextView)select_deliverytimeLAyout.findViewById(R.id.linear_okbutton);
		TextView cancelButton = (TextView)select_deliverytimeLAyout.findViewById(R.id.linear_cancelButton);
		TimePickerMethod(context,Calendar.getInstance());
		OkButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				rightSideContainer.removeView(select_deliverytimeLAyout);
			}
		});
		cancelButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				rightSideContainer.removeView(select_deliverytimeLAyout);
			}
		});

		deliveryTimeFirst = (LinearLayout)select_deliverytimeLAyout.findViewById(R.id.deliveryTimeFirst);
		deliveryTimeSecond = (LinearLayout)select_deliverytimeLAyout.findViewById(R.id.deliveryTimeSecond);
		deliveryTimeThird = (LinearLayout)select_deliverytimeLAyout.findViewById(R.id.deliveryTimeThird);
		deliveryTimeFourth = (LinearLayout)select_deliverytimeLAyout.findViewById(R.id.deliveryTimeFourth);
		deliveryTimeFifth = (LinearLayout)select_deliverytimeLAyout.findViewById(R.id.deliveryTimeFifth);
		deliveryTimeSixth = (LinearLayout)select_deliverytimeLAyout.findViewById(R.id.deliveryTimeSixth);
		deliveryTimeFirst.setOnClickListener(this);
		deliveryTimeSecond.setOnClickListener(this);
		deliveryTimeThird.setOnClickListener(this);
		deliveryTimeFourth.setOnClickListener(this);
		deliveryTimeFifth.setOnClickListener(this);
		deliveryTimeSixth.setOnClickListener(this);


	}


	private void TimePickerMethod(final Context parentActivity, Calendar calendar ) {
		final WheelView hour = (WheelView)select_deliverytimeLAyout.
				findViewById(R.id.wheel_hour);
		final WheelView minute = (WheelView)select_deliverytimeLAyout. 
				findViewById(R.id.wheel_min);
		hour.setViewAdapter(new DateNumericAdapter(parentActivity,1,24,calendar.get(Calendar.HOUR)-1));
		hour.setCurrentItem((calendar.get(Calendar.HOUR)-1));
		hour.setCyclic(true);

		minute.setViewAdapter(new DateNumericAdapter(parentActivity,1,60,calendar.get(Calendar.MINUTE)-1));
		minute.setCurrentItem((calendar.get(Calendar.MINUTE)-1));
		minute.setCyclic(true);
	}




	class DateNumericAdapter extends NumericWheelAdapter {
		int currentItem;
		int currentValue;
		public DateNumericAdapter(Context context, int minValue, int maxValue, int current) {
			super(context, minValue, maxValue);
			this.currentValue = current;
			setTextSize(35);
		}

		@Override
		protected void configureTextView(TextView view) {
			super.configureTextView(view);
			if (currentItem == currentValue) {
				view.setTextColor(getResources().getColor(R.color.blue_dark_whell));
			}
		}

		public View getItem(int index, View cachedView, ViewGroup parent) {
			currentItem = index;
			return super.getItem(index, cachedView, parent);
		}
	}
	
	
	private int getItem(int i) {
		return viewPager.getCurrentItem() + i;
	}
	

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

		adapter_newOrders.setClickedChildPosition(arg2);
		adapter_newOrders.notifyDataSetChanged();

	}
	@Override
	public void updatePager() {
		viewPager.setAdapter(myPagerAdapter);
		
	}
	

}
