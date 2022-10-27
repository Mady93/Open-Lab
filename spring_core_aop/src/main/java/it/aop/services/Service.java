package it.aop.services;

import it.aop.beans.Comment;
import it.aop.proxies.CommentNotificationProxy;
import it.aop.repository.CommentRepository;

public class Service {

    private final CommentRepository commentRepository;
    private final CommentNotificationProxy commentNotificationProxy;

    public Service(CommentRepository commentRepository,
                   CommentNotificationProxy commentNotificationProxy) {
        this.commentRepository = commentRepository;
        this.commentNotificationProxy = commentNotificationProxy;
    }

    public void publishComment(Comment comment) {
        commentRepository.storeComment(comment);
        commentNotificationProxy.sendComment(comment);
    }
}
