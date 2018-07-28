package me.codekiller.djangofiledemo.API;

import android.content.Context;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    private static String BASE_URL = "http://192.168.43.144:8000/FileUpload/";
    private static OkHttpClient okHttpClient = null;
    private static Retrofit retrofit = null;


    private static OkHttpClient getOkHttpClient(Context context){
        if (okHttpClient == null){
            okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new AddCookiesInterceptor())
                    .addInterceptor(new ReceivedCookiesInterceptor())
                    .build();
        }

        return okHttpClient;
    }

    private static Retrofit getRetrofit(Context context){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(getOkHttpClient(context))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static FileService getFileService(Context context){
        return getRetrofit(context).create(FileService.class);
    }
}
