package com.ssafy.vue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.vue.dto.Reply;
import com.ssafy.vue.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService{

	@Autowired
	private ReplyMapper replyMapper;
	
	@Override
	public List<Reply> selectReply(int aptId) {
		return replyMapper.selectReply(aptId);
	}

	@Override
	public boolean insertReply(Reply reply) {
		return replyMapper.insertReply(reply) == 1;
	}

	@Override
	@Transactional
	public boolean updateReply(Reply reply) {
		return replyMapper.updateReply(reply) == 1;
	}

	@Override
	@Transactional
	public boolean deleteReply(int userSeq) {
		return replyMapper.deleteReply(userSeq) == 1;
	}

	

}
