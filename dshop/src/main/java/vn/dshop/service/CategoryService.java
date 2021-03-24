package vn.dshop.service;

import vn.dshop.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategory();
    void save(Category c);
    Category getCategoryById(int id);
    Category getCategoryByName(String name);
    void delete(Category category);
}
