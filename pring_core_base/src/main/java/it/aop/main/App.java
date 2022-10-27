package it.aop.main;

import it.aop.beans.Comment;
import it.aop.conf.ProjectConfig;
import it.aop.services.Service;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ProjectConfig.class);
        Service service = context.getBean(Service.class);
        Comment comment = new Comment();
        comment.setAuthor("Alessandro Zoia");
        comment.setText("Ottimo lavoro");
        service.publishComment(comment);
    }
}
