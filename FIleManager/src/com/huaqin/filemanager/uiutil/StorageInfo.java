package com.huaqin.filemanager.uiutil;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.media.MediaRouter.VolumeCallback;
import android.net.Uri;
import android.os.storage.StorageManager;
import android.provider.MediaStore;

public class StorageInfo {
	public String path;
	public String state;
	public boolean isRemoveable;

	private List<StorageInfo> list;
	public static int VolumeSize;

	public StorageInfo(String path) {
		this.path = path;
	}

	public boolean isMounted() {
		return "mounted".equals(state);
	}

	public static int getVolumeSize(Context context) {
		VolumeSize = listAvaliableStorage(context).size();
		return VolumeSize;
	}

	public static List<StorageInfo> listAvaliableStorage(Context context) {
		ArrayList storagges = new ArrayList();
		StorageManager storageManager = (StorageManager) context
				.getSystemService(Context.STORAGE_SERVICE);
		try {
			Class<?>[] paramClasses = {};
			Method getVolumeList = StorageManager.class.getMethod(
					"getVolumeList", paramClasses);
			getVolumeList.setAccessible(true);
			Object[] params = {};
			Object[] invokes = (Object[]) getVolumeList.invoke(storageManager,
					params);
			if (invokes != null) {
				StorageInfo info = null;
				for (int i = 0; i < invokes.length; i++) {
					Object obj = invokes[i];
					Method getPath = obj.getClass().getMethod("getPath",
							new Class[0]);
					String path = (String) getPath.invoke(obj, new Object[0]);
					info = new StorageInfo(path);
					File file = new File(info.path);
					// if ((file.exists()) && (file.isDirectory())
					// && (file.canWrite())) {
					Method isRemovable = obj.getClass().getMethod(
							"isRemovable", new Class[0]);
					String state = null;
					try {
						Method getVolumeState = StorageManager.class.getMethod(
								"getVolumeState", String.class);
						state = (String) getVolumeState.invoke(storageManager,
								info.path);
						info.state = state;
					} catch (Exception e) {
						e.printStackTrace();
					}
					if (info.isMounted()) {
						info.isRemoveable = ((Boolean) isRemovable.invoke(obj,
								new Object[0])).booleanValue();
						storagges.add(info);
					}
					// }
				}
			}
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		storagges.trimToSize();
		return storagges;
	}

}