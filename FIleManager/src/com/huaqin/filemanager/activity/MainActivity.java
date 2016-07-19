package com.huaqin.filemanager.activity;

import java.util.ArrayList;
import java.util.List;

import com.huaqin.filemanager.R;
import com.huaqin.filemanager.fragment.ClassiFragmentContainer;
import com.huaqin.filemanager.fragment.ClassificationFragment;
import com.huaqin.filemanager.fragment.GridViewItemClickFragment;
import com.huaqin.filemanager.fragment.LocalFragment;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;

public class MainActivity extends Activity {
	private static final String LOG_TAG = "Main";
	public static final int CLASSFICATION_TAB_INDEX = 0;
	public static final int LOCAL_TAB_INDEX = 1;
	private int mSelectedTab;
	private ViewPager mViewPager;
	private TabsAdapter mTabsAdapter = null;
	private TabHost mTabHost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViews();

	}

	private void initViews() {
		if (mTabsAdapter == null) {
			mTabHost = (TabHost) findViewById(android.R.id.tabhost);
			mTabHost.setup();
			mViewPager = (ViewPager) findViewById(R.id.pager);
			mTabsAdapter = new TabsAdapter(this, mTabHost, mViewPager);
			createTabs(mSelectedTab);
		}

	}

	private void createTabs(int selectedTab) {
		// Add common settings
		TabSpec classficationTab = mTabHost.newTabSpec("分类").setIndicator(
				getResources().getString(R.string.tab_1));
		mTabsAdapter.addTab(classficationTab, ClassiFragmentContainer.class,
				null, CLASSFICATION_TAB_INDEX);
		// Add all settings
		TabSpec allTab = mTabHost.newTabSpec("本地").setIndicator(
				getResources().getString(R.string.tab_2));
		mTabsAdapter.addTab(allTab, LocalFragment.class, null, LOCAL_TAB_INDEX);
		mTabHost.setCurrentTab(mSelectedTab);
	}

	private static class TabsAdapter extends FragmentPagerAdapter implements
			ViewPager.OnPageChangeListener, OnTabChangeListener {
		private List<TabInfo> mTabs = new ArrayList<TabInfo>();
		private Context mContext;
		private ViewPager mPager;
		private TabHost mTabHost;

		public TabsAdapter(Activity activity, TabHost tabHost,
				ViewPager viewPager) {
			super(activity.getFragmentManager());
			mContext = activity;
			mTabHost = tabHost;
			mTabHost.setOnTabChangedListener(this);
			mPager = viewPager;
			mPager.setAdapter(this);
			mPager.setOnPageChangeListener(this);
		}

		@Override
		public int getCount() {
			return mTabs.size();
		}

		@Override
		public Fragment getItem(int position) {
			TabInfo info = mTabs.get(position);
			Fragment f = Fragment.instantiate(mContext, info.clss.getName(),
					info.bundle);
			return f;
		}

		public void addTab(TabHost.TabSpec tab, Class<?> clss, Bundle bundle,
				int position) {
			tab.setContent(new DummyTabFactory(mContext));
			mTabHost.addTab(tab);
			TabInfo info = new TabInfo(position, clss, bundle);
			mTabs.add(info);
			notifyDataSetChanged();
		}

		static class DummyTabFactory implements TabHost.TabContentFactory {

			private final Context mContext;

			public DummyTabFactory(Context paramContext) {
				this.mContext = paramContext;
			}

			public View createTabContent(String paramString) {
				View localView = new View(this.mContext);
				localView.setMinimumWidth(0);
				localView.setMinimumHeight(0);
				return localView;
			}
		}

		private final class TabInfo {

			private final int position;
			private final Class<?> clss;
			private final Bundle bundle;

			TabInfo(int position, Class<?> clss, Bundle bundle) {
				this.position = position;
				this.clss = clss;
				this.bundle = bundle;
			}
		}

		@Override
		public void onTabChanged(String paramString) {
			int position = mTabHost.getCurrentTab();
			mPager.setCurrentItem(position);
		}

		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {

		}

		@Override
		public void onPageSelected(int position) {
			mTabHost.setCurrentTab(position);
		}

		@Override
		public void onPageScrollStateChanged(int state) {

		}

	}

}