package com.cym.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cym.dao.CymDao;
import com.cym.dto.CymDtoList;

public class CListCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		CymDao dao = new CymDao();
		ArrayList<CymDtoList> dtos = dao.makeList();
		request.setAttribute("list", dtos);
	}
}
