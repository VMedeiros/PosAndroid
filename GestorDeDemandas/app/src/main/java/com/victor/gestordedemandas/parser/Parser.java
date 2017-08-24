package com.victor.gestordedemandas.parser;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.io.IOException;
import java.io.InputStream;

public interface Parser<T> {

    T parse(String jsonContent) throws IOException;

    T parse(InputStream jsonContent) throws
            IOException;
}