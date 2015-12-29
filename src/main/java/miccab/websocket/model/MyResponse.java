package miccab.websocket.model;

/**
 * Created by michal on 29.12.15.
 */
public class MyResponse {
    private final String content;

    public MyResponse(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
