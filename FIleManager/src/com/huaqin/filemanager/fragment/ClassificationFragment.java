package com.huaqin.filemanager.fragment;

import java.util.List;


import com.huaqin.filemanager.R;
import com.huaqin.filemanager.activity.MainActivity;
import com.huaqin.filemanager.uiutil.DataBaseSelection;
import com.huaqin.filemanager.uiutil.GridViewAdapter;
import com.huaqin.filemanager.uiutil.StorageInfo;

import android.os.Bundle;
import android.os.storage.StorageManager;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

public class ClassificationFragment extends Fragment implements
		OnItemClickListener {
	private View v;
	private GridView mGridView;

	private int image_resourse[] = { R.drawable.ic_strongbox_picture,
			R.drawable.ic_strongbox_music, R.drawable.ic_strongbox_video,
			R.drawable.ic_strongbox_doc, R.drawable.category_icon_compress,
			R.drawable.category_icon_application,
			R.drawable.category_icon_recent, R.drawable.category_icon_favorite,
			R.drawable.ic_mai_strongbox };

	private String[] item_name;
	
	private StorageManager mStorageManager;
	
	public static int gridSelect;
	
	private Fragment gridviewclickfragmenmt;
	
	private FragmentTransaction fragmentTransaction;
	
	


	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.fragment_classification, null, false);
		super.onCreate(savedInstanceState);
		init();
		return v;
	}

	private void init() {
		item_name = getResources().getStringArray(R.array.grid_name);
		
		mGridView = (GridView) v.findViewById(R.id.mGridView);
		mGridView.setAdapter(new GridViewAdapter(getActivity(), item_name,
				image_resourse));
		mGridView.setOnItemClickListener(this);
		
		gridviewclickfragmenmt = new GridViewItemClickFragment();
		fragmentTransaction = getFragmentManager().beginTransaction();
			

		};
	

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		gridSelect = arg2;
		switch (arg2) {
		case 0:
			GridViewItemClickFragment.mSelection = DataBaseSelection.IMAGE_SELECTION;
			break;
		case 1:
			GridViewItemClickFragment.mSelection = DataBaseSelection.AUDIO_SELECTION;
			break;
		case 2:
			GridViewItemClickFragment.mSelection = DataBaseSelection.VIDEO_SELECTION;
			break;
		case 3:
			GridViewItemClickFragment.mSelection = DataBaseSelection.DOC_SELECTION;
			break;
		case 4:
			GridViewItemClickFragment.mSelection = DataBaseSelection.RAR_SELECTION;
			break;
		case 5:
			GridViewItemClickFragment.mSelection = DataBaseSelection.APK_SELECTION;
			break;

		default:
			break;
		}
		
		fragmentTransaction.replace(R.id.classificontainer, gridviewclickfragmenmt);
		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();

	}
	


}
