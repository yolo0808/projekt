package projekt;

public class Sedzia extends Osoba {

	public Sedzia(String imie, String nazwisko) {
		super(imie, nazwisko);
		
	}
	
	public Sedzia(String imieINazwisko){
		super(imieINazwisko.split(" ")[0], imieINazwisko.split(" ")[1]);
	}
	
	
}
