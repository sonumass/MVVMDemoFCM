package com.peoplestrong.mylibrary.ElementContainers;

import android.database.Cursor;

import com.google.gson.annotations.Expose;

import java.util.HashSet;
import java.util.Set;

import com.peoplestrong.mylibrary.FieldElements.EmailElements.EmailElement;
import com.peoplestrong.mylibrary.FieldElements.EmailElements.EmailLabelElement;
import com.peoplestrong.mylibrary.FieldElements.EmailElements.EmailTypeElement;
import com.peoplestrong.mylibrary.Utilities.Utilities;

public class EmailContainer
{
    private transient Cursor cursor;
    @Expose
    private EmailElement email;
    @Expose
    private EmailTypeElement emailType;
    @Expose
    private EmailLabelElement emailLabel;

    public EmailContainer(Cursor cursor)
    {
        this.cursor = cursor;
        email = new EmailElement(cursor);
        emailType = new EmailTypeElement(cursor);
        emailLabel = new EmailLabelElement(cursor);
    }

    public static Set<String> getFieldColumns()
    {
        Set<String> columns = new HashSet<>();
        columns.add(EmailElement.column);
        columns.add(EmailTypeElement.column);
        columns.add(EmailLabelElement.column);
        return columns;
    }

    public String getEmail()
    {
        String result = Utilities.elementValue(email);
        return result;
    }
    public String getEmailType() {
        String result = Utilities.elementValue(emailType);
        return result;
    }
    public String getEmailLabel() {
        String result = Utilities.elementValue(emailLabel);
        return result;
    }
}