package com.pojo;
		/**
		 * 
		 * @author SiVan
		 * @time 2017��4��24�� ����11:41:53
		 * TODO	�������
		 */
public class PortClassifyInfo {

	private String ClassifyName;
	private String ClassifyId;

	public String getClassifyName() {
		return ClassifyName;
	}

	public void setClassifyName(String classifyName) {
		ClassifyName = classifyName;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.ClassifyName;
	}

	public String getClassifyId() {
		return ClassifyId;
	}

	public void setClassifyId(String classifyId) {
		ClassifyId = classifyId;
	}
}
