package jy.kim.lancs.magicdoor.actioninterface;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jy.kim.lancs.magicdoor.vo.ActionForward;

//standardize Action classes 
public interface Action {
	
	public abstract ActionForward excute(HttpServletRequest request, HttpServletResponse response);
	
}
