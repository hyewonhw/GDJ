package service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;

public interface ShapeService {
	
	public ActionForward extcute(HttpServletRequest request, HttpServletResponse response) throws IOException;
	
}
