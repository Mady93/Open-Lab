package it.aop.proxies;

import it.aop.beans.Comment;
import org.springframework.stereotype.Component;

@Component("PUSH")
public class CommentPushNotificationProxy implements CommentNotificationProxy {
    @Override
    public void sendComment(Comment comment) {
        System.out.println("Sending push notification for comment " + comment.getText());
    }
}