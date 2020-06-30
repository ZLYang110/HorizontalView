# HorizontalView

画廊， 横向滑动视图

该库是基于RecyclerView的可滚动列表的实现，其中选中项居中，并且可以使用滑动来更改。

## 介绍

1. 可设置横向滑动，纵向滑动。
2. 选中项居中。
3. 可设置无限滑动。
4. 滑动到指定item。
5. 内有滑动动画，可设置滑动动画时长。


![HorizontalView](https://github.com/yarolegovich/DiscreteScrollView/blob/master/images/cards_shop.gif)

## Gradle 
Add this into your dependencies block.
```
compile 'com.yarolegovich:discrete-scrollview:1.4.9'
```


## 示例

<img src="https://github.com/ZLYang110/HorizontalView/blob/master/screenshot/Screenshot_1.jpg" width = "180" height = "300" alt="图片名称"/><img src="https://github.com/ZLYang110/HorizontalView/blob/master/screenshot/Screenshot_2.jpg" width = "180" height = "300" alt="图片名称"/>

## Wiki



# 使用说明

## 集成

![](https://img.shields.io/badge/Downloads%20Week-655-green) ![](https://img.shields.io/badge/Downloads%20Month-2.4K-blue)

- ### 添加jitpack库

```java
// build.gradle(Project:)
allprojects {
    repositories {
        ...
            maven { url 'https://www.jitpack.io' }
    }
}
```

- ### 添加依赖

```groovy
// build.gradle(Module:)
dependencies {

  implementation 'com.github.ZLYang110:HorizontalView:1.0'

}
```
## 使用
 1. 使用xml或代码将DiscreteScrollView添加到布局中：
 2. 创建您的RecyclerView.Adapter实现。如果您不知道如何做，请参考该示例。
 3. 设置适配器。
 4. 大功告成！
```xml
    <com.zlylib.horizontalviewlib.HorizontalView
            android:id="@+id/item_picker"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
      />
```
```java
 private InfiniteScrollAdapter infiniteAdapter;

      itemPicker.setOrientation(DSVOrientation.HORIZONTAL);

       infiniteAdapter = InfiniteScrollAdapter.wrap(new CardAdapter(CardBean.getData()));
       itemPicker.setAdapter(infiniteAdapter);
       itemPicker.setItemTransitionTimeMillis(150);//设置滑动时长
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
```

### API
#### General
```java
scrollView.setOrientation(DSVOrientation o);  //设置视图
scrollView.setOffscreenItems(count); //Reserve extra space equal to (childSize * count) on each side of the view
scrollView.setOverScrollEnabled(enabled); //Can also be set using android:overScrollMode xml attribute

scrollView.getCurrentItem();  //返回当前所选项目的适配器位置；如果适配器为空，则返回-1。
scrollView.scrollToPosition(int position); //初始位置
scrollView.smoothScrollToPosition(int position); //通过动画滚动到指定位置
scrollView.setItemTransitionTimeMillis(int millis); //同股票滚动所需时间


scrollView.setItemTransformer(new ScaleTransformer.Builder()
  .setMaxScale(1.05f)
  .setMinScale(0.8f)
  .setPivotX(Pivot.X.CENTER) // CENTER is a default one
  .setPivotY(Pivot.Y.BOTTOM) // CENTER is a default one
  .build());
```

#### 滑动多个项目

要允许浏览多个项目，请调用：
```java
scrollView.setSlideOnFling(true);
```
默认阈值设置为2100。阈值越低，动画越流畅。您可以通过以下方式调整阈值：
```java
scrollView.setSlideOnFlingThreshold(value);
```

#### 无限滚动
无限滚动在适配器级别实现：
```java
InfiniteScrollAdapter wrapper = InfiniteScrollAdapter.wrap(yourAdapter);
scrollView.setAdapter(wrapper);
```
实例InfiniteScrollAdapter具有以下有用的方法：
```java
int getRealItemCount();

int getRealCurrentPosition();

int getRealPosition(int position);

/*
 * 在以下用例中，您可能需要此方法：
 * int targetAdapterPosition = wrapper.getClosestPosition(targetPosition);
 * scrollView.smoothScrollTo(targetAdapterPosition);
 * 滚动数据集以达到目标位置所需的最少数量
 */
int getClosestPosition(int position); 
```
当前InfiniteScrollAdapter处理数据集 效率很低请放心使用

#### 回调状态
* 滚动状态回调
```java
scrollView.addScrollStateChangeListener(listener);
scrollView.removeScrollStateChangeListener(listener);

public interface ScrollStateChangeListener<T extends ViewHolder> {

  void onScrollStart(T currentItemHolder, int adapterPosition); //滚动时调用
  
  void onScrollEnd(T currentItemHolder, int adapterPosition); //滚动结束时调用
  /**
   * 滚动进行时调用
   * @param scrollPosition是间隔[-1f..1f]内的一个值，它对应于currentSelectedView的位置。
   * In idle state:
   * |view1|  |currentlySelectedView|  |view2|
   * -view1 is on position -1;
   * -currentlySelectedView is on position 0;
   * -view2 is on position 1.
   * @param currentIndex-当前视图的索引
   * @param newIndex-成为新的当前视图的视图的索引
   * @param currentHolder-当前视图的ViewHolder
   * @paramnewCurrent-成为新当前视图的视图的ViewHolder
   */
  void onScroll(float scrollPosition, int currentIndex, int newIndex, @Nullable T currentHolder, @Nullable T newCurrentHolder); 
}
```
* 滚动回调:
```java
scrollView.addScrollListener(listener);
scrollView.removeScrollListener(listener);

public interface ScrollListener<T extends ViewHolder> {
  //The same as ScrollStateChangeListener, but for the cases when you are interested only in onScroll()
  void onScroll(float scrollPosition, int currentIndex, int newIndex, @Nullable T currentHolder, @Nullable T newCurrentHolder);
}
```
* 当前选中回调:
```java
scrollView.addOnItemChangedListener(listener);
scrollView.removeOnItemChangedListener(listener);

public interface OnItemChangedListener<T extends ViewHolder> {
  /**
    *如果数据集为空，viewHolder将为null
   */
  void onCurrentItemChanged(@Nullable T viewHolder, int adapterPosition); 
}
```

## THANKS
Thanks to [DiscreteScrollView](https://github.com/yarolegovich/DiscreteScrollView)


## 联系方式

QQ： 1833309873
E-mail: 1833309873@QQ.com

## 最后
## 给个star吧！！！！


## License
```
MIT License

Copyright (c) 2020

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.