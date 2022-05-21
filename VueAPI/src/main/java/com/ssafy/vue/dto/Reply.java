package com.ssafy.vue.dto;

public class Reply {
	private int aptId;
	private int userSeq;
	private String content;
	private int score;
	private int replySeq;
	
	public int getAptId() {
		return aptId;
	}
	public void setAptId(int aptId) {
		this.aptId = aptId;
	}
	public int getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getReplySeq() {
		return replySeq;
	}
	public void setReplySeq(int replySeq) {
		this.replySeq = replySeq;
	}
	
	@Override
	public String toString() {
		return "Reply [aptId=" + aptId + ", userSeq=" + userSeq + ", content=" + content + ", score=" + score
				+ ", replySeq=" + replySeq + "]";
	}
	
}
