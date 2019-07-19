package br.mg.customertracker.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.core.controller.Kimera;

import br.mg.customertracker.model.Adress;
import br.mg.customertracker.model.Company;
import br.mg.customertracker.model.Customer;
import br.mg.customertracker.model.Item;
import br.mg.customertracker.model.ItemGroup;

public class PamplonaDatabaseAcessor {

	public static void importAllCustomersAndAdresses() {

		Kimera k = SqliteDatabase.getKimera();

		Connection c = PamplonaDatabase.getInstance().getConnection();
		Statement stat = null;
		ResultSet rs = null;

		try {
			stat = c.createStatement();
			rs = stat.executeQuery("select t.cli_cod CNPJ,\r\n" + "       t.cli_nomtot NOME,\r\n"
					+ "       a.cli_end RUA,\r\n" + "       a.cli_endnro NUMERO,\r\n" + "       a.cli_bai BAIRRO,\r\n"
					+ "       b.cid_des CIDADE,\r\n" + "       b.est_cod ESTADO,\r\n" + "       c.pai_des PAIS,\r\n"
					+ "              a.cli_lat LATITUDE_PRIMUS,\r\n" + "       a.cli_lon LONGITUDE_PRIMUS,\r\n"
					+ "       a.cli_otmlat LATITUDE_OTM,\r\n" + "       a.cli_otmlon LONGITUDE_OTM\r\n"
					+ "  from clicad t, cliend a, cidcad b, paicad c\r\n" + "  where t.cli_cod=a.cli_cod\r\n"
					+ "  and t.cli_fj <>'F'\r\n" + "  and t.cli_sta ='A'\r\n" + "  and a.cli_cidcod = b.cid_cod\r\n"
					+ "  and a.cli_pai = b.pai_cod\r\n" + "  and a.cli_est = b.est_cod\r\n"
					+ "  and b.pai_cod = c.pai_cod and a.cli_tip = 1\r\n" + "  order by t.cli_dat");

			while (rs.next()) {

				String lat = rs.getString(11);
				if (lat == null) {
					lat = "0";
				}
				Customer customer = null;
				Adress adress = null;

				if (!lat.contains(".") || lat.equals("0.0") || lat.equals("0")) {

					adress = new Adress(rs.getString(4), rs.getString(3), rs.getString(6), rs.getString(7),
							rs.getString(8));

				} else {
					adress = new Adress(rs.getString(4), rs.getString(3), rs.getString(6), rs.getString(7),
							rs.getString(8), rs.getDouble(11), rs.getDouble(12));
				}

				customer = new Customer(rs.getLong(1), adress, rs.getString(2));
				k.add(adress);
				k.add(customer);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stat.close();
				c.close();
			} catch (SQLException e) {
			}

		}
		SqliteDatabase.close();
	}

	public static void importItemGroups() {
		Kimera k = SqliteDatabase.getKimera();
		Connection c = PamplonaDatabase.getInstance().getConnection();
		Statement stat = null;
		ResultSet rs = null;
		ItemGroup group;

		try {
			stat = c.createStatement();
			rs = stat.executeQuery("select * from sg1cad");

			while (rs.next()) {

				group = new ItemGroup(rs.getInt(1), rs.getString(2));
				k.add(group);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stat.close();
				c.close();
			} catch (SQLException e) {
			}

		}

		c = PamplonaDatabase.getInstance().getConnection();

		try {
			stat = c.createStatement();
			rs = stat.executeQuery("select gru_cod, gru_des from grucad");

			while (rs.next()) {

				group = new ItemGroup(rs.getInt(1), rs.getString(2));
				k.add(group);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stat.close();
				c.close();
			} catch (SQLException e) {
			}

		}
		SqliteDatabase.close();

	}

	public static void importItems() {

		Kimera k = SqliteDatabase.getKimera();

		ItemGroupBundle groups = new ItemGroupBundle(k.all(ItemGroup.class));

		Connection c = PamplonaDatabase.getInstance().getConnection();
		Statement stat = null;
		ResultSet rs = null;

		try {
			stat = c.createStatement();
			rs = stat.executeQuery(
					"select a.its_cod, a.its_des, a.gru_cod, a.sg1_cod, a.sg2_cod, a.sg3_cod, a.sg4_cod, a.sg5_cod from ITSCAD a where a.gru_cod in (1000,2000,3000,33000)\r\n"
							+ "and a.its_ip <> 'I'");

			int count = 0;
			while (rs.next()) {
				System.out.println(count);
				if (count != 1595) {
					Item item = new Item(rs.getInt(1), rs.getString(2), groups.getAll(rs.getInt(3), rs.getInt(4),
							rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8)));
					k.add(item);
				}
				count++;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stat.close();
				c.close();
			} catch (SQLException e) {
			}

		}
		SqliteDatabase.close();

	}

	public static void importCompanies() {
		Kimera k = SqliteDatabase.getKimera();

		ItemGroupBundle groups = new ItemGroupBundle(k.all(ItemGroup.class));

		Connection c = PamplonaDatabase.getInstance().getConnection();
		Statement stat = null;
		ResultSet rs = null;

		try {
			stat = c.createStatement();
			rs = stat.executeQuery(
					"select * from empcad where emp_cod < 42");


			while (rs.next()) {
				
				Company co = new Company(rs.getInt(1), rs.getString(2));
				k.add(co);
			

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stat.close();
				c.close();
			} catch (SQLException e) {
			}

		}
		SqliteDatabase.close();
	}
	
	public static void main(String[] args) {
	importCompanies();
	}

}
