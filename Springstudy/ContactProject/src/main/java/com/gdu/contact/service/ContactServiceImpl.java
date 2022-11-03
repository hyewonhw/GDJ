package com.gdu.contact.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdu.contact.domain.ContactDTO;
import com.gdu.contact.repository.ContactDAO;


@Service 
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactDAO dao;
	
	@Override
	public List<ContactDTO> findAllContacts() {
		return null;
	}

	@Override
	public ContactDTO findContactByNo(int board_no) {
		return null;
	}

	@Override
	public int saveContact(ContactDTO contact) {
		return 0;
	}

	@Override
	public int modifyContact(ContactDTO contact) {
		return 0;
	}

	@Override
	public int removeContact(int board_no) {
		return 0;
	}

}
