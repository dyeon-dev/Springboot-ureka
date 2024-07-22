package com.ureka.myspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ureka.myspring.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {

}
