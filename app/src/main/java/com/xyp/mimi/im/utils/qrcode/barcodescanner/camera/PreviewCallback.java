package com.xyp.mimi.im.utils.qrcode.barcodescanner.camera;

import com.xyp.mimi.im.utils.qrcode.barcodescanner.SourceData;

/**
 * Callback for camera previews.
 */
public interface PreviewCallback {
    void onPreview(SourceData sourceData);
    void onPreviewError(Exception e);
}
