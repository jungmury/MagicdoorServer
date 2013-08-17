package jy.kim.lancs.magicdoor.schedule.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jy.kim.lancs.magicdoor.actioninterface.Action;
import jy.kim.lancs.magicdoor.appointment.service.RejectAppointmentRequestService;
import jy.kim.lancs.magicdoor.rest.bean.AppointmentRespDetailBean;
import jy.kim.lancs.magicdoor.vo.ActionForward;

public class RejectTeachingAndAppointmentRequestAction implements Action {

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
		forward.setUrl("schedule/reject_appointment_form_teaching_and_appointment_view.jsp");

		return forward;
	}

}
