package com.huaqin.filemanager.uiutil;

import android.provider.MediaStore;

public class ClassificationList {
	private String TITLE;
	private String DATA;
	private String DATE_MODIFIED;
	private String PARENT;
	private String SIZE;
	
	public ClassificationList(String tITLE, String dATA, String dATE_MODIFIED,
			String pARENT, String sIZE) {
		super();
		TITLE = tITLE;
		DATA = dATA;
		DATE_MODIFIED = dATE_MODIFIED;
		PARENT = pARENT;
		SIZE = sIZE;
	}

	public String getTITLE() {
		return TITLE;
	}

	public void setTITLE(String tITLE) {
		TITLE = tITLE;
	}

	public String getDATA() {
		return DATA;
	}

	public void setDATA(String dATA) {
		DATA = dATA;
	}

	public String getDATE_MODIFIED() {
		return DATE_MODIFIED;
	}

	public void setDATE_MODIFIED(String dATE_MODIFIED) {
		DATE_MODIFIED = dATE_MODIFIED;
	}

	public String getPARENT() {
		return PARENT;
	}

	public void setPARENT(String pARENT) {
		PARENT = pARENT;
	}

	public String getSIZE() {
		return SIZE;
	}

	public void setSIZE(String sIZE) {
		SIZE = sIZE;
	}
	
	
}
