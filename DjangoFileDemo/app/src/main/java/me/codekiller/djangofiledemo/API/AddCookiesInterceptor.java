package me.codekiller.djangofiledemo.API;

import android.util.Log;

import java.io.IOException;
import java.util.HashSet;

import me.codekiller.djangofiledemo.MyApp;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AddCookiesInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        HashSet<String> preferences = (HashSet) MyApp.getContext().getSharedPreferences("config",
                MyApp.getContext().MODE_PRIVATE).getStringSet("cookie", null);
        if (preferences != null) {
            for (String cookie : preferences) {
                builder.addHeader("Cookie", cookie);
                Log.v("OkHttp", "Adding Header: " + cookie); // This is done so I know which headers are being added; this interceptor is used after the normal logging of OkHttp
            }
        }
        return chain.proceed(builder.build());
    }
}
