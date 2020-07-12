package com.xyp.mimi.im.ui.adapter.viewholders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.xyp.mimi.R;
import com.xyp.mimi.im.ui.adapter.models.ListItemModel;

public class CommonTextItemViewHolder extends BaseItemViewHolder<ListItemModel<String>> {
    private TextView tvTitle;

    public CommonTextItemViewHolder(@NonNull View itemView) {
        super(itemView);
        tvTitle = itemView.findViewById(R.id.catalog);
    }

    @Override
    public void setOnClickItemListener(View.OnClickListener listener) {

    }

    @Override
    public void setOnLongClickItemListener(View.OnLongClickListener listener) {

    }

    @Override
    public void update(ListItemModel<String> contactModel) {
        tvTitle.setText(contactModel.getDisplayName());
    }

}
