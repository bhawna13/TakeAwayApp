package in.websnoox.tappTablet.dataReport;

import in.websnoox.tappTablet.BaseFragmentActivity;
import in.websnoox.tappTablet.OnBackPressListener;
import in.websnoox.tappTablet.R;
import in.websnoox.tappTablet.constants.StaticConstants;
import in.websnoox.tappTablet.dataReport.adapter.DataReportAllAdapter;
import in.websnoox.tappTablet.dataReport.adapter.DataReportItemisedAdapter;
import in.websnoox.tappTablet.dataReport.entity.DataReportAllItem;
import in.websnoox.tappTablet.dataReport.entity.DataReportSelectionItem;
import in.websnoox.tappTablet.dataReport.entity.ItemisedDataReportItem;
import in.websnoox.tappTablet.ui.CustomListView;
import in.websnoox.tappTablet.ui.CustomListView.OnItemDoubleTapLister;
import in.websnoox.tappTablet.ui.CustomTextView;
import in.websnoox.tappTablet.util.Logger;
import in.websnoox.tappTablet.util.Util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class DRBaseFragment extends Fragment implements OnClickListener,
		OnBackPressListener, ICallBackDelete {

	private Context context;
	View view;

	ArrayList<DataReportAllItem> reportAllItems;
	DataReportAllAdapter<DataReportAllItem> allAdapter;
	DataReportItemisedAdapter<ItemisedDataReportItem> itemisedAdapter;
	CustomListView listViewAll;
	CustomTextView txtItemsCount, txtItemTotal;

	public int KEY_DATA_REPORT_ARG = -1;
	public boolean KEY_DATA_REPORT_SEARCH_ARG = false;
	public String KEY_START_TIME = "";
	public String KEY_END_TIME = "";

	private LinearLayout ll_dr_all_header, ll_dr_PaymentType_header,
			ll_dr_OrderType_header, ll_dr_itemised_header;

	private CustomTextView dr_PTCard, dr_PTCash, dr_PTPaypal;

	private CustomTextView dr_OTDelivery, dr_OTCollection;

	private CustomTextView txtTime_dr, dr_TimeHeader;

	private RelativeLayout rel_report_layout;
	private FrameLayout frameLayout_dr;

	private LinearLayout topDrBar;

	/**
	 * DataReport Fragment HeaderPart
	 */
	private CustomTextView dr_print, dr_delete, dr_export, dr_spinner, dr_back,
			txt_defaultTime_dr, txt_SearchTime_dr;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		KEY_DATA_REPORT_ARG = getArguments().getInt(
				StaticConstants.KEY_DATA_REPORT_ARG);
		KEY_DATA_REPORT_SEARCH_ARG = getArguments().getBoolean(
				StaticConstants.KEY_DATA_REPORT_SERCH_ARG);
		KEY_START_TIME = getArguments().getString(
				StaticConstants.KEY_START_TIME);
		KEY_END_TIME = getArguments().getString(StaticConstants.KEY_END_TIME);

		view = inflater.inflate(R.layout.data_report_base_fragment, container,
				false);

		init(view);

		return view;
	}

	private void init(View v) {

		rel_report_layout = (RelativeLayout) v
				.findViewById(R.id.rel_report_layout);
		frameLayout_dr = (FrameLayout) v.findViewById(R.id.frameLayout_dr);

		// dataReportHeader
		topDrBar = (LinearLayout) v.findViewById(R.id.topDrBar);
		dr_spinner = (CustomTextView) v.findViewById(R.id.dr_spinner);
		dr_spinner.setOnClickListener(this);

		dr_back = (CustomTextView) v.findViewById(R.id.dr_back);
		dr_back.setOnClickListener(this);

		dr_print = (CustomTextView) v.findViewById(R.id.dr_print);
		dr_print.setOnClickListener(this);
		dr_delete = (CustomTextView) v.findViewById(R.id.dr_delete);
		dr_delete.setOnClickListener(this);
		dr_export = (CustomTextView) v.findViewById(R.id.dr_export);
		dr_export.setOnClickListener(this);

		txt_SearchTime_dr = (CustomTextView) v
				.findViewById(R.id.txt_SearchTime_dr);
		txt_SearchTime_dr.setOnClickListener(this);
		txt_defaultTime_dr = (CustomTextView) v
				.findViewById(R.id.txt_defaultTime_dr);

		dr_TimeHeader = (CustomTextView) v.findViewById(R.id.dr_TimeHeader);
		listViewAll = (CustomListView) v.findViewById(R.id.listView_dr);
		listViewAll.setOnItemDoubleClickListener(onItemDoubleTabListener());
		txtItemsCount = (CustomTextView) v.findViewById(R.id.txtItemsCount);
		txtItemTotal = (CustomTextView) v.findViewById(R.id.txtItemTotal);
		ll_dr_all_header = (LinearLayout) v.findViewById(R.id.ll_dr_all_header);
		ll_dr_PaymentType_header = (LinearLayout) v
				.findViewById(R.id.ll_dr_PaymentType_header);
		ll_dr_OrderType_header = (LinearLayout) v
				.findViewById(R.id.ll_dr_OrderType_header);
		ll_dr_itemised_header = (LinearLayout) v
				.findViewById(R.id.ll_dr_itemised_header);

		txtTime_dr = (CustomTextView) v.findViewById(R.id.txtTime_dr);
		txtTime_dr.setOnClickListener(this);

		dr_PTCard = (CustomTextView) v.findViewById(R.id.dr_PTCard);
		dr_PTCard.setOnClickListener(this);
		dr_PTCash = (CustomTextView) v.findViewById(R.id.dr_PTCash);
		dr_PTCash.setOnClickListener(this);
		dr_PTPaypal = (CustomTextView) v.findViewById(R.id.dr_PTPaypal);
		dr_PTPaypal.setOnClickListener(this);

		dr_OTCollection = (CustomTextView) v.findViewById(R.id.dr_OTCollection);
		dr_OTCollection.setOnClickListener(this);
		dr_OTDelivery = (CustomTextView) v.findViewById(R.id.dr_OTDelivery);
		dr_OTDelivery.setOnClickListener(this);

		new Runnable() {

			@Override
			public void run() {

				setAdapter();

			}
		}.run();

	}

	private OnItemDoubleTapLister onItemDoubleTabListener() {
		// TODO Auto-generated method stub
		return new OnItemDoubleTapLister() {

			@Override
			public void OnSingleTap(AdapterView<?> parent, View view,
					int position, long id) {
				/*
				 * Toast.makeText(context, "Single Tab", Toast.LENGTH_SHORT)
				 * .show();
				 */
			}

			@Override
			public void OnDoubleTap(AdapterView<?> parent, View view,
					int position, long id) {

				if (allAdapter != null) {
					allAdapter.doubleTabSelection(position);
					/*
					 * Toast.makeText(context, "double Tab", Toast.LENGTH_SHORT)
					 * .show();
					 */
					
					showOrderItem();

					/*((BaseFragmentActivity) context)
							.oncreateDataReportOrderFragment();*/
				}
			}
		};
	}

	public void setAdapter() {
		switch (KEY_DATA_REPORT_ARG) {
		case 0:
			showAllItem();
			break;
		case 1:
			showSalesOrder();
			break;
		case 2:
			showVoidOrder();
			break;
		case 3:
			showItemised();
			break;
		case 4:
			showPaymentTypeCard();
			break;
		case 5:
			showOrderTypeDelivery();
			break;

		default:
			break;
		}

		if (!(TextUtils.isEmpty(KEY_START_TIME) && TextUtils
				.isEmpty(KEY_END_TIME)))
			txtTime_dr.setText(KEY_START_TIME + " - " + KEY_END_TIME);
	}

	protected void showAllItem() {

		allAdapter = new DataReportAllAdapter<DataReportAllItem>(context);
		listViewAll.setAdapter(allAdapter);

		for (int i = 0; i < 10; i++) {

			DataReportAllItem dataItem = new DataReportAllItem("09:19",
					"12345", "14.31", "85.86", "Card", "Done", "Delivery");
			allAdapter.addDataSet(dataItem);
		}

		for (int i = 0; i < 5; i++) {
			DataReportAllItem dataItem = new DataReportAllItem("09:19",
					"12345", "14.31", "85.86", "Card", "Expired", "Collection");
			allAdapter.addDataSet(dataItem);
		}

		txtItemsCount.setText(getResources().getString(R.string.item_string)
				+ " " + allAdapter.getCount());
		txtItemTotal.setText(getResources().getString(R.string.total_string)
				+ " " + allAdapter.getTotalAmount());
	}

	public void showSalesOrder() {

		allAdapter = new DataReportAllAdapter<DataReportAllItem>(context);
		listViewAll.setAdapter(allAdapter);

		for (int i = 0; i < 3; i++) {

			DataReportAllItem dataItem = new DataReportAllItem("09:19",
					"12345", "14.31", "85.86", "Card", "Done", "Delivery");
			allAdapter.addDataSet(dataItem);
		}

		for (int i = 0; i < 3; i++) {

			DataReportAllItem dataItem = new DataReportAllItem("09:19",
					"12345", "14.31", "85.86", "Card", "Done", "Collection");
			allAdapter.addDataSet(dataItem);
		}

		txtItemsCount.setText(getResources().getString(R.string.item_string)
				+ " " + allAdapter.getCount());
		txtItemTotal.setText(getResources().getString(R.string.total_string)
				+ " " + allAdapter.getTotalAmount());

	}

	public void showVoidOrder() {

		allAdapter = new DataReportAllAdapter<DataReportAllItem>(context);
		listViewAll.setAdapter(allAdapter);

		for (int i = 0; i < 5; i++) {
			DataReportAllItem dataItem = new DataReportAllItem("09:19",
					"12345", "14.31", "85.86", "Card", "Expired", "Collection");
			allAdapter.addDataSet(dataItem);
		}

		for (int i = 0; i < 3; i++) {
			DataReportAllItem dataItem = new DataReportAllItem("09:19",
					"12345", "14.31", "85.86", "Card", "Void", "Collection");
			allAdapter.addDataSet(dataItem);
		}

		for (int i = 0; i < 3; i++) {
			DataReportAllItem dataItem = new DataReportAllItem("09:19",
					"12345", "14.31", "85.86", "Paypal", "Reject", "Collection");
			allAdapter.addDataSet(dataItem);
		}

		txtItemsCount.setText(getResources().getString(R.string.item_string)
				+ " " + allAdapter.getCount());
		txtItemTotal.setText(getResources().getString(R.string.total_string)
				+ " " + allAdapter.getTotalAmount());

	}

	public void showItemised() {

		itemisedAdapter = new DataReportItemisedAdapter<ItemisedDataReportItem>(
				context);
		listViewAll.setAdapter(itemisedAdapter);

		for (int i = 0; i < 5; i++) {
			ItemisedDataReportItem dataItem = new ItemisedDataReportItem(
					"Assorted Vegetable Don", "3", "85.86");
			itemisedAdapter.addDataSet(dataItem);
		}

		for (int i = 0; i < 3; i++) {
			ItemisedDataReportItem dataItem = new ItemisedDataReportItem(
					"Salmon Teriyaki Don", "4", "125.86");
			itemisedAdapter.addDataSet(dataItem);
		}

		txtItemTotal.setText(getResources().getString(R.string.total_string)
				+ " " + itemisedAdapter.getTotalAmount());
	}

	public void showPaymentTypeCard() {

		allAdapter = new DataReportAllAdapter<DataReportAllItem>(context);
		listViewAll.setAdapter(allAdapter);

		for (int i = 0; i < 10; i++) {

			DataReportAllItem dataItem = new DataReportAllItem("09:19",
					"12345", "14.31", "44.86", "Card", "Done", "Delivery");
			allAdapter.addDataSet(dataItem);
		}

		for (int i = 0; i < 5; i++) {
			DataReportAllItem dataItem = new DataReportAllItem("09:19",
					"12345", "14.31", "35.86", "Card", "Expired", "Collection");
			allAdapter.addDataSet(dataItem);
		}

		dr_PTCard.setSelected(true);
		dr_PTCash.setSelected(false);
		dr_PTPaypal.setSelected(false);

		txtItemsCount.setText(getResources().getString(R.string.item_string)
				+ " " + allAdapter.getCount());
		txtItemTotal.setText(getResources().getString(R.string.total_string)
				+ " " + allAdapter.getTotalAmount());

	}

	public void showPaymentTypeCash() {

		allAdapter = new DataReportAllAdapter<DataReportAllItem>(context);
		listViewAll.setAdapter(allAdapter);

		for (int i = 0; i < 10; i++) {

			DataReportAllItem dataItem = new DataReportAllItem("09:19",
					"12345", "14.31", "85.86", "Cash", "Reject", "Delivery");
			allAdapter.addDataSet(dataItem);
		}

		for (int i = 0; i < 5; i++) {
			DataReportAllItem dataItem = new DataReportAllItem("09:19",
					"12345", "14.31", "85.86", "Cash", "Expired", "Collection");
			allAdapter.addDataSet(dataItem);
		}

		dr_PTCash.setSelected(true);
		dr_PTCard.setSelected(false);
		dr_PTPaypal.setSelected(false);

		txtItemsCount.setText(getResources().getString(R.string.item_string)
				+ " " + allAdapter.getCount());
		txtItemTotal.setText(getResources().getString(R.string.total_string)
				+ " " + allAdapter.getTotalAmount());
	}

	public void showPaymentTypePaypal() {

		allAdapter = new DataReportAllAdapter<DataReportAllItem>(context);
		listViewAll.setAdapter(allAdapter);

		for (int i = 0; i < 10; i++) {

			DataReportAllItem dataItem = new DataReportAllItem("09:19",
					"12345", "14.31", "85.86", "Paypal", "Reject", "Delivery");
			allAdapter.addDataSet(dataItem);
		}

		for (int i = 0; i < 5; i++) {
			DataReportAllItem dataItem = new DataReportAllItem("09:19",
					"12345", "14.31", "85.86", "Paypal", "Void", "Collection");
			allAdapter.addDataSet(dataItem);
		}

		dr_PTPaypal.setSelected(true);
		dr_PTCash.setSelected(false);
		dr_PTCard.setSelected(false);

		txtItemsCount.setText(getResources().getString(R.string.item_string)
				+ " " + allAdapter.getCount());
		txtItemTotal.setText(getResources().getString(R.string.total_string)
				+ " " + allAdapter.getTotalAmount());
	}

	public void showOrderTypeDelivery() {

		allAdapter = new DataReportAllAdapter<DataReportAllItem>(context);
		listViewAll.setAdapter(allAdapter);

		for (int i = 0; i < 10; i++) {

			DataReportAllItem dataItem = new DataReportAllItem("09:19",
					"12345", "14.31", "85.86", "Paypal", "Reject", "Delivery");
			allAdapter.addDataSet(dataItem);
		}

		for (int i = 0; i < 5; i++) {
			DataReportAllItem dataItem = new DataReportAllItem("09:19",
					"12345", "14.31", "85.86", "Paypal", "Void", "Delivery");
			allAdapter.addDataSet(dataItem);
		}

		dr_OTDelivery.setSelected(true);
		dr_OTCollection.setSelected(false);

		txtItemsCount.setText(getResources().getString(R.string.item_string)
				+ " " + allAdapter.getCount());
		txtItemTotal.setText(getResources().getString(R.string.total_string)
				+ " " + allAdapter.getTotalAmount());
	}

	public void showOrderTypeCollection() {

		allAdapter = new DataReportAllAdapter<DataReportAllItem>(context);
		listViewAll.setAdapter(allAdapter);

		for (int i = 0; i < 10; i++) {

			DataReportAllItem dataItem = new DataReportAllItem("09:19",
					"12345", "14.31", "85.86", "Paypal", "Reject", "Collection");
			allAdapter.addDataSet(dataItem);
		}

		for (int i = 0; i < 5; i++) {
			DataReportAllItem dataItem = new DataReportAllItem("09:19",
					"12345", "14.31", "85.86", "Paypal", "Void", "Collection");
			allAdapter.addDataSet(dataItem);
		}

		dr_OTCollection.setSelected(true);
		dr_OTDelivery.setSelected(false);

		txtItemsCount.setText(getResources().getString(R.string.item_string)
				+ " " + allAdapter.getCount());
		txtItemTotal.setText(getResources().getString(R.string.total_string)
				+ " " + allAdapter.getTotalAmount());
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.context = activity;
	}

	public void insertDataReportAll() {

		int i;
		InputStream inputStream;
		String rawJSON = "";

		try {
			inputStream = getResources().openRawResource(R.raw.datareport);
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

			i = inputStream.read();
			while (i != -1) {
				byteArrayOutputStream.write(i);
				i = inputStream.read();
			}

			inputStream.close();
			rawJSON = byteArrayOutputStream.toString();
			Logger.i("rawJSON", "::" + rawJSON);

		} catch (IOException e) {
			e.printStackTrace();
		}

		JSONArray jsonArray;
		try {

			jsonArray = new JSONArray(rawJSON);

			int countOrder = jsonArray.length();

			for (int j = 0; j < countOrder; j++) {

				JSONArray jsonArray2 = jsonArray.getJSONArray(j);
				// jsonArray2.getString(StaticConstants.)
				// Logger.i("response", "response is...  " +
				// jsonArray.getJSONArray(j));
				// Order order = Util.getMobileOrder(context,
				// jsonArray.getJSONArray(j), true);

			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.dr_PTCard:
			showPaymentTypeCard();
			break;
		case R.id.dr_PTCash:
			showPaymentTypeCash();
			break;
		case R.id.dr_PTPaypal:
			showPaymentTypePaypal();
			break;
		case R.id.dr_OTCollection:
			showOrderTypeCollection();
			break;
		case R.id.dr_OTDelivery:
			showOrderTypeDelivery();
			break;

		case R.id.txtTime_dr:
			showTimeFragment();
			break;

		case R.id.dr_spinner:
			DRSelection(v);
			break;

		case R.id.dr_back:
			// DRTabFragment();
			onBackPressed();
			break;

		case R.id.dr_print:
			break;

		case R.id.dr_delete:
			if (!dr_delete.getText().toString().equalsIgnoreCase("ok")) {
				dr_delete.setText(getResources().getString(R.string.ok_string));
				showListSelection();
			} else {

				DeleteDialog dialog = new DeleteDialog(context,
						DRBaseFragment.this);
				dialog.show();
			}

			break;

		case R.id.dr_export:
			break;

		case R.id.txt_SearchTime_dr:
			showDateFragment();
			break;

		default:
			break;
		}

	}

	private void DRSelection(View v) {

		String[] ddlist = getResources()
				.getStringArray(R.array.datareport_list);

		DataReportSelectionItem[] item = new DataReportSelectionItem[ddlist.length];
		for (int i = 0; i < ddlist.length; i++) {
			DataReportSelectionItem entity = new DataReportSelectionItem(i,
					ddlist[i]);
			item[i] = entity;
		}

		Util.initiateDataReportPopUp(DRBaseFragment.this, item, v);

	}

	public void showListSelection() {
		if (KEY_DATA_REPORT_ARG != 3) {
			dr_TimeHeader.setVisibility(View.VISIBLE);
			allAdapter.showListSelection(true);
		}

	}

	public void showListSelectionOk() {
		if (KEY_DATA_REPORT_ARG != 3) {
			dr_TimeHeader.setVisibility(View.GONE);

			if (allAdapter != null) {
				allAdapter.removeSeletedItem();
				allAdapter.showListSelection(false);
				setdeleteButton();
			}
		}

	}

	public void showListSelectionNo() {

		if (KEY_DATA_REPORT_ARG != 3) {
			dr_TimeHeader.setVisibility(View.GONE);

			if (allAdapter != null) {

				allAdapter.showListSelection(true);
				int count = allAdapter.getCount();
				for (int i = 0; i < count; i++) {
					DataReportAllItem allItem = (DataReportAllItem) allAdapter
							.getItem(i);
					allItem.setSelected(false);
				}
				allAdapter.showListSelection(false);
				setdeleteButton();
			}
		}

	}

	public void setdeleteButton() {
		dr_delete.setText(getResources().getString(R.string.dr_Delete_string));
	}

	public void showTimeFragment() {

		rel_report_layout.setVisibility(View.GONE);
		frameLayout_dr.setVisibility(View.VISIBLE);
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		Bundle bundle = new Bundle();
		bundle.putInt(StaticConstants.KEY_DATA_REPORT_ARG, KEY_DATA_REPORT_ARG);
		bundle.putBoolean(StaticConstants.KEY_DATA_REPORT_SERCH_ARG,
				KEY_DATA_REPORT_SEARCH_ARG);
		DRTimeFragment dataReportFragment = new DRTimeFragment();
		dataReportFragment.setArguments(bundle);
		ft.replace(R.id.frameLayout_dr, dataReportFragment,
				StaticConstants.DATA_REPORT_TIME_TAG);
		ft.addToBackStack(null);
		ft.commit();

	}

	public void showDateFragment() {
		rel_report_layout.setVisibility(View.GONE);
		frameLayout_dr.setVisibility(View.VISIBLE);
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		Bundle bundle = new Bundle();
		bundle.putInt(StaticConstants.KEY_DATA_REPORT_ARG, KEY_DATA_REPORT_ARG);
		bundle.putBoolean(StaticConstants.KEY_DATA_REPORT_SERCH_ARG,
				KEY_DATA_REPORT_SEARCH_ARG);
		DRDateFragment dataReportFragment = new DRDateFragment();
		dataReportFragment.setArguments(bundle);
		ft.replace(R.id.frameLayout_dr, dataReportFragment,
				StaticConstants.DATA_REPORT_DATE_TAG);
		ft.commit();
	}

	@Override
	public boolean onBackPressed() {

		Fragment fragment = getFragmentManager().findFragmentById(R.id.frameLayout_dr);
			if (fragment != null && (fragment.equals(getFragmentManager().findFragmentByTag(StaticConstants.DATA_REPORT_TIME_TAG))
					|| fragment.equals(getFragmentManager().findFragmentByTag(StaticConstants.DATA_REPORT_DATE_TAG))
					|| fragment.equals(getFragmentManager().findFragmentByTag(StaticConstants.DATA_REPORT_ORDER_TAG)))) {
				
				rel_report_layout.setVisibility(View.VISIBLE);
				frameLayout_dr.setVisibility(View.GONE);
				fragment.getFragmentManager().popBackStack();
				return true;
				
			}else {
				
				((BaseFragmentActivity)context).DRTabFragment();
				return true;
			}
		
	}

	public void CurrentTimeBundle(Bundle bundle) {

		KEY_START_TIME = bundle.getString(StaticConstants.KEY_START_TIME);
		KEY_END_TIME = bundle.getString(StaticConstants.KEY_END_TIME);
		if (!(TextUtils.isEmpty(KEY_START_TIME) && TextUtils
				.isEmpty(KEY_END_TIME)))
			txtTime_dr.setText(KEY_START_TIME + " - " + KEY_END_TIME);

		Fragment fragment = getFragmentManager().findFragmentByTag(
				StaticConstants.DATA_REPORT_TIME_TAG);
		if (fragment != null && fragment.isVisible()) {
			rel_report_layout.setVisibility(View.VISIBLE);
			frameLayout_dr.setVisibility(View.GONE);
			fragment.getFragmentManager().popBackStack();
		}

	}

	public void setCurrentReport(int position) {
		KEY_DATA_REPORT_ARG = position;
		switch (KEY_DATA_REPORT_ARG) {
		case 0:
			ll_dr_all_header.setVisibility(View.VISIBLE);
			ll_dr_itemised_header.setVisibility(View.GONE);
			ll_dr_PaymentType_header.setVisibility(View.GONE);
			ll_dr_OrderType_header.setVisibility(View.GONE);
			showAllItem();
			break;
		case 1:
			ll_dr_all_header.setVisibility(View.VISIBLE);
			ll_dr_itemised_header.setVisibility(View.GONE);
			ll_dr_PaymentType_header.setVisibility(View.GONE);
			ll_dr_OrderType_header.setVisibility(View.GONE);
			showSalesOrder();
			break;
		case 2:
			ll_dr_all_header.setVisibility(View.VISIBLE);
			ll_dr_itemised_header.setVisibility(View.GONE);
			ll_dr_PaymentType_header.setVisibility(View.GONE);
			ll_dr_OrderType_header.setVisibility(View.GONE);
			showVoidOrder();
			break;
		case 3:
			ll_dr_all_header.setVisibility(View.GONE);
			ll_dr_itemised_header.setVisibility(View.VISIBLE);
			ll_dr_PaymentType_header.setVisibility(View.GONE);
			ll_dr_OrderType_header.setVisibility(View.GONE);
			showItemised();
			break;
		case 4:
			ll_dr_all_header.setVisibility(View.VISIBLE);
			ll_dr_itemised_header.setVisibility(View.GONE);
			ll_dr_PaymentType_header.setVisibility(View.VISIBLE);
			ll_dr_OrderType_header.setVisibility(View.GONE);
			showPaymentTypeCard();
			break;
		case 5:
			ll_dr_all_header.setVisibility(View.VISIBLE);
			ll_dr_itemised_header.setVisibility(View.GONE);
			ll_dr_PaymentType_header.setVisibility(View.GONE);
			ll_dr_OrderType_header.setVisibility(View.VISIBLE);
			showOrderTypeDelivery();
			break;

		default:
			break;
		}

	}

	@Override
	public void deleteRows(boolean canDelete) {
		if (canDelete)
			showListSelectionOk();
		else
			showListSelectionNo();
	}

	public void showOrderItem() {
		rel_report_layout.setVisibility(View.GONE);
		frameLayout_dr.setVisibility(View.VISIBLE);
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		DROrderFragment dataReportFragment = new DROrderFragment();
		ft.replace(R.id.frameLayout_dr, dataReportFragment,
				StaticConstants.DATA_REPORT_ORDER_TAG);
		ft.addToBackStack(null);
		ft.commit();
	}

}
