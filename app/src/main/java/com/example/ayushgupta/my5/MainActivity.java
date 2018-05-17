package com.example.ayushgupta.my5;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    final MyAdapter myAdapter = new MyAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.lv1);
        grantPermission();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                File f = (File) myAdapter.getItem(position);
                Intent intent = new Intent(MainActivity.this,FullscreenActivity.class);
                intent.putExtra("file",f);
                startActivity(intent);
                }
        });
    }
    private void grantPermission(){
        int status = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (status == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this,"Access the files",Toast.LENGTH_SHORT).show();
            listView.setAdapter(myAdapter);
        }
        else{
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},100);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this,"Access the files",Toast.LENGTH_SHORT).show();
            listView.setAdapter(new MyAdapter(this));
        }
        else{
            Toast.makeText(this,"Can't access the files",Toast.LENGTH_SHORT).show();
        }
    }
}
