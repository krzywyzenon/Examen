package composer.temp;

import java.util.ArrayList;
import java.util.List;

public class TestClass {

	public static void main(String[] args) {
	
		
		//Composer c = new Composer("");
		
//		Map<String, String> mapa = new HashMap();
//		mapa.put("Jozek", "Siwik");
//		System.out.println(mapa.get("Jozek"));
//		mapa.put("Jozek", "Wandzik");
//		System.out.println(mapa.get("Jozek"));
			
		List<Integer> lista = new ArrayList();
		Integer x = 1;
		Integer y = 2;
		Integer z = 3;
		Integer a = null;
		
		lista.add(x);
		lista.add(y);
		lista.add(z);
		lista.add(a);
		
		for(Integer i : lista)
		{
			System.out.println("Index :" + lista.indexOf(i) + ", Value: " + i);
		}
		
		lista.set(1, null);
		for(Integer i : lista)
		{
			System.out.println("Index :" + lista.indexOf(i) + ", Value: " + i);
		}
	}

}
