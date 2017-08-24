package com.victor.gestordedemandas.interfaces;

import com.victor.gestordedemandas.model.Demanda;

import java.util.List;

public interface DemandasCallbackIF {
    void retornoListaDemandas(List<Demanda> demandas);

    void errorServicoDemandas(String erro);
}
