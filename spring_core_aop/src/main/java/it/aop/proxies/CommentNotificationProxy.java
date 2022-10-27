package it.aop.proxies;

import it.aop.beans.Comment;

public interface CommentNotificationProxy {
    public void sendComment(Comment comment);
}
