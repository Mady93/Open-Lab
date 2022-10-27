package it.aop.main;

import it.aop.beans.Comment;
import it.aop.services.EmailCommentService;
import it.aop.conf.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ProjectConfig.class);
        EmailCommentService service = context.getBean(EmailCommentService.class);
        Comment comment = new Comment();
        comment.setAuthor("Alessandro Zoia");
        comment.setText("Ottimo lavoro");
        service.publishComment(comment);
        //service.publishComment(null);
    }
}
