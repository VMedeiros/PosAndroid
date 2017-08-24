package com.victor.gestordedemandas.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class GestorDemandasBD extends SQLiteOpenHelper{

    public static GestorDemandasBD instance = null;

    public final static String NOME_BD = "gestor_demandas.db";
    public final static int VERSAO = 1;

    public final static String NOME_TB_GERENTE = "gerente_tbl";
    public final static String NOME_TB_DEMANDA = "demanda_tbl";



    public static String[] COLUNAS_TBL_GERENTE = {"id", "nome"};
    public static String[] COLUNAS_TBL_DEMANDA = {"id", "gerente", "numero", "titulo", "status", "responsavel", "prazo","observacao" };


    public final static String CRIA_TBL_GERENTE =
            "CREATE TABLE "+NOME_TB_GERENTE+"("+
                    COLUNAS_TBL_GERENTE[0]+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    COLUNAS_TBL_GERENTE[1]+" TEXT NOT NULL );";

    public final static String CRIA_TBL_DEMANDA = "CREATE TABLE "+NOME_TB_DEMANDA+"("+
            COLUNAS_TBL_DEMANDA[0]+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            COLUNAS_TBL_DEMANDA[1]+" TEXT NOT NULL,"+
            COLUNAS_TBL_DEMANDA[2]+" INTEGER NOT NULL,"+
            COLUNAS_TBL_DEMANDA[3]+" TEXT NOT NULL,"+
            COLUNAS_TBL_DEMANDA[4]+" TEXT NOT NULL,"+
            COLUNAS_TBL_DEMANDA[5]+" TEXT NOT NULL,"+
            COLUNAS_TBL_DEMANDA[6]+" TEXT NOT NULL,"+
            COLUNAS_TBL_DEMANDA[7]+" TEXT );";






    private GestorDemandasBD(Context context) {
        super(context, NOME_BD, null, VERSAO);
    }

    public static GestorDemandasBD getInstance(Context context){
        if(instance == null){
            instance = new GestorDemandasBD(context);
        }
        return instance;
    }





    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CRIA_TBL_GERENTE);
        Log.i("GerenteDB", "Tabela criada: " + CRIA_TBL_GERENTE);
        db.execSQL(CRIA_TBL_DEMANDA);
        Log.i("DEMANDADB", "DEMANDA CRIADA" + CRIA_TBL_DEMANDA);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
