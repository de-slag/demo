package de.slag.demo.persist;

import javax.persistence.Basic;
import javax.persistence.Entity;

import de.slag.common.model.EntityBean;

@Entity
public class TestEntity42 extends EntityBean {
	
	@Basic
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
