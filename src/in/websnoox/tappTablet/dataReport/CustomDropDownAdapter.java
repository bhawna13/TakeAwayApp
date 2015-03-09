package in.websnoox.tappTablet.dataReport;

import in.websnoox.tappTablet.R;
import in.websnoox.tappTablet.dataReport.entity.DataReportSelectionItem;
import in.websnoox.tappTablet.ui.CustomTextView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.PopupWindow;

public class CustomDropDownAdapter extends BaseAdapter {
	private DataReportSelectionItem[] mListItems;
	private LayoutInflater mInflater;
	Context context;
	View mselectbutton;

	PopupWindow pw;

	public CustomDropDownAdapter(Context context, View mselectbutton,
			DataReportSelectionItem[] mListItems, PopupWindow pw) {
		this.context = context;
		this.mListItems = mListItems;
		this.mselectbutton = mselectbutton;
		this.pw = pw;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return mListItems.length;
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		CustomViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.drop_down_list_row, null);
			holder = new CustomViewHolder();
			holder.tv = (CustomTextView) convertView
					.findViewById(R.id.SelectDrop);
			convertView.setTag(holder);
		} else {
			holder = (CustomViewHolder) convertView.getTag();
		}

		holder.tv.setText(mListItems[position].getName());
		holder.tv.setBackgroundResource(setDrawableAtPosition(position));
		holder.tv.setPadding(10, 0, 0, 0);
		return convertView;
	}

	private int setDrawableAtPosition(int position) {

		switch (position) {
		case 0:
			return R.drawable.dr_dropdown_txt_selectorstart;
		case 1:
		case 2:
		case 3:
		case 4:
			return R.drawable.dr_dropdown_txt_selectormiddle;
		case 5:
			return R.drawable.dr_dropdown_txt_selectorlast;
		default:
			return R.drawable.dr_dropdown_txt_selectormiddle;
		}

	}

	private class CustomViewHolder {
		CustomTextView tv;
	}
}
