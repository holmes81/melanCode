package com.huaqin.filemanager.uiutil;

import android.provider.MediaStore;

public class DataBaseSelection {
	public static final String AUDIO_SELECTION = "(" + MediaStore.Files.FileColumns.MIME_TYPE + "=='application/ogg')"+" OR "+"(" + MediaStore.Files.FileColumns.MIME_TYPE + "=='audio/mpeg')";
	
	public static final String IMAGE_SELECTION = "(" + MediaStore.Files.FileColumns.MIME_TYPE + "=='image/jpeg')";
	
	public static final String VIDEO_SELECTION = "(" + MediaStore.Files.FileColumns.MIME_TYPE + "=='video/mp4')"+" OR "+"(" + MediaStore.Files.FileColumns.MIME_TYPE + "=='video/mpeg')"+" OR "+"(" + MediaStore.Files.FileColumns.MIME_TYPE + "=='video/3gpp')";
	
	public static final String DOC_SELECTION = "(" + MediaStore.Files.FileColumns.MIME_TYPE + "=='text/plain')";
	
	public static final String RAR_SELECTION = "(" + MediaStore.Files.FileColumns.MIME_TYPE + "=='application/rar')"+" OR "+"(" + MediaStore.Files.FileColumns.MIME_TYPE + "=='application/zip')";
	
	public static final String APK_SELECTION = "(" + MediaStore.Files.FileColumns.MIME_TYPE + "=='application/vnd.android.package-archive')";

}

