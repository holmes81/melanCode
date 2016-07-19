package com.huaqin.filemanager.uiutil;

import android.R.layout;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.huaqin.filemanager.R;
import com.huaqin.filemanager.fragment.GridViewItemClickFragment;

import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class GridViewAdapter extends BaseAdapter {
	private Context context;
	private String []item_name;
	private String []item_number;
	private int []image_resourse;
	
	
	public GridViewAdapter(Context context, String[] item_name,int[] image_resourse) {
		super();
		this.context = context;
		this.item_name = item_name;
		this.image_resourse = image_resourse;
	}




	@Override
	public int getCount() {
		return image_resourse.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh;
		if(convertView==null){
			vh= new ViewHolder();
			convertView=LayoutInflater.from(context).inflate(R.layout.grid_item, null);
			vh.item_image=(ImageView) convertView.findViewById(R.id.ItemImage);
			vh.item_text=(TextView) convertView.findViewById(R.id.ItemText);
			vh.item_number=(TextView) convertView.findViewById(R.id.NumText);
			convertView.setTag(vh);
		}else{
			vh=(ViewHolder) convertView.getTag();
		}
		vh.item_image.setImageResource(image_resourse[position]);
		vh.item_text.setText(item_name[position]);
		return convertView;
	}
	
}

class ViewHolder{
	ImageView item_image;
	TextView item_text;
	TextView item_number;
}
