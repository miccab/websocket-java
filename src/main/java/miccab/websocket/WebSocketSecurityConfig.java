package miccab.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

/**
 * Created by michal on 03.01.16.
 */
@Configuration
public class WebSocketSecurityConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {

    @Override
    protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
        messages
                .simpDestMatchers("/user/*").hasRole("bla");
    }

    @Override
    protected boolean sameOriginDisabled() {
        // TODO: enable it back once CSFR header flow is setup (http://docs.spring.io/spring-security/site/docs/current/reference/html/websocket.html#websocket-sameorigin)
        return true;
    }
}
