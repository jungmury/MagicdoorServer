package jy.kim.lancs.magicdoor.appointment.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jy.kim.lancs.magicdoor.actioninterface.Action;
import jy.kim.lancs.magicdoor.appointment.service.RejectAppointmentRequestProService;
import jy.kim.lancs.magicdoor.rest.bean.AppointmentRespDetailBean;
import jy.kim.lancs.magicdoor.vo.ActionForward;

public class RejectAppointmentRequestProListViewAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String refValue = request.getParameter("refValue");
		String note = request.getParameter("note");
		System.out.println("refValue = " + refValue);
		
		RejectAppointmentRequestProService service = new RejectAppointmentRequestProService();
		AppointmentRespDetailBean appointment = null;
		boolean isUpdated = service.rejectAppointmentRquestWith(refValue, note);
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
