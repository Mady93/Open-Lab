package jdk.proxies;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import jdk.model.Comment;
@Component()
@Primary
public class EmailNotificationProxy implements CommentNotificationProxy{

	@Override
	public void sendComment(Comment comment) {
		System.out.println("Sending notification for comment: "+comment.getText());
	}

}
