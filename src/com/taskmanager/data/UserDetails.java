/**
 * 
 */
package com.taskmanager.data;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author dwarakak
 *
 */
@Document(collection="SystemMembers")
public class UserDetails {

	private String empId;
	
	private String empName;
	
	private String empPwd;
	
	private String empRole;
	
	private String empStatus;
	
	private String contactNo;
	
	private String contactAddress;

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpRole() {
		return empRole;
	}

	public void setEmpRole(String empRole) {
		this.empRole = empRole;
	}

	public String getEmpPwd() {
		return empPwd;
	}

	public void setEmpPwd(String empPwd) {
		this.empPwd = empPwd;
	}

	public String getEmpStatus() {
		return empStatus;
	}

	public void setEmpStatus(String empStatus) {
		this.empStatus = empStatus;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	@Override
	public String toString() {
		return "UserDetails [empId=" + empId + ", empName=" + empName
				+ ", empPwd=" + empPwd + ", empRole=" + empRole
				+ ", empStatus=" + empStatus + ", contactNo=" + contactNo
				+ ", contactAddress=" + contactAddress + "]";
	}
}
