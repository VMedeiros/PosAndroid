package com.victor.gestordedemandas.parser;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public abstract class RootUnwrappedParser<T> implements Parser<T> {

    public static final ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper();
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, false);
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
        OBJECT_MAPPER.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, false);
        OBJECT_MAPPER.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, false);
        OBJECT_MAPPER.configure(SerializationFeature.EAGER_SERIALIZER_FETCH, true);
        OBJECT_MAPPER.configure(SerializationFeature.WRAP_ROOT_VALUE, true);

        SimpleModule testModule = new SimpleModule("MyModule", new Version(1, 0, 0, null, null,
                null)).addDeserializer(Map.class, MapDeserializer.instance());
        OBJECT_MAPPER.registerModule(testModule);
    }

    @Override
    public T parse(InputStream jsonContent) throws
            IOException {
        throw new RuntimeException("You must implement Parser.parse(InputStream).");
    }
}