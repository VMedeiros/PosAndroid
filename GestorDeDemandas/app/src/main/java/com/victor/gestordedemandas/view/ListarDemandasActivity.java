package com.victor.gestordedemandas.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;


import com.victor.gestordedemandas.controlador.ControladorDemanda;
import com.victor.gestordedemandas.data.DemandaProjetoCRUD;
import com.victor.gestordedemandas.interfaces.DemandasCallbackIF;
import com.victor.gestordedemandas.model.Demanda;
import com.victor.gestordedemandas.R;
import com.victor.gestordedemandas.adapter.DemandaPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ListarDemandasActivity extends AppCompatActivity implements DemandasCallbackIF {

    @Bind(R.id.vp_demandas)
    ViewPager mVpDemandas;

    ProgressDialog mProgressDialog;

    List<Demanda> mDemandas;

    ControladorDemanda mControladorDemanda;

    DemandaProjetoCRUD demadaCRUD;

    DemandaPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_demandas);
        ButterKnife.bind(this);

        demadaCRUD = new DemandaProjetoCRUD(this);




        mControladorDemanda = new ControladorDemanda(this);
        mDemandas = new ArrayList<>();
        mDemandas = demadaCRUD.buscarTodasDemandas();
        mPagerAdapter= new DemandaPagerAdapter(getSupportFragmentManager(), mDemandas, getBaseContext());
        mVpDemandas.setAdapter(mPagerAdapter);





    }
/**
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_listar, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.item_lista:
                Toast.makeText(this, "Lista", Toast.LENGTH_LONG).show();
                return true;

            case R.id.item_grade:
                Toast.makeText(this, "Grade", Toast.LENGTH_LONG).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }


    }
**/
    public void inicializarConntroladorDemandas(){
        mProgressDialog = ProgressDialog.show(this, "Carregando...", "Buscandos demadas no servidor");
        mControladorDemanda.buscarDemandas();

    }


    @Override
    public void retornoListaDemandas(List<Demanda> demandas) {
        if (mProgressDialog != null && mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
        mDemandas = demandas;

        mPagerAdapter = new DemandaPagerAdapter(getSupportFragmentManager(), mDemandas, getBaseContext());
        mVpDemandas.setAdapter(mPagerAdapter);
        Log.i("Lista demandas", demandas.size() + "");

    }

    @Override
    public void errorServicoDemandas(String erro) {
        Toast.makeText(this, erro, Toast.LENGTH_LONG).show();

    }



}

