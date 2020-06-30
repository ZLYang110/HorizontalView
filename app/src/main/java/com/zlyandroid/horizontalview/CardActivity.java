package com.zlyandroid.horizontalview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.zlyandroid.horizontalview.adapter.Card2Adapter;
import com.zlyandroid.horizontalview.adapter.CardAdapter;
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
import butterknife.OnClick;
import butterknife.Unbinder;

public class CardActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.abc)
    ActionBarCommon abc;

    @BindView(R.id.item_picker)
    HorizontalView itemPicker;
    @BindView(R.id.item_name)
    TextView itemName;
    @BindView(R.id.item_price)
    TextView itemPrice;

    @BindView(R.id.item_picker2)
    HorizontalView itemPicker2;
    @BindView(R.id.item_name2)
    TextView item_name2;


    @BindView(R.id.btn_smooth_scroll)
    Button btn_smooth_scroll;



    private InfiniteScrollAdapter infiniteAdapter;

    private Unbinder unbinder;

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, CardActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
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
        initBanner2();
        btn_smooth_scroll.setOnClickListener(this);
    }

   private void initBanner1(){
       itemPicker.setOrientation(DSVOrientation.HORIZONTAL);

       infiniteAdapter = InfiniteScrollAdapter.wrap(new CardAdapter(CardBean.getData()));
       itemPicker.setAdapter(infiniteAdapter);
       itemPicker.setItemTransitionTimeMillis(150);
       itemPicker.setItemTransformer(new ScaleTransformer.Builder()
               .setMinScale(0.8f)
               .build());
       itemPicker.addOnItemChangedListener(new HorizontalView.OnItemChangedListener<RecyclerView.ViewHolder>() {
           @Override
           public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int position) {
               int positionInDataSet = infiniteAdapter.getRealPosition(position);
               onItemChanged(CardBean.getData().get(positionInDataSet));
           }
       });

    }

    private void initBanner2(){
        itemPicker2.setOrientation(DSVOrientation.HORIZONTAL);

        infiniteAdapter = InfiniteScrollAdapter.wrap(new Card2Adapter(CardBean.getData()));
        itemPicker2.setAdapter(infiniteAdapter);
        itemPicker2.setItemTransitionTimeMillis(150);
        itemPicker2.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build());
        itemPicker2.addOnItemChangedListener(new HorizontalView.OnItemChangedListener<RecyclerView.ViewHolder>() {
            @Override
            public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int position) {
                int positionInDataSet = infiniteAdapter.getRealPosition(position);
                item_name2.setText(CardBean.getData().get(positionInDataSet).getName());
            }
        });

    }

    private void onItemChanged(CardBean item) {
        itemName.setText(item.getName());
        itemPrice.setText(item.getPrice());
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_smooth_scroll:
                HorizontalViewOptions.smoothSelectedPosition(itemPicker,itemPicker2, v);
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