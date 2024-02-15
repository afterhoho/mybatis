package com.oracle.oBootBoard.dao;

import java.util.ArrayList;

import com.oracle.oBootBoard.dto.BDto;

public interface BDao {
	public ArrayList<BDto>boardList() ;

	public void write(String bName, String bTitle, String bContent);

	public BDto contentView(int bId);

	public void modify(int bId, String bName, String bTitle, String bContent);

	public BDto reply_view(int bId);

	public void reply(int bId, String bName, String bTitle, String bContent, int bGroup, int bStep, int bIndent);

	public void delete(int bId);

}
