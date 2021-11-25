package com.example.intentsgeoemail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button abrir=findViewById(R.id.buttonWeb);
        abrir.setOnClickListener(this::abrir);
    }


    public void abrir(View v){
        Intent i = new Intent();
        Intent chooser=null;

        switch(v.getId()){
            case R.id.buttonWeb:
                EditText edURL=(EditText)findViewById(R.id.texturl);
                i.setAction(Intent.ACTION_VIEW);
                i.setData(Uri.parse(edURL.getText().toString()));
                chooser=i.createChooser(i,"Elige Navegador");
                startActivity(i);
                Toast.makeText(this.getApplicationContext(),
                        "Acceso a web!",Toast.LENGTH_LONG).show();                 break;

        }
    }
}