package com.boot.community.controller;

import com.boot.community.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api")
public class BoardApiController {
    @Autowired
    private BoardRepository boardRepository;

    @DeleteMapping("/delete/{id}")
    void deleteBoard(@PathVariable Long id){
        log.info("@# deleteBoard()");

        boardRepository.deleteById(id);
    }
}
