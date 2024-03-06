package com.boot.community.controller;

import com.boot.community.model.Board;
import com.boot.community.repository.BoardRepository;
import com.boot.community.service.BoardService;
import com.boot.community.validator.BoardValidator;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BoardValidator boardValidator;

    @Autowired
    private BoardService boardService;

    @GetMapping("/list")
    public String list(Model model, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, @RequestParam(required = false, defaultValue = "") String searchText){
        log.info("@# list()");

        Page<Board> boards = boardRepository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);

        int startPage = 1;
        int endPage = boards.getTotalPages();

        model.addAttribute("boards",boards);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);

        return "board/list";
    }
    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long id){
        log.info("@# GetMapping form()");

        if (id == null){//쓰기
            model.addAttribute("board",new Board());
        }else {//수정
            Board board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board",board);
        }

        return "board/form";
    }

    @PostMapping("/form")
    public String form(@Valid Board board, BindingResult bindingResult, Authentication authentication) {
        log.info("@# PostMapping form()");

        boardValidator.validate(board, bindingResult);

        if (bindingResult.hasErrors()) {
            return "board/form";
        }

        String username = authentication.getName();
        boardService.save(username, board);

        return "redirect:/board/list";
    }

    @GetMapping("/view")
    public String view(Model model, @RequestParam(required = false) Long id){
        log.info("@# GetMapping form()");

        // 조회수를 증가시키는 로직이 포함된 boardService.getBoard(id)를 호출
        Board board = boardService.getBoard(id);
        model.addAttribute("board",board);

        return "board/view";
    }

    @PostMapping("/upvote/{id}")
    public ResponseEntity<?> upvote(@PathVariable Long id) {
        boardService.upvote(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/downvote/{id}")
    public ResponseEntity<?> downvote(@PathVariable Long id) {
        boardService.downvote(id);
        return ResponseEntity.ok().build();
    }
}
