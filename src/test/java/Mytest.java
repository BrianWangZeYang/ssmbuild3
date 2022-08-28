import com.kuang.pojo.Books;
import com.kuang.service.BookService;
import org.apache.ibatis.javassist.ClassPath;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.awt.print.Book;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @author xxx
 * @version 1.0
 * @Description
 * @date 2022/8/22 23:24
 */
public class Mytest {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    BookService bookServiceImpl = (BookService) context.getBean("BookServiceImpl");
    @Test
    public void addBook(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookServiceImpl = (BookService) context.getBean("BookServiceImpl");
        int book = bookServiceImpl.addBook(new Books(1, "狂神", 999, "佳佑"));

        System.out.println(book);
    }
    @Test
    public void deleteBookById(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookServiceImpl = (BookService) context.getBean("BookServiceImpl");
        bookServiceImpl.deleteBookById(10);
    }
    @Test
    public void updateBook(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookServiceImpl = (BookService) context.getBean("BookServiceImpl");
        bookServiceImpl.updateBook(new Books(9, "狂神", 999, "java"));
    }

    @Test
    public void queryBookById(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookServiceImpl = (BookService) context.getBean("BookServiceImpl");
        bookServiceImpl.queryBookById(9);
    }

    @Test
    public void queryAlLBook(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookServiceImpl = (BookService) context.getBean("BookServiceImpl");
        List<Books> booksList = bookServiceImpl.queryAllBook();

    }
    @Test
    public void queryBookByName(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookServiceImpl = (BookService) context.getBean("BookServiceImpl");
        Books books = bookServiceImpl.queryBookByName("狂神");
        System.out.println(books);
    }
    @Test
    public void random(){
        for(int i=0;i<200;i++){

            // String uid = UUID.randomUUID().toString().substring(0,5)+i;  2fb6c59 书籍名称

            //书籍数量，0-100随机 Random rd=new Random();  int num=rd.nextInt(100)+1;
            //书籍详情
            for(int i1=0;i<200;i++){
                String uid = UUID.randomUUID().toString().substring(0,5)+i;
                String uids = UUID.randomUUID().toString();
                Random rd=new Random();
                int num=rd.nextInt(100)+1;
                bookServiceImpl.addBook(new Books(null,uid,num,uids));
            }
        }
    }
}
