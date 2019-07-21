package com.zbw.fame.controller.admin;

import com.zbw.fame.controller.BaseController;
import com.zbw.fame.service.CategoryService;
import com.zbw.fame.service.MetaService;
import com.zbw.fame.util.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 属性(标签和分类)管理 Controller
 *
 * @author zbw
 * @since 2017/8/28 23:16
 */
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public abstract class AbstractMetaController extends BaseController {

    private final MetaService metaService;


    /**
     * 获取所有属性
     *
     * @return {@see List<MetaDto>}
     */
    @GetMapping
    public RestResponse getAll() {
        return RestResponse.ok(metaService.getMetaInfosWithAllArticle());
    }

    /**
     * 根据name删除分类
     *
     * @param name 属性名
     * @return {@see RestResponse.ok()}
     */
    @DeleteMapping
    public RestResponse delete(@RequestParam String name) {
        metaService.delete(name);
        return RestResponse.ok();
    }

    /**
     * 添加一个分类
     *
     * @param name 属性名
     * @return {@see RestResponse.ok()}
     */
    @PostMapping
    public RestResponse save(@RequestParam String name) {
        metaService.save(name);
        return RestResponse.ok();
    }

    /**
     * 根据id修改分类
     *
     * @param id   属性id
     * @param name 新属性名
     * @return {@see RestResponse.ok()}
     */
    @PostMapping("{id}")
    public RestResponse update(@PathVariable Integer id, @RequestParam String name) {
        metaService.update(id, name);
        return RestResponse.ok();
    }
}
