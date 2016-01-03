package miccab.websocket.controller;

import miccab.websocket.model.MyRequest;
import miccab.websocket.model.MyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by michal on 30.12.15.
 */
@Controller
public class ParallelInOutQueueController {

    private final Map<String, String> userToLastRequestedName = new ConcurrentHashMap<>();

    @MessageMapping("/exec")
    public void capture(MyRequest request, Principal principal) {
        userToLastRequestedName.put(getUserName(principal), request.getName());
    }

    private String getUserName(Principal principal) {
        return ((UserDetails)(((Authentication) principal).getPrincipal())).getUsername();
    }

    @Autowired
    private SimpMessagingTemplate messagingTemplate;


    @Scheduled(fixedDelay = 8_000)
    public void broadcast() {
        for (String userName : userToLastRequestedName.keySet()) {
            messagingTemplate.convertAndSendToUser(userName, "/executed", new MyResponse(String.valueOf("#latest: " + userToLastRequestedName.get(userName))));
        }
    }

}
