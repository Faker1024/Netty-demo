package org.example.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import org.example.protocol.request.CreateGroupRequestPacket;
import org.example.protocol.response.CreateGroupResponsePacket;
import org.example.util.IDUtil;
import org.example.util.SessionUtil;

import java.util.ArrayList;
import java.util.List;

public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket createGroupRequestPacket) {
        List<String> userIdList = createGroupRequestPacket.getUserIdList();
        List<String> userNameList = new ArrayList<>();
        ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());
        for (String userId : userIdList) {
            Channel channel = SessionUtil.getChannel(userId);
            if (channel != null && SessionUtil.hasLogin(channel)) {
                channelGroup.add(channel);
                userNameList.add(SessionUtil.getSession(channel).getUserName());
            }
        }
        CreateGroupResponsePacket response = CreateGroupResponsePacket.builder()
                .success(true).groupId(IDUtil.randomId())
                .userNameList(userNameList).build();
        channelGroup.writeAndFlush(response);
        System.out.print("群创建成功，id为[" + response.getGroupId() + "],");
        System.out.println("群里面有：" + response.getUserNameList());
    }
}
