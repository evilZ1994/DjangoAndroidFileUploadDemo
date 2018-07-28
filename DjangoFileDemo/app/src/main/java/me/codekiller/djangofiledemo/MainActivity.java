package me.codekiller.djangofiledemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import me.codekiller.djangofiledemo.FileUpload.FileUploadActivity;

public class MainActivity extends AppCompatActivity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        initViews();
    }

    private void initViews() {
        Button uploadFileBtn = findViewById(R.id.upload_file_btn);
        uploadFileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, FileUploadActivity.class));
            }
        });
    }
}
