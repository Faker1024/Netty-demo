package org.example.serialize.impl;

import org.example.serialize.Serializer;
import org.example.serialize.SerializerAlgorithm;

public class JSONSerializer implements Serializer {


    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return com.alibaba.fastjson.JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return com.alibaba.fastjson.JSON.parseObject(bytes, clazz);
    }
}
