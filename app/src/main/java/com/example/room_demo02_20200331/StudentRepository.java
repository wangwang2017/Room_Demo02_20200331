package com.example.room_demo02_20200331;

import android.content.Context;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

public class StudentRepository {

    private LiveData<List<Student>> liveDataAllStudent;
    private StudentDao studentDao;

    public StudentRepository(Context context){
        AppDatabase database=AppDatabase.getInstance(context);
        studentDao=database.studentDao();
        if(liveDataAllStudent==null){
            liveDataAllStudent = studentDao.getAllLiveDataStudent();
        }
    }
    //提供一些API给viewmodel使用
    void insert(Student... students){
        studentDao.insert(students);
    }
    void delete(Student student){
        studentDao.delete(student);
    }
    void update(Student student){
        studentDao.update(student);
    }
    List<Student> getAll(){
        return studentDao.getAll();
    }

    LiveData<List<Student>> getAllLiveDataStudent(){
        return studentDao.getAllLiveDataStudent();
    }

}
