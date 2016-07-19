package com.huaqin.filemanager.fragment;

import java.io.File;
import java.util.ArrayList;

import com.huaqin.filemanager.R;
import com.huaqin.filemanager.uiutil.ClassificationList;
import com.huaqin.filemanager.uiutil.FileListAdapter;
import android.app.Fragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class GridViewItemClickFragment extends Fragment implements OnItemClickListener{
	public static final Uri INTERNAL_CONTENT_URI = MediaStore.Files
			.getContentUri("internal");
	public static final Uri EXTERNAL_CONTENT_URI = MediaStore.Files
			.getContentUri("external");
	public static String mSelection;
	private ArrayList<ClassificationList> classificationLists;
	private TextView classifitext;
	private View v;
	private ListView filelist;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.gridview_click_fragment, null, false);


		init();
		new MyAsncTask().execute();

//		for (ClassificationList list : classificationLists) {
//			Log.i("path", list.getDATE_MODIFIED());
//		}

		return v;
	}

	public ArrayList<ClassificationList> refreshList(String selection) {
		String[] columns = new String[] { MediaStore.Files.FileColumns.TITLE,
				MediaStore.Files.FileColumns.DATA,
				MediaStore.Files.FileColumns.DATE_MODIFIED,
				MediaStore.Files.FileColumns.PARENT,
				MediaStore.Files.FileColumns.SIZE, };

		Uri uri = EXTERNAL_CONTENT_URI;
		Cursor c = getActivity().getContentResolver().query(uri, columns,
				selection, null, MediaStore.Files.FileColumns.SIZE + " DESC");
		if (c == null) {
			return null;
		}

		if (c.moveToFirst()) {
			int titleIndex = c
					.getColumnIndex(MediaStore.Files.FileColumns.TITLE);
			int dataIndex = c.getColumnIndex(MediaStore.Files.FileColumns.DATA);
			int modifiedIndex = c
					.getColumnIndex(MediaStore.Files.FileColumns.DATE_MODIFIED);

			int parentIndex = c
					.getColumnIndex(MediaStore.Files.FileColumns.PARENT);
			int sizeIndex = c.getColumnIndex(MediaStore.Files.FileColumns.SIZE);

			String fileSize;

			do {

				if (c.getDouble(sizeIndex) / 1024 / 1024 > 1) {
					fileSize = String.format("%.2f ",
							c.getDouble(sizeIndex) / 1024 / 1024) + " MB";
				} else {
					fileSize = String.format("%.2f ",
							c.getDouble(sizeIndex) / 1024) + " KB";
				}

				classificationLists.add(new ClassificationList(
						// 文件名
						c.getString(dataIndex).substring(
								c.getString(dataIndex).lastIndexOf("/") + 1,
								c.getString(dataIndex).length()),
						// 文件路径
						c.getString(dataIndex),
						// 修改时间
						new java.text.SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(new java.util.Date(c.getLong(modifiedIndex) * 1000)),
						// 父路径
						c.getString(parentIndex),
						// 文件大小
						fileSize));

			} while (c.moveToNext()); // 循环获取文件
		}

		return classificationLists;
	}

	private void init() {
		classificationLists = new ArrayList<ClassificationList>();

		classifitext = (TextView) v.findViewById(R.id.classifi_text);

		filelist = (ListView) v.findViewById(R.id.filelist);

		classifitext.setText(getResources().getStringArray(R.array.grid_name)[ClassificationFragment.gridSelect]);
		
//		filelist.setAdapter(new FileListAdapter(getActivity(), classificationLists));
		
		filelist.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
	    Intent intent = new Intent("android.intent.action.VIEW");  
	    intent.addCategory("android.intent.category.DEFAULT");  
	    intent.addFlags (Intent.FLAG_ACTIVITY_NEW_TASK);  
	    Uri uri = Uri.fromFile(new  File(classificationLists.get(arg2).getDATA()));
	    switch (ClassificationFragment.gridSelect) {
		case 0:
			intent.setDataAndType (uri, "image/*");
			break;
		case 1:
			intent.setDataAndType (uri, "audio/*");
			break;
		case 2:
			intent.setDataAndType (uri, "video/*");
			break;
		case 3:
			intent.setDataAndType (uri, "text/plain");
			break;
		case 4:
			intent.setDataAndType (uri, "application/x-zip-compressed");
			break;
		case 5:
			intent.setDataAndType (uri, "application/vnd.android.package-archive");
			break;
			
		default:
			break;
		}
	      
	    this.startActivity(intent);  
	}
	
	class MyAsncTask extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			refreshList(mSelection);
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			filelist.setAdapter(new FileListAdapter(getActivity(), classificationLists));
		}
		
		
	}

}
