package io.company.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("api")
public class LibraryRestController {
    //here we are creating our end-point rest API
    @Autowired
    BookRepository bookRepository;

    //CRUD: read
    @GetMapping("books")
    public Page bookPageable(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }




}
