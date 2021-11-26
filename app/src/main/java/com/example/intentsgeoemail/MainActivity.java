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
        Intent intent = getIntent();
        String action = intent.getAction();
        String textoRecibido;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button abrir=findViewById(R.id.buttonWeb);
        abrir.setOnClickListener(this::abrir);
        Button map = findViewById(R.id.buttonMap);
        map.setOnClickListener(this::abrir);
        Button mail = findViewById(R.id.buttonMail);
        mail.setOnClickListener(this::abrir);


        EditText ed = findViewById(R.id.edRecibido);

        if (action.equals(Intent.ACTION_SEND)){
            textoRecibido= intent.getStringExtra(Intent.EXTRA_TEXT);
            if (textoRecibido!=null){
                ed.setText(textoRecibido);
            }
        }
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
                        "Acceso a web!",Toast.LENGTH_LONG).show();
                break;
            case R.id.buttonMap:
                EditText latitud = findViewById(R.id.textLatitud);
                EditText longitud = findViewById(R.id.textLongitud);
                i.setAction(Intent.ACTION_VIEW);
                i.setData(Uri.parse("geo:"+latitud.getText().toString()+
                        " ," + longitud.getText().toString()));
                chooser = i.createChooser(i,"Lanzar mapas");
                startActivity(i);
                Toast.makeText(this.getApplicationContext(),
                        "Abriendo maps",Toast.LENGTH_LONG).show();
                break;
            case R.id.buttonMail:
                EditText email = findViewById(R.id.textMail);
                i.setAction(Intent.ACTION_SEND);
                i.setData(Uri.parse("mailto:"));
                String para[]={email.getText().toString(),"heisenferg@gmail.com"};
                i.putExtra(Intent.EXTRA_EMAIL, para);
                i.putExtra(Intent.EXTRA_SUBJECT, "Saludos desde Android");
                i.putExtra(Intent.EXTRA_TEXT, "Email enviado desde app");
                i.setType("message/rfc822");
                chooser= i.createChooser(i, "Enviar email");
                startActivity(i);
                Toast.makeText(this.getApplicationContext(),
                        "Abriendo correo",Toast.LENGTH_LONG).show();
                break;

        }
    }
}