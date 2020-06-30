package com.zlylib.horizontalviewlib.adapter;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.zlylib.horizontalviewlib.HorizontalView;

/**
 * Created by zhangliyang on 2019-6-30.

 */
public class ScrollListenerAdapter<T extends RecyclerView.ViewHolder> implements HorizontalView.ScrollStateChangeListener<T> {

    private HorizontalView.ScrollListener<T> adaptee;

    public ScrollListenerAdapter(@NonNull HorizontalView.ScrollListener<T> adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void onScrollStart(@NonNull T currentItemHolder, int adapterPosition) {

    }

    @Override
    public void onScrollEnd(@NonNull T currentItemHolder, int adapterPosition) {

    }

    @Override
    public void onScroll(float scrollPosition,
                         int currentIndex, int newIndex,
                         @Nullable T currentHolder, @Nullable T newCurrentHolder) {
        adaptee.onScroll(scrollPosition, currentIndex, newIndex, currentHolder, newCurrentHolder);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ScrollListenerAdapter) {
            return adaptee.equals(((ScrollListenerAdapter) obj).adaptee);
        } else {
            return super.equals(obj);
        }
    }
}
