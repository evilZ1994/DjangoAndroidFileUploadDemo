package me.codekiller.djangofiledemo.FileUpload;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import me.codekiller.djangofiledemo.R;

public class FileUploadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_upload);

        FileUploadFragment fragment = (FileUploadFragment) getSupportFragmentManager().findFragmentById(R.id.container);
        if (fragment == null) {
            fragment = FileUploadFragment.newInstance();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.container, fragment);
            transaction.commit();
        }
        Presenter presenter = new Presenter(this, fragment);
    }
}
