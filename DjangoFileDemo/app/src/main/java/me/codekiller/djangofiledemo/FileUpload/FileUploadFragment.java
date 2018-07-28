package me.codekiller.djangofiledemo.FileUpload;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileNotFoundException;

import me.codekiller.djangofiledemo.R;

public class FileUploadFragment extends Fragment implements FileUploadContract.View{
    private FileUploadContract.Presenter presenter;
    private static int IMAGE_REQUEST_CODE = 100;

    public FileUploadFragment() {
        // Required empty public constructor
    }

    public static FileUploadFragment newInstance() {
        return new FileUploadFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_file_upload, container, false);

        initViews(view);

        return view;
    }

    @Override
    public void initViews(View v) {
        Button uploadImgBtn = v.findViewById(R.id.upload_img_btn);
        uploadImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(Intent.createChooser(intent, "选择图片"), IMAGE_REQUEST_CODE);
            }
        });
    }

    @Override
    public void setPresenter(FileUploadContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(getContext().getContentResolver().openInputStream(uri));
                presenter.uploadImage(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onUploadDone() {
        Toast.makeText(getContext(), "Upload Done", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUploadError(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }
}
