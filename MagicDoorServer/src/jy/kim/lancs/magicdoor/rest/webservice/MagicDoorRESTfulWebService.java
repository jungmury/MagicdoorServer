package jy.kim.lancs.magicdoor.rest.webservice;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jy.kim.lancs.magicdoor.rest.action.RESTDeleteAnnouncementAction;
import jy.kim.lancs.magicdoor.rest.action.RESTDeleteAppointmentOfTheStudentUserAction;
import jy.kim.lancs.magicdoor.rest.action.RESTGETAppointmentsOfTheLecturerUserInTheMonthAction;
import jy.kim.lancs.magicdoor.rest.action.RESTGETAppointmentsOfTheLecturerUserInTheWeekAction;
import jy.kim.lancs.magicdoor.rest.action.RESTGETAppointmentsOfTheLecturerUserOnTheDateAction;
import jy.kim.lancs.magicdoor.rest.action.RESTGetAccountInfoAction;
import jy.kim.lancs.magicdoor.rest.action.RESTGetAllAppointmentsOfTheLecturerUserAction;
import jy.kim.lancs.magicdoor.rest.action.RESTGetAllMessagesOfTheSenderAction;
import jy.kim.lancs.magicdoor.rest.action.RESTGetAllNFCTagsInfoAction;
import jy.kim.lancs.magicdoor.rest.action.RESTGetAllReceivedMessagesOfTheReceiverAction;
import jy.kim.lancs.magicdoor.rest.action.RESTGetAllReceivedMessagesOfTheSenderAction;
import jy.kim.lancs.magicdoor.rest.action.RESTGetAllSentMessagesOfTheReceiverAction;
import jy.kim.lancs.magicdoor.rest.action.RESTGetAllSentMessagesOfTheSenderAction;
import jy.kim.lancs.magicdoor.rest.action.RESTGetAnnouncementsAction;
import jy.kim.lancs.magicdoor.rest.action.RESTGetAppointmentsListOfTheUserAction;
import jy.kim.lancs.magicdoor.rest.action.RESTGetDayTimeTableAction;
import jy.kim.lancs.magicdoor.rest.action.RESTGetDuplicateIDAction;
import jy.kim.lancs.magicdoor.rest.action.RESTGetLecturerInfoForTheRejectedAppointmentAction;
import jy.kim.lancs.magicdoor.rest.action.RESTGetLogInAction;
import jy.kim.lancs.magicdoor.rest.action.RESTGetWebServiceAction;
import jy.kim.lancs.magicdoor.rest.action.RESTGetWebServiceForQRCodeAction;
import jy.kim.lancs.magicdoor.rest.action.RESTGetWeekTimeTableAction;
import jy.kim.lancs.magicdoor.rest.action.RESTPostAnnouncementAction;
import jy.kim.lancs.magicdoor.rest.action.RESTPostNewMessageAction;
import jy.kim.lancs.magicdoor.rest.action.RESTPostNewTagAction;
import jy.kim.lancs.magicdoor.rest.action.RESTPostReplyMessageAction;
import jy.kim.lancs.magicdoor.rest.action.RESTPostTimeTableAction;
import jy.kim.lancs.magicdoor.rest.action.RESTPutAnnouncementAction;
import jy.kim.lancs.magicdoor.rest.action.RESTPutConfirmAppointmentAction;
import jy.kim.lancs.magicdoor.rest.action.RESTPutNewAppointmentAction;
import jy.kim.lancs.magicdoor.rest.action.RESTPutRejectAppointmentAction;
import jy.kim.lancs.magicdoor.rest.action.RESTPutSignUpAction;
import jy.kim.lancs.magicdoor.rest.action.RESTPutTimeTableAction;
import jy.kim.lancs.magicdoor.rest.action.RESTUpdateAccountInfoAction;

@Path("/webservice")
public class MagicDoorRESTfulWebService {
	@POST
	@Path("/get-appointments-of-the-lecturer-user-in-the-month")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAppointmentsOfTheTagOwnerInTheMonth(String query) {
		RESTGETAppointmentsOfTheLecturerUserInTheMonthAction action = new RESTGETAppointmentsOfTheLecturerUserInTheMonthAction();
		String result = action.excute(query);
		return result;
	}

	//excluding "rejected"
	@POST
	@Path("/get-appointments-of-the-lecturer-user-in-the-week")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAppointmentsOfTheTagOwnerInTheWeek(String query) {
		RESTGETAppointmentsOfTheLecturerUserInTheWeekAction action = new RESTGETAppointmentsOfTheLecturerUserInTheWeekAction();
		String result = action.excute(query);
		return result;
	}	
	
	@POST
	@Path("/get-all-appointments-of-the-lecturer-user-on-the-date")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAppointmentsOfTheLecturerUserOnTheDate(String query) {
		RESTGETAppointmentsOfTheLecturerUserOnTheDateAction action = new RESTGETAppointmentsOfTheLecturerUserOnTheDateAction();
		String result = action.excute(query);
		return result;
	}

	@POST
	@Path("/update-account-info")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateAccountInfo(String accInfo) {
		RESTUpdateAccountInfoAction updateAcc = new RESTUpdateAccountInfoAction();
		String result = updateAcc.excute(accInfo);
		return result;
	}

	@POST
	@Path("/log-in/")
	@Produces(MediaType.APPLICATION_JSON)
	public String logInFor(String userInfo) {
		RESTGetLogInAction logIn = new RESTGetLogInAction();
		String result = logIn.excute(userInfo);
		return result;
	}

	@POST
	@Path("/sign-up")
	@Produces(MediaType.APPLICATION_JSON)
	public String signUp(String signUpInfo) {
		RESTPutSignUpAction signUp = new RESTPutSignUpAction();
		String result = signUp.excute(signUpInfo);
		return result;
	}

	@POST
	@Path("/post-new-message")
	@Produces(MediaType.APPLICATION_JSON)
	public String addNewMessage(String message) {
		RESTPostNewMessageAction action = new RESTPostNewMessageAction();
		String result = action.excute(message);
		System.out.println("result: " + result);
		return result;
	}

	@POST
	@Path("/post-reply-message")
	@Produces(MediaType.APPLICATION_JSON)
	public String replyMessage(String message) {
		RESTPostReplyMessageAction action = new RESTPostReplyMessageAction();
		String result = action.excute(message);
		System.out.println("result: " + result);
		return result;
	}

	@POST
	@Path("/post-new-nfc-tag")
	@Produces(MediaType.APPLICATION_JSON)
	public String addNewTag(String message) {
		RESTPostNewTagAction action = new RESTPostNewTagAction();
		String result = action.excute(message);
		System.out.println("result: " + result);
		return result;
	}

	@POST
	@Path("/post-announcement")
	@Produces(MediaType.APPLICATION_JSON)
	public String postAnnouncement(String announcement) {
		RESTPostAnnouncementAction addNewAnn = new RESTPostAnnouncementAction();
		String result = addNewAnn.excute(announcement);
		return result;
	}

	@POST
	@Path("/insert-time-table")
	@Produces(MediaType.APPLICATION_JSON)
	public String addTimeTable(String timeTableInfo) {
		RESTPostTimeTableAction action = new RESTPostTimeTableAction();
		String result = action.excute(timeTableInfo);
		return result;
	}

	@GET
	@Path("/get-account/{userName}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAccInfo(@PathParam("userName") String userName) {
		RESTGetAccountInfoAction getAccInfo = new RESTGetAccountInfoAction();
		userName = "jane";
		String result = getAccInfo.excute(userName);
		return result;
	}

	@GET
	@Path("/get-all-announcements/{tagOwner}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAnnouncementsOfTheTagOwner(
			@PathParam("tagOwner") String lecturer) {
		RESTGetAnnouncementsAction action = new RESTGetAnnouncementsAction();
		String result = action.excute(lecturer);
		return result;
	}
	
	

	@GET
	@Path("/get-web-service/{tagId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getWebService(@PathParam("tagId") String tagId) {
		RESTGetWebServiceAction reqWebSvc = new RESTGetWebServiceAction();
		String result = reqWebSvc.excute(tagId);
		return result;
	}
	
	@GET
	@Path("/get-web-service-for-qr-code/{tagOwner}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getWebServiceForQRCode(@PathParam("tagOwner") String tagOwner) {
		RESTGetWebServiceForQRCodeAction reqWebSvc = new RESTGetWebServiceForQRCodeAction();
		String result = reqWebSvc.excute(tagOwner);
		return result;
	}

	@GET
	@Path("/get-account-info/{userName}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAccountInfo(@PathParam("userName") String userName) {
		RESTGetAccountInfoAction getAccInfo = new RESTGetAccountInfoAction();
		String result = getAccInfo.excute(userName);
		return result;
	}
	
	
	@GET
	@Path("/get-lecturer-info-for-the-rejected-appointment/{refValue}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getLecturerInfoForTheRejectedAppointment(@PathParam("refValue") String refValue) {
		System.out.println("GET LECTURER' INFO");
		RESTGetLecturerInfoForTheRejectedAppointmentAction getAccInfo = new RESTGetLecturerInfoForTheRejectedAppointmentAction();
		String result = getAccInfo.excute(refValue);
		return result;
	}
	
	

	@GET
	@Path("/get-week-time-table/{userName}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getWeekTimeTable(@PathParam("userName") String userName) {
		RESTGetWeekTimeTableAction action = new RESTGetWeekTimeTableAction();
		String result = action.excute(userName);
		return result;
	}

	@GET
	@Path("/get-day-time-table/{userName}/{day}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getDayTimeTable(@PathParam("userName") String userName,
			@PathParam("day") String day) {
		RESTGetDayTimeTableAction action = new RESTGetDayTimeTableAction();
		String result = action.excute(userName, day);
		return result;
	}

	@GET
	@Path("/get-all-messages-of-the-sender/{sender}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllMessagesOfTheSender(@PathParam("sender") String sender) {
		RESTGetAllMessagesOfTheSenderAction action = new RESTGetAllMessagesOfTheSenderAction();
		String result = action.excute(sender);
		return result;
	}

	@GET
	@Path("/get-all-appointments-of-the-student-user/{userName}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAppointmentsOfTheStduentUser(
			@PathParam("userName") String userName) {
		RESTGetAppointmentsListOfTheUserAction getAppointmentsList = new RESTGetAppointmentsListOfTheUserAction();
		String result = getAppointmentsList.excute(userName);
		return result;
	}

	@GET
	@Path("/check-duplicate-id/{userName}")
	@Produces(MediaType.APPLICATION_JSON)
	public String checkDuplicateID(@PathParam("userName") String userName) {
		System.out.println("userName= " + userName);
		RESTGetDuplicateIDAction chkDupID = new RESTGetDuplicateIDAction();
		String result = chkDupID.excute(userName);
		return result;
	}

	@GET
	@Path("/get-all-sent-messages-of-the-sender/{sender}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllSentMessagesOfTheSender(
			@PathParam("sender") String sender) {
		RESTGetAllSentMessagesOfTheSenderAction action = new RESTGetAllSentMessagesOfTheSenderAction();
		String result = action.excute(sender);
		return result;
	}

	@GET
	@Path("/get-all-received-messages-of-the-sender/{sender}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllReceivedMessagesOfTheSender(
			@PathParam("sender") String sender) {
		RESTGetAllReceivedMessagesOfTheSenderAction action = new RESTGetAllReceivedMessagesOfTheSenderAction();
		String result = action.excute(sender);
		return result;
	}

	@GET
	@Path("/get-all-nfc-tags-of-the-tag-owner/{tagOwner}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllNFCTagsOfTheTagOwner(
			@PathParam("tagOwner") String tagOwner) {
		RESTGetAllNFCTagsInfoAction action = new RESTGetAllNFCTagsInfoAction();
		String result = action.excute(tagOwner);
		return result;
	}

	@GET
	@Path("/get-all-received-messages-of-the-receiver/{receiver}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllReceivedMessagesOfTheReceiver(
			@PathParam("receiver") String sender) {
		RESTGetAllReceivedMessagesOfTheReceiverAction action = new RESTGetAllReceivedMessagesOfTheReceiverAction();
		String result = action.excute(sender);
		return result;
	}

	@GET
	@Path("/get-all-sent-messages-of-the-receiver/{receiver}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllSentMessagesOfTheReceiver(
			@PathParam("receiver") String sender) {
		RESTGetAllSentMessagesOfTheReceiverAction action = new RESTGetAllSentMessagesOfTheReceiverAction();
		String result = action.excute(sender);
		return result;
	}

	@GET
	@Path("/get-all-appointments-of-the-lecturer-user/{tagOwner}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllAppointmentsOfTheLecturerUser(
			@PathParam("tagOwner") String tagOwner) {
		RESTGetAllAppointmentsOfTheLecturerUserAction getAppointmentsList = new RESTGetAllAppointmentsOfTheLecturerUserAction();
		String result = getAppointmentsList.excute(tagOwner);
		return result;
	}

	@PUT
	@Path("/update-time-table")
	@Produces(MediaType.APPLICATION_JSON)
	public String modifyTimeTable(String timeTableInfo) {
		System.out.println("Update!");
		RESTPutTimeTableAction action = new RESTPutTimeTableAction();
		String result = action.excute(timeTableInfo);
		return result;
	}

	@PUT
	@Path("/request-appointment")
	@Produces(MediaType.APPLICATION_JSON)
	public String requestNewAppointment(String appointmentReqDetail) {
		RESTPutNewAppointmentAction reqNewAppointment = new RESTPutNewAppointmentAction();

		String result = reqNewAppointment.excute(appointmentReqDetail);
		return result;
	}

	@PUT
	@Path("/reject-appointment/")
	@Produces(MediaType.APPLICATION_JSON)
	public String rejectAnnouncement(String query) {
		RESTPutRejectAppointmentAction action = new RESTPutRejectAppointmentAction();
		String result = action.excute(query);
		return result;
	}

	@PUT
	@Path("/confirm-appointment/{userName}/{refValue}")
	@Produces(MediaType.APPLICATION_JSON)
	public String confirmAnnouncement(@PathParam("userName") String userName,
			@PathParam("refValue") String refValue) {
		RESTPutConfirmAppointmentAction action = new RESTPutConfirmAppointmentAction();
		String result = action.excute(refValue, userName);
		return result;
	}
	
	@PUT
	@Path("/put-announcement/")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateAnnouncement(String announcement) {
		RESTPutAnnouncementAction action = new RESTPutAnnouncementAction();
		String result = action.excute(announcement);
		System.out.println("result: " + result);
		return result;
	}

	@DELETE
	@Path("/delete-announcement/{annNo}/{tagOwner}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteAnAnnouncement(@PathParam("annNo") String annNo,
			@PathParam("tagOwner") String lecturer) {
		RESTDeleteAnnouncementAction action = new RESTDeleteAnnouncementAction();
		String result = action.excute(annNo, lecturer);
		return result;
	}

	@DELETE
	@Path("/delete-an-appointment-of-the-student-user/{requesterName}/{refValue}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteAnAppointmentOfTheUser(
			@PathParam("requesterName") String requesterName,
			@PathParam("refValue") String refValue) {
		RESTDeleteAppointmentOfTheStudentUserAction action = new RESTDeleteAppointmentOfTheStudentUserAction();
		String result = action.excute(refValue, requesterName);
		return result;
	}
}
