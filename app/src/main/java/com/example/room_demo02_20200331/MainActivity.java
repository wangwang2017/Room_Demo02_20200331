package com.example.room_demo02_20200331;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.room_demo02_20200331.adapter.GoodsAdapter;

import java.util.List;
import java.util.Observable;

public class MainActivity extends AppCompatActivity {

    StudentViewModel studentViewModel;
    ListView listView;


    @Nullable
    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        Log.i("jett","onRetainCustomNonConfigurationInstance");
        return super.onRetainCustomNonConfigurationInstance();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public Object getLastNonConfigurationInstance() {
        Log.i("jett","getLastNonConfigurationInstance");
        return super.getLastNonConfigurationInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listView);

        studentViewModel= ViewModelProviders.of(this).get(StudentViewModel.class);
        //添加观察者
        studentViewModel.getAllLiveDataStudent().observe(this, new Observer<List<Student>>() {
                    @Override
                    public void onChanged(List<Student> students) {
                        Log.i("jett","记录数:"+students.size());
                        listView.setAdapter(new GoodsAdapter(MainActivity.this,students));
                    }
                }
        );


//        for (int i = 0; i < 50; i++) {
//            studentViewModel.insert(new Student("jett","123",1));
//        }

        //由于mainactivity重置了线程  旋转屏幕后  会有多个线程在跑
        new Thread(){
            @Override
            public void run() {
                int x=0;
                for (int i = 0; i < 50; i++) {
                    try{
                        Thread.sleep(1000);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    studentViewModel.update(new Student(6,"jett"+i,"123",1));
                }
            }
        }.start();

    }
}





