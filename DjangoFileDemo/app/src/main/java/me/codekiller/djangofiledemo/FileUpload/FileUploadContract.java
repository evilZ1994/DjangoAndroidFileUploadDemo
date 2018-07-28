package me.codekiller.djangofiledemo.FileUpload;

import android.graphics.Bitmap;

import me.codekiller.djangofiledemo.base.BasePresenter;
import me.codekiller.djangofiledemo.base.BaseView;

public interface FileUploadContract {
    public interface View extends BaseView<Presenter>{
        void onUploadDone();

        void onUploadError(String msg);
    }

    public interface Presenter extends BasePresenter{
        void uploadImage(String path);

        void uploadImage(Bitmap bitmap);
    }
}
