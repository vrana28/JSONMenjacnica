package rs.ac.bg.fon.ai.JSONMenjacnica;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class Main1 {
	
	private static final String BASE_URL = "http://api.currencylayer.com";
	private static final String API_KEY = "2e4baadf5c5ae6ba436f53ae5558107f";
	private static final String SOURCE = "USD";
	private static final String CURRENCIES = "CAD";

	public static void main(String[] args) {
		
		Transakcija t = new Transakcija();
	
		t.setDatumTranskacije(new Date());
		t.setIzvornaValuta("USD");
		t.setKrajnjaValuta("CAD");
		t.setPocetniIznos(155);
		
		try {
			Gson gson = new Gson();

			URL url = new URL(BASE_URL+"/live?access_key="+API_KEY+"&source="+SOURCE+"&currencies="+CURRENCIES);
		
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			
			con.setRequestMethod("GET");
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			JsonObject rez = gson.fromJson(reader, JsonObject.class); 
			if(rez.get("success").getAsBoolean()) {
			System.out.println(rez);
			double kurs = rez.get("quotes").getAsJsonObject().get("USDCAD").getAsDouble();
			
			t.setKonvertovaniIznos(155*kurs);
			System.out.println("Kurs je " + t.getKonvertovaniIznos());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		try(FileWriter file = new FileWriter("prva_transakcija.json")){
			Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
			
			gson.toJson(t, file);
			
			}catch (Exception e) {
				e.printStackTrace();
			}

	}

}
