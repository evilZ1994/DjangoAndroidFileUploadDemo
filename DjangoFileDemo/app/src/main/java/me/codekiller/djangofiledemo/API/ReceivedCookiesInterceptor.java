package me.codekiller.djangofiledemo.API;

import android.content.SharedPreferences;
import android.util.Log;

import java.io.IOException;
import java.util.HashSet;

import me.codekiller.djangofiledemo.MyApp;
import okhttp3.Interceptor;
import okhttp3.Response;

public class ReceivedCookiesInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = new HashSet<>();

            for (String header : originalResponse.headers("Set-Cookie")) {
                Log.i("Received header", header);
                cookies.add(header);
            }

            SharedPreferences.Editor config = MyApp.getContext().getSharedPreferences("config", MyApp.getContext().MODE_PRIVATE)
                    .edit();
            config.putStringSet("cookie", cookies);
            config.commit();
        }

        return originalResponse;
    }
}
