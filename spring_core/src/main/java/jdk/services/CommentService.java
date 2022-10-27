package jdk.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import jdk.model.Comment;
import jdk.proxies.CommentNotificationProxy;
import jdk.repositories.CommentRepository;

@Component
public class CommentService {
	
	private final CommentRepository commentRepository;
	private final CommentNotificationProxy commentNotificationProxy;
	
	@Autowired               // @Autowired non serve quando si ha un solo costruttore
	public CommentService(CommentRepository commentRepository, CommentNotificationProxy commentNotificationProxy) {
		this.commentRepository = commentRepository;
		this.commentNotificationProxy = commentNotificationProxy;
	}
	
	public void publishComment(Comment comment) {
		commentRepository.storeComment(comment);
		commentNotificationProxy.sendComment(comment);
	}
}
