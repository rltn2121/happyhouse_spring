package com.ssafy.vue.service;

import java.util.List;

import com.ssafy.vue.dto.Reply;

public interface ReplyService {
	public List<Reply> selectReply(int aptId);
	public boolean insertReply(Reply reply);
	public boolean updateReply(Reply reply);
	public boolean deleteReply(int userSeq);
}
