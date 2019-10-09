package com.pratham.headytest.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import com.pratham.headytest.R
import com.pratham.headytest.ui.home.model.RankingUiModel


internal class CustomArrayAdapter(
    @param:NonNull private val mContext: Context, @param:LayoutRes private val mResource: Int,
    @NonNull objects: List<RankingUiModel>
) : ArrayAdapter<RankingUiModel>(mContext, mResource, 0, objects) {

    private val mInflater: LayoutInflater
    private val items: List<RankingUiModel>

    init {
        mInflater = LayoutInflater.from(mContext)
        items = objects
    }

    override fun getDropDownView(
        position: Int, @Nullable convertView: View?,
        @NonNull parent: ViewGroup
    ): View? {
        return createItemView(position, convertView, parent)
    }

    @NonNull
    override fun getView(position: Int, @Nullable convertView: View?, @NonNull parent: ViewGroup): View? {
        return createItemView(position, convertView, parent)
    }

    private fun createItemView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val view: View? = mInflater.inflate(mResource, parent, false)

        val rankingName = view?.findViewById(R.id.txtValue) as TextView

        val rankingData = items[position]

        rankingName.setText(rankingData.name)

        return view
    }
}