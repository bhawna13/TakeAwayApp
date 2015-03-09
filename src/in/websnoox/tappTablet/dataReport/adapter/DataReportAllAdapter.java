package in.websnoox.tappTablet.dataReport.adapter;

import in.websnoox.tappTablet.R;
import in.websnoox.tappTablet.adapter.BaseListAdapter;
import in.websnoox.tappTablet.dataReport.entity.DataReportAllItem;
import in.websnoox.tappTablet.ui.CustomTextView;
import in.websnoox.tappTablet.util.Util;

import java.util.Iterator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class DataReportAllAdapter<T> extends BaseListAdapter<T> {

	public DataReportAllAdapter(Context context) {
		super(context);
	}

	@SuppressLint("NewApi")
	@SuppressWarnings("unchecked")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		DataReportAllHolder holder;

		if (convertView == null) {
			holder = new DataReportAllHolder();

			convertView = inflater.inflate(R.layout.data_report_row_all, null);

			holder.ll_row_dr = (LinearLayout) convertView .findViewById(R.id.ll_row_dr);
			holder.tvTime = (CustomTextView) convertView .findViewById(R.id.tvTime_drAll);
			holder.tvOrderNo = (CustomTextView) convertView .findViewById(R.id.tvOrderNo_drAll);
			holder.tvVAT = (CustomTextView) convertView .findViewById(R.id.tvVat_drAll);
			holder.tvAmount = (CustomTextView) convertView .findViewById(R.id.tvAmount_drAll);
			holder.tvPaymentType = (CustomTextView) convertView .findViewById(R.id.tvPaymentType_drAll);
			holder.tvStatus = (CustomTextView) convertView .findViewById(R.id.tvStatus_drAll);
			holder.tvOrderType = (CustomTextView) convertView .findViewById(R.id.tvOrderType_drAll);
			holder.relSelected_drAll = (RelativeLayout) convertView .findViewById(R.id.tvRelSelected_drAll);
			holder.tvSelected_drAll = (ImageView) convertView .findViewById(R.id.tvSelected_drAll);

			convertView.setTag(holder);

		} else
			holder = (DataReportAllHolder) convertView.getTag();

		DataReportAllItem item = (DataReportAllItem) dataSetItems.get(position);

		holder.tvTime.setText(item.getTime());
		holder.tvOrderNo.setText(item.getOrderNo());
		holder.tvVAT.setText("£ " + item.getVAT());
		holder.tvAmount.setText("£ " + item.getAmount());
		holder.tvPaymentType.setText(item.getPaymentType());
		holder.tvStatus.setText(item.getStatus());
		holder.tvOrderType.setText(item.getOrderType());

		if (item.getStatus().equalsIgnoreCase("Done"))
			holder.tvStatus.setTextColor(context.getResources().getColor(
					R.color.blue_txt_clor));
		else if (item.getStatus().equalsIgnoreCase("Expired")
				|| item.getStatus().equalsIgnoreCase("Void")
				|| item.getStatus().equalsIgnoreCase("Reject")
				|| item.getStatus().equalsIgnoreCase("Cancelled"))
			holder.tvStatus.setTextColor(context.getResources().getColor(
					R.color.txt_red));

		if (isSelectionEnable())
			holder.relSelected_drAll.setVisibility(View.VISIBLE);
		else
			holder.relSelected_drAll.setVisibility(View.GONE);

		if (item.isSelected())
			holder.tvSelected_drAll.setSelected(true);
		else
			holder.tvSelected_drAll.setSelected(false);
		

		if(item.isDoubleTabSelected())
	     	holder.ll_row_dr.setActivated(true);
		else
			holder.ll_row_dr.setActivated(false);
		
		holder.tvTime.setTag(holder);
		holder.tvOrderNo.setTag(holder);
		holder.tvVAT.setTag(holder);
		holder.tvAmount.setTag(holder);
		holder.tvPaymentType.setTag(holder);
		holder.tvStatus.setTag(holder);
		holder.tvOrderType.setTag(holder);
		holder.relSelected_drAll.setTag(holder);
		holder.tvSelected_drAll.setTag(holder);

		holder.relSelected_drAll.setOnClickListener(onClickListener(position));
		

		return convertView;
	}
	
	
	public class DataReportAllHolder {

		public LinearLayout ll_row_dr;
		public CustomTextView tvTime, tvOrderNo, tvVAT, tvAmount,
				tvPaymentType, tvType, tvStatus, tvOrderType;
		public ImageView tvSelected_drAll;
		public RelativeLayout relSelected_drAll;
	}

	private OnClickListener onClickListener(final int position) {
		// TODO Auto-generated method stub
		return new OnClickListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void onClick(View arg0) {

				DataReportAllItem item = (DataReportAllItem) dataSetItems
						.get(position);

				if (item.isSelected())
					item.setSelected(false);
				else
					item.setSelected(true);
				dataSetItems.set(position, (T) item);

				notifyDataSetChanged();

			}
		};
	}

	public void showListSelection(boolean isSelectionEnable) {
		setSelectionEnable(isSelectionEnable);
		notifyDataSetChanged();
	}
	
	@SuppressWarnings("unchecked")
	public void doubleTabSelection(int position)
	{
		int noOfRows = getCount();
		for (int i = 0; i < noOfRows; i++) {
			DataReportAllItem item = (DataReportAllItem) dataSetItems
					.get(i);
			item.setDoubleTabSelected(false);
		}
		
		DataReportAllItem item = (DataReportAllItem) dataSetItems
				.get(position);
		item.setDoubleTabSelected(true);
		
		dataSetItems.set(position, (T) item);
		notifyDataSetChanged();
	}

	public String getTotalAmount() {

		float totalAmount = 0.00f;
		int noOfRows = getCount();
		for (int i = 0; i < noOfRows; i++) {
			DataReportAllItem item = (DataReportAllItem) dataSetItems.get(i);
			totalAmount = totalAmount + Float.valueOf(item.getAmount());
		}

		return Util.numberfornmat(totalAmount);
	}

	public void removeSeletedItem() {

		Iterator<T> it = dataSetItems.iterator();
		while (it.hasNext()) {
			DataReportAllItem item = (DataReportAllItem) it.next();
			if (item.isSelected())
				it.remove();
		}

		notifyDataSetChanged();
	}

}
