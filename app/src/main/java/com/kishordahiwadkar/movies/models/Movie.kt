package com.kishordahiwadkar.movies.models

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie")
class Movie (

    @ColumnInfo(name = "adult")
    @SerializedName("adult") val adult : Boolean,

    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path") val backdrop_path : String,

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    @SerializedName("id") val id : Int,

    @ColumnInfo(name = "original_language")
    @SerializedName("original_language") val original_language : String,

    @ColumnInfo(name = "original_title")
    @SerializedName("original_title") val original_title : String,

    @ColumnInfo(name = "overview")
    @SerializedName("overview") val overview : String,

    @ColumnInfo(name = "popularity")
    @SerializedName("popularity") val popularity : Double,

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path") val poster_path : String,

    @ColumnInfo(name = "release_date")
    @SerializedName("release_date") val release_date : String,

    @ColumnInfo(name = "title")
    @SerializedName("title") val title : String,

    @ColumnInfo(name = "video")
    @SerializedName("video") val video : Boolean,

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average") val vote_average : Double,

    @ColumnInfo(name = "vote_count")
    @SerializedName("vote_count") val vote_count : Int
): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readByte() != 0.toByte(),
        parcel.readString()?:"",
        parcel.readInt(),
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readDouble(),
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readByte() != 0.toByte(),
        parcel.readDouble(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (adult) 1 else 0)
        parcel.writeString(backdrop_path)
        parcel.writeInt(id)
        parcel.writeString(original_language)
        parcel.writeString(original_title)
        parcel.writeString(overview)
        parcel.writeDouble(popularity)
        parcel.writeString(poster_path)
        parcel.writeString(release_date)
        parcel.writeString(title)
        parcel.writeByte(if (video) 1 else 0)
        parcel.writeDouble(vote_average)
        parcel.writeInt(vote_count)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }

}