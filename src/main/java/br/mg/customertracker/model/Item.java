package br.mg.customertracker.model;

import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@SuppressWarnings("serial")
@Entity(name = "items")
public class Item implements Serializable {

	@Id
	private int Id;
	private String description;

	@OneToMany(targetEntity = ItemGroup.class, fetch = FetchType.EAGER)
	@OrderColumn
	private ItemGroup[] groups;

	public Item() {
	}

	public Item(int id, String description, ItemGroup... groups) {
		this.Id = id;
		this.description = description;
		this.groups = groups;
	}

	public int getId() {
		return Id;
	}

	public String getDescription() {
		return description;
	}

	public ItemGroup[] getGroups() {
		return groups;
	}

	@Override
	public String toString() {
		return "Item [Id=" + Id + ", description=" + description + ", groups=" + Arrays.toString(groups) + "]";
	}

}
