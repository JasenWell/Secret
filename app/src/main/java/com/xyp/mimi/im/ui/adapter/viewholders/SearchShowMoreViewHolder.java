package com.xyp.mimi.im.ui.adapter.viewholders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.xyp.mimi.R;
import com.xyp.mimi.im.ui.adapter.models.SearchShowMorModel;
import com.xyp.mimi.im.ui.interfaces.OnShowMoreClickListener;

public class SearchShowMoreViewHolder extends BaseViewHolder<SearchShowMorModel> {
    private TextView tv_showMore;
    private OnShowMoreClickListener listener;
    private SearchShowMorModel searchShowMorModel;

    public SearchShowMoreViewHolder(@NonNull View itemView, OnShowMoreClickListener l) {
        super(itemView);
        this.listener = l;
        tv_showMore = itemView.findViewById(R.id.search_show_more);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onSearchShowMoreClicked(searchShowMorModel.getBean());
                }
            }
        });
    }

    @Override
    public void update(SearchShowMorModel searchShowMorModel) {
        this.searchShowMorModel = searchShowMorModel;
        tv_showMore.setText(searchShowMorModel.getBean());
    }
}
