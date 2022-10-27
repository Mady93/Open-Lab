package jdk;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import jdk.model.Comment;
import jdk.services.CommentService;
import jdk.services.ProjectConfig;

public class Main {

	public static void main(String[] args) {


		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);
		var comment = new Comment();
		comment.setAuthor("Pippo");
		comment.setText("Ciao");
		var commentservice = context.getBean(CommentService.class);
		commentservice.publishComment(comment);


	}

}
