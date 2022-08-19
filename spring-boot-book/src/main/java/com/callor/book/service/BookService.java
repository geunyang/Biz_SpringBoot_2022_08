package com.callor.book.service;

import com.callor.book.model.BookVO;

import java.util.List;
import java.util.Optional;

public interface BookService {

    public List<BookVO> selectAll();
    
    /*
    * Spring-data 와 관련된 method 에서 findById() method 의 return type 이 Optional<T> 이다
    * 보통 findById(id) 는 1개의 데이터를 select 하여 VO 에 담아주는 method 이다
    * 그런데 id 에 해당하는 데이터가 없을 경우 findById() 는 null 값을 return 한다
    * null 값을 return 한다는 것은 잠재적으로 nullPointer Exception 을 일으킬 수 있다는 것을 의미한다
    * 보통 이 return 된 데이터를 취급할때 값이 null 인지 검사하는 코드가 많이 만들어져야 한다
    * 이러한 번거로움을 감소하고 코드를 간소화 하기 위하여 null 값이 될만한 데이터는 Optional 로 감싸서
    * null 검사를 쉽게 할 수 있도록 만든 새로운 type 이다
    * 실제 데이터를 감싸는 wrapper 이며 null 을 쉽게 다루게 해주는 도구이다
    * */
    public BookVO findById(String isbn);

    public int insert(BookVO bookVO);
    public int update(BookVO bookVO);
    public int delete(BookVO bookVO);

    public List<BookVO> findByTitle(String title);
    public List<BookVO> findByComp(String comp);
    public List<BookVO> findByAuthor(String author);

}
