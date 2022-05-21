package com.ssafy.vue.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.dto.Reply;

@Mapper
public interface ReplyMapper {
	public List<Reply> selectReply(int aptId);
	public int insertReply(Reply reply);
	public int updateReply(Reply reply);
	public int deleteReply(int userSeq);
}
