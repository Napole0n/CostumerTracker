package br.mg.customertracker.model;

public class Item {

	private int Id;
	private String description;
	private ItemGroup group;

	public enum ItemGroup {

		CARNE_BOVINA(1000), CARNE_SUINA(2000), INDUSTRIALIZADOS(3000);

		public final int group_code;

		private ItemGroup(int group_code) {
			this.group_code = group_code;
		}

	}

}
