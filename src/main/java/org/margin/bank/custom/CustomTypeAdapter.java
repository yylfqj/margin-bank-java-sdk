package org.margin.bank.custom;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.internal.bind.ObjectTypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CustomTypeAdapter
 * @Author yyl
 * @Date 2022-03-14 10:55:40
 * @Description CustomTypeAdapter
 * @Version 1.0
 */
public class CustomTypeAdapter extends TypeAdapter<Object> {

    private final Gson gson;

    public CustomTypeAdapter(Gson gson) {
        this.gson = gson;
    }

    @Override
    public void write(JsonWriter jsonWriter, Object o) throws IOException {
        if (o == null) {
            jsonWriter.nullValue();
            return;
        }
        TypeAdapter<Object> typeAdapter = (TypeAdapter<Object>) gson.getAdapter(o.getClass());
        if (typeAdapter instanceof ObjectTypeAdapter) {
            jsonWriter.beginObject();
            jsonWriter.endObject();
            return;
        }
        typeAdapter.write(jsonWriter, o);
    }

    @Override
    public Object read(JsonReader jsonReader) throws IOException {
        JsonToken peek = jsonReader.peek();

        switch (peek) {
            case BEGIN_ARRAY:
                List<Object> list = new ArrayList<>();
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    list.add(read(jsonReader));
                }
                jsonReader.endArray();
                return list;
            case BEGIN_OBJECT:
                LinkedTreeMap<String, Object> map = new LinkedTreeMap<>();
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    map.put(jsonReader.nextName(), read(jsonReader));
                }
                jsonReader.endObject();
                return map;
            case STRING:
                return jsonReader.nextString();
            case NUMBER:
                String s = jsonReader.nextString();
                if (s.contains(".")) {
                    return Double.valueOf(s);
                } else {
                    try {
                        return Integer.valueOf(s);
                    } catch (Exception e) {
                        return Long.valueOf(s);
                    }
                }
            case BOOLEAN:
                return jsonReader.nextBoolean();
            case NULL:
                jsonReader.nextNull();
                return null;
            default:
                throw new IllegalStateException();
        }
    }

    public static final TypeAdapterFactory FACTORY = (TypeAdapterFactory)(new TypeAdapterFactory() {
        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {

            return typeToken.getRawType() == Map.class ? (TypeAdapter<T>) new CustomTypeAdapter(gson) : null;
        }

    });
}
