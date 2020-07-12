package com.xyp.mimi.im.ui.interfaces;

import com.xyp.mimi.im.ui.adapter.models.CheckableContactModel;
import io.rong.imlib.model.Conversation;

public interface OnCheckConversationClickListener {
    void onCheckConversationClick(CheckableContactModel<Conversation> conversation);
}
