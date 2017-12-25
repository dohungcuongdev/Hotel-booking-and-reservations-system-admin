/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.mysql;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import model.AbstractModel;

/**
 *
 * @author Do Hung Cuong
 */

@MappedSuperclass
public abstract class MySQLAbstractModel extends AbstractModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	protected int id;
	
	@Column(name = "name")
	protected String name;

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
    public MySQLAbstractModel() {}
    
    public MySQLAbstractModel(int id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
    public abstract String toString();
}
