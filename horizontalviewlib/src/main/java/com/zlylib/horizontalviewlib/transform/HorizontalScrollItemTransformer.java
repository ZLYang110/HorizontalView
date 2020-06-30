package com.zlylib.horizontalviewlib.transform;

import android.view.View;

/**
 * Created by zhangliyang on 2019-6-30.
 */

public interface HorizontalScrollItemTransformer {
    void transformItem(View item, float position);
}
