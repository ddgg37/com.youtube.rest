package com.youtube.classes;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ItemEntry {
	private String PC_PARTS_TITLE;
	private String PC_PARTS_CODE;
	private String PC_PARTS_MAKER;
	private String PC_PARTS_AVAIL;
	private String PC_PARTS_DESC;
	
	public String getPC_PARTS_TITLE() {
		return PC_PARTS_TITLE;
	}
	public void setPC_PARTS_TITLE(String pC_PARTS_TITLE) {
		PC_PARTS_TITLE = pC_PARTS_TITLE;
	}
	public String getPC_PARTS_CODE() {
		return PC_PARTS_CODE;
	}
	public void setPC_PARTS_CODE(String pC_PARTS_CODE) {
		PC_PARTS_CODE = pC_PARTS_CODE;
	}
	public String getPC_PARTS_MAKER() {
		return PC_PARTS_MAKER;
	}
	public void setPC_PARTS_MAKER(String pC_PARTS_MAKER) {
		PC_PARTS_MAKER = pC_PARTS_MAKER;
	}
	public String getPC_PARTS_AVAIL() {
		return PC_PARTS_AVAIL;
	}
	public void setPC_PARTS_AVAIL(String pC_PARTS_AVAIL) {
		PC_PARTS_AVAIL = pC_PARTS_AVAIL;
	}
	public String getPC_PARTS_DESC() {
		return PC_PARTS_DESC;
	}
	public void setPC_PARTS_DESC(String pC_PARTS_DESC) {
		PC_PARTS_DESC = pC_PARTS_DESC;
	}
	
}
