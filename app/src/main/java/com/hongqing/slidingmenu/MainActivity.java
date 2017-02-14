package com.hongqing.slidingmenu;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Bitmap> imageViews;
    private SlidingMenu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);

        initSlidingment();
    }

    private void initSlidingment() {
        menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.RIGHT);
        //设置触摸屏幕的样式    设置边缘模式滑动打开menu(整个屏幕，边缘，不能通过手势启动三个参数)
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        menu.setBehindWidth(550);//相对屏幕的偏移量
        menu.setBehindScrollScale(0);//设置出来的样式   1平移出现  0  代表下方出现
        menu.setFadeDegree(0.5f);//设置渐出值
        menu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);//设置出来时 的样式有基于window和content
        View view = LayoutInflater.from(this).inflate(R.layout.list_menu, null);
        ListView lv_listView = (ListView) view.findViewById(R.id.lv_listView);
//        List<Bitmap> imageViews=new ArrayList<>();
//        imageViews.add()
        lv_listView.setAdapter(new MenuListViewAdapter(this));
//        menu.setMenu(R.layout.slidingmenu);
        menu.setMenu(view);
    }

    class MenuListViewAdapter extends BaseAdapter {
        private Context context;
        private int[] imageViews={R.mipmap.down,R.mipmap.peel,R.mipmap.sleep_time};
        private String[] content = {"我的下载", "换肤", "睡眠定时"};

        public MenuListViewAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return content.length;
        }

        @Override
        public Object getItem(int i) {
            return content[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            MyViewHolder myViewHolder = null;
            if (view == null) {
                view = LayoutInflater.from(context).inflate(R.layout.list_item_menu, null);
                myViewHolder = new MyViewHolder();
                myViewHolder.imageView = (ImageView) view.findViewById(R.id.imageView);
                myViewHolder.tv_content = (TextView) view.findViewById(R.id.tv_content);
                view.setTag(myViewHolder);

            }else{
                myViewHolder= (MyViewHolder) view.getTag();
            }
            myViewHolder.imageView.setImageResource(imageViews[position]);
            myViewHolder.tv_content.setText(content[position]);
            return view;
        }

        class MyViewHolder {
            ImageView imageView;
            TextView tv_content;
        }
    }
}
