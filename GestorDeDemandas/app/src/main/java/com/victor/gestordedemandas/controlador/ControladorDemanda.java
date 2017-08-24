package com.victor.gestordedemandas.controlador;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.victor.gestordedemandas.interfaces.DemandasCallbackIF;
import com.victor.gestordedemandas.model.Demanda;
import com.victor.gestordedemandas.parser.DemandaParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ControladorDemanda {

    DemandasCallbackIF demandasCallbackIF;

    public ControladorDemanda(Context context){
        demandasCallbackIF = (DemandasCallbackIF) context;
    }



    public void buscarDemandas(){
        new DemandaAsyncTask().execute();
    }

    public void trataResultado(JSONArray jsonArray){
        List<Demanda> demandas = new ArrayList<>();
        Demanda demanda;
        JSONObject jsonObject;
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject =  jsonArray.getJSONObject(i);

                demanda = new DemandaParser().parse(jsonObject.toString());

                demandas.add(demanda);
            }
            demandasCallbackIF.retornoListaDemandas(demandas);

        }catch (JSONException e){
            notificacaoErro(e.getMessage());
        }catch (IOException e){
            notificacaoErro(e.getMessage());

        }
    }


    public void notificacaoErro(String messenger){
        demandasCallbackIF.errorServicoDemandas(messenger);
    }



    class DemandaAsyncTask extends AsyncTask<String, Void, String>{
        String resultado;

        @Override
        protected String doInBackground(String... params) {


            BufferedReader bufferedReader;
            String resultado = "";


            try {
                URL url = new URL("http://10.30.80.49:8080/gestordemandas/rest/demandas");
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("GET");
                connection.setDoInput(true);
                connection.connect();

                StringBuilder builder = new StringBuilder();
                bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String linha;

                while((linha = bufferedReader.readLine()) != null){
                    builder.append(linha + "\n");
                }
                resultado = builder.toString();
                Log.i("AsyncTask", resultado);



            }catch(MalformedURLException e){
                Log.e("URL", e.getMessage());
            }catch(IOException e){
                Log.e("URL", e.getMessage());
            }
            return resultado;
        }


        protected void onPostExecute(String resultado){
            if(resultado == null){
                notificacaoErro("A requisição nao obteve resultado");
            }else{
                try{
                    JSONArray jsonArray = new JSONArray(resultado);
                    trataResultado(jsonArray);
                }catch (JSONException e){
                   notificacaoErro(e.getMessage());
                }
            }
        }


    }

}
