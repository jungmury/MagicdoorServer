package jy.kim.lancs.magicdoor.rest.bean;

public class WebServiceInfoRespBean {
	private String serviceDesc;
	private String tagOwner;
	private String tagOwnerName;

	public WebServiceInfoRespBean() {
	}

	public String getServiceDesc() {
		return serviceDesc;
	}

	public void setServiceDesc(String serviceDesc) {
		this.serviceDesc = serviceDesc;
	}

	public String getTagOwner() {
		return tagOwner;
	}

	public void setTagOwner(String tagOwner) {
		this.tagOwner = tagOwner;
	}

	public void setTagOwnerName(String tagOwnerName) {
		this.tagOwnerName = tagOwnerName;
	}

	public String getTagOwnerName() {
		return tagOwnerName;
	}
}
