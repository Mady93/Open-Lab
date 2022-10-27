package it.aop.services;

import it.aop.annotations.IsNull;
import it.aop.annotations.LogTime;
import it.aop.annotations.ToLog;
import it.aop.beans.Comment;
import it.aop.proxies.CommentNotificationProxy;
import it.aop.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class EmailCommentService extends Service {

    public EmailCommentService(CommentRepository commentRepository, @Qualifier("EMAIL")
            CommentNotificationProxy commentNotificationProxy) {
        super(commentRepository, commentNotificationProxy);
    }

    @Override
    @ToLog(level = "warning") //OFF, SEVERE, WARNING, INFO, CONFIG, FINE, FINER, FINEST, ALL
    @LogTime
    @IsNull
    public void publishComment(Comment comment) {
        super.publishComment(comment);
    }
}
