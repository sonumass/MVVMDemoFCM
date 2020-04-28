package com.peoplestrong.mylibrary.FieldElements.AddressElement;

import android.database.Cursor;
import android.provider.ContactsContract;

import com.google.gson.annotations.Expose;

import com.peoplestrong.Abstracts.ElementParent;
import com.peoplestrong.mylibrary.Utilities.Utilities;

public class AddressElement extends ElementParent
{
    @Expose
    private String address = "";
    @Expose
    private final String elementType;
    public static final String column = ContactsContract.CommonDataKinds.StructuredPostal.FORMATTED_ADDRESS;
    public static final String mime = ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE;


    public AddressElement(Cursor cursor)
    {
        elementType = this.getClass().getSimpleName();
        setValue(cursor);
    }

    @Override
    public String getValue() {
        return elementType;
    }
    @Override
    public void setValue(Cursor cursor)
    {
        if (cursor==null)
        {
            return;
        }

        address = Utilities.getColumnIndex(cursor, column);

        if (address == null)
        {
            address = "";
        }
    }

    @Override
    public String getElementType()
    {
        return getClass().getSimpleName();
    }
}