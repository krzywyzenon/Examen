package composer.temp;

import java.util.HashMap;
import java.util.Map;

public class TestClass {

	public static void main(String[] args) {
	
		
		//Composer c = new Composer("");
		
		Map<String, String> mapa = new HashMap();
		mapa.put("Jozek", "Siwik");
		System.out.println(mapa.get("Jozek"));
		mapa.put("Jozek", "Wandzik");
		System.out.println(mapa.get("Jozek"));
			
	}

}
