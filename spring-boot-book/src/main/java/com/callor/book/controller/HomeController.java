package com.callor.book.controller;

import com.callor.book.model.BookVO;
import com.callor.book.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
public class HomeController {

    private final BookService bookService;

    public HomeController(BookService bookService) {
        this.bookService = bookService;
    }

    // dao 를 jpa 로 바꿨을때 서비스에 있는 코드 변경으로 컨트롤러 그대로 사용 가능
    // 연결구조 활용해 코드를 최소화 할것, 결합도 낮은 코드!
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(@RequestParam(name = "code", required = false, defaultValue = "") String isbn, Model model) {
        List<BookVO> bookList = bookService.selectAll();

        // isbn 이 null 이 아니고 값이 있으면
        if(isbn != null || !isbn.isEmpty()) {
            BookVO bookVO = bookService.findById(isbn);
            model.addAttribute("BOOK",bookVO);
            log.debug("findBy 데이터 {}", bookVO);
        }
        model.addAttribute("BOOKS",bookList);
        return "index";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String home(BookVO bookVO) {

        log.debug("받은데이터 {}", bookVO);

        bookService.insert(bookVO);

        return "redirect:/";

    }
}
