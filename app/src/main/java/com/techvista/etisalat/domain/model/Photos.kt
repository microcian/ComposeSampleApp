package com.techvista.etisalat.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.techvista.etisalat.data.remote.ResponseConstants.RES_ALBUM_ID
import com.techvista.etisalat.data.remote.ResponseConstants.RES_ID
import com.techvista.etisalat.data.remote.ResponseConstants.RES_THUMBNAIL_URL
import com.techvista.etisalat.data.remote.ResponseConstants.RES_TITLE
import com.techvista.etisalat.data.remote.ResponseConstants.RES_URL

@Entity
data class Photos(
    @SerializedName(RES_ALBUM_ID) val albumId: Int,
    @PrimaryKey @SerializedName(RES_ID) val id: Int,
    @SerializedName(RES_TITLE) val title: String,
    @SerializedName(RES_URL) val url: String,
    @SerializedName(RES_THUMBNAIL_URL) val thumbnailUrl: String
)
