package com.peoplestrong.mvvmdemo.database.mylibrary;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;

import com.peoplestrong.mvvmdemo.database.DateConverter;
import com.peoplestrong.mvvmdemo.model.Article;

import java.lang.annotation.Inherited;
import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
@TypeConverters(DateConverter.class)
public interface MyLibraryDao {
    @Query("select * from mylibrary")
    LiveData<List<Article>> getAllLibrary();
    @Query("select * from mylibrary")
    List<Article> getAllLibraryWithoutLLive();

    @Query("SELECT * FROM mylibrary ORDER BY subName ASC")
    LiveData<List<MyLibrary>> getAllLibraryBySubName();

    @Query("select count(id) from mylibrary where id = :id")
    int findMyLibraryByID(int id);

    @Insert(onConflict = REPLACE)
    void insertLibrary(MyLibrary myLibrary);

    @Query("UPDATE mylibrary SET subName=:subName WHERE id = :id")
    void updateMyLibrary(String subName , int id);

    @Query("delete from mylibrary")
    void deleteLibrary();

}
