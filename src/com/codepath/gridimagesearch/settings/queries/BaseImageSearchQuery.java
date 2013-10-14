package com.codepath.gridimagesearch.settings.queries;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.codepath.gridimagesearch.R;
import com.codepath.gridimagesearch.settings.ImageSettingOption;


public class BaseImageSearchQuery implements OnItemSelectedListener {
	public String title;
	
	public String queryParam;
	private String curSelected;
	
	public ArrayList<ImageSettingOption> imageSettingOptions;
	
	public String toString() {
		return this.getQueryParam();
	}
	
	public void setSelected(Integer id) {
		this.curSelected = this.imageSettingOptions.get(id).toString();
	}
	
	public void clearSelected() {
		this.curSelected = null;
	}
	
	public String getCurSelected() {
		return this.curSelected;
	}
	
	@Override
	public void onItemSelected(AdapterView<?> parent, View v, int position,
			long rowId) {
		this.setSelected(position);
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		this.clearSelected();
	}
	
	public String getQueryParam() { 
		String value = this.getCurSelected();
		if (value != null) {
			return this.queryParam + "=" + this.getCurSelected();
		}
		else {
			return "";
		}
	}
	
	public ArrayList<ImageSettingOption> getOptions() {
		return this.imageSettingOptions;
	}
	
	public String[] convertFrom(Context context, int stringArrayId) {
	  	String[] imageSizes = context.getResources().getStringArray(stringArrayId);
	
	  	return imageSizes;
	}

	public void setTitle(Context context, int titleId) {
		this.title = context.getResources().getString(titleId);
	}
	
	public BaseImageSearchQuery(Context context, int stringArrayId, int titleId) {
		
		this.setTitle(context, titleId);
		
		String[] imageItems = this.convertFrom(context, stringArrayId);
		
		imageSettingOptions = new ArrayList<ImageSettingOption>();
		
		for (int i = 0; i < imageItems.length; i++)
		{
			imageSettingOptions.add(new ImageSettingOption(imageItems[i], imageItems[i]));
		}
		
	}
}