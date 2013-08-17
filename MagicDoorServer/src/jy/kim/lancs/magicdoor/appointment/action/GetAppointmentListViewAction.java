package jy.kim.lancs.magicdoor.appointment.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jy.kim.lancs.magicdoor.actioninterface.Action;
import jy.kim.lancs.magicdoor.appointment.service.GetAppointmentService;
import jy.kim.lancs.magicdoor.rest.bean.AppointmentRespDetailBean;
import jy.kim.lancs.magicdoor.vo.ActionForward;

public class GetAppointmentListViewAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String userName = request.getParameter("userName");
		
		System.out.println("userName = " + userName);
		
		GetAppointmentService service = new GetAppointmentService();
		ArrayList<AppointmentRespDetailBean> appointments = service.getAppointmentsOf(userName);
		ActionForward forward = new ActionForward();
		if (appointments != null) {
			request.setAttribute("appointmentList", appointments);
		}
		forward.setUrl("appointment/list_view.jsp");

		return forward;
	}

}
