package org.example.util;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import org.example.attribute.Attributes;
import org.example.session.Session;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class SessionUtil {

    private static final Map<String, Channel> userIdChannelMap = new ConcurrentHashMap<>();

    private static final Map<String, ChannelGroup> groupIdChannelGroupMap = new ConcurrentHashMap<>();

    public static void bindSession(Session session, Channel channel){
        userIdChannelMap.put(session.getUserId(), channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    public static void unbBindSession(Channel channel) {
        if (hasLogin(channel)){
            Session session = getSession(channel);
            userIdChannelMap.remove(Objects.requireNonNull(session).getUserId());
            channel.attr(Attributes.SESSION).set(null);
            System.out.println(session + "退出登录！");
        }
    }

    public static Session getSession(Channel channel) {
        return channel.attr(Attributes.SESSION).get();
    }

    public static boolean hasLogin(Channel channel) {
        return channel.hasAttr(Attributes.SESSION);
    }

    public static Channel getChannel(String userId) {
        return userIdChannelMap.get(userId);
    }

    public static void bindChannelGroup(String groupId, ChannelGroup group) {
        groupIdChannelGroupMap.put(groupId, group);
    }

    public static ChannelGroup getChannelGroup(String groupId) {
        return groupIdChannelGroupMap.get(groupId);
    }

}

