package com.example.eltaysser.mymovies;

import android.os.Parcel;
import android.os.Parcelable;

class LayoutContent implements Parcelable {
    //implement the parcelable interface
   private String imageUrlForPicasso;
   private String movieName;
   private final String description;
   private String voteCount;

    private LayoutContent(Parcel in) {
        imageUrlForPicasso = in.readString();
        movieName = in.readString();
        description = in.readString();
        voteCount = in.readString();
    }

    public static final Creator<LayoutContent> CREATOR = new Creator<LayoutContent>() {
        @Override
        public LayoutContent createFromParcel(Parcel in) {
            return new LayoutContent(in);
        }

        @Override
        public LayoutContent[] newArray(int size) {
            return new LayoutContent[size];
        }
    };

    public String getDescription() {
        return description;
    }

    public String getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(String voteCount) {
        this.voteCount = voteCount;
    }

    public LayoutContent(String imageUrlForPicasso, String movieName, String description, String voteCount) {
        this.imageUrlForPicasso = imageUrlForPicasso;
        this.movieName = movieName;
        this.description = description;
        this.voteCount = voteCount;
    }

    public String getImageUrlForPicasso() {
        return imageUrlForPicasso;
    }

    public void setImageUrlForPicasso(String imageUrlForPicasso) {
        this.imageUrlForPicasso = imageUrlForPicasso;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imageUrlForPicasso);
        dest.writeString(movieName);
        dest.writeString(description);
        dest.writeString(voteCount);
    }
}
