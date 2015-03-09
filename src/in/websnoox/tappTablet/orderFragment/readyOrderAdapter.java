package in.websnoox.tappTablet.orderFragment;

import in.websnoox.tappTablet.R;
import in.websnoox.tappTablet.entity.Order;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

public class readyOrderAdapter extends BaseAdapter{

	private Context context;
	private ArrayList<Order> items;
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}
	public readyOrderAdapter(Context context, ArrayList<Order> items) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.items = items;
		
	}
	
	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return items.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;

		if (convertView == null) {
			 holder = new ViewHolder();

			convertView = LayoutInflater.from(context).inflate(
					R.layout.ready_listview_item, parent,false);
			holder.linearOrderNo= (LinearLayout) convertView
					.findViewById(R.id.linear_orderNo);
			
			convertView.setTag(holder);
			
		}
		ViewHolder hold = (ViewHolder) convertView.getTag();
		return convertView;
	}
	static class ViewHolder {
		LinearLayout linearOrderNo;

	}
}
