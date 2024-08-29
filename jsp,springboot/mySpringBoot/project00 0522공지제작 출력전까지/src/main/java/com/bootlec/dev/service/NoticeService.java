package com.bootlec.dev.service;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bootlec.dev.DataNotFoundException;
import com.bootlec.dev.entity.Notice;
import com.bootlec.dev.entity.NoticeRepository;
import com.bootlec.dev.user.SiteUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public Notice getNotice(Integer id) {
        return noticeRepository.findById(id)
                               .orElseThrow(() -> new DataNotFoundException("Notice not found with id: " + id));
    }

    public List<Notice> getList() {
        return noticeRepository.findAll();
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
        // 키워드를 사용하여 리스트를 가져옵니다.
        return noticeRepository.findAllByKeyword(kw);
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

