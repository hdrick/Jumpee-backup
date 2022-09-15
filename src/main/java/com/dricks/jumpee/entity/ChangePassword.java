package com.dricks.jumpee.entity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class ChangePassword {
	
	@NotEmpty(message = "Current password is required!")
	private String currentPassword;
	
	@Size(min=8, message="Password should have 8 chracters")
	@Pattern(regexp=".*[A-Z].*.[0-9].*", message="Password should have alphanumeric.")
	@NotEmpty(message = "New Password is required!")
	private String newPassword;
	@NotEmpty(message = "Confirm password is required!")
	private String confirmPassword;

	public ChangePassword() {}
	
	public ChangePassword(String currentPassword,String newPassword,String confirmPassword) {
		this.currentPassword = currentPassword;
		this.newPassword = newPassword;
		this.confirmPassword = confirmPassword;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Override
	public String toString() {
		return "ChangePassword [currentPassword=" + currentPassword + ", newPassword=" + newPassword
				+ ", confirmPassword=" + confirmPassword + "]";
	}
}
