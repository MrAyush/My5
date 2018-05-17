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
import android.widget.Toast;

import java.io.File;

class ViewHolder{
    TextView tv1;
    TextView tv2;
    ImageView iv1;
    ImageView iv2;
}

public class MyAdapter extends BaseAdapter
{

    private File files[];
    File file;
    private MainActivity mActivity;
    MyAdapter(MainActivity mActivity){
        this.mActivity = mActivity;
        String path = "/storage/sdcard0/WhatsApp/Media/WhatsApp Images/";
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
        return files[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mActivity);
        final File f = files[position];
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.my_list,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.iv1 = convertView.findViewById(R.id.iv1);
            viewHolder.tv1 = convertView.findViewById(R.id.tv1);
            viewHolder.tv2 = convertView.findViewById(R.id.tv2);
            viewHolder.iv2 = convertView.findViewById(R.id.iv2);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Bitmap bmp = BitmapFactory.decodeFile(f.getPath());
        Bitmap bmp1 = ThumbnailUtils.extractThumbnail(bmp,40,40);
        viewHolder.iv1.setImageBitmap(bmp1);
        viewHolder.tv1.setText(f.getName());
        String s = String.valueOf(f.length() / 1024) + " kb";
        viewHolder.tv2.setText(s);
        viewHolder.iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean b = f.delete();
                if (!b){
                    Toast.makeText(mActivity,"Can't delete the file!!", Toast.LENGTH_SHORT).show();
                }
                files = file.listFiles();
                MyAdapter.this.notifyDataSetChanged();
            }
        });

        return convertView;
    }
}
