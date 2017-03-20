package com.example.androiddevelopment.ispitz.db;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by androiddevelopment on 20.3.17..
 */
@DatabaseTable(tableName = Lkontakti.TABLE_NAME_KONTAKTI)
public class Lkontakti {

    public static final String TABLE_NAME_KONTAKTI ="Lkontakti" ;
    public static final String FIELD_KONTAKT_NAME = "name";
    public static final String FIELD_NAME_ID = "id";
    public static final String FIELD_KONTAKT_SURNAME = "surname";
    public static final String FIELD_NAME_ADRESS = "adress";
    public static final String FIELD_KONTAKT_TELEFON = "telefon" ;



    @DatabaseField(columnName = FIELD_NAME_ID, generatedId = true)
    private int id;

    @DatabaseField(columnName = FIELD_KONTAKT_NAME)
    private String Name;

    @DatabaseField(columnName = FIELD_KONTAKT_SURNAME)
    private String Surname;

    @DatabaseField(columnName = FIELD_NAME_ADRESS)
    private String Adress;







    @ForeignCollectionField(columnName = Lkontakti.FIELD_KONTAKT_TELEFON, eager = true)
    private ForeignCollection<Kontakt> Telefoni;


    public Lkontakti() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }

    public ForeignCollection<Kontakt> getTelefoni() {
        return Telefoni;
    }

    public void setTelefoni(ForeignCollection<Kontakt> telefoni) {
        Telefoni = telefoni;
    }

    @Override
    public String toString() {
        return "Lkontakti{" +
                "Name='" + Name + '\'' +
                '}';
    }
}
