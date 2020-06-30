package com.zlyandroid.horizontalview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.zlyandroid.horizontalview.adapter.Card2Adapter;
import com.zlyandroid.horizontalview.adapter.CardAdapter;
import com.zlyandroid.horizontalview.adapter.ForecastAdapter;
import com.zlyandroid.horizontalview.bean.CardBean;
import com.zlyandroid.horizontalview.utils.HorizontalViewOptions;
import com.zlylib.horizontalviewlib.DSVOrientation;
import com.zlylib.horizontalviewlib.HorizontalView;
import com.zlylib.horizontalviewlib.InfiniteScrollAdapter;
import com.zlylib.horizontalviewlib.transform.ScaleTransformer;
import com.zlylib.titlebarlib.ActionBarCommon;
import com.zlylib.titlebarlib.OnActionBarChildClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class WeakActivity extends AppCompatActivity implements View.OnClickListener,
        HorizontalView.ScrollStateChangeListener<ForecastAdapter.ViewHolder>,
        HorizontalView.OnItemChangedListener<ForecastAdapter.ViewHolder> {


    @BindView(R.id.abc)
    ActionBarCommon abc;

    @BindView(R.id.forecast_city_picker)
    HorizontalView itemPicker;
    @BindView(R.id.forecast_view)
    ImageView forecast_view;




    @BindView(R.id.btn_smooth_scroll)
    Button btn_smooth_scroll;



    private InfiniteScrollAdapter infiniteAdapter;

    private Unbinder unbinder;

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, WeakActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weak);
        unbinder = ButterKnife.bind(this);
        initView();
    }
    public void initView() {
        abc.setOnLeftIconClickListener(new OnActionBarChildClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initBanner1();
        btn_smooth_scroll.setOnClickListener(this);
    }

   private void initBanner1(){

       itemPicker.setSlideOnFling(true);
       itemPicker.setAdapter(new ForecastAdapter(CardBean.getData()));
       itemPicker.addOnItemChangedListener(this);
       itemPicker.addScrollStateChangeListener(this);
       itemPicker.scrollToPosition(2);
       itemPicker.setItemTransitionTimeMillis(150);
       itemPicker.setItemTransformer(new ScaleTransformer.Builder()
               .setMinScale(0.8f)
               .build());


    }

    @Override
    public void onScrollStart(@NonNull ForecastAdapter.ViewHolder holder, int adapterPosition) {
        holder.hideText();
    }

    @Override
    public void onScrollEnd(@NonNull ForecastAdapter.ViewHolder currentItemHolder, int adapterPosition) {

    }

    @Override
    public void onScroll(float scrollPosition, int currentPosition, int newPosition, @Nullable ForecastAdapter.ViewHolder currentHolder, @Nullable ForecastAdapter.ViewHolder newCurrent) {

    }

    @Override
    public void onCurrentItemChanged(@Nullable ForecastAdapter.ViewHolder holder, int position) {
        if (holder != null) {
            forecast_view.setImageResource(CardBean.getData().get(position).getImage());
            holder.showText();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_smooth_scroll:
                HorizontalViewOptions.smoothSelectedPosition(itemPicker,null, v);
                break;


        }
    }



    private void showSnackBar() {
        Snackbar.make(itemPicker, "点我干嘛", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {

        unbinder.unbind();
        super.onDestroy();

    }

}