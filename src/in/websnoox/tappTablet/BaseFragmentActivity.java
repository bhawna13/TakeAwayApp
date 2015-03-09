package in.websnoox.tappTablet;

import in.websnoox.tappTablet.constants.StaticConstants;
import in.websnoox.tappTablet.dataReport.DRBaseFragment;
import in.websnoox.tappTablet.dataReport.DRDateFragment;
import in.websnoox.tappTablet.dataReport.DROrderFragment;
import in.websnoox.tappTablet.dataReport.DRSearchFragment;
import in.websnoox.tappTablet.dataReport.DRTabFragment;
import in.websnoox.tappTablet.dataReport.DRTimeFragment;
import in.websnoox.tappTablet.dataReport.DeleteDialog;
import in.websnoox.tappTablet.dataReport.ICallBackDelete;
import in.websnoox.tappTablet.dataReport.entity.DataReportSelectionItem;
import in.websnoox.tappTablet.entity.Order;
import in.websnoox.tappTablet.mainMenuFragment.MainMenuFragment;
import in.websnoox.tappTablet.notificationFragment.NotificationFragment;
import in.websnoox.tappTablet.orderFragment.OrderFragment;
import in.websnoox.tappTablet.ui.CustomTextView;
import in.websnoox.tappTablet.util.Util;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BaseFragmentActivity extends FragmentActivity implements
		OnClickListener {

	private LinearLayout notificationButton;
	private RelativeLayout transParentLayout;
	private LayoutInflater inflater;
	private PopupWindow popupWindow;
	private boolean doubleBackToExitPressedOnce;
	private LinearLayout HomeButtonButton;
	private RelativeLayout orderStatusNuttonLayout;
	private LinearLayout VoidButton;
	private LinearLayout ReadyButton;
	private LinearLayout ReprintButton;
	private LinearLayout DoneButton;
	private LinearLayout statusConfirmationLayout;
	private LinearLayout topHEader;
	private LinearLayout logout_topActionBAr;
	private TextView tv_versionNumber;
	private ImageView img_gridView;
	private ImageView img_searchView;

	OnBackPressListener currentBackListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.fragment_main);
		initView();
	}

	private void initView() {

		topHEader = (LinearLayout) findViewById(R.id.topActionBar);
		notificationButton = (LinearLayout) findViewById(R.id.notification_topActionBAr);
		HomeButtonButton = (LinearLayout) findViewById(R.id.home_topActionBAr);

		logout_topActionBAr = (LinearLayout) findViewById(R.id.logout_topActionBAr);

		tv_versionNumber = (TextView) findViewById(R.id.tv_versionNumber);
		img_gridView = (ImageView) findViewById(R.id.img_gridView);
		img_searchView = (ImageView) findViewById(R.id.img_searchView);
		orderStatusNuttonLayout = (RelativeLayout) findViewById(R.id.relative_orderRelatedButtons);
		transParentLayout = (RelativeLayout) findViewById(R.id.transparent_layout);
		statusConfirmationLayout = (LinearLayout) findViewById(R.id.linear_statusConfirmation);
		VoidButton = (LinearLayout) findViewById(R.id.VoidButtonOrderfragment);
		ReadyButton = (LinearLayout) findViewById(R.id.ReadyButtonOrderfragment);
		ReprintButton = (LinearLayout) findViewById(R.id.ReprintButtonOrderfragment);
		DoneButton = (LinearLayout) findViewById(R.id.DoneButtonOrderfragment);
		inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		Selectfragment(0);
		notificationButton.setOnClickListener(this);
		transParentLayout.setOnClickListener(this);
		HomeButtonButton.setOnClickListener(this);
		VoidButton.setOnClickListener(this);
		ReadyButton.setOnClickListener(this);
		ReprintButton.setOnClickListener(this);
		DoneButton.setOnClickListener(this);
	}

	@Override
	public void onBackPressed() {
		if (currentBackListener != null
				&& currentBackListener.onBackPressed() == true) {
			return;
		} else
			super.onBackPressed();

	}

	public void Selectfragment(int tag) {
		switch (tag) {
		case 0:
			OnMainMenuFragmentClick();

			break;

		default:
			break;
		}
	}

	private void OnMainMenuFragmentClick() {

		orderStatusNuttonLayout.setVisibility(View.INVISIBLE);
		topHEader.setBackgroundColor(getResources().getColor(
				R.color.grey_topHeader_mainMEnu));
		HomeButtonButton.setBackgroundColor(getResources().getColor(
				R.color.grey_topHeader_individual));
		notificationButton.setBackgroundColor(getResources().getColor(
				R.color.grey_topHeader_individual));
		logout_topActionBAr.setBackgroundColor(getResources().getColor(
				R.color.grey_topHeader_individual));
		tv_versionNumber.setVisibility(View.VISIBLE);
		img_gridView.setVisibility(View.INVISIBLE);
		img_searchView.setVisibility(View.INVISIBLE);
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		MainMenuFragment mainMenu = new MainMenuFragment();
		ft.replace(R.id.base_container, mainMenu, "MainMenu");
		ft.commit();
		
		currentBackListener = mainMenu;

	}

	public void OnTakeAwayOrderFragmentClick() {
		orderStatusNuttonLayout.setVisibility(View.INVISIBLE);
		topHEader.setBackgroundColor(getResources().getColor(
				R.color.purple_topHeader_mainMEnu));
		HomeButtonButton.setBackgroundColor(getResources().getColor(
				R.color.purple_topHeader_mainMEnu));
		logout_topActionBAr.setBackgroundColor(getResources().getColor(
				R.color.purple_topHeader_mainMEnu));
		notificationButton.setBackgroundColor(getResources().getColor(
				R.color.purple_topHeader_mainMEnu));
		tv_versionNumber.setVisibility(View.INVISIBLE);
		img_gridView.setVisibility(View.VISIBLE);
		img_searchView.setVisibility(View.VISIBLE);
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		OrderFragment TakeAwayOrder = new OrderFragment();
		ft.replace(R.id.base_container, TakeAwayOrder, "TakeAwayOrder");
		ft.commit();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.notification_topActionBAr:

			OnNotificationFragmentClick();

			break;
		case R.id.home_topActionBAr:

			OnMainMenuFragmentClick();

			break;
		case R.id.transparent_layout:

			// popupWindow.dismiss();
			break;
		case R.id.VoidButtonOrderfragment:

			transParentLayout.setVisibility(View.VISIBLE);
			statusConfirmationLayout.setVisibility(View.VISIBLE);
			inflateOrderReadyPopup("Void");

			break;
		case R.id.ReadyButtonOrderfragment:
			transParentLayout.setVisibility(View.VISIBLE);
			statusConfirmationLayout.setVisibility(View.VISIBLE);
			inflateOrderReadyPopup("Ready");
			break;
		case R.id.ReprintButtonOrderfragment:
			break;
		case R.id.DoneButtonOrderfragment:
			transParentLayout.setVisibility(View.VISIBLE);
			statusConfirmationLayout.setVisibility(View.VISIBLE);
			inflateOrderReadyPopup("Completed");
			break;

		default:
			break;
		}

	}

	private void inflateOrderReadyPopup(final String whichButton) {

		final View voidOrderView = inflater.inflate(
				R.layout.popup_order_reday_horizontal, null);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		voidOrderView.setLayoutParams(params);
		statusConfirmationLayout.addView(voidOrderView);

		Button yes_voidOrder = (Button) voidOrderView
				.findViewById(R.id.yes_Orderready);
		Button No_voidOrder = (Button) voidOrderView
				.findViewById(R.id.No_Orderready);
		TextView orderStatus = (TextView) voidOrderView
				.findViewById(R.id.tv_orderStatus);
		ImageView img_Orderstatus = (ImageView) voidOrderView
				.findViewById(R.id.img_Orderstatus);
		if (whichButton.equalsIgnoreCase("Void")) {
			orderStatus.setText("Void this order ?");
			img_Orderstatus.setBackgroundDrawable(BaseFragmentActivity.this
					.getResources().getDrawable(R.drawable.void_small));

		}

		else if (whichButton.equalsIgnoreCase("Completed")) {
			orderStatus.setText("Order completed ?");
			img_Orderstatus.setBackgroundDrawable(BaseFragmentActivity.this
					.getResources().getDrawable(R.drawable.done_small));
		}

		else if (whichButton.equalsIgnoreCase("Ready")) {
			orderStatus.setText("Order ready ?");
			img_Orderstatus.setBackgroundDrawable(BaseFragmentActivity.this
					.getResources().getDrawable(R.drawable.ready_small));
		}

		yes_voidOrder.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (whichButton.equalsIgnoreCase("Void")
						&& whichButton.equalsIgnoreCase("Completed")) {
					statusConfirmationLayout.removeView(voidOrderView);
					statusConfirmationLayout.setVisibility(View.INVISIBLE);
					transParentLayout.setVisibility(View.INVISIBLE);
					orderStatusNuttonLayout.setVisibility(View.INVISIBLE);
				} else {
					orderStatusNuttonLayout.setVisibility(View.INVISIBLE);
					statusConfirmationLayout.removeView(voidOrderView);
					statusConfirmationLayout.setVisibility(View.INVISIBLE);
					transParentLayout.setVisibility(View.INVISIBLE);
					Order newItem = new Order();
					Util.readyOrdersList.add(newItem);
					OrderFragment orderfragment = (OrderFragment) getSupportFragmentManager()
							.findFragmentById(R.id.base_container);
					orderfragment.inflateReadyOrdersAdapter();
				}

			}
		});
		No_voidOrder.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				statusConfirmationLayout.removeView(voidOrderView);
				statusConfirmationLayout.setVisibility(View.INVISIBLE);
				transParentLayout.setVisibility(View.INVISIBLE);
				orderStatusNuttonLayout.setVisibility(View.INVISIBLE);
			}
		});

	}

	private void OnNotificationFragmentClick() {
		orderStatusNuttonLayout.setVisibility(View.INVISIBLE);
		topHEader.setBackgroundColor(getResources().getColor(
				R.color.grey_topHeader_mainMEnu));
		HomeButtonButton.setBackgroundColor(getResources().getColor(
				R.color.grey_topHeader_individual));
		notificationButton.setBackgroundColor(getResources().getColor(
				R.color.grey_topHeader_individual));
		logout_topActionBAr.setBackgroundColor(getResources().getColor(
				R.color.grey_topHeader_individual));
		tv_versionNumber.setVisibility(View.VISIBLE);
		img_gridView.setVisibility(View.INVISIBLE);
		img_searchView.setVisibility(View.INVISIBLE);
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		NotificationFragment notification = new NotificationFragment();
		ft.replace(R.id.base_container, notification, "Notification");
		ft.commit();
	}

	static String lasttag;
	private View lastview;

	public void OnIndiviDualReciptTwiceClick(View v) {

		View view = v;

		if (doubleBackToExitPressedOnce) {
			if (view.equals(lastview)) {
				transParentLayout.setVisibility(View.VISIBLE);
				inflatePopUpForOrderRecipt(v);
			}

		}

		this.doubleBackToExitPressedOnce = true;
		// Toast.makeText(this, "Please click BACK again to exit",
		// Toast.LENGTH_SHORT).show();

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				doubleBackToExitPressedOnce = false;
			}
		}, 1000);
		lastview = view;

	}

	private void inflatePopUpForOrderRecipt(View v) {
		// TODO Auto-generated method stub
		DisplayMetrics metrics = new DisplayMetrics();
		this.getWindowManager().getDefaultDisplay().getMetrics(metrics);

		View popupView = this.inflater.inflate(
				R.layout.individual_order_status_screen, null);

		int density = metrics.densityDpi;
		int height = 550;
		float scaleFactor = metrics.density;
		int widthPixels = metrics.widthPixels;
		int heightPixels = metrics.heightPixels;
		float widthDp = widthPixels / scaleFactor;
		float heightDp = heightPixels / scaleFactor;

		float smallestWidth = Math.min(widthDp, heightDp);

		/*
		 * if (density == 213) { height = 670; } if (smallestWidth > 720) {
		 * height = 550; } if (density >= 290) { height = 750; }
		 */
		final LinearLayout orderReadyLayout = (LinearLayout) popupView
				.findViewById(R.id.orderReadyLinearLAyout);
		RelativeLayout paidUnpaid = (RelativeLayout) popupView
				.findViewById(R.id.relative_paidUnpaid);
		paidUnpaid.setVisibility(View.VISIBLE);

		LinearLayout voidOrderLayout = (LinearLayout) popupView
				.findViewById(R.id.linear_voidOrder);
		LinearLayout readyOrderLayout = (LinearLayout) popupView
				.findViewById(R.id.linear_ReadyOrder);
		LinearLayout completedOrderLayout = (LinearLayout) popupView
				.findViewById(R.id.linear_completedOrder);

		RelativeLayout orderReceipt_topheader = (RelativeLayout) popupView
				.findViewById(R.id.orderReceipt_topheader);
		RelativeLayout orderReceipt_relativeBottom = (RelativeLayout) popupView
				.findViewById(R.id.relative_bottom);
		orderReceipt_relativeBottom.setVisibility(View.GONE);
		orderReceipt_topheader.setVisibility(View.GONE);
		orderReadyLayout.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				transParentLayout.setVisibility(View.INVISIBLE);
				popupWindow.dismiss();
			}
		});

		voidOrderLayout.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				orderReadyLayout.setVisibility(View.VISIBLE);
				View voidOrderView = inflater.inflate(
						R.layout.void_order_popup, null);
				LayoutParams params = new LayoutParams(
						LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
				voidOrderView.setLayoutParams(params);
				orderReadyLayout.addView(voidOrderView);

				getVoidOrderIds(voidOrderView, orderReadyLayout);
			}
		});

		readyOrderLayout.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				orderReadyLayout.setVisibility(View.VISIBLE);
				orderReadyLayout.removeAllViews();
				View readyOrderView = inflater.inflate(
						R.layout.order_ready_popup, null);
				LayoutParams params = new LayoutParams(
						LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
				readyOrderView.setLayoutParams(params);
				orderReadyLayout.addView(readyOrderView);

				getReadyOrderIds(readyOrderView, orderReadyLayout);
			}
		});

		completedOrderLayout.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				popupWindow.dismiss();
				transParentLayout.setVisibility(View.INVISIBLE);

				OrderFragment orderfragment = (OrderFragment) getSupportFragmentManager()
						.findFragmentById(R.id.base_container);
				orderfragment.inflateCalculator(arg0);

			}
		});

		if (density == 213) {
			popupWindow = new PopupWindow(popupView, LayoutParams.MATCH_PARENT,
					670);
		} else if (density == 160) {
			if (heightDp == 600) {
				popupWindow = new PopupWindow(popupView,
						LayoutParams.MATCH_PARENT, 570);
			} else {
				popupWindow = new PopupWindow(popupView,
						LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
			}

		} else {
			popupWindow = new PopupWindow(popupView, LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT);
		}

		popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);

		popupWindow.setFocusable(true);

		popupWindow.setOutsideTouchable(true);
		popupWindow.setBackgroundDrawable(new BitmapDrawable());
		// popupWindow.update();
	}

	protected void getcompletedOrderIds(View completedOrderView,
			final LinearLayout orderReadyLayout) {
		Button yes_voidOrder = (Button) completedOrderView
				.findViewById(R.id.yes_Ordercompleted);
		Button No_voidOrder = (Button) completedOrderView
				.findViewById(R.id.No_Ordercompleted);

		yes_voidOrder.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				orderReadyLayout.setVisibility(View.INVISIBLE);
				transParentLayout.setVisibility(View.INVISIBLE);
				popupWindow.dismiss();
				Order newItem = new Order();
				Util.readyOrdersList.add(newItem);
				OrderFragment orderfragment = (OrderFragment) getSupportFragmentManager()
						.findFragmentById(R.id.base_container);
				orderfragment.inflateReadyOrdersAdapter();
			}
		});
		No_voidOrder.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				orderReadyLayout.setVisibility(View.INVISIBLE);
			}
		});

	}

	protected void getReadyOrderIds(View readyOrderView,
			final LinearLayout orderReadyLayout) {
		Button yes_voidOrder = (Button) readyOrderView
				.findViewById(R.id.yes_Orderready);
		Button No_voidOrder = (Button) readyOrderView
				.findViewById(R.id.No_Orderready);

		yes_voidOrder.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				orderReadyLayout.setVisibility(View.INVISIBLE);
				transParentLayout.setVisibility(View.INVISIBLE);
				popupWindow.dismiss();
				Order newItem = new Order();
				Util.readyOrdersList.add(newItem);
				OrderFragment orderfragment = (OrderFragment) getSupportFragmentManager()
						.findFragmentById(R.id.base_container);
				orderfragment.inflateReadyOrdersAdapter();
			}
		});
		No_voidOrder.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				orderReadyLayout.setVisibility(View.INVISIBLE);
			}
		});

	}

	protected void getVoidOrderIds(View voidOrderView,
			final LinearLayout orderReadyLayout) {
		Button yes_voidOrder = (Button) voidOrderView
				.findViewById(R.id.yes_voidOrder);
		Button No_voidOrder = (Button) voidOrderView
				.findViewById(R.id.No_voidOrder);

		yes_voidOrder.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				orderReadyLayout.setVisibility(View.INVISIBLE);
				popupWindow.dismiss();
				transParentLayout.setVisibility(View.INVISIBLE);
			}
		});
		No_voidOrder.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				orderReadyLayout.setVisibility(View.INVISIBLE);
				// transParentLayout.setVisibility(View.INVISIBLE);

			}
		});

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		transParentLayout.setVisibility(View.INVISIBLE);
		return true;
	}

	public void HighLiGhtButtons(String whichButton) {

		orderStatusNuttonLayout.setVisibility(View.VISIBLE);
		if (whichButton.equalsIgnoreCase("Void")) {
			VoidButton.setBackgroundColor(getResources().getColor(
					R.color.purple_OrderStatus_selected));
			VoidButton.setVisibility(View.VISIBLE);
			ReadyButton.setVisibility(View.INVISIBLE);
			ReprintButton.setVisibility(View.INVISIBLE);
			DoneButton.setVisibility(View.INVISIBLE);
		}
		if (whichButton.equalsIgnoreCase("Ready")) {
			ReadyButton.setBackgroundColor(getResources().getColor(
					R.color.purple_OrderStatus_selected));
			VoidButton.setVisibility(View.INVISIBLE);
			ReadyButton.setVisibility(View.VISIBLE);
			ReprintButton.setVisibility(View.INVISIBLE);
			DoneButton.setVisibility(View.INVISIBLE);
		}
		if (whichButton.equalsIgnoreCase("Reprint")) {
			ReprintButton.setBackgroundColor(getResources().getColor(
					R.color.purple_OrderStatus_selected));
			VoidButton.setVisibility(View.INVISIBLE);
			ReadyButton.setVisibility(View.INVISIBLE);
			ReprintButton.setVisibility(View.VISIBLE);
			DoneButton.setVisibility(View.INVISIBLE);
		}
		if (whichButton.equalsIgnoreCase("Done")) {
			DoneButton.setBackgroundColor(getResources().getColor(
					R.color.purple_OrderStatus_selected));
			VoidButton.setVisibility(View.INVISIBLE);
			ReadyButton.setVisibility(View.INVISIBLE);
			ReprintButton.setVisibility(View.INVISIBLE);
			DoneButton.setVisibility(View.VISIBLE);
		}

	}

	public void DRTabFragment() {

		topHEader.setBackgroundColor(getResources().getColor(
				R.color.green_dr_tab));
		HomeButtonButton.setBackgroundColor(getResources().getColor(
				R.color.green_dr_tab));
		notificationButton.setBackgroundColor(getResources().getColor(
				R.color.green_dr_tab));
		logout_topActionBAr.setBackgroundColor(getResources().getColor(
				R.color.green_dr_tab));
		tv_versionNumber.setVisibility(View.VISIBLE);
		img_gridView.setVisibility(View.INVISIBLE);
		img_searchView.setVisibility(View.INVISIBLE);

		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		DRTabFragment dataReportFragment = new DRTabFragment();
		ft.replace(R.id.base_container, dataReportFragment,
				StaticConstants.DATA_REPORT_TAB_TAG);
		ft.commit();
		
		currentBackListener = dataReportFragment;

	}

	public void DRBaseFragment(Bundle bundle) {

		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		DRBaseFragment dataReportFragment = new DRBaseFragment();
		dataReportFragment.setArguments(bundle);
		ft.replace(R.id.base_container, dataReportFragment,
				StaticConstants.DATA_REPORT_BASE_TAG);
		ft.commit();
		currentBackListener = dataReportFragment;
	}

	private void onDataReportDelete() {
		Fragment fragment = getSupportFragmentManager().findFragmentById(
				R.id.base_container);
		if (fragment.getTag().equalsIgnoreCase(
				StaticConstants.DATA_REPORT_BASE_TAG)) {

			DRBaseFragment baseFragment = (DRBaseFragment) getSupportFragmentManager()
					.findFragmentById(R.id.base_container);
			baseFragment.showListSelection();
		}

	}

	private void onDataReportDeleteOk() {

		Fragment fragment = getSupportFragmentManager().findFragmentById(
				R.id.base_container);
		if (fragment.getTag().equalsIgnoreCase(
				StaticConstants.DATA_REPORT_BASE_TAG)) {

			DRBaseFragment baseFragment = (DRBaseFragment) getSupportFragmentManager()
					.findFragmentById(R.id.base_container);
			baseFragment.showListSelectionOk();
		}

	}

	private void onDataReportDeleteCancel() {

		Fragment fragment = getSupportFragmentManager().findFragmentById(
				R.id.base_container);
		if (fragment.getTag().equalsIgnoreCase(
				StaticConstants.DATA_REPORT_BASE_TAG)) {

			DRBaseFragment baseFragment = (DRBaseFragment) getSupportFragmentManager()
					.findFragmentById(R.id.base_container);
			baseFragment.showListSelectionNo();
		}

	}

	public void oncreateDataReportTimeFragment(Bundle bundle) {

		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		DRTimeFragment dataReportFragment = new DRTimeFragment();
		dataReportFragment.setArguments(bundle);
		ft.replace(R.id.base_container, dataReportFragment,
				StaticConstants.DATA_REPORT_TIME_TAG);
		ft.commit();

	}

	public void oncreateDataReportDateFragment() {

		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		DRDateFragment dataReportFragment = new DRDateFragment();
		ft.replace(R.id.base_container, dataReportFragment,
				StaticConstants.DATA_REPORT_DATE_TAG);
		ft.commit();

	}

	public void oncreateDataReprotSearchFragment() {

		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		DRSearchFragment dataReportFragment = new DRSearchFragment();
		ft.replace(R.id.base_container, dataReportFragment,
				StaticConstants.DATA_REPORT_SEARCH_TAG);
		ft.commit();

	}

	public void oncreateDataReportOrderFragment() {
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		DROrderFragment orderFragment = new DROrderFragment();
		ft.replace(R.id.base_container, orderFragment,
				StaticConstants.DATA_REPORT_DATE_TAG);
		ft.commit();
	}

}
