package com.example.vanguard2;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ConexionServidor extends AsyncTask<String,Void,String> {
private static final String USER_AGENT="Mozilla/5.0";
private static final String POST_DATA="";
private String servidor;
public ConexionServidor(String servidor){
    this.servidor=servidor;
}

    @Override
    protected String doInBackground(String... strings) {
    String respuesta="";
    URL url=null;
        try {
            url=new URL("https://"+servidor+"/api");
            HttpURLConnection con=(HttpURLConnection) url.openConnection();
            con.setRequestProperty("User-Agent",USER_AGENT);
            int codigo=con.getResponseCode();
            if(codigo== HttpURLConnection.HTTP_OK){
                //Everything works fine :)
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String lineas;
                StringBuffer res=new StringBuffer();
                while((lineas=in.readLine() )!=null){
                    res.append(lineas);
                }
                in.close();
                Log.e("RESPUESTA",res.toString());
                respuesta=res.toString();
            }else{
                System.out.print(codigo);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return respuesta;
    }
}
