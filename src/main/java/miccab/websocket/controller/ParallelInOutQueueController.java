package miccab.websocket.controller;

import miccab.websocket.model.MyRequest;
import miccab.websocket.model.MyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by michal on 30.12.15.
 */
@Controller
public class ParallelInOutQueueController {

    private final List<String> names = Collections.synchronizedList(new LinkedList<>());

    @MessageMapping("/exec")
    public void capture(MyRequest request) {
        names.add(request.getName());
    }

    @Autowired
    private SimpMessagingTemplate messagingTemplate;


    @Scheduled(fixedDelay = 8_000)
    public void broadcast() {
        messagingTemplate.convertAndSendToUser("myuser", "/executed", new MyResponse(String.valueOf(names.size())));
    }

}
