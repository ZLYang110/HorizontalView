package com.zlyandroid.horizontalview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zlyandroid.horizontalview.utils.HorizontalViewOptions;
import com.zlylib.horizontalviewlib.HorizontalView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.ll_card)
    LinearLayout ll_card;
    @BindView(R.id.ll_weak)
    LinearLayout ll_weak;
    @BindView(R.id.ll_user)
    LinearLayout ll_user;


    private Unbinder unbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        ll_card.setOnClickListener(this);
        ll_weak.setOnClickListener(this);
        ll_user.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.ll_card:
                CardActivity.start(this);
                break;
            case R.id.ll_weak:
                WeakActivity.start(this);
                break;
            case R.id.ll_user:
                Intent intent =new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.xloong.com/"));
                startActivity(intent);
                break;


        }
    }
    @Override
    protected void onDestroy() {

        unbinder.unbind();
        super.onDestroy();

    }
}
