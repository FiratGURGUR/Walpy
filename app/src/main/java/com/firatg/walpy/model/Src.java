package com.firatg.walpy.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Src implements Parcelable {

	@SerializedName("small")
	public String small;

	@SerializedName("original")
	public String original;

	@SerializedName("large")
	public String large;

	@SerializedName("tiny")
	public String tiny;

	@SerializedName("medium")
	public String medium;

	@SerializedName("large2x")
	public String large2x;

	@SerializedName("portrait")
	public String portrait;

	@SerializedName("landscape")
	public String landscape;

	protected Src(Parcel in) {
		small = in.readString();
		original = in.readString();
		large = in.readString();
		tiny = in.readString();
		medium = in.readString();
		large2x = in.readString();
		portrait = in.readString();
		landscape = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(small);
		dest.writeString(original);
		dest.writeString(large);
		dest.writeString(tiny);
		dest.writeString(medium);
		dest.writeString(large2x);
		dest.writeString(portrait);
		dest.writeString(landscape);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<Src> CREATOR = new Creator<Src>() {
		@Override
		public Src createFromParcel(Parcel in) {
			return new Src(in);
		}

		@Override
		public Src[] newArray(int size) {
			return new Src[size];
		}
	};

	public String getSmall(){
		return small;
	}

	public String getOriginal(){
		return original;
	}

	public String getLarge(){
		return large;
	}

	public String getTiny(){
		return tiny;
	}

	public String getMedium(){
		return medium;
	}

	public String getLarge2x(){
		return large2x;
	}

	public String getPortrait(){
		return portrait;
	}

	public String getLandscape(){
		return landscape;
	}
}