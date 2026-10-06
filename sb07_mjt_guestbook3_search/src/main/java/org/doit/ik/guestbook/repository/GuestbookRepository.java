package org.doit.ik.guestbook.repository;

import org.doit.ik.guestbook.entity.Guestbook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

//Querydsl의 BooleanBuilder을 사용하려면 GuestbookRepository가 
// QuerydslPredicateExecutor을 상속해야한다.
public interface GuestbookRepository extends JpaRepository<Guestbook, Long>
, QuerydslPredicateExecutor<Guestbook>{

}
