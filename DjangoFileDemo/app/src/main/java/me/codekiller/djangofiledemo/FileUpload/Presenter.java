package me.codekiller.djangofiledemo.FileUpload;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import me.codekiller.djangofiledemo.API.DataManager;
import me.codekiller.djangofiledemo.bean.ImageUpResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Presenter implements FileUploadContract.Presenter {
    private Context context;
    private FileUploadContract.View view;

    public Presenter(Context context, FileUploadContract.View view) {
        this.context = context;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void uploadImage(String path) {
        Callback<ImageUpResult> callback = new Callback<ImageUpResult>() {
            @Override
            public void onResponse(Call<ImageUpResult> call, Response<ImageUpResult> response) {
                view.onUploadDone();
                Log.i("response", response.body()+"");
            }

            @Override
            public void onFailure(Call<ImageUpResult> call, Throwable t) {
                view.onUploadError(t.getMessage());
                Log.i("upload error", t.getMessage());
            }
        };
        DataManager.getInstance().uploadImage(context, path, callback);
    }

    @Override
    public void uploadImage(Bitmap bitmap) {
        Callback<ImageUpResult> callback = new Callback<ImageUpResult>() {
            @Override
            public void onResponse(Call<ImageUpResult> call, Response<ImageUpResult> response) {
                view.onUploadDone();
                Log.i("response", response.body()+"");
            }

            @Override
            public void onFailure(Call<ImageUpResult> call, Throwable t) {
                view.onUploadError(t.getMessage());
                Log.i("upload error", t.getMessage());
            }
        };
        DataManager.getInstance().uploadImage(context, bitmap, callback);
    }
}
