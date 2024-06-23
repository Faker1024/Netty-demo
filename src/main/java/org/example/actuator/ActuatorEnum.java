package org.example.actuator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.actuator.client.receive.LoginReceiveResponseActuator;
import org.example.actuator.client.send.LoginSendRequestActuator;
import org.example.actuator.server.LoginServerActuator;

@AllArgsConstructor
@Getter
public enum ActuatorEnum {
    CLIENT_LOGIN_REQUEST((byte) 1, "登录请求", LoginSendRequestActuator.INSTANCE),
    SERVER_LOGIN((byte) 2, "登录逻辑",  LoginServerActuator.INSTANCE),
    CLIENT_LOGIN_RESPONSE((byte) 3, "登录响应",  LoginReceiveResponseActuator.INSTANCE);
    private final byte flag;
    private final String description;
    private final Actuator actuator;

    public static String getDescriptionByFlag(byte flag) {
        for (ActuatorEnum actuatorEnum : ActuatorEnum.values()) {
            if (actuatorEnum.flag == flag) {
                return actuatorEnum.description;
            }
        }
        return "No matching ActuatorEnum found for the given flag";
    }

    public static Actuator getActuatorByFlag(byte flag) {
        for (ActuatorEnum actuatorEnum : ActuatorEnum.values()) {
            if (actuatorEnum.flag == flag) {
                return actuatorEnum.actuator;
            }
        }
        return null;
    }
}
