package com.example.ayushgupta.my5;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class MyAdapter extends BaseAdapter
{

    File files[];
    File file;
    String path;
    MainActivity mActivity;
    MyAdapter(MainActivity mActivity){
        this.mActivity = mActivity;
        path = "/storage/sdcard0/WhatsApp/Media/WhatsApp Images/";
        file = new File(path);
        if (!file.exists()){
            path = "/storage/emulated/0/WhatsApp/Media/WhatsApp Images/";
            file = new File(path);
        }
        files = file.listFiles();
    }
    @Override
    public int getCount() {
        return files.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mActivity);
        View v = inflater.inflate(R.layout.my_list, null);
        ImageView iv1 = v.findViewById(R.id.iv1);
        TextView tv1 = v.findViewById(R.id.tv1);
        TextView tv2 = v.findViewById(R.id.tv2);
        ImageView iv2 = v.findViewById(R.id.iv2);
        final File f = files[position];
        Bitmap bmp = BitmapFactory.decodeFile(f.getPath());
        Bitmap bmp1 = ThumbnailUtils.extractThumbnail(bmp, 40, 40);
        iv1.setImageBitmap(bmp1);
        tv1.setText(f.getName());
        String str = String.valueOf(f.length() / 1024.0) + " kb";
        tv2.setText(str);
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f.delete();
                files = file.listFiles();
                MyAdapter.this.notifyDataSetChanged();
            }
        });
        return v;
    }
}
