package com.victor.gestordedemandas.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import com.victor.gestordedemandas.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.btn_cadastro)
    Button btnCadastrar;

    @Bind(R.id.btn_listar)
    Button btnListar;

    String tag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }


    @OnClick(R.id.btn_cadastro)
    public void btnCadastrarListener() {

        Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
        startActivity(intent);
        finish();

    }

    @OnClick(R.id.btn_listar)
    public void btnListarListener() {

        Intent intent = new Intent(MainActivity.this, ListarDemandasActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.item_gerente:
                Intent intent = new Intent(MainActivity.this, CadastrarGerentes.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
