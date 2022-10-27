package it.aop.services;

import it.aop.annotations.IsNull;
import it.aop.annotations.ToLog;
import it.aop.beans.Comment;
import it.aop.proxies.CommentNotificationProxy;
import it.aop.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class PushCommentService extends Service {

    public PushCommentService(CommentRepository commentRepository,
                              @Qualifier("PUSH") CommentNotificationProxy commentNotificationProxy) {
        super(commentRepository, commentNotificationProxy);
    }

    @Override
    @IsNull
    @ToLog(level = "warning")
    public void publishComment(Comment comment) {
       super.publishComment(comment);
    }
}
