package org.example.serialize;

import org.example.serialize.impl.JSONSerializer;

public interface Serializer {

    public static final Serializer JSON = new JSONSerializer();

    byte getSerializerAlgorithm();

    public byte[] serialize(Object object);

    public <T> T deserialize(Class<T> clazz, byte[] bytes);
}
