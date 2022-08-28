package com.kuang.controller;

import com.github.pagehelper.PageHelper;

import com.github.pagehelper.PageInfo;
import com.kuang.pojo.Books;
import com.kuang.pojo.Msg;
import com.kuang.service.BookService;
import com.sun.org.glassfish.gmbal.ParameterNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    @Qualifier("BookServiceImpl")
    private BookService bookService;

    @RequestMapping("/allBook")
    @ResponseBody
    public Msg list(@RequestParam(value = "pn", defaultValue = "1") Integer pn){
        // 引入PageHelper分页插件
        // 在查询之前只需要调用，传入页码，以及每页的大小

        PageHelper.startPage(pn,5);
        List<Books> emps = bookService.queryAllBook();
        for (Books books:emps) {
            System.out.println(books);
        }
        PageInfo page = new PageInfo(emps, 5);

        return Msg.success().add("pageInfo", page);
    }
    //Ajax增加员工方法，此处使用restful风格
    //由于提交过来的属性和Books类的属性相同，所以此处直接使用Books来接收
    @RequestMapping(value = "/addBook" ,method = RequestMethod.POST)
    @ResponseBody
    public Msg addBookAjax(Books books){
        bookService.addBook(books);
        return Msg.success();
    }


    @RequestMapping("/toUpdateBook")
    public String toUpdateBook(int id,Model model){
        System.out.println("执行到了这里toUpdateBook");
        Books bookById = bookService.queryBookById(id);
        System.out.println(bookById);
        model.addAttribute("books",bookById);
        return "updateBook";
    }

    @RequestMapping("/updateBook")
    public String updateBook(Books books){
        System.out.println(books);
        bookService.updateBook(books);
        return "redirect:/book/allBook";
    }
    @ResponseBody
    @RequestMapping(value="/deleteBook/{bookID}",method= RequestMethod.DELETE)
    public Msg deleteBook(@PathVariable("bookID") String ids){
        System.out.println("执行到了这里");
        System.out.println(ids);
        int id = Integer.parseInt(ids);
        bookService.deleteBookById(id);
        return Msg.success();
    }

    @RequestMapping("/toAddBook")
    public String toAddBook(){
            return "addBook";
    }


    //非Ajax增加员工方法
    @RequestMapping("/addBook")
    public String addBook(Books books){
        bookService.addBook(books);
        return "redirect:/book/allBook";
    }

    @RequestMapping("/queryBook")
    public String queryBook(String queryBookName, Model model){
        Books books = bookService.queryBookByName(queryBookName);
        ArrayList<Books> booksList = new ArrayList<>();
        booksList.add(books);
        if (books == null){
            model.addAttribute("error","未查到");
            List<Books> list = bookService.queryAllBook();
            model.addAttribute("list",list);
            return "allBook";
        }
        model.addAttribute("list",booksList);
        return "allBook";
    }
}
