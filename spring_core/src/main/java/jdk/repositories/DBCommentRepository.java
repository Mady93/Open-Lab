package jdk.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import jdk.model.Comment;

@Component
@Qualifier("PUSH")
public class DBCommentRepository implements CommentRepository{

	@Override
	public void storeComment(Comment comment) {
System.out.println("Storing comment "+comment.getText());
	}

}
