package projekt;

public abstract class Osoba {
	
	private String imie;
	private String nazwisko;
	
	public Osoba(String imie, String nazwisko) {
		super();
		this.imie = imie;
		this.nazwisko = nazwisko;
	}

	public String getImie() {
		return imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}
	
}
