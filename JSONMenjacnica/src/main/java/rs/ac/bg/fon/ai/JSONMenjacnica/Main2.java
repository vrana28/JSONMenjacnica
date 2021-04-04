package rs.ac.bg.fon.ai.JSONMenjacnica;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;


public class Main2 {
	
	private static final String BASE_URL = "http://api.currencylayer.com";
	private static final String API_KEY = "2e4baadf5c5ae6ba436f53ae5558107f";
	private static final String SOURCE = "USD";
	private static final String CURRENCIES = "EUR";
	private static final String CURRENCIES2 = "CHF";
	private static final String CURRENCIES3 = "CAD";


	public static void main(String[] args) {
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2020);
		cal.set(Calendar.MONTH, Calendar.AUGUST);
		cal.set(Calendar.DAY_OF_MONTH, 28);
		
		Transakcija t1 = new Transakcija();
		Transakcija t2 = new Transakcija();
		Transakcija t3 = new Transakcija();
	
		t1.setDatumTranskacije(cal.getTime());
		t1.setIzvornaValuta("USD");
		t1.setKrajnjaValuta("EUR");
		t1.setPocetniIznos(100);
		
		t2.setDatumTranskacije(cal.getTime());
		t2.setIzvornaValuta("USD");
		t2.setKrajnjaValuta("CHF");
		t2.setPocetniIznos(100);
		
		t3.setDatumTranskacije(cal.getTime());
		t3.setIzvornaValuta("USD");
		t3.setKrajnjaValuta("CAD");
		t3.setPocetniIznos(100);
		
		try {
			Gson gson = new Gson();

			URL url1 = new URL(BASE_URL+"/live?access_key="+API_KEY+"&source="+SOURCE+"&currencies="+CURRENCIES+"&date=2020-08-28");
			URL url2 = new URL(BASE_URL+"/live?access_key="+API_KEY+"&source="+SOURCE+"&currencies="+CURRENCIES2+"&date=2020-08-28");
			URL url3 = new URL(BASE_URL+"/live?access_key="+API_KEY+"&source="+SOURCE+"&currencies="+CURRENCIES3+"&date=2020-08-28");
		
			HttpURLConnection con = (HttpURLConnection)url1.openConnection();
			HttpURLConnection con2 = (HttpURLConnection)url2.openConnection();
			HttpURLConnection con3 = (HttpURLConnection)url3.openConnection();
			
			con.setRequestMethod("GET");
			con2.setRequestMethod("GET");
			con3.setRequestMethod("GET");
			
			BufferedReader reader1 = new BufferedReader(new InputStreamReader(con.getInputStream()));
			BufferedReader reader2 = new BufferedReader(new InputStreamReader(con2.getInputStream()));
			BufferedReader reader3 = new BufferedReader(new InputStreamReader(con3.getInputStream()));
			
			JsonObject rez = gson.fromJson(reader1, JsonObject.class); 
			if(rez.get("success").getAsBoolean()) {
			//System.out.println(rez);
			double kurs = rez.get("quotes").getAsJsonObject().get("USDEUR").getAsDouble();
			
			t1.setKonvertovaniIznos(t1.getPocetniIznos()*kurs);
			//System.out.println("Kurs je " + t1.getKonvertovaniIznos());
			
			}
			
			JsonObject rez2 = gson.fromJson(reader2, JsonObject.class); 
			if(rez2.get("success").getAsBoolean()) {
			//System.out.println(rez);
			double kurs = rez2.get("quotes").getAsJsonObject().get("USDCHF").getAsDouble();
			
			t2.setKonvertovaniIznos(t2.getPocetniIznos()*kurs);
			//System.out.println("Kurs je " + t2.getKonvertovaniIznos());
			
			}
			
			JsonObject rez3 = gson.fromJson(reader3, JsonObject.class); 
			if(rez3.get("success").getAsBoolean()) {
			//System.out.println(rez3);
			double kurs = rez3.get("quotes").getAsJsonObject().get("USDCAD").getAsDouble();
			
			t3.setKonvertovaniIznos(t3.getPocetniIznos()*kurs);
			//System.out.println("Kurs je " + t3.getKonvertovaniIznos());
			
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		List<Transakcija> transactions = new LinkedList<>();
		transactions.add(t1);
		transactions.add(t2);
		transactions.add(t3);
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		try (FileWriter file = new FileWriter("ostale_transakcije.json")) {
			
			gson.toJson(transactions, file);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
