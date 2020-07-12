package com.xyp.mimi.im.im.provider;

import android.text.TextUtils;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import com.xyp.mimi.R;
import com.xyp.mimi.im.im.message.GroupApplyMessage;
import com.xyp.mimi.im.model.GroupApplyMessageData;
import io.rong.imkit.model.ConversationProviderTag;
import io.rong.imkit.model.UIConversation;
import io.rong.imkit.widget.provider.PrivateConversationProvider;

@ConversationProviderTag(conversationType = "private", centerInHorizontal = true)
public class GroupApplyMessageProvider extends PrivateConversationProvider {
    @Override
    public void bindView(View view, int i, UIConversation uiConversation) {
        if (uiConversation.getMessageContent() instanceof GroupApplyMessage) {
            GroupApplyMessage groupApplyMessage = (GroupApplyMessage) uiConversation.getMessageContent();
            if (!TextUtils.isEmpty(groupApplyMessage.getData())) {
                GroupApplyMessageData bean = null;
                try {
                    Gson gson = new Gson();
                    bean = gson.fromJson(groupApplyMessage.getData(), GroupApplyMessageData.class);
                    if (bean != null) {
                        //uiConversation.setConversationContent(new SpannableString(view.getContext().getString(R.string.seal_conversation_notification_group_tips)));
                        uiConversation.setUIConversationTitle(view.getContext().getString(R.string.seal_conversation_notification_group));

                    }
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }
        }
        super.bindView(view, i, uiConversation);
    }

}
