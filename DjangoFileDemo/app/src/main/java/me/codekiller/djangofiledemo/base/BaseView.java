package me.codekiller.djangofiledemo.base;

import android.view.View;

public interface BaseView<T> {
    void initViews(View v);

    void setPresenter(T presenter);
}
