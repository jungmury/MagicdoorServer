package jy.kim.lancs.magicdoor.appointment.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jy.kim.lancs.magicdoor.actioninterface.Action;
import jy.kim.lancs.magicdoor.appointment.service.ConfirmAppointmentRequestService;
import jy.kim.lancs.magicdoor.rest.bean.AppointmentRespDetailBean;
import jy.kim.lancs.magicdoor.vo.ActionForward;

public class ConfirmAppointmentRequestListViewAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String refValue = request.getParameter("refValue");
		
		System.out.println("refValue = " + refValue);
		
		ConfirmAppointmentRequestService service = new ConfirmAppointmentRequestService();
		AppointmentRespDetailBean appointment = null;
		boolean isUpdated = service.confirmAppointmentRquestWith(refValue);
		ActionForward forward = new ActionForward();
		if(isUpdated){
			appointment = service.getAppointmentWithRefVal(refValue);
		}
		if (appointment != null) {
			request.setAttribute("appointment", appointment);
		}
		forward.setUrl("appointment/content_list_view_result.jsp");

		return forward;
	}

}
