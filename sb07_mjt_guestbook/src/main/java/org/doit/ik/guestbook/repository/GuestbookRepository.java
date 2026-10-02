package org.doit.ik.guestbook.repository;

import org.doit.ik.guestbook.entity.Guestbook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestbookRepository extends JpaRepository<Guestbook, Long>{

}
