package com.zbw.fame.controller.admin;

import com.github.pagehelper.Page;
import com.zbw.fame.controller.BaseController;
import com.zbw.fame.dto.CommentDto;
import com.zbw.fame.dto.Pagination;
import com.zbw.fame.model.Comments;
import com.zbw.fame.service.CommentsService;
import com.zbw.fame.util.FameConsts;
import com.zbw.fame.util.FameUtil;
import com.zbw.fame.util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 后台评论管理 Controller
 *
 * @author zbw
 * @create 2018/1/21 10:47
 */
@RestController
@RequestMapping("/api/admin/comment")
public class CommentController extends BaseController {

    @Autowired
    private CommentsService commentsService;

    @GetMapping
    public RestResponse index(@RequestParam(required = false, defaultValue = "1") Integer page,
                              @RequestParam(required = false, defaultValue = FameConsts.PAGE_SIZE) Integer limit) {
        Page<Comments> comments = commentsService.getComments(page, limit);
        return RestResponse.ok(new Pagination<Comments>(comments));
    }

    @GetMapping("{id}")
    public RestResponse detail(@PathVariable Integer id) {
        CommentDto comment = commentsService.getCommentDetail(id);
        if (null == comment) {
            return this.error404();
        }
        if (null != comment.getpComment()) {
            comment.getpComment().setContent(FameUtil.mdToHtml(comment.getpComment().getContent()));
        }
        comment.setContent(FameUtil.mdToHtml(comment.getContent()));
        return RestResponse.ok(comment);
    }

    @DeleteMapping("{id}")
    public RestResponse delete(@PathVariable Integer id) {
        if (commentsService.deleteComment(id)) {

            return RestResponse.ok();
        } else {
            return RestResponse.fail("删除评论失败");
        }
    }

}
