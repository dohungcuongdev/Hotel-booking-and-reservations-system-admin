/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.mongodb;

import javax.persistence.MappedSuperclass;

import model.AbstractModel;

/**
 *
 * @author Do Hung Cuong
 */

@MappedSuperclass
public abstract class MongoDbAbstractModel extends AbstractModel {
	
	protected String _id;
	protected String name;
	protected String created_at;
	
	public String get_id() {
		return _id;
	}

	public void set_id(String id) {
		this._id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	@Override
	public abstract String toString();
}
