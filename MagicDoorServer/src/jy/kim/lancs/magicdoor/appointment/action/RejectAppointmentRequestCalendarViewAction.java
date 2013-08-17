package jy.kim.lancs.magicdoor.appointment.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jy.kim.lancs.magicdoor.actioninterface.Action;
import jy.kim.lancs.magicdoor.appointment.service.RejectAppointmentRequestService;
import jy.kim.lancs.magicdoor.rest.bean.AppointmentRespDetailBean;
import jy.kim.lancs.magicdoor.vo.ActionForward;

public class RejectAppointmentRequestCalendarViewAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String refValue = request.getParameter("refValue");

		System.out.println("refValue = " + refValue);

		RejectAppointmentRequestService service = new RejectAppointmentRequestService();
		AppointmentRespDetailBean appointment = service
				.getAppointmentWithRefVal(refValue);
		ActionForward forward = new ActionForward();
		if (appointment != null) {
			request.setAttribute("appointment", appointment);
		}
		forward.setUrl("appointment/reject_appointment_form_calendar_view.jsp");

		return forward;
	}

}
