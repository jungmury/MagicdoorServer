package jy.kim.lancs.magicdoor.vo;
//to create objects of the values to use to deal with url and determine if the request is to redirect

public class ActionForward {
	
	private String url;
	private boolean redirect;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isRedirect() {
		return redirect;
	}
	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}
	
}
