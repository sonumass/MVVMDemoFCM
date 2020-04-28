package com.peoplestrong.mylibrary.ElementContainers;

import android.database.Cursor;

import com.peoplestrong.mylibrary.FieldElements.NumberElements.LabelElement;
import com.peoplestrong.mylibrary.FieldElements.NumberElements.NormNumElement;
import com.peoplestrong.mylibrary.FieldElements.NumberElements.NumberElement;
import com.peoplestrong.mylibrary.FieldElements.NumberElements.NumberTypeElement;
import com.peoplestrong.mylibrary.Utilities.Utilities;
import com.google.gson.annotations.Expose;
import java.util.HashSet;
import java.util.Set;

public class NumberContainer
{
    @Expose
    private NumberElement number;
    @Expose
    private NormNumElement normalizedNumber;
    @Expose
    private NumberTypeElement numType;
    @Expose
    private LabelElement numLabel;

    public NumberContainer(Cursor cursor) {
        number = new NumberElement(cursor);
        normalizedNumber = new NormNumElement(cursor);
        numType = new NumberTypeElement(cursor);
        numLabel = new LabelElement(cursor);
    }

    public static Set<String> getFieldColumns() {
        Set<String> columns = new HashSet<>();
        columns.add(NumberElement.column);
        columns.add(NormNumElement.column);
        columns.add(NumberTypeElement.column);
        columns.add(LabelElement.column);
        return columns;
    }

    public String elementValue() {

        String result = Utilities.elementValue(number);
        return result;
    }
    public String getNormalizedNumber() {

        String result = Utilities.elementValue(normalizedNumber);
        return result;
    }
    public String getNumlabel() {

        String result = Utilities.elementValue(numLabel);
        return result;
    }
    public String getNumType() {

        String result = Utilities.elementValue(numType);
        return result;
    }


}