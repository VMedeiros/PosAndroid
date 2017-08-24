package com.victor.gestordedemandas.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.victor.gestordedemandas.model.GerenteProjeto;

import java.util.ArrayList;
import java.util.List;

public class GerenteProjetoCRUD {

    private Context mContext;

    public GerenteProjetoCRUD(Context mContext) {
        this.mContext = mContext;
    }

    public void salvar(GerenteProjeto gerente){
        ContentValues values = new ContentValues();

        values.put(GestorDemandasBD.COLUNAS_TBL_GERENTE[1], gerente.getmNomeGerente());

        GestorDemandasBD.getInstance(mContext).getWritableDatabase().insert(GestorDemandasBD.NOME_TB_GERENTE, null, values);

        Log.i("Salvando", gerente.toString());
    }

    public void remover(GerenteProjeto gerente){
        int gerentId = gerente.getId();

        GestorDemandasBD.getInstance(mContext).getWritableDatabase().delete(GestorDemandasBD.NOME_TB_GERENTE, GestorDemandasBD.COLUNAS_TBL_GERENTE[0] + "=" + gerentId, null);


    }

    public void atualizar(GerenteProjeto gerente){
        ContentValues values = new ContentValues();
        values.put(GestorDemandasBD.COLUNAS_TBL_GERENTE[0], gerente.getId());
        values.put(GestorDemandasBD.COLUNAS_TBL_GERENTE[1], gerente.getmNomeGerente());

        GestorDemandasBD.getInstance(mContext).getWritableDatabase().update(GestorDemandasBD.NOME_TB_GERENTE, values, GestorDemandasBD.COLUNAS_TBL_GERENTE[0] + "=" + gerente.getId(), null);

            }

    public GerenteProjeto buscarPorId(int id){

        Cursor cursor = GestorDemandasBD.getInstance(mContext).getReadableDatabase()
                .query(GestorDemandasBD.NOME_TB_GERENTE,
                        GestorDemandasBD.COLUNAS_TBL_GERENTE,
                        GestorDemandasBD.COLUNAS_TBL_GERENTE[0] + "=" +id,
                        null, null,null, null);
        List<GerenteProjeto> gerenteProjeto = new ArrayList<>();

        cursor.moveToFirst();
        while (cursor.moveToNext()){
            gerenteProjeto.add(cursorParaGerente(cursor));
        }

        return gerenteProjeto.get(0);
    }

    public GerenteProjeto cursorParaGerente(Cursor cursor){
        GerenteProjeto gerente = new GerenteProjeto();
        gerente.setId(cursor.getInt(0));
        gerente.setmNomeGerente(cursor.getString(1));

        return  gerente;
    }

    public List<GerenteProjeto> buscarTodos(){
        Cursor cursor = GestorDemandasBD.getInstance(mContext).getReadableDatabase()
                .query(GestorDemandasBD.NOME_TB_GERENTE,
                        GestorDemandasBD.COLUNAS_TBL_GERENTE,
                        null,
                        null, null,null, null);
        List<GerenteProjeto> gerenteProjeto = new ArrayList<>();
        cursor.moveToFirst();
        while (cursor.moveToNext()){
            gerenteProjeto.add(cursorParaGerente(cursor));
        }

        return gerenteProjeto;

    }


}
