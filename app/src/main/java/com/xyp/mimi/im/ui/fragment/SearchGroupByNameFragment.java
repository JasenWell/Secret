package com.xyp.mimi.im.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import java.util.List;

import com.xyp.mimi.im.bean.ResponseGroupInfo;
import com.xyp.mimi.im.model.Status;
import com.xyp.mimi.im.ui.adapter.models.SearchModel;
import com.xyp.mimi.im.ui.interfaces.OnGroupItemClickListener;

public class SearchGroupByNameFragment extends SearchBaseFragment {
    private String lastSearchWord = "";
    private SearchResultListener resultListener;

    public void init(OnGroupItemClickListener onGroupItemClickListener) {
        init(null, onGroupItemClickListener, null, null, null);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        viewModel.getGroupContactList().observe(this, resource -> {
            if (resource.status != Status.LOADING) {
//                search(lastSearchWord);
                refreshView(lastSearchWord,resource.data.getGroupList());
            }
        });
        viewModel.requestGroupContactList();
        return view;
    }

    private void refreshView(String search, List<ResponseGroupInfo> groupInfoList){
        super.search(search);
        if (viewModel != null) {
            List<SearchModel> searchModels  = viewModel.wrapModels(search,groupInfoList);
            updateData(searchModels);
            if (resultListener != null) {
                resultListener.onSearchResult(lastSearchWord, searchModels);
            }
            lastSearchWord = search;
        }
    }

    @Override
    public void search(String search) {
        super.search(search);
    }

    public void setOnSearchResultListener(SearchResultListener listener) {
        resultListener = listener;
    }

    public interface SearchResultListener {
        void onSearchResult(String lastKeyWord, List<SearchModel> searchModels);
    }
}
