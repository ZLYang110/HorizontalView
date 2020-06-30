package com.zlyandroid.horizontalview.bean;

import com.zlyandroid.horizontalview.R;

import java.util.Arrays;
import java.util.List;

public class CardBean {

    private final int id;
    private final String name;
    private final String price;
    private final int image;
    private final int icon;

    public CardBean(int id, String name, String price, int image, int icon) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getImage() {
        return image;
    }
    public int getIcon() {
        return icon;
    }
    public static List<CardBean> getData() {
        return Arrays.asList(
                new CardBean(1, "看着美味么", "￥12.00 CN", R.drawable.card1,R.drawable.london),
                new CardBean(2, "想吃么", "￥50.00 CN", R.drawable.card2,R.drawable.new_york),
                new CardBean(3, "想吃吧", "￥265.00 CN", R.drawable.card3,R.drawable.paris),
                new CardBean(4, "我也想吃", "￥18.00 CN", R.drawable.card4,R.drawable.pisa),
                new CardBean(5, "要不给个star吧", "￥36.00 CN", R.drawable.card5,R.drawable.rome),
                new CardBean(6, "star", "￥145.00 CN", R.drawable.card6,R.drawable.washington));
    }

}
