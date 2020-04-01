package com.example.room_demo02_20200331;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class StudentViewModel extends AndroidViewModel {

    private StudentRepository studentRepository;//jetpack数据库操作在viewmodel层中  首先拿到仓库

    public StudentViewModel(@NonNull Application application) {
        super(application);
        studentRepository=new StudentRepository(application);
    }

    void insert(Student... students){
        studentRepository.insert(students);
    }
    void delete(Student student){
        studentRepository.delete(student);
    }
    void update(Student student){
        studentRepository.update(student);
    }
    List<Student> getAll(){
        return studentRepository.getAll();
    }

    LiveData<List<Student>> getAllLiveDataStudent(){
        return studentRepository.getAllLiveDataStudent();
    }

    //这里可以清除资源
    @Override
    protected void onCleared() {
        super.onCleared();
    }
}






