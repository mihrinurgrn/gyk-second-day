package com.mihrinurgrn.seconddayingyk;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button btnBaslat, btnOynat, btnDurdur;

    private MediaRecorder mediaRecorder;

    private String recordedFile;

    private final int MY_PERMISSIONS_RECORD_AUDIO=1;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnBaslat = findViewById(R.id.btnBaslat);
        btnDurdur = findViewById(R.id.btnDurdur);
        btnOynat = findViewById(R.id.btnOynat);

        btnOynat.setEnabled(false);
        btnDurdur.setEnabled(false);

        recordedFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording.3gp";


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, MY_PERMISSIONS_RECORD_AUDIO);
        }
        else {
            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP); //cikis formati
            mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
            mediaRecorder.setOutputFile(recordedFile);

        }
        btnBaslat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    mediaRecorder.prepare();
                    mediaRecorder.start();
                    btnBaslat.setEnabled(false);
                    btnOynat.setEnabled(false);
                    btnDurdur.setEnabled(true);

                    Toast.makeText(MainActivity.this, "Kayit basladi", Toast.LENGTH_SHORT).show();

                }

                catch(IllegalStateException ise)
                {
                    ise.printStackTrace();
                } catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
        });


        btnDurdur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaRecorder.stop();
                mediaRecorder.release();
                mediaRecorder=null;
                btnDurdur.setEnabled(false);
                btnOynat.setEnabled(true);
                Toast.makeText(MainActivity.this, "Kayit islemi tamamlandi", Toast.LENGTH_SHORT).show();
            }
        });

        btnOynat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mediaPlayer= new MediaPlayer();
                try
                {
                    mediaPlayer.setDataSource(recordedFile);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    Toast.makeText(MainActivity.this, "Ses caliyor", Toast.LENGTH_SHORT).show();
                }

                catch(IOException e)
                {

                    e.printStackTrace();
                }
            }
        });
    }



}
