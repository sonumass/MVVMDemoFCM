package com.peoplestrong.mylibrary.FieldElements.EventElements;

import android.database.Cursor;
import android.provider.ContactsContract;

import com.google.gson.annotations.Expose;

import com.peoplestrong.Abstracts.ElementParent;
import com.peoplestrong.mylibrary.Utilities.Utilities;

public class EventLabelElement extends ElementParent
{
    @Expose
    private String eventLable = "";
    @Expose
    private final String elementType;
    public static final String column = ContactsContract.CommonDataKinds.Event.LABEL;
    public static final String mime = ContactsContract.CommonDataKinds.Event.CONTENT_ITEM_TYPE;

    public EventLabelElement(Cursor cursor)
    {
        elementType = this.getClass().getSimpleName();
        setValue(cursor);
    }
    @Override
    public String getElementType()
    {
        return elementType;
    }
    @Override
    public String getValue()
    {
        return eventLable;
    }
    @Override
    public void setValue(Cursor cursor)
    {
        if (cursor==null)
        {
            return;
        }

        eventLable = Utilities.getColumnIndex(cursor, column);

        if (eventLable == null)
        {
            eventLable = "";
        }
    }

}