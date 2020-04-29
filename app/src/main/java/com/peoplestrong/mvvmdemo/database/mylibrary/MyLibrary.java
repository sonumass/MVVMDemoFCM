package com.peoplestrong.mvvmdemo.database.mylibrary;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.peoplestrong.mvvmdemo.database.DateConverter;

import java.util.Date;

@Entity
public class MyLibrary {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public MyLibrary(String subName, String work, String remark, String imagePath, Date createdAt) {
        this.subName = subName;
        this.work = work;
        this.remark = remark;
        this.imagePath = imagePath;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    private String subName;
    private String work;
    private String remark;
    private String imagePath;
    @TypeConverters(DateConverter.class)
    private Date createdAt;
}
