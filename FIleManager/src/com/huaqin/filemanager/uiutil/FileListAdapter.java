package com.huaqin.filemanager.uiutil;

import java.util.ArrayList;

import com.huaqin.filemanager.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FileListAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<ClassificationList> classificationLists;

	public FileListAdapter(Context context,
			ArrayList<ClassificationList> classificationLists) {
		super();
		this.context = context;
		this.classificationLists = classificationLists;
	}

	@Override
	public int getCount() {
		return classificationLists.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		FViewHolder vh;
		if (convertView == null) {
			vh = new FViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.filelist_item, null);
			vh.fName = (TextView) convertView.findViewById(R.id.file_name);
			vh.fInfo = (TextView) convertView.findViewById(R.id.file_info);
			vh.fico = (ImageView) convertView.findViewById(R.id.file_ico);
			convertView.setTag(vh);
		} else {
			vh = (FViewHolder) convertView.getTag();
		}
		vh.fName.setText(classificationLists.get(position).getTITLE());
		vh.fInfo.setText(classificationLists.get(position).getDATE_MODIFIED()
				+ "   " + classificationLists.get(position).getSIZE());
		if (vh.fName.getText().toString().endsWith(".jpg")) {
			vh.fico.setImageResource(R.drawable.icon_jpeg);
		} else if (vh.fName.getText().toString().endsWith(".mp3")) {
			vh.fico.setImageResource(R.drawable.icon_music_mp3);
		} else if (vh.fName.getText().toString().endsWith(".ogg")) {
			vh.fico.setImageResource(R.drawable.icon_music);
		} else if (vh.fName.getText().toString().endsWith(".txt")) {
			vh.fico.setImageResource(R.drawable.icon_text);
		} else if (vh.fName.getText().toString().endsWith(".mp4")) {
			vh.fico.setImageResource(R.drawable.icon_video_mp4);
		}
		return convertView;
	}

}

class FViewHolder {
	ImageView fico;
	TextView fName;
	TextView fInfo;
}
