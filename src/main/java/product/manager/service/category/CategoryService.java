package product.manager.service.category;

import product.manager.config.ConnectionSingleton;
import product.manager.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryService implements ICategoryService{

    // TẠO INSTANCE THUỘC INTERFACE CONNECTION ĐỂ CUNG CẤP METHOD LÀM VỚI DATABASE
    Connection connection = ConnectionSingleton.getConnection();
    @Override
    public List<Category> findAll() {
        List<Category> categoryList = new ArrayList<>();
        // TẠO INSTANCE ĐỂ GỬI CÂU LỆNH SQL TỚI DATABASE
        try {
            PreparedStatement ps = connection.prepareStatement("select * from category");
            // SAU KHI TÌM, DÙNG ResultSet HỨNG CÁC RECORD
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("idC");
                String name = rs.getString("name");
                String description = rs.getString("description");
                categoryList.add(new Category(id, name, description));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryList;
    }

    @Override
    public void insert(Category category) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("insert into category(name , description) value (?,?)");
            ps.setString(1, category.getName());
            ps.setString(2, category.getDescription());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean update(Category category) throws SQLException {
        boolean rowUpdate = false;
        try {
            PreparedStatement ps = connection.prepareStatement("update category set name =?, description=? where idC=?");
            ps.setString(1, category.getName());
            ps.setString(2, category.getDescription());
            ps.setInt(3, category.getId());

            rowUpdate = ps.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdate;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        boolean rowDelete = false;
        try {
            PreparedStatement ps = connection.prepareStatement("delete from category where idC=?");
            ps.setInt(1, id);

            rowDelete = ps.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowDelete;
    }

    @Override
    public Category findById(int id) {
        Category category = null;
        try {
            PreparedStatement ps = connection.prepareStatement("select * from category where idC=?");
            ps.setInt(1, id);
            // SAU KHI TÌM DÙNG ResultSet HỨNG CÁC RECORD
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String name = rs.getString("name");
                String description = rs.getString("description");
                category = new Category(id, name, description);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }
}
