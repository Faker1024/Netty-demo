package org.example.actuator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.actuator.client.receive.LoginReceiveResponseActuator;
import org.example.actuator.client.receive.MessageReceiveResponseActuator;
import org.example.actuator.client.send.LoginSendRequestActuator;
import org.example.actuator.client.send.MessageSendRequestActuator;
import org.example.actuator.server.LoginServerActuator;
import org.example.actuator.server.MessageServerActuator;

@AllArgsConstructor
@Getter
public enum ActuatorEnum {
    CLIENT_LOGIN_REQUEST((byte) 1, "登录请求", LoginSendRequestActuator.INSTANCE),
    SERVER_LOGIN((byte) 2, "登录逻辑",  LoginServerActuator.INSTANCE),
    CLIENT_LOGIN_RESPONSE((byte) 3, "登录响应",  LoginReceiveResponseActuator.INSTANCE),
    CLIENT_MESSAGE_REQUEST((byte) 4, "消息请求", MessageSendRequestActuator.INSTANCE),
    SERVER_MESSAGE((byte) 5, "消息逻辑", MessageServerActuator.INSTANCE),
    SERVER_MESSAGE_RESPONSE((byte) 6, "消息响应", MessageReceiveResponseActuator.INSTANCE);

    private final byte flag;
    private final String description;
    private final Actuator actuator;


    public static Actuator getActuatorByFlag(byte flag) {
        for (ActuatorEnum actuatorEnum : ActuatorEnum.values()) {
            if (actuatorEnum.flag == flag) {
                return actuatorEnum.actuator;
            }
        }
        return null;
    }
    public static String getDescriptionByFlag(byte flag) {
        for (ActuatorEnum actuatorEnum : ActuatorEnum.values()) {
            if (actuatorEnum.flag == flag) {
                return actuatorEnum.description;
            }
        }
        return "No matching ActuatorEnum found for the given flag";
    }
}
