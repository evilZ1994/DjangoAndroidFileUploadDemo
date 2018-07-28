package me.codekiller.djangofiledemo.API;

import me.codekiller.djangofiledemo.bean.ImageUpResult;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface FileService {
    @Multipart
    @POST("upload/")
    Call<ImageUpResult> upload(@Part MultipartBody.Part image);
}
