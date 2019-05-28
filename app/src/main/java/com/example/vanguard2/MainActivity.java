package com.example.vanguard2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
Button blogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String respuesta= null;
        try {
            respuesta = new ConexionServidor("http://deskode.com/toxic/usuarios.php?email=silveira.alberto24@gmail.com&password=Patito123").execute().get();
            JSONObject json=(JSONObject) new JSONObject(respuesta);
            String datos_result=json.getJSONArray("usuario").getJSONObject(0).get("Nombre").toString();
            Toast.makeText(getApplicationContext(),datos_result+"EJemplo",Toast.LENGTH_LONG).show();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        blogin= findViewById(R.id.blogin);

        blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(MainActivity.this,Vanguard.class);
                startActivity(intent);
            }
        });
    }
}
