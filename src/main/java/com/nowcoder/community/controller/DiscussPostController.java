package com.nowcoder.community.controller;

import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.Page;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.DiscussPostService;
import com.nowcoder.community.service.UserService;
import com.nowcoder.community.util.CommunityUtil;
import com.nowcoder.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/discuss")
public class DiscussPostController {

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;

    @Autowired
    private HostHolder hostHolder;

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String addDiscussPost(String title, String content) {
        User user = hostHolder.getUser();
        if(user == null) {
            return CommunityUtil.getJSONString(403, "你还没有登录");
        }
        DiscussPost post = new DiscussPost();
        post.setUserId(user.getId());
        post.setTitle(title);
        post.setContent(content);
        post.setCreateTime(new Date());
        // post.setCommentCount(0);
        // post.setStatus(0);
        // post.setType(0);
        discussPostService.addDiscussPost(post);

        return CommunityUtil.getJSONString(0, "发布成功");
    }

    @RequestMapping(path = "/detail/{discussPostId}", method = RequestMethod.GET)
    public String getDiscussPost(@PathVariable("discussPostId") int discussPostId, Model model, Page page) {
        // 查询帖子
        DiscussPost discussPost = discussPostService.findDiscussPostById(discussPostId);
        model.addAttribute("post", discussPost);
        User user = userService.findUserById(discussPost.getUserId());
        model.addAttribute("user", user);

/*        // 点赞
        long likeCount = likeService.findEntityLikeCount(ENTITY_TYPE_POST, discussPostId);
        model.addAttribute("likeCount", likeCount);
        int likeStatus = hostHolder.getUser() == null ? 0 :
                likeService.findEntityLikeStatus(hostHolder.getUser().getId(), ENTITY_TYPE_POST, discussPostId);
        model.addAttribute("likeStatus", likeStatus);


        // 评论的分页信息
        page.setLimit(5);
        page.setPath("/discuss/detail/" + discussPostId);
        page.setRows(discussPost.getCommentCount());

        // 评论
        List<Comment> comments = commentService.findCommentsByEntity(ENTITY_TYPE_POST, discussPost.getId(), page.getOffset(), page.getLimit());
        List<Map<String, Object>> commentViewList = new ArrayList<>();
        if (comments != null) {
            for(Comment comment : comments) {
                Map<String, Object> commentView = new HashMap<>();
                commentView.put("comment", comment);
                commentView.put("user", userService.findUserById(comment.getUserId()));
                // 点赞
                likeCount = likeService.findEntityLikeCount(ENTITY_TYPE_COMMENT, comment.getId());
                commentView.put("likeCount", likeCount);
                likeStatus = hostHolder.getUser() == null ? 0 :
                        likeService.findEntityLikeStatus(hostHolder.getUser().getId(), ENTITY_TYPE_COMMENT, comment.getId());
                commentView.put("likeStatus", likeStatus);
                // 回复列表
                List<Comment> replies = commentService.findCommentsByEntity(ENTITY_TYPE_COMMENT, comment.getId(), 0, Integer.MAX_VALUE);
                List<Map<String, Object>> replyViewList = new ArrayList<>();
                if(replies != null) {
                    for(Comment reply : replies) {
                        Map<String, Object> replyView = new HashMap<>();
                        replyView.put("reply", reply);
                        replyView.put("user", userService.findUserById(reply.getUserId()));
                        // 这里需要处理targetId
                        User target = reply.getTargetId() == null ? null : userService.findUserById(reply.getTargetId());
                        replyView.put("target", target);
                        // 点赞
                        likeCount = likeService.findEntityLikeCount(ENTITY_TYPE_COMMENT, reply.getId());
                        replyView.put("likeCount", likeCount);
                        likeStatus = hostHolder.getUser() == null ? 0 :
                                likeService.findEntityLikeStatus(hostHolder.getUser().getId(), ENTITY_TYPE_COMMENT, reply.getId());
                        replyView.put("likeStatus", likeStatus);
                        replyViewList.add(replyView);
                    }
                }
                commentView.put("replies", replyViewList);
                // 回复数量
                int replyCount = commentService.findCommentCount(ENTITY_TYPE_COMMENT, comment.getId());
                commentView.put("replyCount", replyCount);

                commentViewList.add(commentView);
            }
        }

        model.addAttribute("comments", commentViewList);*/

        return "/site/discuss-detail";
    }

}
