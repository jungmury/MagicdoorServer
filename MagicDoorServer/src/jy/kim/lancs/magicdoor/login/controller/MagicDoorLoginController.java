package jy.kim.lancs.magicdoor.login.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jy.kim.lancs.magicdoor.actioninterface.Action;
import jy.kim.lancs.magicdoor.login.action.CloseAccountAction;
import jy.kim.lancs.magicdoor.login.action.LogInAction;
import jy.kim.lancs.magicdoor.login.action.RetrieveAccountInfoAction;
import jy.kim.lancs.magicdoor.login.action.SignUpAction;
import jy.kim.lancs.magicdoor.login.action.UpdateAccountInfoAction;
import jy.kim.lancs.magicdoor.vo.ActionForward;

/**
 * Servlet implementation class MagicDoorController
 */
@WebServlet("*.login")
public class MagicDoorLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MagicDoorLoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		// ex) http://localhost:8088/board/writeForm.bo 요청이 전송되었으면..
		// ex) 반환값 = /board/writeForm.bo
		System.out.println("URI : " + requestURI);
		
		System.out.println("context path : " + request.getContextPath());
		
		String directory = "/login";
		String context = request.getContextPath() + directory;
		
		System.out.println("getContextPath : "+ context);
		// ex) 반환값 = /board
		String command = requestURI.substring(context.length());
		System.out.println("command : " + command);
		
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/LogInAction.login")){
			action = new LogInAction();
			try{
				forward = action.excute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if(command.equals("/SignUpAction.login")){
			action = new SignUpAction();
			try{
				forward = action.excute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if(command.equals("/CloseAccountAction.login")){
			action = new CloseAccountAction();
			try{
				forward = action.excute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if(command.equals("/RetrieveAccountInfoAction.login")){
			action = new RetrieveAccountInfoAction();
			try{
				forward = action.excute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if(command.equals("/UpdateAccountInfoAction.login")){
			action = new UpdateAccountInfoAction();
			try{
				forward = action.excute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if(command.equals("/RESTfulLogInAction.login")){
			System.out.println("RESTful REQUEST");
			System.out.println(request.getParameterNames());
			System.out.println(request.getContentType());
		} 
		
		if(forward != null) {
			if(forward.isRedirect()){
				response.sendRedirect(forward.getUrl());
			} else {
				RequestDispatcher dispatcher = 
						request.getRequestDispatcher(forward.getUrl());
				dispatcher.forward(request, response);
			}
		}
	}
	
}
