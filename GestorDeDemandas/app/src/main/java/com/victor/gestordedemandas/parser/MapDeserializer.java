package com.victor.gestordedemandas.parser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;


public class MapDeserializer extends StdDeserializer<Map<String, String>> {

    /**
     *
     */
    private static final long serialVersionUID = 8181560972238497853L;

    public MapDeserializer() {
        this(Map.class);
    }

    protected MapDeserializer(Class<?> arg0) {
        super(arg0);
    }

    public static MapDeserializer instance() {
        return new MapDeserializer();
    }

    @Override
    public Map<String, String> deserialize(JsonParser parser, DeserializationContext context)
            throws IOException {
        final LinkedHashMap<String, String> deserializedProperty = new LinkedHashMap<String, String>();

        if (parser.getCurrentToken() == JsonToken.START_OBJECT) {
            if (parser.nextToken() == JsonToken.FIELD_NAME
                    && parser.getCurrentName().equalsIgnoreCase("map")) {
                final ObjectNode objectToParser = parser.readValueAsTree();

                final Iterator<JsonNode> map = objectToParser.elements();

                while (map.hasNext()) {
                    final Iterator<JsonNode> parameters = map.next().elements();

                    while (parameters.hasNext()) {
                        final JsonNode parameter = parameters.next();
                        final Iterator<JsonNode> values = parameter.elements();
                        deserializedProperty.put(values.next().asText(), values.next().asText());
                    }
                }

                return deserializedProperty;
            }
        } else if (parser.getCurrentToken() == JsonToken.START_ARRAY) {
            while (parser.nextToken() != JsonToken.END_ARRAY) {
                while (parser.nextToken() != JsonToken.END_ARRAY) {
                    final String key = parser.getValueAsString();
                    parser.nextToken();

                    deserializedProperty.put(key, parser.getValueAsString());
                }
            }

            return deserializedProperty;
        }

        throw new RuntimeException("Property can not be used with this Deserializer.");
    }
}
