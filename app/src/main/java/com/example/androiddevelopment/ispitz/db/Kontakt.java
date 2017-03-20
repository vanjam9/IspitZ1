package com.example.androiddevelopment.ispitz.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by androiddevelopment on 20.3.17..
 */







@DatabaseTable(tableName=Kontakt.TABLE_NAME_KONTAKT)
public class Kontakt {


    public static final String TABLE_NAME_KONTAKT = "kontakt";

    public static final String FIELD_NAME_ID ="id" ;

    public static final String FIELD__NAME_TELEFON ="telefon" ;
    public static final String FIELD_NAME_USER = "mUser";


    @DatabaseField(columnName = FIELD_NAME_ID, generatedId = true)
    private int mId;

    @DatabaseField(columnName = FIELD__NAME_TELEFON)
    private String mName;



    @DatabaseField(columnName = FIELD_NAME_USER, foreign = true, foreignAutoRefresh = true)
    private Lkontakti mUser;


    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public Lkontakti getmUser() {
        return mUser;
    }

    public void setmUser(Lkontakti mUser) {
        this.mUser = mUser;
    }



    @Override
    public String toString() {
        return "Kontakt{" +
                "mName='" + mName + '\'' +
                '}';
    }
}
