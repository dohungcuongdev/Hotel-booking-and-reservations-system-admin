/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.List;

/**
 *
 * @author Do Hung Cuong
 */

public class TestModel {
	private String id;
	private Name name;
	private List<String> contribs;
	private List<Award> awards;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public List<String> getContribs() {
		return contribs;
	}

	public void setContribs(List<String> contribs) {
		this.contribs = contribs;
	}

	public List<Award> getAwards() {
		return awards;
	}

	public void setAwards(List<Award> awards) {
		this.awards = awards;
	}

	@Override
	public String toString() {
		return "TestModel [id=" + id + ", name=" + name + ", contribs=" + contribs + ", awards=" + awards + "]";
	}

}