package com.victor.gestordedemandas.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.victor.gestordedemandas.fragment.StatusDemandaFragment;
import com.victor.gestordedemandas.model.Demanda;
import com.victor.gestordedemandas.R;

import java.util.ArrayList;
import java.util.List;

public class DemandaPagerAdapter extends FragmentStatePagerAdapter {


    String mCadastradas;
    String mDesenvolvimento;
    String mHomologacao;
    String mImpedimento;
    String mFinalizada;

    List<Demanda> mDemandas;

    public DemandaPagerAdapter(FragmentManager fm, List<Demanda> mDemandas, Context context) {
        super(fm);
        this.mDemandas = mDemandas;

        mCadastradas = context.getResources().getString(R.string.demandas_cadastradas);
        mDesenvolvimento = context.getResources().getString(R.string.desenvolvimento);
        mHomologacao = context.getResources().getString(R.string.homologacao);
        mImpedimento = context.getResources().getString(R.string.impedimento);
        mFinalizada = context.getResources().getString(R.string.finalizada);
    }

    protected DemandaPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        StatusDemandaFragment statusDemandaFragment = new StatusDemandaFragment();

        switch (position) {

            case 0:
                statusDemandaFragment.setmDemandas(mDemandas);
                break;

            case 1:
                statusDemandaFragment.setmDemandas(filtrarPorStatus(mDemandas, mDesenvolvimento));
                break;

            case 2:
                statusDemandaFragment.setmDemandas(filtrarPorStatus(mDemandas, mImpedimento));
                break;

            case 3:
                statusDemandaFragment.setmDemandas(filtrarPorStatus(mDemandas, mHomologacao));
                break;

            case 4:
                statusDemandaFragment.setmDemandas(filtrarPorStatus(mDemandas, mFinalizada));
                break;

            default:
                break;
        }

        return statusDemandaFragment;

    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return mCadastradas;
            case 1:
                return mDesenvolvimento;
            case 2:
                return mImpedimento;
            case 3:
                return mHomologacao;
            case 4:
                return mFinalizada;
            default:
                return "";
        }


    }

    @Override
    public int getCount() {
        return 5;
    }

    private List<Demanda> filtrarPorStatus(List<Demanda> demandas, String statusDemanda) {

        List<Demanda> demandasFiltradas = new ArrayList<>();

        for (Demanda demanda : demandas) {

            if (statusDemanda.equals(demanda.getmStatus())) {
                demandasFiltradas.add(demanda);
            }

        }

        return demandasFiltradas;

    }

}
