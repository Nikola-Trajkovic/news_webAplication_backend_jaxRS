package rs.raf.demo.repositories.category;

import rs.raf.demo.entities.Category;

import java.util.List;

public interface CategoryRepository {

    Category addCategory(Category category);

    List<Category> allCategories();

    Category findCategory(Integer id);

    void delete(Integer id);

    void update(Category category);

}
