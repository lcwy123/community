package com.nowcoder.community;

import com.nowcoder.community.dao.CommentMapper;
import com.nowcoder.community.entity.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CommentTests {

    @Autowired
    private CommentMapper commentMapper;

    @Test
    public void test() {
        List<Comment> commentList = commentMapper.selectCommentByUser(160, 0, 10);
        for(Comment comment : commentList) {
            System.out.println(comment);
        }
    }
}
