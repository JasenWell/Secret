package com.xyp.mimi.mvp.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.xyp.mimi.R;

/**
 * Created by fyf on 2019/3/1.
 * 描述：是二级显示列表的adapter
 */
public class ExpandableListviewAdapter extends BaseExpandableListAdapter {
    //Model：定义的数据
    private String[] groups;
    //注意，字符数组不要写成{{"A1,A2,A3,A4"}, {"B1,B2,B3,B4，B5"}, {"C1,C2,C3,C4"}}
    private String[][] childs;
    private Context context;

    private Handler handler;

    public ExpandableListviewAdapter() {
    }

    public ExpandableListviewAdapter(Context context, String[] groups, String[][] childs){
        this.context=context;
        this.groups=groups;
        this.childs=childs;
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                notifyDataSetChanged();//更新数据
                super.handleMessage(msg);
            }
        };

    }

    /*供外界更新数据的方法*/
    public void refresh(){
        handler.sendMessage(new Message());
//        //必须重新伸缩之后才能更新数据
//        expandableListView.collapseGroup(groupPosition);
//        expandableListView.expandGroup(groupPosition);
    }



    @Override
    public int getGroupCount() {
        return groups.length;
    }

    @Override
    public int getChildrenCount(int i) {
        return childs[i].length;
    }

    @Override
    public Object getGroup(int i) {
        return groups[i];
    }

    @Override
    public Object getChild(int i, int i1) {
        return childs[i][i1];
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    //分组和子选项是否持有稳定的ID, 就是说底层数据的改变会不会影响到它们
    public boolean hasStableIds() {
        return true;
    }

    @Override
/**
 *
 * 获取显示指定组的视图对象
 *
 * @param groupPosition 组位置
 * @param isExpanded 该组是展开状态还是伸缩状态，true=展开
 * @param convertView 重用已有的视图对象
 * @param parent 返回的视图对象始终依附于的视图组
 */
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.expand_parent_item,parent,false);
            groupViewHolder = new GroupViewHolder();
            groupViewHolder.parent_textview_id = convertView.findViewById(R.id.parent_textview_id);
            groupViewHolder.parent_image = convertView.findViewById(R.id.parent_image);
            convertView.setTag(groupViewHolder);
        }else {
            groupViewHolder = (GroupViewHolder)convertView.getTag();
        }
        groupViewHolder.parent_textview_id.setText(groups[groupPosition]);
        //如果是展开状态，
        if (isExpanded){
            groupViewHolder.parent_image.setImageDrawable(ContextCompat.getDrawable(context,R.mipmap.up_arrow));
        }else{
            groupViewHolder.parent_image.setImageDrawable(ContextCompat.getDrawable(context,R.mipmap.right_arrow));
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView==null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.expand_children_item,parent,false);
            childViewHolder = new ChildViewHolder();
            childViewHolder.chidren_item = (TextView)convertView.findViewById(R.id.chidren_item);
            convertView.setTag(childViewHolder);

        }else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        childViewHolder.chidren_item.setText(childs[groupPosition][childPosition]);
        return convertView;
    }

    //指定位置上的子元素是否可选中
    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
    static class GroupViewHolder {
        TextView parent_textview_id;
        ImageView parent_image;
    }

    static class ChildViewHolder {
        TextView chidren_item;

    }
}