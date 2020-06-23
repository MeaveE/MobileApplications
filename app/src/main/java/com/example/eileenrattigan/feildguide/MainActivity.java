package com.example.eileenrattigan.feildguide;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      //  Database thisDatabase = new Database(this);
        //thisDatabase.open();

       // thisDatabase.insertUser("Eileen", "Password");


        final EditText username = findViewById(R.id.editText);
        final EditText password = findViewById(R.id.editText2);
        //String name = username.getText().toString();
        //String pass = password.getText().toString();
        final int[] counter = {3};
        final Button submit = findViewById(R.id.button);
        final TextView tx1 = findViewById(R.id.textView);

//       Cursor log = thisDatabase.login(name, pass);

//modified from https://www.tutorialspoint.com/android/android_login_screen.html

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().trim().equals("Eileen") &&
                        password.getText().toString().trim().equals("Pass")) {

                    startActivity(new Intent(MainActivity.this,Index.class));

                }else{
                    Toast.makeText(getApplicationContext(), "Wrong Credentials",Toast.LENGTH_SHORT).show();

                    tx1.setVisibility(View.VISIBLE);
                    tx1.setBackgroundColor(Color.RED);
                    counter[0]--;
                    tx1.setText(Integer.toString(counter[0]));

                    if (counter[0] == 0) {
                        submit.setEnabled(false);
                    }
                }
            }
        });


       // thisDatabase.close();

    }



}
