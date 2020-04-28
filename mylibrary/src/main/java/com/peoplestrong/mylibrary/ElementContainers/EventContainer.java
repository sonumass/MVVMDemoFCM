package com.peoplestrong.mylibrary.ElementContainers;

import android.database.Cursor;

import com.google.gson.annotations.Expose;

import java.util.HashSet;
import java.util.Set;

import com.peoplestrong.mylibrary.FieldElements.EventElements.EventLabelElement;
import com.peoplestrong.mylibrary.FieldElements.EventElements.EventStartDateElement;
import com.peoplestrong.mylibrary.FieldElements.EventElements.EventTypeElement;
import com.peoplestrong.mylibrary.Utilities.Utilities;

public class EventContainer
{
    private transient Cursor cursor;
    @Expose
    private EventStartDateElement startDate;
    @Expose
    private EventTypeElement eventType;
    @Expose
    private EventLabelElement eventLable;

    public EventContainer(Cursor cursor)
    {
        this.cursor = cursor;
        startDate = new EventStartDateElement(cursor);
        eventType = new EventTypeElement(cursor);
        eventLable = new EventLabelElement(cursor);
    }

    public static Set<String> getFieldColumns()
    {
        Set<String> columns = new HashSet<>();
        columns.add(EventStartDateElement.column);
        columns.add(EventTypeElement.column);
        columns.add(EventLabelElement.column);
        return columns;
    }

    public String getEventStartDate()
    {
        String result = Utilities.elementValue(startDate);
        return result;
    }
    public String getEventType()
    {
        String result = Utilities.elementValue(eventType);
        return result;
    }
    public String getEventLabel()
    {
        String result = Utilities.elementValue(eventLable);
        return result;
    }
}