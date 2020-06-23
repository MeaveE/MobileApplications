package com.example.eileenrattigan.feildguide;

import android.database.Cursor;
import android.graphics.drawable.Animatable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;



public class Display extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        ImageView image = findViewById(R.id.imagemain);
        TextView name = findViewById(R.id.name);
        TextView desc = findViewById(R.id.Description);
        TextView habitat = findViewById(R.id.Habitat);
        TextView lifespan = findViewById(R.id.lifespan);

        DatabaseAnimal db = new DatabaseAnimal(this);
        db.open();

        Cursor creCursor = db.getAnimal();
        creCursor.moveToFirst();
        String nam = creCursor.getString(creCursor.getColumnIndex(db.NAME));
        Integer im = creCursor.getInt(creCursor.getColumnIndex(db.IMAGE));
        String dec = creCursor.getString(creCursor.getColumnIndex(db.DESC));
        String hab = creCursor.getString(creCursor.getColumnIndex(db.HABITAT));
        String life = creCursor.getString(creCursor.getColumnIndex(db.LIFESPAN));

        name.setText(nam);
        image.setImageResource(im);
        desc.setText(dec);
        habitat.setText(hab);
        lifespan.setText(life);

        String[] columns = new String[]{"_id","image","name","description","habitat","lifespan"};
        int[] rowIDs = new int [] {R.id.id, R.id.imagemain,R.id.name,R.id.Description,R.id.Habitat,R.id.lifespan};


        SimpleCursorAdapter AniAdapter = new SimpleCursorAdapter(this,R.layout.row, creCursor, columns, rowIDs);


        db.close();

    }
}
