package it.aop.proxies;

import it.aop.beans.Comment;
import it.aop.proxies.CommentNotificationProxy;
import org.springframework.stereotype.Component;

@Component("EMAIL")
public class EmailCommentNotificationProxy implements CommentNotificationProxy {
    @Override
    public void sendComment(Comment comment) {
        System.out.println("Sending comment " + comment.getText());
    }
}