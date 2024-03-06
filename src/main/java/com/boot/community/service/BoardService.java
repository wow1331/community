package com.boot.community.service;

import com.boot.community.model.Board;
import com.boot.community.model.User;
import com.boot.community.repository.BoardRepository;
import com.boot.community.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    public Board save(String username, Board board){
        User user = userRepository.findByUsername(username);
        board.setUser(user);

        return boardRepository.save(board);
    }

    public Board getBoard(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        board.setHit(board.getHit() + 1); // 조회수 1 증가
        boardRepository.save(board); // 변경된 정보를 저장

        return board;
    }

    public void upvote(Long id) {
        // 게시글을 찾아서 추천수를 1 증가시키는 로직
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        board.setRecommend(board.getRecommend() + 1); // 추천수 1 증가
        boardRepository.save(board); // 변경된 정보를 저장
    }

    public void downvote(Long id) {
        // 게시글을 찾아서 비추천수를 1 증가시키는 로직
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        board.setRecommend(board.getRecommend() - 1); // 추천수 1 감소
        boardRepository.save(board); // 변경된 정보를 저장
    }
}
