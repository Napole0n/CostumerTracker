package br.mg.customertracker.geocoding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;





public class MapQuestCaller {

	private static final String API_KEY = "2mZz6hPAgXPoGmR5J68OrMjSGXVDAAzY";
	private static final String SEPARATE_WITH_COLUMN_AND_SPACE = "%2C+";
	private static final String BASE_URL = "http://www.mapquestapi.com/geocoding/v1/address?key=" + API_KEY
			+ "&inFormat=kvp&outFormat=json&location=";
	private static final String USER_AGENT = "Mozilla/5.0";
	private static final String URL_FINAL = "&thumbMaps=false";

	private static void testGet() throws IOException {

		StringBuilder builder = new StringBuilder();

		builder.append(BASE_URL);
		builder.append("Rua+Porto+Alegre");
		builder.append(SEPARATE_WITH_COLUMN_AND_SPACE);
		builder.append("177");
		builder.append(SEPARATE_WITH_COLUMN_AND_SPACE);
		builder.append("Rio+do+Sul");
		builder.append(SEPARATE_WITH_COLUMN_AND_SPACE);
		builder.append("SC");
		builder.append(URL_FINAL);

		URL objUrl = new URL(builder.toString());
		HttpURLConnection con = (HttpURLConnection) objUrl.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = con.getResponseCode();

		System.out.println("\nSending 'GET' request to URL : " + builder.toString());
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		
		in.close();
		JsonParser parser = new JsonParser();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		
		
		System.out.println(gson.toJson(parser.parse(response.toString())));

	}

	public static void main(String[] args) throws IOException {
		testGet();
	}

}
