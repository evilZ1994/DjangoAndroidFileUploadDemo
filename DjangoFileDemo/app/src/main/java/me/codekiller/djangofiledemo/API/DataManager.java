package me.codekiller.djangofiledemo.API;

import android.content.Context;
import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import me.codekiller.djangofiledemo.bean.ImageUpResult;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Callback;

public class DataManager {
    private static DataManager dataManager = null;

    public static DataManager getInstance() {
        if (dataManager == null) {
            dataManager = new DataManager();
        }

        return dataManager;
    }

    public void uploadImage(Context context, String filePath, Callback<ImageUpResult> callback) {
        File file = new File(filePath);
        RequestBody fileBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("image", file.getName(), fileBody);
        RetrofitHelper.getFileService(context).upload(part).enqueue(callback);
    }

    public void uploadImage(Context context, Bitmap bitmap, Callback<ImageUpResult> callback) {
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bStream);
        RequestBody fileBody = RequestBody.create(MediaType.parse("multipart/form-data"), bStream.toByteArray());
        String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.US).format(new Date()) + ".jpg";
        MultipartBody.Part part = MultipartBody.Part.createFormData("image", fileName, fileBody);
        RetrofitHelper.getFileService(context).upload(part).enqueue(callback);
    }
}
