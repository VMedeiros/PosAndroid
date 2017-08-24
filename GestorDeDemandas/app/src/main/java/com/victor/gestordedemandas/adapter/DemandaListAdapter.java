package com.victor.gestordedemandas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.victor.gestordedemandas.model.Demanda;
import com.victor.gestordedemandas.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DemandaListAdapter extends BaseAdapter {

    List<Demanda> mDemandas;
    Context mContext;
    LayoutInflater mInflater;

    public DemandaListAdapter(List<Demanda> mDemandas, Context mContext) {
        this.mDemandas = mDemandas;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mDemandas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDemandas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
        ViewHolder holder;

        if (convertView == null) {
            view = mInflater.inflate(R.layout.item_list_demandas, parent, false);
            holder = new ViewHolder(view);
            Demanda demanda = mDemandas.get(position);

            holder.mTvNumDemanda.setText(demanda.getmNumero()+"");
            holder.mTvStatusDemanda.setText(demanda.getmStatus());
            holder.mTvTituloDemanda.setText(demanda.getmTitulo());
            holder.mTvPrazo.setText(demanda.getmPrazo());

        } else {
            view = convertView;
        }


        return view;
    }


    class ViewHolder {

        @Bind(R.id.tv_num_demanda)
        TextView mTvNumDemanda;
        @Bind(R.id.tv_titulo_demanda)
        TextView mTvTituloDemanda;
        @Bind(R.id.tv_status_demanda)
        TextView mTvStatusDemanda;
        @Bind(R.id.tv_prazo_demanda)
        TextView mTvPrazo;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }


    }
}