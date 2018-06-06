package com.zlj.zl.sysConfig.webSocket;

import com.zlj.zl.util.base64.Base64Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.StringJoiner;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ld
 * @name 通讯，推送
 * @table
 * @remarks 实时通讯，服务器推送
 * 参考：https://blog.csdn.net/zhangdehua678/article/details/78913839
 * https://blog.csdn.net/xieliaowa9231/article/details/79424598
 */
@Slf4j
@ServerEndpoint(value = "/websocket/{account}/{touser}")
@Component
public class WebSocketServer {

    //统计在线人数
    private static int onlineCount = 0;

    //用本地线程保存session
    private static ThreadLocal<Session> sessions = new ThreadLocal<>();

    //保存所有连接上的session
    private static Map<String, Session> sessionMap = new ConcurrentHashMap<>();

    //    保存sessionId与account的关系
    private static Map<String, String> accountMap = new ConcurrentHashMap<>();

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        onlineCount--;
    }

    //连接
    @OnOpen
    public void onOpen(@PathParam("account") String account, Session session) {
        sessions.set(session);
        addOnlineCount();
        sessionMap.put(session.getId(), session);
        accountMap.put(session.getId(), Base64Util.decode(account));
        //连接上后给客户端一个消息
        sendMsg(session, "欢迎使用消息系统！");
    }

    //关闭
    @OnClose
    public void onClose(Session session) {
        subOnlineCount();
        sessionMap.remove(session.getId());
        accountMap.remove(session.getId());
    }

    //接收消息   客户端发送过来的消息
    @OnMessage
    public void onMessage(@PathParam("account") String account,
                          @PathParam("touser") String touser,
                          String message, Session session) {
        StringJoiner ssessionId = new StringJoiner("");
        accountMap.forEach((k, v) -> {
            if (v.equals(touser)) {
                ssessionId.add(k);
                return;
            }
        });
        Session ss = sessionMap.get(ssessionId.toString());
        if (ss != null) {
            String msgTo = "【" + Base64Util.decode(account) + "】发送给【您】的消息:\n【" + message + "】";
            String msgMe = "【我】发送消息给【" + touser + "】:\n" + message;
            sendMsg(ss, msgTo);
            sendMsg(session, msgMe);
        } else {
            for (Session s : sessionMap.values()) {
                if (!s.getId().equals(session.getId())) {
                    sendMsg(s, "【" + accountMap.get(session.getId()) + "】发送给【您】的广播消息:\n【" + message + "】");
                } else {
                    sendMsg(session, "【我】发送广播消息给大家\n" + message);
                }
            }
        }

    }

    //异常
    @OnError
    public void onError(Session session, Throwable throwable) {
        log.info("实时通讯控件发生异常!");
        throwable.printStackTrace();
    }


    //统一的发送消息方法
    public synchronized void sendMsg(Session session, String msg) {
        try {
            session.getBasicRemote().sendText(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
