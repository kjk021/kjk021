package com.bootlec.dev.entity;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {
    Notice findBySubject(String subject);
    Notice findBySubjectAndContent(String subject, String content);
    List<Notice> findBySubjectLike(String subject);
    
    // 페이지를 사용하지 않고 작성자와 리스트 값만 가져오도록 수정된 쿼리
    @Query("select distinct n "
            + "from Notice n "
            + "left outer join SiteUser u on n.author.id = u.id "
            + "where "
            + "   (n.subject like CONCAT('%', :kw, '%') "
            + "   or n.content like CONCAT('%', :kw, '%') "
            + "   or u.username like CONCAT('%', :kw, '%')) "
            + "and n.createDate in ("
            + "   select n2.createDate "
            + "   from Notice n2 "
            + "   ORDER BY n2.createDate DESC "
            + "   LIMIT 2)"
            + "order by n.createDate desc") // 최신순으로 정렬합니다.
    List<Notice> findTop2ByKeywordOrderByCreateDateDesc(@Param("kw") String kw);
}
