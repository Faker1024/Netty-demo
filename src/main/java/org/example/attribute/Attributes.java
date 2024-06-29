package org.example.attribute;

import io.netty.util.AttributeKey;
import org.example.session.Session;

public interface Attributes {
    AttributeKey<Boolean> LOGIN =  AttributeKey.newInstance("login");
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
