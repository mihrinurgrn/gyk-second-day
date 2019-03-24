package com.mihrinurgrn.seconddayingyk;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class mapActivity extends AppCompatActivity {

    private EditText etEnlem, etBoylam;

    private Button btnHaritaGit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);


        final EditText etEnlem=findViewById(R.id.editTextEnlem);
        final EditText etBoylam=findViewById(R.id.editTextBoylam);

        Button buttonMap=findViewById(R.id.btnHaritaGit);

        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lat=etEnlem.getText().toString();
                String lon=etBoylam.getText().toString();
                Intent intent= new Intent(Intent.ACTION_VIEW, Uri.parse("geo:"+lat+","+lon));
                startActivity(intent);
            }
        });
    }
}
