package vn.dshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.dshop.entity.Category;
import vn.dshop.repository.CategoryRepository;
import vn.dshop.service.CategoryService;

import javax.transaction.Transactional;
import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    @Override
    public List<Category> getAllCategory() {
        return this.categoryRepository.getAllCategory();
    }

    @Override
    public void save(Category c) {
        this.categoryRepository.save(c);
    }

    @Override
    public Category getCategoryById(int id) {
        return this.categoryRepository.getCategoryById(id);
    }

    @Override
    public Category getCategoryByName(String name) {
        return this.categoryRepository.getCategoryByName(name);
    }

    @Override
    @Transactional
    public void delete(Category category) {
        this.categoryRepository.delete(category);
    }


}
