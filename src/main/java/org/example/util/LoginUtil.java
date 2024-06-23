package org.example.util;

import io.netty.channel.Channel;
import org.example.attribute.Attributes;

public class LoginUtil {

    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel) {
        return channel.hasAttr(Attributes.LOGIN) && channel.attr(Attributes.LOGIN).get();
    }
}
