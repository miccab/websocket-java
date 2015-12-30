package miccab.websocket.controller;

import miccab.websocket.model.MyRequest;
import miccab.websocket.model.MyResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Created by michal on 29.12.15.
 */
@Controller
public class SequentialInOutTopicController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public MyResponse greeting(MyRequest myRequest) throws InterruptedException {
        Thread.sleep(3_000);
        return new MyResponse(myRequest.getName());
    }
}
