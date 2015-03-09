package in.websnoox.tappTablet.util;

import in.websnoox.tappTablet.BaseFragmentActivity;
import in.websnoox.tappTablet.R;
import in.websnoox.tappTablet.constants.StaticConstants;
import in.websnoox.tappTablet.dataReport.CustomDropDownAdapter;
import in.websnoox.tappTablet.dataReport.DRBaseFragment;
import in.websnoox.tappTablet.dataReport.entity.DataReportSelectionItem;
import in.websnoox.tappTablet.entity.Order;
import in.websnoox.tappTablet.ui.CustomTextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

public class Util {

	public static ArrayList<Order> readyOrdersList = new ArrayList<>();

	public static String numberfornmat(float value) {

		NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
		DecimalFormat df = (DecimalFormat) nf;
		df.applyPattern("0.00");
		return df.format(value);
	}

	public static String numberfornmat(Double value) {

		NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
		DecimalFormat df = (DecimalFormat) nf;
		df.applyPattern("0.00");
		return df.format(value);
	}

	public static void initiateDataReportPopUp(final DRBaseFragment fragment,
			final DataReportSelectionItem[] item, final View view) {
		
		Context context =fragment.getActivity();
		Util.hideSoftKeyboard(context, view);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LinearLayout layout = (LinearLayout) inflater.inflate(
				R.layout.pop_up_window, null);

		final PopupWindow pw = new PopupWindow(layout,
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, true);
		pw.setWidth(view.getWidth());
		pw.setBackgroundDrawable(new BitmapDrawable());
		pw.setTouchable(true);
		pw.setOutsideTouchable(true);
		pw.setHeight(LayoutParams.WRAP_CONTENT);
		pw.setTouchInterceptor(new OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
					pw.dismiss();
					return true;
				}
				return false;
			}
		});

		pw.setContentView(layout);
		pw.showAsDropDown(view);
		final ListView list = (ListView) layout
				.findViewById(R.DropDownList.dropDownList);
		CustomDropDownAdapter adapter = new CustomDropDownAdapter(context,
				view, item, pw);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				((CustomTextView) view.findViewById(R.id.dr_spinner))
						.setText(item[arg2].getName());
				fragment.setCurrentReport(item[arg2].getPosition());
				pw.dismiss();

				/*
				 * ((POSApplication) context.getApplicationContext())
				 * .getDataModel().populateRecentStateList(
				 * drowupdowlistFileName[arg2]);
				 */
			}
		});
	}

	public static void hideSoftKeyboard(Context context, View view) {
		InputMethodManager imm = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
	}
	
	public static int calculateIndexOfchild(int density, float heightDp, int value){
		int IndexOfVisibleChild=0;
		if(density==160){
			if(heightDp==600){
				if(value>1 && value<1005){
					IndexOfVisibleChild=1;
				}
				else if(value>1005 && value<1510){
					IndexOfVisibleChild=2;
				}
				else if(value>1510 && value<2015){
					IndexOfVisibleChild=3;
				}
				else if(value>2015 && value<2520){
					IndexOfVisibleChild=4;
				}
				else if(value>2520 && value<3025){
					IndexOfVisibleChild=5;
				}
				else if(value>3025 && value<3530){
					IndexOfVisibleChild=6;
				}
				else
					IndexOfVisibleChild=7;
			}
			else{
				if(value>1 && value<1300){
					IndexOfVisibleChild=1;
				}
				else if(value>1300 && value<1800){
					IndexOfVisibleChild=2;
				}
				else if(value>1800 && value<2300){
					IndexOfVisibleChild=3;
				}
				else if(value>2300 && value<2800){
					IndexOfVisibleChild=4;
				}
				else if(value>2800 && value<3300){
					IndexOfVisibleChild=5;
				}
				else if(value>3300 && value<3800){
					IndexOfVisibleChild=6;
				}
				else
					IndexOfVisibleChild=7;
			}
			
		}
		return IndexOfVisibleChild;
		
	}

}
