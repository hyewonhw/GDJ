package service;

import domain.ContactDTO;

public interface ContactService {
	// 인터페이스 : 작업지시서같은 역할을 수행함
	// 추상메소드 : 본문이 없는 메소드 / 세미콜론으로 마무리
	
	public void addContact(ContactDTO contact);    // 연락처 추가 (이름, 연락처, 이메일등 필요)
	public void modifyContact(ContactDTO contact); // 연락처 수정 
	public void deleteContact(int contact_no);     // 연락처 삭제 (번호만 있으면되니까 int로)
	public void findContactByNo(int contact_no);   // 연락처 조회 (번호로 조회)
	public void findAllContacts();
}
