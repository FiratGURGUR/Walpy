package com.firatg.walpy.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PhotosItem implements Parcelable {

	@SerializedName("src")
	@Expose
	public Src src;

	@SerializedName("width")
	@Expose
	public int width;

	@SerializedName("photographer")
	@Expose
	public String photographer;

	@SerializedName("photographer_url")
	@Expose
	public String photographerUrl;

	@SerializedName("id")
	@Expose
	public int id;

	@SerializedName("url")
	@Expose
	public String url;

	@SerializedName("photographer_id")
	@Expose
	public int photographerId;

	@SerializedName("liked")
	@Expose
	public boolean liked;

	@SerializedName("height")
	@Expose
	public int height;


	protected PhotosItem(Parcel in) {
		src = in.readParcelable(Src.class.getClassLoader());
		width = in.readInt();
		photographer = in.readString();
		photographerUrl = in.readString();
		id = in.readInt();
		url = in.readString();
		photographerId = in.readInt();
		liked = in.readByte() != 0;
		height = in.readInt();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(src, flags);
		dest.writeInt(width);
		dest.writeString(photographer);
		dest.writeString(photographerUrl);
		dest.writeInt(id);
		dest.writeString(url);
		dest.writeInt(photographerId);
		dest.writeByte((byte) (liked ? 1 : 0));
		dest.writeInt(height);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<PhotosItem> CREATOR = new Creator<PhotosItem>() {
		@Override
		public PhotosItem createFromParcel(Parcel in) {
			return new PhotosItem(in);
		}

		@Override
		public PhotosItem[] newArray(int size) {
			return new PhotosItem[size];
		}
	};

	public Src getSrc(){
		return src;
	}

	public int getWidth(){
		return width;
	}

	public String getPhotographer(){
		return photographer;
	}

	public String getPhotographerUrl(){
		return photographerUrl;
	}

	public int getId(){
		return id;
	}

	public String getUrl(){
		return url;
	}

	public int getPhotographerId(){
		return photographerId;
	}

	public boolean isLiked(){
		return liked;
	}

	public int getHeight(){
		return height;
	}


}