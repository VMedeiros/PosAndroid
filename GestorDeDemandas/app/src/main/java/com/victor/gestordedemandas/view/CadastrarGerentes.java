package com.victor.gestordedemandas.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.victor.gestordedemandas.R;
import com.victor.gestordedemandas.data.GerenteProjetoCRUD;
import com.victor.gestordedemandas.model.GerenteProjeto;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CadastrarGerentes extends AppCompatActivity {


    @Bind(R.id.et_nome_gerente)
    EditText mNomeGerente;
    GerenteProjetoCRUD gerenteCRUD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_gerentes);
        ButterKnife.bind(this);
        gerenteCRUD = new GerenteProjetoCRUD(this);
        List<GerenteProjeto> gerentes = gerenteCRUD.buscarTodos();
        for(GerenteProjeto  gerente : gerentes){
            Log.i("Gerente: ", gerente.toString());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cadastro, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.item_cancelar:
                return true;

            case R.id.item_salvar:
                salvarGerente();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }


    }


    private void salvarGerente() {

        GerenteProjeto gerente = new GerenteProjeto();
        gerente.setmNomeGerente(mNomeGerente.getText() == null ? "" : mNomeGerente.getText().toString());
        gerenteCRUD.salvar(gerente);

    }
}
