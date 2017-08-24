package com.victor.gestordedemandas.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.victor.gestordedemandas.model.Demanda;
import com.victor.gestordedemandas.R;
import com.victor.gestordedemandas.adapter.DemandaListAdapter;
import com.victor.gestordedemandas.data.DemandaProjetoCRUD;
import com.victor.gestordedemandas.view.CadastroActivity;
import com.victor.gestordedemandas.view.ListarDemandasActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import butterknife.OnItemLongClick;

public class StatusDemandaFragment extends Fragment {

    @Bind(R.id.lv_status_demanda)
    ListView mLvStatusDemanda;

    DemandaListAdapter mAdapter;

    List<Demanda> mDemandas;

    DemandaProjetoCRUD crud;
    int idremover;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_status_demanda, null);
        ButterKnife.bind(this, view);
        crud = new DemandaProjetoCRUD(getActivity());

        mAdapter = new DemandaListAdapter(getmDemandas(), getActivity().getBaseContext());
        mLvStatusDemanda.setAdapter(mAdapter);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public List<Demanda> getmDemandas() {
        if (mDemandas == null) {
            mDemandas = new ArrayList<>();
        }

        return mDemandas;
    }


    @OnItemClick(R.id.lv_status_demanda)
    protected void itemClickListener(int position){
        Demanda demanda = (Demanda) mLvStatusDemanda.getItemAtPosition(position);
        Intent intent = new Intent(getActivity(), CadastroActivity.class);
        intent.putExtra("demanda", demanda);
        startActivity(intent);




    }


    @OnItemLongClick(R.id.lv_status_demanda)
    boolean onLongClick(int position) {
        Demanda demanda = (Demanda) mLvStatusDemanda.getItemAtPosition(position);
        idremover = demanda.getId();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Deseja apagar a demanda?");
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                crud.removerDemanda(idremover);
                Intent intent = new Intent(getActivity(), ListarDemandasActivity.class);
                dialog.dismiss();
                startActivity(intent);
            }
        });
        builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
        return true;
    }

    public void setmDemandas(List<Demanda> mDemandas) {
        this.mDemandas = mDemandas;
    }
}
