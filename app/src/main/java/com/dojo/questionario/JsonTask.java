package com.dojo.questionario;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JsonTask extends AsyncTask<String, String, String> {

    JSONObject listaJson ;
    String txtTitulo ;
    JSONArray questionario;

    protected void onPreExecute() {
        super.onPreExecute();
    }

    protected String doInBackground(String... params) {

        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();

            String line = "";

            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
                Log.d("Response: ", "> " + line);
            }
            return buffer.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        Log.i("meuLog", "" + result);

        try{
            listaJson = new JSONObject(result);
            txtTitulo = listaJson.getString("titulo");
            questionario = listaJson.getJSONArray("questionario");

            for(int i=0 ; i<questionario.length() ; i++){
                JSONObject questao = questionario.getJSONObject(i);
                Log.i("meuLog",""+questao.getString("Pergunta"));
                Log.i("meuLog",""+questao.getString("respA"));
                Log.i("meuLog",""+questao.getString("respB"));
                Log.i("meuLog",""+questao.getString("respC"));
                Log.i("meuLog",""+questao.getInt("correta"));

            }

        }catch(JSONException e){
            e.printStackTrace();
        }
    }
}
