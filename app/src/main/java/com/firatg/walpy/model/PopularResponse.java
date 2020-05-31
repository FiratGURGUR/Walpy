package com.firatg.walpy.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PopularResponse{

	@SerializedName("next_page")
	@Expose
	private String nextPage;

	@SerializedName("per_page")
	@Expose
	private int perPage;

	@SerializedName("page")
	@Expose
	private int page;

	@SerializedName("photos")
	@Expose
	private List<PhotosItem> photos;

	public String getNextPage(){
		return nextPage;
	}

	public int getPerPage(){
		return perPage;
	}

	public int getPage(){
		return page;
	}

	public List<PhotosItem> getPhotos(){
		return photos;
	}
}