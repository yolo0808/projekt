package projekt;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Turniejo {
	private ArrayList<Sedzia> listaSedziowieT;
	private ArrayList<Druzyna> listaDruzynyT;
	private String rodzaj;
	private String dataUtworzenia;
	private String[][] mecze;
	private HashMap<String, Integer> tabela;
	private String[][] posortowanaTabela;
	private String[][] finaly;

	public Turniejo(String rodzaj, String dataUtworzenia, String druzyny, String sedziowie, String meczes) {
		System.out.println(rodzaj + " " + dataUtworzenia + " " + druzyny + " " + sedziowie);
		listaSedziowieT = new ArrayList<Sedzia>();
		listaDruzynyT = new ArrayList<Druzyna>();
		this.rodzaj = rodzaj;
		this.dataUtworzenia = dataUtworzenia;
		String[] d = druzyny.split(";");
		for (String d2 : d) {
			listaDruzynyT.add(new Druzyna(d2));
		}
		String[] s = sedziowie.split(";");
		for (String s2 : s) {
			System.out.println(s2);
			listaSedziowieT.add(new Sedzia(s2));
		}

		mecze = new String[d.length * (d.length - 1) / 2][4];
		if(d.length > 3) {
			finaly = new String[3][4];
		}
		else {
			finaly = new String[1][4];
		}
		String[] m = meczes.split("<");
		System.out.println(meczes);
		for (int i=0; i<mecze.length; i++) {
			String m2 = m[i];
			System.out.println(m2);
			String[] m2s = m2.split(";");
			mecze[i][0] = m2s[0];
			mecze[i][1] = m2s[1];
			mecze[i][2] = m2s[2];
			mecze[i][3] = m2s[3];
		}
		for (int i=mecze.length; i<mecze.length+finaly.length; i++) {
			String m2 = m[i];
			System.out.println(m2);
			String[] m2s = m2.split(";");
			finaly[i-mecze.length][0] = m2s[0];
			finaly[i-mecze.length][1] = m2s[1];
			finaly[i-mecze.length][2] = m2s[2];
			finaly[i-mecze.length][3] = m2s[3];
			System.out.println(finaly[i-mecze.length][3]);
		}

		tabela = new HashMap<String, Integer>();
		uzupelnijDruzyny();
		generujTabele();
	}

	public Turniejo(ArrayList<Sedzia> listaSedziowieT, ArrayList<Druzyna> listaDruzynyT, String rodzaj) {
		super();
		this.listaSedziowieT = listaSedziowieT;
		this.listaDruzynyT = listaDruzynyT;
		this.rodzaj = rodzaj;
		mecze = new String[listaDruzynyT.size() * (listaDruzynyT.size() - 1) / 2][4];
		if(listaDruzynyT.size() > 3) {
			finaly = new String[3][4];
		}
		else {
			finaly = new String[1][4];
		}
		int k = 0;
		for (int i = 0; i < listaDruzynyT.size(); i++) {
			for (int j = i + 1; j < listaDruzynyT.size(); j++) {
				mecze[k][0] = listaDruzynyT.get(i).getNazwa();
				mecze[k][1] = "null";
				mecze[k][2] = "null";
				mecze[k][3] = listaDruzynyT.get(j).getNazwa();
				k++;
			}
			System.out.println(mecze[k - 1][0] + " " + mecze[k - 1][3]);
		}
		Calendar c = Calendar.getInstance();
		dataUtworzenia = c.getTime().toString();

		tabela = new HashMap<String, Integer>();
		uzupelnijDruzyny();
		generujTabele();
	}

	public void setWynik(String d1, String w1, String w2, String d2) {
		for (String[] m : mecze) {
			if (m[0].equals(d1) && m[3].equals(d2)) {
				m[1] = w1;
				m[2] = w2;
			}
		}
	}
	
	public void setWynikFinalow(String d1, String w1, String w2, String d2) {
		System.out.println(d1 + " " + w1 + " " + w2 + " " + d2);
		for (String[] m : finaly) {
			System.out.println(m[0] + " " + m[3]);
			if (m[0] != null && m[0].equals(d1) && m[3].equals(d2)) {
				m[1] = w1;
				m[2] = w2;
				break;
			}
		}
	}

	public void generujTabele() {
		for(String s : tabela.keySet()){
			tabela.replace(s, 0);
		}
		for (String[] m : mecze) {
			try {
				if(m[1].equals("null") || m[2].equals("null"))
					continue;
				int w1 = Integer.parseInt(m[1]);
				int w2 = Integer.parseInt(m[2]);
				if (w1 > w2) {
					tabela.replace(m[0], tabela.get(m[0]) + 3);
				} else if (w1 < w2) {
					tabela.replace(m[3], tabela.get(m[3]) + 3);
				}
			} catch (NumberFormatException nfe) {
				nfe.printStackTrace();
			}
		}
		
		sortuj();

	}
	
	public void generujFinal() {
		if(posortowanaTabela.length > 3) {
			if(Integer.parseInt(finaly[0][1]) >= Integer.parseInt(finaly[0][2])) {
				finaly[2][0] = finaly[0][0];
			}
			else {
				finaly[2][0] = finaly[0][3];
			}
			if(Integer.parseInt(finaly[1][1]) >= Integer.parseInt(finaly[1][2])) {
				finaly[2][3] = finaly[1][0];
			}
			else {
				finaly[2][3] = finaly[1][3];	
			}
		}
	}
	
	public void generujPolfinaly() {
		if(posortowanaTabela.length > 3) {
			finaly = new String[3][4];	
		}
		else {
			finaly = new String[1][4];
		}
		if(posortowanaTabela.length > 3) {
			finaly[0][0] = posortowanaTabela[0][0];
			finaly[0][3] = posortowanaTabela[3][0];
			finaly[1][0] = posortowanaTabela[1][0];
			finaly[1][3] = posortowanaTabela[2][0];
		}
		else if(posortowanaTabela.length > 1) {
			finaly[0][0] = posortowanaTabela[0][0];
			finaly[0][3] = posortowanaTabela[1][0];
		}
	}

	private void uzupelnijDruzyny() {
		for (Druzyna d : listaDruzynyT) {
			tabela.put(d.getNazwa(), 0);
		}
	}
	
	private void sortuj(){
		posortowanaTabela = new String[tabela.keySet().size()][2];
		Set<String> keys = new HashSet<String>();
		for(String s : tabela.keySet())
			keys.add(s);
		int len = keys.size();
		int max = -1;
		String key = "";
		for(int i=0; i<len; i++){
			for(Object s : keys){
				if(tabela.get(s) > max){
					max = tabela.get(s);
					key = (String)s;
				}
			}
			keys.remove(key);
			posortowanaTabela[i][0] = key;
			posortowanaTabela[i][1] = String.valueOf(max);
			System.out.println(key + " " + max);
			max = -1;
		}
	}
	
	@Override
	public String toString() {
		return rodzaj + " --> " + dataUtworzenia;
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
	public String[][] getMecze() {
		return mecze;
	}

	public HashMap<String, Integer> getTabela() {
		return tabela;
	}
	public String[][] getPosortowanaTabela(){
		return posortowanaTabela;
	}

	public String[][] getFinaly() {
		return finaly;
	}

}
