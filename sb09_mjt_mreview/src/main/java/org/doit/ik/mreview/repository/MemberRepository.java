package org.doit.ik.mreview.repository;

import org.doit.ik.mreview.entity.Member;
import org.doit.ik.mreview.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>{

}
