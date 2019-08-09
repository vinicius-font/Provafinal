package com.example.aluno.sendemail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText txtAssunto;
    EditText txtEmailDestino;
    EditText txtMensagem;
    Button buttonEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtAssunto = (EditText) findViewById(R.id.txtAssunto);
        txtEmailDestino = (EditText) findViewById(R.id.txtEmailDestino);
        txtMensagem = (EditText) findViewById(R.id.txtMensagem);

        buttonEnviar = (Button) findViewById(R.id.buttonEnviar);

        buttonEnviar.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                String string = txtEmailDestino.getText().toString();
                String[] parts = string.split(";");
                i.putExtra(Intent.EXTRA_EMAIL  , parts);
                i.putExtra(Intent.EXTRA_SUBJECT, (txtAssunto.getText().toString()));
                i.putExtra(Intent.EXTRA_TEXT   , (txtMensagem.getText().toString()));
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(MainActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }

                txtAssunto.setText("");
                txtEmailDestino.setText("");
                txtMensagem.setText("");

            }
        });




    }


}
