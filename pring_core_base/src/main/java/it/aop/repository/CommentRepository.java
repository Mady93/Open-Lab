package it.aop.repository;


import it.aop.beans.Comment;

public interface CommentRepository {
    void storeComment(Comment comment);
}
