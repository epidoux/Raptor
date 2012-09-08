package com.raptor.entities.core;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
   /** Default value included to remove warning. Remove or modify at will. **/
   private static final long serialVersionUID = 1L;

   private Long id;

   private String pass;

   private String email;

   private Boolean active;
   
   private String role;
   
   private List<Scenario> scenarios;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getPass() {
      return this.pass;
   }

   public void setPass(String pass) {
      this.pass = pass;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public Boolean isActive() {
      return this.active;
   }

   public void setActive(Boolean active) {
      this.active = active;
   }
   
   public String getRole(){
	   return role;
   }
   
   public void setRole(String role){
	   this.role=role;
   }

	public List<Scenario> getScenarios() {
		return scenarios;
	}
	
	public void setScenarios(List<Scenario> scenarios) {
		this.scenarios = scenarios;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", active=" + active + ", role=" + role
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
   
}