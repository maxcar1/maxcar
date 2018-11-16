package com.maxcar.base.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.DecimalFormat;

/**
 * Created by Administrator on 2018/11/7.
 */
public class CustomDoubleSerialize extends JsonSerializer<Double> {

    private DecimalFormat df = new DecimalFormat("##.00");

    @Override
    public void serialize(Double value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException, JsonProcessingException {
        if (value != null) {
            gen.writeString(df.format(value));
        }
    }
}
