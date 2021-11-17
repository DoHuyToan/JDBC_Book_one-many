package product.manager.service.book;

import product.manager.config.ConnectionSingleton;
import product.manager.model.Book;
import product.manager.model.Category;
import product.manager.service.category.CategoryService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookService implements IBookService{
    private Connection connection = ConnectionSingleton.getConnection();
    private CategoryService categoryService = new CategoryService();

    @Override
    public List<Book> findAll() {
        List<Book> bookList = new ArrayList<>();
        // TẠO ĐỐI TƯỢNG ĐỂ GỬI CÂU LỆNH SQL ĐẾN DATABASE
        try {
            PreparedStatement ps = connection.prepareStatement("select * from book;");
            // SAU KHI DÙNG CÂU LỆNH SELECT TÌM, DÙNG ResultSet HỨNG CÁC RECORD
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idB");
                String name = rs.getString("name");
                String description = rs.getString("description");
                int ibC = rs.getInt("idC");
                Category category = categoryService.findById(ibC);
                bookList.add(new Book(id, name, description, category));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    @Override
    public void insert(Book book) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("insert into book(name, description, idC) value (?,?,?)");
            // LẤY GIÁ TRỊ Ở NGOÀI TRUYỀN VÀO ĐỂ SET CHO LẦN LƯỢT CÁC GIÁ TRỊ ?
            ps.setString(1, "name");
            ps.setString(2, "description");
            ps.setInt(3, book.getCategory().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean update(Book book) throws SQLException {
        boolean rowUpdate = false;
        try {
            PreparedStatement ps = connection.prepareStatement("update book set name=?, description=?, idC=? where idB=?");
            // LẤY GIÁ TRỊ Ở NGOÀI TRUYỀN VÀO ĐỂ SET CHO LẦN LƯỢT CÁC GIÁ TRỊ ?
            ps.setString(1, "name");
            ps.setString(2, "description");
            ps.setInt(3, book.getCategory().getId());
            ps.setInt(4, book.getId());

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
            PreparedStatement ps = connection.prepareStatement("delete from book where idB=?");
            // LẤY GIÁ TRỊ Ở NGOÀI TRUYỀN VÀO ĐỂ SET CHO LẦN LƯỢT CÁC GIÁ TRỊ ?
            ps.setInt(1, id);

            rowDelete = ps.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowDelete;
    }

    @Override
    public Book findById(int id) {
        Book book = null;
        try {
            PreparedStatement ps = connection.prepareStatement("select from book where idB=?");
            // SAU KHI DÙNG CÂU LỆNH SELECT TÌM, DÙNG ResultSet HỨNG CÁC RECORD
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String name= rs.getString("name");
                String description = rs.getString("description");
                int idC = rs.getInt("idC");
                Category category = categoryService.findById(idC);
                book = new Book();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }
}
