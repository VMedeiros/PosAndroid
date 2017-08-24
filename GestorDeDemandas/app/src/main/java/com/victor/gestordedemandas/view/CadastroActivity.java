package com.victor.gestordedemandas.view;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.victor.gestordedemandas.data.DemandaProjetoCRUD;
import com.victor.gestordedemandas.fragment.SelecionarDataDialog;
import com.victor.gestordedemandas.model.Demanda;
import com.victor.gestordedemandas.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CadastroActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    @Bind(R.id.et_num_demanda)
    EditText mEtNumDemanda;

    @Bind(R.id.et_responsavel_demanda)
    EditText mEtRespDemanda;

    @Bind(R.id.et_titulo_demanda)
    EditText mEtTituloDemanda;

    @Bind(R.id.et_prazo_demanda)
    EditText mEtPrazoDemanda;

    @Bind(R.id.et_observacao_demanda)
    EditText mEtObservacaoDemanda;

    @Bind(R.id.sp_nome_gp)
    Spinner mSpNomeGerente;

    @Bind(R.id.sp_status_demanda)
    Spinner mSpStatusDemanda;

    @Nullable
    @Bind(R.id.et_status_nome)
    EditText mEtStatus;
    @Nullable
    @Bind(R.id.et_nome)
    EditText mEtNome;



    DemandaProjetoCRUD cadastroCRUD;
    Integer idremover;

    int comportamento = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        ButterKnife.bind(this);




        cadastroCRUD = new DemandaProjetoCRUD(this);

        Bundle extras = getIntent().getExtras();

        if(extras != null && extras.containsKey("demanda")){
            setTitle("Detalhamento de Demanda");
            Demanda demanda = extras.getParcelable("demanda");
            idremover = demanda.getId();
            visualizarDemanda(demanda);
            comportamento = 1;

        }


        mEtPrazoDemanda.setOnKeyListener(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cadastro, menu);

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        if(comportamento == 1){
            menu.getItem(0).setVisible(false);
            menu.getItem(2).setVisible(true);
        }


        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.item_cancelar:


                return true;

            case R.id.item_salvar:

                if(comportamento == 0){
                    salvarDemanda();
                    Intent intent = new Intent(CadastroActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }if(comportamento == 1){
                item.setVisible(true);
                editarDemanda();
                Intent intent = new Intent(CadastroActivity.this, ListarDemandasActivity.class);
                startActivity(intent);
                finish();

            }else{
                Intent intent = new Intent(CadastroActivity.this, ListarDemandasActivity.class);
                startActivity(intent);

                }
                return true;
            case R.id.item_editar:
                camposEditaveis();
                item.setVisible(false);
                comportamento = 1;


                return  true;

            default:
                return super.onOptionsItemSelected(item);
        }


    }

    @OnClick(R.id.et_prazo_demanda)
    public void selecionarData() {
        SelecionarDataDialog dialog = new SelecionarDataDialog();
        dialog.show(getSupportFragmentManager(), "selecionarData");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        String dataSelecionada = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
        mEtPrazoDemanda.setText(dataSelecionada);
    }

    private void salvarDemanda() {

        Demanda demanda = new Demanda();

            demanda.setGerenteProjeto(mSpNomeGerente.getSelectedItem() == null ? "" : mSpNomeGerente.getSelectedItem().toString());
            demanda.setmStatus(mSpStatusDemanda.getSelectedItem() == null ? "" : mSpStatusDemanda.getSelectedItem().toString());
            demanda.setmTitulo(mEtTituloDemanda.getText() == null ? "" : mEtTituloDemanda.getText().toString());
            demanda.setmNumero(mEtNumDemanda.getText() == null ? 0 : Integer.parseInt(mEtNumDemanda.getText().toString()));
            demanda.setmPrazo(mEtPrazoDemanda.getText() == null ? "" : mEtPrazoDemanda.getText().toString());
            demanda.setmObservacoes(mEtObservacaoDemanda.getText() == null ? "" : mEtObservacaoDemanda.getText().toString());
            demanda.setmResponsavel(mEtRespDemanda.getText() == null ? "" : mEtRespDemanda.getText().toString());

            cadastroCRUD.salvarDemanda(demanda);

    }

    private void editarDemanda() {

        Demanda demanda = new Demanda();
        demanda.setGerenteProjeto(mSpNomeGerente.getSelectedItem() == null ? "" : mSpNomeGerente.getSelectedItem().toString());
        demanda.setmStatus(mSpStatusDemanda.getSelectedItem() == null ? "" : mSpStatusDemanda.getSelectedItem().toString());
        demanda.setmTitulo(mEtTituloDemanda.getText() == null ? "" : mEtTituloDemanda.getText().toString());
        demanda.setmNumero(mEtNumDemanda.getText() == null ? 0 : Integer.parseInt(mEtNumDemanda.getText().toString()));
        demanda.setmPrazo(mEtPrazoDemanda.getText() == null ? "" : mEtPrazoDemanda.getText().toString());
        demanda.setmObservacoes(mEtObservacaoDemanda.getText() == null ? "" : mEtObservacaoDemanda.getText().toString());
        demanda.setmResponsavel(mEtRespDemanda.getText() == null ? "" : mEtRespDemanda.getText().toString());

        cadastroCRUD.atualizarDemanda(demanda, idremover);
    }

    public void deletarDemanda(int id){
        cadastroCRUD.removerDemanda(id);

    }

    private void visualizarDemanda(Demanda demanda){
        mEtNumDemanda.setText(demanda.getmNumero().toString());
        mEtNumDemanda.setEnabled(false);
        mEtObservacaoDemanda.setText(demanda.getmObservacoes());
        mEtObservacaoDemanda.setEnabled(false);
        mEtPrazoDemanda.setText(demanda.getmPrazo());
        mEtPrazoDemanda.setEnabled(false);
        mEtTituloDemanda.setText(demanda.getmTitulo());
        mEtTituloDemanda.setEnabled(false);
        mEtRespDemanda.setText(demanda.getmResponsavel());
        mEtRespDemanda.setEnabled(false);
        mEtStatus.setText(demanda.getmStatus());
        mEtStatus.setEnabled(false);
        mEtNome.setText(demanda.getGerenteProjeto());
        mEtNome.setEnabled(false);
        mSpStatusDemanda.setSelection(getIndex(mSpStatusDemanda, demanda.getmStatus()));
        mSpStatusDemanda.setEnabled(false);
        mSpNomeGerente.setSelection(getIndex(mSpNomeGerente, demanda.getGerenteProjeto()));
        mSpNomeGerente.setEnabled(false);


    }

    private void camposEditaveis(){

        mEtNumDemanda.setEnabled(true);
        mEtObservacaoDemanda.setEnabled(true);
        mEtPrazoDemanda.setEnabled(true);
        mEtTituloDemanda.setEnabled(true);
        mEtRespDemanda.setEnabled(true);
        mEtStatus.setEnabled(true);
        mEtNome.setEnabled(true);
        mSpStatusDemanda.setEnabled(true);
        mSpNomeGerente.setEnabled(true);

    }

    private void visualizarEditarDemanda(Demanda demanda){
        mEtNumDemanda.setText(demanda.getmNumero().toString());
        mEtObservacaoDemanda.setText(demanda.getmObservacoes());
        mEtPrazoDemanda.setText(demanda.getmPrazo());
        mEtTituloDemanda.setText(demanda.getmTitulo());
        mEtRespDemanda.setText(demanda.getmResponsavel());
        mSpStatusDemanda.setSelection(getIndex(mSpStatusDemanda, demanda.getmStatus()));
        mSpNomeGerente.setSelection(getIndex(mSpNomeGerente, demanda.getGerenteProjeto()));


    }

    private int getIndex(Spinner spinner, String myString)
    {
        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                index = i;
                break;
            }
        }
        return index;
    }

}
