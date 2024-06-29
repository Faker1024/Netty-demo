package org.example.util;

import io.netty.channel.Channel;
import org.example.attribute.Attributes;
import org.example.session.Session;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class SessionUtil {

    private static final Map<String, Channel> userIdChannelMap = new ConcurrentHashMap<>();

    public static void bindSession(Session session, Channel channel){
        userIdChannelMap.put(session.getUserId(), channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    public static void unbindSession(Channel channel) {
        if (hasLogin(channel)){
            userIdChannelMap.remove(Objects.requireNonNull(getSession(channel)).getUserId());
            channel.attr(Attributes.SESSION).set(null);
        }
    }

    private static Session getSession(Channel channel) {
        return channel.attr(Attributes.SESSION).get();
    }

    private static boolean hasLogin(Channel channel) {
        return channel.hasAttr(Attributes.SESSION);
    }

    public static Channel getChannel(String userId) {
        return userIdChannelMap.get(userId);
    }

}

