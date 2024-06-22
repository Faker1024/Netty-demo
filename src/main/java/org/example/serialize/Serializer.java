package org.example.serialize;

import org.example.serialize.impl.JSONSerializer;

public interface Serializer {

    Serializer DEFAULT = new JSONSerializer();

    byte getSerializerAlgorithm();

    public byte[] serialize(Object object);

    public <T> T deserialize(Class<T> clazz, byte[] bytes);
}
