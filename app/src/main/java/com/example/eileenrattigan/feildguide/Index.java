package com.example.eileenrattigan.feildguide;


import android.app.ListActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class Index extends ListActivity {

   /* String[] Animals ={"Fox","Hare","Grey squirrel","Red squirrel"};
    String[] IMAGE ={"fox","hare","greysquirrel","redsquirrel"};
 */

    final DatabaseAnimal thisDatabase = new DatabaseAnimal(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageButton image = findViewById(R.id.animalimage);
        setContentView(R.layout.activity_index);
        /*
        setListAdapter(new AdapterX(this, android.R.layout.simple_list_item_1, Animals));
        setListAdapter(new AdapterX(this, R.layout.row, Animals));

       EditText filter = findViewById(R.id.searchFilter);
        // */
        thisDatabase.open();

        final ImageButton search = findViewById(R.id.imageButton);

        thisDatabase.insertAnimals("Fox", "R.drawable.fox", "Red fur dog like", "forest and grassland", "2-5 years");
        thisDatabase.insertAnimals("Hare", "R.drawable.irishHare", "Hare brown fur", "forest and grassland", "9 years");
        thisDatabase.insertAnimals("Grey squirrel", "R.drawable.greysquirrel", "Grey fur harmful to red Squirrel", "forest", "6 years");
        thisDatabase.insertAnimals("Red squirrel", "R.drawable.redsquirrel", "Red fur native species", "forest", "3 years");

        final Cursor creCursor = thisDatabase.getAllAnimals();
        String[] columns = new String[]{"_id", "image", "name", "status"};
        int[] rowIDs = new int[]{R.id.id, R.id.animalimage, R.id.animalName, R.id.imageButtonAni};
        SimpleCursorAdapter AniAdapter = new SimpleCursorAdapter(this, R.layout.row, creCursor, columns, rowIDs);
        setListAdapter(AniAdapter);

        final Integer[] imgs = {R.drawable.fox, R.drawable.irishhare, R.drawable.greysquirrel, R.drawable.redsquirrel};

//Caused by: java.lang.NullPointerException: Attempt to invoke virtual method 'void android.widget.ImageButton.setImageBitmap(android.graphics.Bitmap)' on a null object reference
//     when trying to invoke below
 //      Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.fox);
//        image.setImageBitmap(bMap);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thisDatabase.open();
                final EditText SearchFilter = findViewById(R.id.search);
                String query = SearchFilter.getText().toString().trim();
                Cursor searchCurs = thisDatabase.getSearch(query);


                if (searchCurs.moveToFirst()) {
                    TextView result = findViewById(R.id.animalName);
                    result.setText(query);

                }
            }
        });


        thisDatabase.close();

    }//end oncreate

    public void onListItemClick(ListView parent, View v, int position, long id) {
        super.onListItemClick(parent, v, position, id);

        final Cursor creCursor = thisDatabase.getAllAnimals();
        String[] columns = new String[]{"_id", "image", "name", "status"};
        int[] rowIDs = new int[]{R.id.id, R.id.animalimage, R.id.animalName, R.id.imageButtonAni};
        SimpleCursorAdapter AniAdapter = new SimpleCursorAdapter(this, R.layout.row, creCursor, columns, rowIDs);
        setListAdapter(AniAdapter);
        Cursor cre = (Cursor) AniAdapter.getItem(position);

        startActivity(new Intent(Index.this, Display.class));

        final ImageButton icon = findViewById(R.id.imageButtonAni);
        Cursor stats = thisDatabase.status();
        int getstat = 0;
        if (stats.moveToFirst())
            getstat = stats.getInt(0);

        if(getstat==1) {
            Toast.makeText(getApplicationContext(),"you have found this creature!",Toast.LENGTH_LONG).show();

        } else {
            icon.setImageResource(R.drawable.foundanimal);
            thisDatabase.insertStatus(1);
        }


    }

}


    /* everything that didn't work out

        //ImageView Ani = findViewById(R.id.animalimage);
        //Ani.setImageResource(R.drawable.fox);
        //setContentView(R.layout.row);
        //ImageButton animal = findViewById(R.id.animalimage);
     /*
        Cursor stats = thisDatabase.status();
        int getstat = 0;
        if (stats.moveToFirst())
            getstat = stats.getInt(0);

        if(getstat==1) {
            icon.setImageResource(R.drawable.foundanimal);
        } else {
            icon.setImageResource(R.drawable.notfound);
        }
*/
       /* int resourceid = R.drawable.fox;
        String image = creCursor.getString("image").trim()

refrenced from stack overflow answer https://stackoverflow.com/questions/33452195/how-to-load-images-from-drawable-to-simplecursoradapter
AniAdapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                switch (view.getId()) {
                    case R.id.animalimage:
                        int imageType = creCursor.getInt(columnIndex);
                        int imageToBeShown=0;
                        switch (imageType) {
                            case 1:
                                imageToBeShown=imgs[0];
                                break;
                            case 2:
                                imageToBeShown=imgs[1];
                                break;
                            case 3:
                                imageToBeShown=imgs[2];
                                break;
                            case 4:
                                imageToBeShown=imgs[3];
                                break;
                        }
                        ((ImageView)view).setImageResource(imageToBeShown);

                       // Bitmap bitmap = BitmapFactory.decodeFile(imageToBeShown.getPath());
                       // R.id.animalimage.setImageBitmap(bitmap);

                        return true;
                }
                return false;
            }

        });

        startActivity(new Intent(Index.this,Display.class));
        final ImageButton icon = findViewById(R.id.imageButtonAni);


       icon.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {



           }
       });*/

/*/    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null)
        {
            LayoutInflater inflater = getLayoutInflater();
            convertView  = (LinearLayout)inflater.inflate(R.layout.row, null);
        }

        ImageButton Button1= convertView  .findViewById(R.id.animalimage);

        Button1.setOnClickListener(new android.view.View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Your code that you want to execute on this button click
                startActivity(new Intent(Index.this,Display.class));
            }

        });

        return convertView ;
    }
*/



