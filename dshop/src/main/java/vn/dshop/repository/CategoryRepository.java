package vn.dshop.repository;

import vn.dshop.entity.Category;

import java.util.List;

public interface CategoryRepository {
    List<Category> getAllCategory();
    void delete(Category category);
    void save(Category c);
    Category getCategoryById(int id);
    Category getCategoryByName(String name);
}
