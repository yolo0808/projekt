package projekt;

import java.util.ArrayList;
import java.util.Calendar;

public class Turniejo {
	private ArrayList<Sedzia> listaSedziowieT;
	private ArrayList<Druzyna> listaDruzynyT;
	private String rodzaj;
	private String dataUtworzenia;
	
	public Turniejo(String rodzaj, String dataUtworzenia, String druzyny, String sedziowie){
		System.out.println(rodzaj + " " + dataUtworzenia + " " + druzyny + " " + sedziowie);
		listaSedziowieT = new ArrayList<Sedzia>();
		listaDruzynyT = new ArrayList<Druzyna>();
		this.rodzaj = rodzaj;
		this.dataUtworzenia = dataUtworzenia;
		String[] d = druzyny.split(";");
		for(String d2 : d){
			listaDruzynyT.add(new Druzyna(d2));
		}
		String[] s = sedziowie.split(";");
		for(String s2 : s){
			System.out.println(s2);
			listaSedziowieT.add(new Sedzia(s2));
		}
	}
	
	public Turniejo(ArrayList<Sedzia> listaSedziowieT, ArrayList<Druzyna> listaDruzynyT, String rodzaj) {
		super();
		this.listaSedziowieT = listaSedziowieT;
		this.listaDruzynyT = listaDruzynyT;
		this.rodzaj = rodzaj;
		Calendar c = Calendar.getInstance();
		dataUtworzenia = c.getTime().toString();
		
	}
	@Override
	public String toString() {
		return  rodzaj + " --> " + dataUtworzenia;
	}
	public ArrayList<Sedzia> getListaSedziowieT() {
		return listaSedziowieT;
	}
	public ArrayList<Druzyna> getListaDruzynyT() {
		return listaDruzynyT;
	}
	public String getRodzaj() {
		return rodzaj;
	}
	public String getDataUtworzenia() {
		return dataUtworzenia;
	}
	
}
