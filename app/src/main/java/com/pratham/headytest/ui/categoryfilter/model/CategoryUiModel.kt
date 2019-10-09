package com.pratham.headytest.ui.categoryfilter.model

import android.os.Parcel
import android.os.Parcelable

data class CategoryUiModel(
    val categoryName: String,
    val catServerId: Int,
    val catLocalId: Long?
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
        source.readInt(),
        source.readLong()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(categoryName)
        writeInt(catServerId)
        if (catLocalId != null) {
            writeLong(catLocalId)
        }
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<CategoryUiModel> =
            object : Parcelable.Creator<CategoryUiModel> {
                override fun createFromParcel(source: Parcel): CategoryUiModel =
                    CategoryUiModel(source)

                override fun newArray(size: Int): Array<CategoryUiModel?> = arrayOfNulls(size)
            }
    }
}