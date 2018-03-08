package com.PT.web;


import com.PT.service.KBSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * created by yxhuang
 */
@Controller
@RequestMapping("/kbs")
public class KBSController {

//    @Autowired
//    private KBSService kbsService;


//    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
//    @ResponseBody
//    private String add(Book book) {
//        Book hasBook = bookService.getById(book.getBookId());
//        int i = -2;
//        if (hasBook == null) {
//            i = bookService.addBook(book);
//        }
//        return i > 0 ? "success" : "error";
//    }
}
