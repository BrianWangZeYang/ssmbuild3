package com.kuang.service;

import com.kuang.dao.BookMapper;
import com.kuang.pojo.Books;

import java.util.List;

/**
 * @author xxx
 * @version 1.0
 * @Description
 * @date 2022/8/22 23:20
 */
public class BookServiceImpl implements  BookService {

    private BookMapper bookMapper;

    public void setBookMapper(BookMapper bookMapper){
        this.bookMapper = bookMapper;
    }
    @Override
    public int addBook(Books book) {
        int addBook = bookMapper.addBook(book);
        return addBook;
    }

    @Override
    public int deleteBookById(int id) {
        return bookMapper.deleteBookById(id);
    }

    @Override
    public int updateBook(Books books) {
        return bookMapper.updateBook(books);
    }

    @Override
    public Books queryBookById(int id) {
        return bookMapper.queryBookById(id);
    }

    @Override
    public List<Books> queryAllBook() {
        return bookMapper.queryAllBook();
    }

    @Override
    public Books queryBookByName(String queryBookName) {
        return bookMapper.queryBookByName(queryBookName);
    }


}
