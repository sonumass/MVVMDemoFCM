package com.peoplestrong.mylibrary.ElementContainers;

import android.database.Cursor;

import com.google.gson.annotations.Expose;

import java.util.HashSet;
import java.util.Set;

import com.peoplestrong.mylibrary.FieldElements.NoteElement.NoteElement;
import com.peoplestrong.mylibrary.Utilities.Utilities;


public class NoteContainer
{
    @Expose
    private NoteElement note;

    public NoteContainer(Cursor cursor)
    {
        note = new NoteElement(cursor);
    }

    public static Set<String> getFieldColumns()
    {
        Set<String> columns = new HashSet<>();
        columns.add(NoteElement.column);
        return columns;
    }

    public String getNote()
    {
        String result = Utilities.elementValue(note);
        return result;
    }

}
