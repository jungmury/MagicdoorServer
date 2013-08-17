package jy.kim.lancs.magicdoor.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jy.kim.lancs.magicdoor.actioninterface.Action;
import jy.kim.lancs.magicdoor.announcement.action.DeleteAllAnnouncementAction;
import jy.kim.lancs.magicdoor.announcement.action.DeleteAnnouncementAction;
import jy.kim.lancs.magicdoor.announcement.action.GetAnnoucementAction;
import jy.kim.lancs.magicdoor.announcement.action.GetAnnoucementListAction;
import jy.kim.lancs.magicdoor.announcement.action.PostAnnoucementAction;
import jy.kim.lancs.magicdoor.announcement.action.UpdateAnnouncementAction;
import jy.kim.lancs.magicdoor.announcement.action.UpdateAnnouncementProAction;
import jy.kim.lancs.magicdoor.appointment.action.ConfirmAppointmentRequestCalendarViewAction;
import jy.kim.lancs.magicdoor.appointment.action.ConfirmAppointmentRequestListViewAction;
import jy.kim.lancs.magicdoor.appointment.action.GetAppointmentCalendarViewAction;
import jy.kim.lancs.magicdoor.appointment.action.GetAppointmentContentCalendarViewAction;
import jy.kim.lancs.magicdoor.appointment.action.GetAppointmentContentListViewAction;
import jy.kim.lancs.magicdoor.appointment.action.GetAppointmentListViewAction;
import jy.kim.lancs.magicdoor.appointment.action.RejectAppointmentRequestCalendarViewAction;
import jy.kim.lancs.magicdoor.appointment.action.RejectAppointmentRequestListViewAction;
import jy.kim.lancs.magicdoor.appointment.action.RejectAppointmentRequestProCalendarViewAction;
import jy.kim.lancs.magicdoor.appointment.action.RejectAppointmentRequestProListViewAction;
import jy.kim.lancs.magicdoor.message.action.GetReceivedMessageListAction;
import jy.kim.lancs.magicdoor.message.action.GetSelectedReceivedMessageAction;
import jy.kim.lancs.magicdoor.message.action.GetSelectedSentMessageAction;
import jy.kim.lancs.magicdoor.message.action.GetSentMessageListAction;
import jy.kim.lancs.magicdoor.message.action.ReplySelectedMessageAction;
import jy.kim.lancs.magicdoor.message.action.ReplySelectedMessageProAction;
import jy.kim.lancs.magicdoor.schedule.action.ConfirmTeachingAndAppointmentRequestAction;
import jy.kim.lancs.magicdoor.schedule.action.DeleteTimetableTeachingAndAppointmentAction;
import jy.kim.lancs.magicdoor.schedule.action.GetAppointmentContentScheduleViewAction;
import jy.kim.lancs.magicdoor.schedule.action.GetTeachingAndAppointmentAction;
import jy.kim.lancs.magicdoor.schedule.action.ManageTimetableTeachingAndAppointmentAction;
import jy.kim.lancs.magicdoor.schedule.action.RejectTeachingAndAppointmentRequestAction;
import jy.kim.lancs.magicdoor.schedule.action.RejectTeachingAndAppointmentRequestProAction;
import jy.kim.lancs.magicdoor.timetable.action.DeleteTimetableAction;
import jy.kim.lancs.magicdoor.timetable.action.GetTimetableAction;
import jy.kim.lancs.magicdoor.timetable.action.ManageTimetableAction;
import jy.kim.lancs.magicdoor.vo.ActionForward;

/**
 * Servlet implementation class MagicDoorController
 */
@WebServlet("*.action")
public class MagicDoorFunctionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MagicDoorFunctionController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		// ex) http://localhost:8088/board/writeForm.bo 요청이 전송되었으면..
		// ex) 반환값 = /board/writeForm.bo
		System.out.println("URI : " + requestURI);

		System.out.println("context path : " + request.getContextPath());

		// String directory = "/announcement"; //when jsp file is in
		// announcement directory
		String context = request.getContextPath();// + directory;

		System.out.println("getContextPath : " + context);
		// ex) 반환값 = /board
		String command = requestURI.substring(context.length());
		System.out.println("command : " + command);

		Action action = null;
		ActionForward forward = null;

		if (command.equals("/PostAnnouncement.action")) {
			action = new PostAnnoucementAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/UpdateAnnouncement.action")) {
			action = new UpdateAnnouncementAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/UpdateAnnouncementPro.action")) {
			action = new UpdateAnnouncementProAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/GetAnnouncementList.action")) {
			action = new GetAnnoucementListAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/GetAnnouncement.action")) {
			action = new GetAnnoucementAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/DeleteAnnouncement.action")) {
			action = new DeleteAnnouncementAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/DeleteAllAnnouncement.action")) {
			action = new DeleteAllAnnouncementAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/GetAppointmentListView.action")) {
			action = new GetAppointmentListViewAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/GetAppointmentCalendarView.action")) {
			// get appointments for calendar view
			action = new GetAppointmentCalendarViewAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/GetAppointmentContentListView.action")) {
			action = new GetAppointmentContentListViewAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/GetAppointmentContentCalendarView.action")) {
			action = new GetAppointmentContentCalendarViewAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/ConfirmAppointmentRequestListView.action")) {
			action = new ConfirmAppointmentRequestListViewAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command
				.equals("/ConfirmAppointmentRequestCalendarView.action")) {
			action = new ConfirmAppointmentRequestCalendarViewAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/RejectAppointmentRequestListView.action")) {
			action = new RejectAppointmentRequestListViewAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command
				.equals("/RejectAppointmentRequestCalendarView.action")) {
			action = new RejectAppointmentRequestCalendarViewAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command
				.equals("/RejectAppointmentRequestProCalendarView.action")) {
			action = new RejectAppointmentRequestProCalendarViewAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command
				.equals("/RejectAppointmentRequestProListView.action")) {
			action = new RejectAppointmentRequestProListViewAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/GetReceivedMessageList.action")) {
			action = new GetReceivedMessageListAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/GetSelectedReceivedMessage.action")) {
			action = new GetSelectedReceivedMessageAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/GetSentMessageList.action")) {
			action = new GetSentMessageListAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/GetSelectedSentMessage.action")) {
			action = new GetSelectedSentMessageAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/ReplySelectedMessage.action")) {
			action = new ReplySelectedMessageAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/ReplySelectedMessagePro.action")) {
			action = new ReplySelectedMessageProAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/GetTimetable.action")) {
			action = new GetTimetableAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/ManageTimetable.action")) {
			action = new ManageTimetableAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/DeleteTimetable.action")) {
			action = new DeleteTimetableAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/DeleteTimetableTeachingAndAppointment.action")) {
			action = new DeleteTimetableTeachingAndAppointmentAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/ManageTimetableTeachingAndAppointment.action")) {
			action = new ManageTimetableTeachingAndAppointmentAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/GetAppointmentContentScheduleView.action")) {
			action = new GetAppointmentContentScheduleViewAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/GetTeachingAndAppointment.action")) {
			action = new GetTeachingAndAppointmentAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/ConfirmTeachingAndAppointmentRequest.action")) {
			action = new ConfirmTeachingAndAppointmentRequestAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/RejectTeachingAndAppointmentRequest.action")) {
			action = new RejectTeachingAndAppointmentRequestAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}  else if (command.equals("/RejectTeachingAndAppointmentRequestPro.action")) {
			action = new RejectTeachingAndAppointmentRequestProAction();
			try {
				forward = action.excute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} 
		
		
		
		
		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getUrl());
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher(forward.getUrl());
				dispatcher.forward(request, response);
			}
		}
	}
}
