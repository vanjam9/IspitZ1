package com.example.androiddevelopment.ispitz.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.androiddevelopment.ispitz.MainActivity;
import com.example.androiddevelopment.ispitz.R;
import com.example.androiddevelopment.ispitz.db.DatabaseHelper;
import com.example.androiddevelopment.ispitz.db.Kontakt;
import com.example.androiddevelopment.ispitz.db.Lkontakti;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by androiddevelopment on 20.3.17..
 */

public class SecondActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private Lkontakti l;

    private EditText name;
    private EditText surname;
    private EditText adress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Each lifecycle method should call the method it overrides
        super.onCreate(savedInstanceState);
        // setContentView method draws UI
        setContentView(R.layout.activity_priprema_detail);

        // Shows a toast message (a pop-up message)
        //   Toast toast = Toast.makeText(getBaseContext(), "Activity.onCreate()", Toast.LENGTH_SHORT);
        // toast.show();
        //}
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        int key = getIntent().getExtras().getInt(MainActivity.key);

        try {
             l = getDatabaseHelper().getGlumcibDao().queryForId(key);

            name = (EditText) findViewById(R.id.Name);
            surname = (EditText) findViewById(R.id.surname);
            adress=(EditText)findViewById(R.id.adress);

            name.setText(l.getName());
            surname.setText(l.getSurname());
            adress.setText(l.getAdress());

    }catch (SQLException e) {
            e.printStackTrace();
        }



        final ListView listView = (ListView) findViewById(R.id.telefoni_kontakta);

        try {

            List<Kontakt> list = getDatabaseHelper().getKontaktDao().queryBuilder()
                    .where()
                    .eq(Kontakt.FIELD_NAME_USER, l.getId())
                    .query();

            ListAdapter adapter = new ArrayAdapter<>(this, R.layout.list_item, list);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Kontakt m = (Kontakt) listView.getItemAtPosition(position);


                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_create:
                //OTVORI SE DIALOG UNESETE INFORMACIJE
                final Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.priprema_add_telefon);

                Button add = (Button) dialog.findViewById(R.id.add_telefon);
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText telefon = (EditText) dialog.findViewById(R.id.telefoni_kontakta);

                        Kontakt m = new Kontakt();
                        m.setmName(name.getText().toString());


                        try {
                            getDatabaseHelper().getKontaktDao().create(m);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        //URADITI REFRESH
                        refresh();


                        dialog.dismiss();
                    }
                });

                dialog.show();

                break;



            case R.id.action_update:
                //POKUPITE INFORMACIJE SA EDIT POLJA

                l.setName(name.getText().toString());
              l.setSurname(surname.getText().toString());


                try {
                    getDatabaseHelper().getGlumcibDao().update(l);

                    Toast.makeText(this,("Telephone detail updated"),Toast.LENGTH_SHORT).show();

                } catch (SQLException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.action_delete:
                try {
                    getDatabaseHelper().getGlumcibDao().delete(l);


                    finish(); //moramo pozvati da bi se vratili na prethodnu aktivnost
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }



    private void refresh() {
        ListView listview = (ListView) findViewById(R.id.listaKontakata);

        if (listview != null){
            ArrayAdapter<Kontakt> adapter = (ArrayAdapter<Kontakt>) listview.getAdapter();

            if(adapter!= null)
            {
                try {
                    adapter.clear();
                    List<Kontakt> list = getDatabaseHelper().getKontaktDao().queryBuilder()
                            .where()
                            .eq(Kontakt.FIELD_NAME_USER, l.getId())
                            .query();

                    adapter.addAll(list);

                    adapter.notifyDataSetChanged();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }












    public DatabaseHelper getDatabaseHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return databaseHelper;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        // nakon rada sa bazo podataka potrebno je obavezno
        //osloboditi resurse!
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }
    // onOptionsItemSelected method is called whenever an item in the Toolbar is selected.

















}