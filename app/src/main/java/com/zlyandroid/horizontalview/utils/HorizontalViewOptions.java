package com.zlyandroid.horizontalview.utils;


import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.zlyandroid.horizontalview.R;
import com.zlylib.horizontalviewlib.HorizontalView;
import com.zlylib.horizontalviewlib.InfiniteScrollAdapter;

import java.lang.ref.WeakReference;

import static androidx.recyclerview.widget.RecyclerView.*;

/**
 * Created by zhangliyang on 08.03.2017.
 */

public class HorizontalViewOptions {



    public static void smoothSelectedPosition(final HorizontalView scrollView, final HorizontalView scrollView2,View anchor) {
        PopupMenu popupMenu = new PopupMenu(scrollView.getContext(), anchor);
        Menu menu = popupMenu.getMenu();
        final Adapter adapter = scrollView.getAdapter();
        int itemCount = (adapter instanceof InfiniteScrollAdapter) ?
                ((InfiniteScrollAdapter) adapter).getRealItemCount() :
                adapter.getItemCount();
        for (int i = 0; i < itemCount; i++) {
            menu.add(String.valueOf(i + 1));
        }
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int destination = Integer.parseInt(String.valueOf(item.getTitle())) - 1;
                if (adapter instanceof InfiniteScrollAdapter) {
                    destination = ((InfiniteScrollAdapter) adapter).getClosestPosition(destination);
                }
                scrollView.smoothScrollToPosition(destination);
                if(scrollView2 !=null){
                    scrollView2.smoothScrollToPosition(destination);
                }
                return true;
            }
        });
        popupMenu.show();
    }

}
