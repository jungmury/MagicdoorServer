package jy.kim.lancs.magicdoor.schedule.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jy.kim.lancs.magicdoor.actioninterface.Action;
import jy.kim.lancs.magicdoor.rest.bean.AppointmentRespDetailBean;
import jy.kim.lancs.magicdoor.schedule.service.ConfirmTeachingAndAppointmentRequestService;
import jy.kim.lancs.magicdoor.vo.ActionForward;

public class ConfirmTeachingAndAppointmentRequestAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String refValue = request.getParameter("refValue");
		
		System.out.println("refValue = " + refValue);
		
		ConfirmTeachingAndAppointmentRequestService service = new ConfirmTeachingAndAppointmentRequestService();
		AppointmentRespDetailBean appointment = null;
		boolean isUpdated = service.confirmAppointmentRquestWith(refValue);
		ActionForward forward = new ActionForward();
		if(isUpdated){
			appointment = service.getAppointmentWithRefVal(refValue);
		}
		if (appointment != null) {
			request.setAttribute("appointment", appointment);
		}
		forward.setUrl("schedule/content_teaching_and_appointment_view_result.jsp");

		return forward;
	}

}
