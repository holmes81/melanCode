package com.huaqin.filemanager.fragment;


import java.util.List;


import com.huaqin.filemanager.R;
import com.huaqin.filemanager.uiutil.StorageInfo;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class LocalFragment extends Fragment {
	private View v;
	private RelativeLayout mSDcardLayout;
	private RelativeLayout mClassifiLayout;
	private LinearLayout mLocalLine;
	@Override
	public View onCreateView(LayoutInflater inflater,
			 ViewGroup container, Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.fragment_local,null,false);
		init();
		return v;
	}
	
	private void init(){
		mSDcardLayout = (RelativeLayout) v.findViewById(R.id.SDCardLayout);
		mClassifiLayout = (RelativeLayout) v.findViewById(R.id.ClassifiLayout);
		mLocalLine = (LinearLayout) v.findViewById(R.id.localline);
		if(StorageInfo.getVolumeSize(getActivity())==1){
			mSDcardLayout.setVisibility(View.GONE);
			mLocalLine.setVisibility(View.GONE);
		}
	}
}
