package com.atguigu.gulimall.product.service.impl;

import com.atguigu.gulimall.product.service.CategoryBrandRelationService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;

import com.atguigu.gulimall.product.dao.CategoryDao;
import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.atguigu.gulimall.product.service.CategoryService;
import org.springframework.transaction.annotation.Transactional;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {
        // 1、查询所有分类
        List<CategoryEntity> categoryEntities = this.baseMapper.selectList(null);
        // 2、组装树
        List<CategoryEntity> levelOne = categoryEntities.stream().filter(categoryEntity ->
            categoryEntity.getParentCid() == 0
        ).map((menu)->{
            menu.setChildren(getChildren(menu,categoryEntities));
            return menu;
        }).sorted((s1,s2)->
            s1.getSort()==null ? 0 : s1.getSort() - (s1.getSort()==null ? 0 : s1.getSort())
        ).collect(Collectors.toList());

        return levelOne;
    }

    @Override
    public void removeMenuByIds(List<Long> catIdList) {
        // 1、检测当前删除的菜单，是否被别的地方引用

        // 逻辑删除
        this.baseMapper.deleteBatchIds(catIdList);
    }

    @Override
    public Long[] findCategoryPath(Long catelogId) {
        List<Long> path = new ArrayList<>();
        List<Long> parentPath = this.findParent(catelogId, path);
        Collections.reverse(parentPath);
        return parentPath.toArray(new Long[parentPath.size()]);
    }

    @Transactional
    @Override
    public void updateCascade(CategoryEntity category) {
        this.updateById(category);
        if(StringUtils.isNotEmpty(category.getName())) {
            this.categoryBrandRelationService.updateCategory(category.getCatId(), category.getName());
        }
    }

    private List<Long> findParent(Long categoryId, List<Long> path){
        path.add(categoryId);
        CategoryEntity byId = this.getById(categoryId);
        if(byId.getParentCid() != 0){
            findParent(byId.getParentCid(),path);
        }
        return path;
    }

    /**
     * 递归查找所有菜单的子菜单
     * @param root
     * @param all
     * @return
     */
    private List<CategoryEntity> getChildren(CategoryEntity root, List<CategoryEntity> all){
        return all.stream().filter(categoryEntity ->
            categoryEntity.getParentCid() == root.getCatId()
        ).map(categoryEntity -> {
            categoryEntity.setChildren(getChildren(categoryEntity, all));
            return categoryEntity;
        }).sorted((s1,s2)->
                s1.getSort()==null ? 0 : s1.getSort() - (s1.getSort()==null ? 0 : s1.getSort())
        ).collect(Collectors.toList());
    }

}