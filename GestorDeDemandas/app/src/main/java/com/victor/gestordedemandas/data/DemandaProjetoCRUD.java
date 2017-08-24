package com.victor.gestordedemandas.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.victor.gestordedemandas.model.Demanda;

import java.util.ArrayList;
import java.util.List;

public class DemandaProjetoCRUD {
    private Context mContext;

    public DemandaProjetoCRUD(Context mContext){
        this.mContext = mContext;
    }

    public void salvarDemanda(Demanda demanda){
        ContentValues values = new ContentValues();

        values.put(GestorDemandasBD.COLUNAS_TBL_DEMANDA[1], demanda.getGerenteProjeto());
        values.put(GestorDemandasBD.COLUNAS_TBL_DEMANDA[2], demanda.getmNumero());
        values.put(GestorDemandasBD.COLUNAS_TBL_DEMANDA[3], demanda.getmTitulo());
        values.put(GestorDemandasBD.COLUNAS_TBL_DEMANDA[4], demanda.getmStatus());
        values.put(GestorDemandasBD.COLUNAS_TBL_DEMANDA[5], demanda.getmResponsavel());
        values.put(GestorDemandasBD.COLUNAS_TBL_DEMANDA[6], demanda.getmPrazo());
        values.put(GestorDemandasBD.COLUNAS_TBL_DEMANDA[7], demanda.getmObservacoes());

        GestorDemandasBD.getInstance(mContext).getWritableDatabase().insert(GestorDemandasBD.NOME_TB_DEMANDA, null, values);
        Log.i("Salvando demanda", demanda.toString());

    }

    public void removerDemanda(Integer demanda){
        int demandaId = demanda;

        GestorDemandasBD.getInstance(mContext).getWritableDatabase().delete(GestorDemandasBD.NOME_TB_DEMANDA, GestorDemandasBD.COLUNAS_TBL_DEMANDA[0] + "=" + demandaId, null);

    }

    public void atualizarDemanda(Demanda demanda, int id){
        ContentValues values = new ContentValues();

        values.put(GestorDemandasBD.COLUNAS_TBL_DEMANDA[1], demanda.getGerenteProjeto());
        values.put(GestorDemandasBD.COLUNAS_TBL_DEMANDA[2], demanda.getmNumero());
        values.put(GestorDemandasBD.COLUNAS_TBL_DEMANDA[3], demanda.getmTitulo());
        values.put(GestorDemandasBD.COLUNAS_TBL_DEMANDA[4], demanda.getmStatus());
        values.put(GestorDemandasBD.COLUNAS_TBL_DEMANDA[5], demanda.getmResponsavel());
        values.put(GestorDemandasBD.COLUNAS_TBL_DEMANDA[6], demanda.getmPrazo());
        values.put(GestorDemandasBD.COLUNAS_TBL_DEMANDA[7], demanda.getmObservacoes());

        GestorDemandasBD.getInstance(mContext).getWritableDatabase().update(GestorDemandasBD.NOME_TB_DEMANDA, values, GestorDemandasBD.COLUNAS_TBL_DEMANDA[0] + "=" + id, null);

    }

    public Demanda buscarDemandaPorId(int id){
        Cursor cursor = GestorDemandasBD.getInstance(mContext).getReadableDatabase().query(GestorDemandasBD.NOME_TB_DEMANDA, GestorDemandasBD.COLUNAS_TBL_DEMANDA, GestorDemandasBD.COLUNAS_TBL_DEMANDA[0]+ "=" + id, null, null, null, null);
        List<Demanda> demandas = new ArrayList<>();
        cursor.moveToFirst();
        while(cursor.moveToNext()){
            demandas.add(cursoParaDemanda(cursor));
        }

        return demandas.get(0);


    }

    public Demanda cursoParaDemanda(Cursor cursor){
        Demanda demanda = new Demanda();
        demanda.setId(cursor.getInt(0));
        demanda.setGerenteProjeto(cursor.getString(1));
        demanda.setmNumero(cursor.getInt(2));
        demanda.setmTitulo(cursor.getString(3));
        demanda.setmStatus(cursor.getString(4));
        demanda.setmResponsavel(cursor.getString(5));
        demanda.setmPrazo(cursor.getString(6));
        demanda.setmObservacoes(cursor.getString(7));


        return demanda;
    }

    public List<Demanda> buscarTodasDemandas(){
        Cursor cursor = GestorDemandasBD.getInstance(mContext).getReadableDatabase().query(GestorDemandasBD.NOME_TB_DEMANDA, GestorDemandasBD.COLUNAS_TBL_DEMANDA, null, null, null, null, null);
        List<Demanda> demandas = new ArrayList<>();
        cursor.moveToFirst();
        while (cursor.moveToNext()){
            demandas.add(cursoParaDemanda(cursor));
        }
        return  demandas;
    }







}
