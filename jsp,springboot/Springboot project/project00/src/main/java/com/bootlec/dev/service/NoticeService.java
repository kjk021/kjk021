package com.bootlec.dev.service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import com.bootlec.dev.DataNotFoundException;
import com.bootlec.dev.entity.Notice;
import com.bootlec.dev.entity.NoticeRepository;
import com.bootlec.dev.entity.Question;
import com.bootlec.dev.user.SiteUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public Notice getNotice(Integer id) {
       
        Optional<Notice> notice = this.noticeRepository.findById(id);
        if (notice.isPresent()) {
            return notice.get();
        } else {
            throw new DataNotFoundException("notice not found");
        }
    
    }
    

    public List<Notice> getList() {
       
        return noticeRepository.findTop2ByKeywordOrderByCreateDateDesc("");
    }

    public void create(String subject, String content, SiteUser user) {
        Notice n = new Notice();
        n.setSubject(subject);
        n.setContent(content);
        n.setCreateDate(LocalDateTime.now());
        n.setAuthor(user);
        noticeRepository.save(n);
    }

    public List<Notice> getList(String kw) {
      
        return noticeRepository.findTop2ByKeywordOrderByCreateDateDesc(kw);
    }

    public void modify(Notice notice, String subject, String content) {
        notice.setSubject(subject);
        notice.setContent(content);
        notice.setModifyDate(LocalDateTime.now());
        noticeRepository.save(notice);
    }

    public void delete(Notice notice) {
        noticeRepository.delete(notice);
    }
}

