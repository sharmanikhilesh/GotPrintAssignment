package org.gotprint.assignment.usernotes.model;

public class LinkReference {
	
	private String src;
	private String ref;
	
	public LinkReference() {
		super();
	}
	
	public LinkReference(String href, String src) {
		super();
		this.src = src;
		this.ref = href;
	}
	
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getHref() {
		return ref;
	}
	public void setHref(String href) {
		this.ref = href;
	}
	
}
