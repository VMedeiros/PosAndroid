package com.victor.gestordedemandas.parser;

import com.victor.gestordedemandas.model.Demanda;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.io.IOException;

public class DemandaParser extends RootUnwrappedParser<Demanda>{
    @Override
    public Demanda parse(String jsonContent) throws IOException {
        return OBJECT_MAPPER.readValue(jsonContent, Demanda.class);
    }
}
