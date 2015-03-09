package in.websnoox.tappTablet.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class BaseListAdapter<T> extends BaseAdapter {

	protected ArrayList<T> dataSetItems = new ArrayList<>();
	protected Context context;
	protected LayoutInflater inflater;
	protected boolean isSelectionEnable = false;

	public BaseListAdapter(Context c) {
		inflater = (LayoutInflater) c
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		context = c;
	}

	@Override
	public int getCount() {
		return dataSetItems.size();
	}

	@Override
	public Object getItem(int position) {
		return dataSetItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public abstract View getView(int position, View convertView,
			ViewGroup parent);

	public void addDataSet(T dataItem) {
		dataSetItems.add(dataItem);
		notifyDataSetChanged();
	}

	public void addDataSet(int index, T dataItem) {
		dataSetItems.set(index, dataItem);
		notifyDataSetChanged();
	}

	public void addItemWithoutNotifying(final T item) {
		dataSetItems.add(item);
	}

	public void removeDataSetItem(int position) {
		dataSetItems.remove(position);
		notifyDataSetChanged();
	}

	public void clearDataSetAll() {
		dataSetItems.clear();
		notifyDataSetChanged();
	}

	protected boolean isSelectionEnable() {
		return isSelectionEnable;
	}

	protected void setSelectionEnable(boolean isSelectionEnable) {
		this.isSelectionEnable = isSelectionEnable;
	}

}
