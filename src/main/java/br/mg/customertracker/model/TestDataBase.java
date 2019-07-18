package br.mg.customertracker.model;

import com.core.controller.Kimera;

import br.mg.customertracker.database.SqliteDatabase;

public class TestDataBase {

	public static void main(String[] args) {
		SqliteDatabase database = new SqliteDatabase();
		Kimera k = database.getKimera();

	}

}
