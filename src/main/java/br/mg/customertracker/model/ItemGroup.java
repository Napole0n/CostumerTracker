package br.mg.customertracker.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity(name = "item_groups")
public class ItemGroup implements Serializable {
	@Id
	private int groupId;
	private String groupDesc;

	public ItemGroup() {

	};

	public ItemGroup(int groupId, String groupDesc) {
		this.groupDesc = groupDesc;
		this.groupId = groupId;
	}

	public int getGroupId() {
		return groupId;
	}

	public String getGroupDesc() {
		return groupDesc;
	}

}
