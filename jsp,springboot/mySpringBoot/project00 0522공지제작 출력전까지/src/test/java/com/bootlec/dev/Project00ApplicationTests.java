package com.bootlec.dev;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.bootlec.dev.entity.AnswerRepository;
import com.bootlec.dev.service.QuestionService;

@SpringBootTest
class Project00ApplicationTests {

	 @Autowired
	    private  QuestionService questionService;

	 //@Autowired
	    //private AnswerRepository answerRepository;
	//@Transactional //이거넣으니까 테스트작동안함 뭐지?
	@Test
	void contextLoads() {		
		
		//페이징 테스트를 위한 더미값
		 for (int i = 1; i <= 100; i++) {
	            String subject = String.format("테스트 데이터입니다:[%03d]", i);
	            String content = "내용무";
	            this.questionService.create(subject, content,null);
	        }
		
		
		//test용 기본입력 텍스트[테스트완료]
		/*Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가요?");
        q1.setContent("sbb에 대해서 알고 싶습니다.");
        q1.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q1);  // 첫번째 질문 저장

        Question q2 = new Question();
        q2.setSubject("스프링부트 모델 질문입니다.");
        q2.setContent("id는 자동으로 생성되나요?");
        q2.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q2);  // 두번째 질문 저장*/
        
        /*List<Question> all = this.questionRepository.findAll();
        assertEquals(2, all.size());*/
        
         //전체검색 명령어
        /*Question q = all.get(0);
        assertEquals("sbb가 무엇인가요?", q.getSubject());*/
        
        //id를통해검색
        /*ifOptional<Question> oq = this.questionRepository.findById(1);
       (oq.isPresent()) {
            Question q = oq.get();
            assertEquals("sbb가 무엇인가요?", q.getSubject());}*/
        // 내용으로검색    
       /* Question q = this.questionRepository.findBySubjectAndContent(
                "sbb가 무엇인가요?", "sbb에 대해서 알고 싶습니다.");
        assertEquals(1, q.getId());*/
        //문자열을 통해검색
        /*List<Question> qList = this.questionRepository.findBySubjectLike("sbb%");
        Question q = qList.get(0);
        assertEquals("sbb가 무엇인가요?", q.getSubject());*/
        
        //수정하기
        /*Optional<Question> oq = this.questionRepository.findById(1);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        q.setSubject("수정된 제목");
        this.questionRepository.save(q);*/
        
        //삭제
        /*assertEquals(2, this.questionRepository.count());
        Optional<Question> oq = this.questionRepository.findById(1);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        this.questionRepository.delete(q);
        assertEquals(1, this.questionRepository.count());*/
	
        //답변데이터저장
        /*Optional<Question> oq = this.questionRepository.findById(2);
        assertTrue(oq.isPresent());
        Question q = oq.get();

        //------------------------질문저장---------------------
        Answer a = new Answer();
        a.setContent("네 자동으로 생성됩니다.");
        a.setQuestion(q);  // 어떤 질문의 답변인지 알기위해서 Question 객체가 필요하다.
        a.setCreateDate(LocalDateTime.now());
        this.answerRepository.save(a);*/
        
        //답변데이터조회
        
        /*Optional<Answer> oa = this.answerRepository.findById(1);
        assertTrue(oa.isPresent());
        Answer a = oa.get();
        assertEquals(2, a.getQuestion().getId());*/
        
        //답변 or 질문으로 찾기
        /*Optional<Question> oq = this.questionRepository.findById(2);
        assertTrue(oq.isPresent());
        Question q = oq.get();

        List<Answer> answerList = q.getAnswerList();

        assertEquals(1, answerList.size());
        assertEquals("네 자동으로 생성됩니다.", answerList.get(0).getContent());*/

}
	
}
