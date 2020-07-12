package com.xyp.mimi.im.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.xyp.mimi.R;
import com.xyp.mimi.im.ui.adapter.models.SearchModel;
import com.xyp.mimi.im.ui.adapter.viewholders.BaseViewHolder;
import com.xyp.mimi.im.ui.adapter.viewholders.SearchConversationViewHolder;
import com.xyp.mimi.im.ui.adapter.viewholders.SearchDivViewHolder;
import com.xyp.mimi.im.ui.adapter.viewholders.SearchFriendViewHolder;
import com.xyp.mimi.im.ui.adapter.viewholders.SearchGroupViewHolder;
import com.xyp.mimi.im.ui.adapter.viewholders.SearchMessageViewHolder;
import com.xyp.mimi.im.ui.adapter.viewholders.SearchShowMoreViewHolder;
import com.xyp.mimi.im.ui.adapter.viewholders.SearchTitleViewHolder;
import com.xyp.mimi.im.ui.interfaces.OnChatItemClickListener;
import com.xyp.mimi.im.ui.interfaces.OnContactItemClickListener;
import com.xyp.mimi.im.ui.interfaces.OnGroupItemClickListener;
import com.xyp.mimi.im.ui.interfaces.OnMessageRecordClickListener;
import com.xyp.mimi.im.ui.interfaces.OnShowMoreClickListener;

public class SearchAdapter extends RecyclerView.Adapter<BaseViewHolder<SearchModel>> {
    private List<SearchModel> data;
    private OnChatItemClickListener onChatItemClickListener;
    private OnGroupItemClickListener onGroupItemClickListener;
    private OnShowMoreClickListener onShowMoreClickListener;
    private OnContactItemClickListener onContactItemClickListener;
    private OnMessageRecordClickListener onMessageRecordClickListener;

    public SearchAdapter(OnChatItemClickListener chatItemClickListener,
                         OnGroupItemClickListener onGroupItemClickListener,
                         OnShowMoreClickListener onShowMoreClickListener,
                         OnContactItemClickListener onContactItemClickListener,
                         OnMessageRecordClickListener onMessageRecordClickListener) {
        this.onChatItemClickListener = chatItemClickListener;
        this.onGroupItemClickListener = onGroupItemClickListener;
        this.onShowMoreClickListener = onShowMoreClickListener;
        this.onContactItemClickListener = onContactItemClickListener;
        this.onMessageRecordClickListener = onMessageRecordClickListener;
        data = new ArrayList<>();
    }

    public void updateData(List<SearchModel> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BaseViewHolder<SearchModel> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BaseViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(viewType, null, false);
        switch (viewType) {
            case R.layout.search_fragment_recycler_title_layout:
                viewHolder = new SearchTitleViewHolder(itemView);
                break;
            case R.layout.serach_fragment_recycler_friend_item:
                viewHolder = new SearchFriendViewHolder(itemView, onContactItemClickListener);
                break;
            case R.layout.serach_fragment_recycler_conversation_item:
                viewHolder = new SearchConversationViewHolder(itemView, onChatItemClickListener);
                break;
            case R.layout.serach_fragment_recycler_group_item:
                viewHolder = new SearchGroupViewHolder(itemView, onGroupItemClickListener);
                break;
            case R.layout.search_frament_show_more_item:
                viewHolder = new SearchShowMoreViewHolder(itemView, onShowMoreClickListener);
                break;
            case R.layout.search_fragment_recycler_div_layout:
                viewHolder = new SearchDivViewHolder(itemView);
                break;
            case R.layout.search_fragment_recycler_chatting_records_list:
                viewHolder = new SearchMessageViewHolder(itemView, onMessageRecordClickListener);
                break;
            default:
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<SearchModel> holder, int position) {
        holder.update(data.get(position));
    }


    @Override
    public int getItemViewType(int position) {
        return data.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
