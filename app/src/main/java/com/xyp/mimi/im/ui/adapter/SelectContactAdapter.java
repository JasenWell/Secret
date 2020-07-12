package com.xyp.mimi.im.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.xyp.mimi.R;
import com.xyp.mimi.im.ui.adapter.models.ContactModel;
import com.xyp.mimi.im.ui.adapter.viewholders.BaseViewHolder;
import com.xyp.mimi.im.ui.adapter.viewholders.CheckableContactViewHolder;
import com.xyp.mimi.im.ui.adapter.viewholders.TitleViewHolder;
import com.xyp.mimi.im.ui.interfaces.OnCheckContactClickListener;

public class SelectContactAdapter extends RecyclerView.Adapter<BaseViewHolder<ContactModel>> {
    private List<ContactModel> data;
    private OnCheckContactClickListener checkableItemClickListener;

    public SelectContactAdapter(OnCheckContactClickListener onContactItemClickListener) {
        this.data = new ArrayList<ContactModel>();
        this.checkableItemClickListener = onContactItemClickListener;
    }

    public void setData(List<ContactModel> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BaseViewHolder<ContactModel> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BaseViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(viewType, parent, false);
        switch (viewType) {
            case R.layout.select_fragment_contact_item:
                viewHolder = new CheckableContactViewHolder(itemView, checkableItemClickListener);
                break;
            case R.layout.contact_contact_title:
                viewHolder = new TitleViewHolder(itemView);
                break;
            default:
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<ContactModel> holder, int position) {
        holder.update(data.get(position));
    }


    @Override
    public int getItemViewType(int position) {
        return data != null ? data.get(position).getType() : 0;
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }
}
