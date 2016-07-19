package com.huaqin.filemanager.fragment;

import com.huaqin.filemanager.R;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ClassiFragmentContainer extends Fragment {
	private Fragment classififragment;

	private FragmentTransaction fragmentTransaction;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v;
		v = inflater.inflate(R.layout.fragment_classicontainer, null, false);
		classififragment = new ClassificationFragment();
		fragmentTransaction = getFragmentManager().beginTransaction();
		fragmentTransaction.replace(R.id.classificontainer, classififragment);
		
		fragmentTransaction.commit();
		return v;
	}

}
