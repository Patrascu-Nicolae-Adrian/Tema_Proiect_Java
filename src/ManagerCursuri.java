import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ManagerCursuri {
	// Organizarea claselor se face deobicei dupa regula: Membri, Metode private, Constructori, metode publice
	List<Curs> cursuri;
	HashMap<Integer, ArrayList<Student>> grupareaStudentilorDupaAn;
	
	// Metoda cauta un curs in lista de cursuri si arunca exceptie in cazul in care nu-l gaseste. Exceptia trebuie tratata in exterior
	private Curs search(Curs unCurs) throws Exception {
		int i = cursuri.indexOf(unCurs);
		if ( i != -1 ) {
			return cursuri.get(i);
		}
		else {
			throw new Exception("Cursul " + unCurs + " nu se se regaseste in programa scolara");
		}
	}
	
	public ManagerCursuri() {

		this.cursuri = new ArrayList<Curs>();
		HashMap<Integer, ArrayList<Student>> grupareaStudentilorDupaNrGrupei = new HashMap<Integer, ArrayList<Student>>();
	}
	public ManagerCursuri(Curs[] cursuri) {

		this.cursuri = Arrays.asList(cursuri);
		this.grupareaStudentilorDupaAn = ordonareStudentiDupaAn();
	}
	public void AddCurs(Curs c) {
		this.cursuri.add(c);
	}
	
	public void RemoveCurs(Curs c) throws Exception {
		if (!this.cursuri.remove(c)) {
			throw new Exception("Cursrul " + c + " nu poate fi sters pentru ca nu se regaseste in programa scolara");
		}
	}
	
	public void EditCurs(Curs c, Curs cursNou) throws Exception {
		// Caut cursul in lista de cursuri
		int i = cursuri.indexOf(c);
		// verific daca cursul exista
		if ( i != -1) {
			cursuri.set(i, cursNou);
		} else {
			throw new Exception("Cursul " + c + " nu se regaseste in programa scolara");
		}
	}
	
	public void reportStudentsOf(Curs unCurs) throws Exception {
		Curs c = this.search(unCurs);
		c.AfisareStudenti();
	}
	
	public void reportAllCourses() {
		for( Curs c: cursuri) {
			System.out.println( c.nume + " " + c.descriere);
			try {
				// this.reportStudentsOf(c);
			} catch (Exception e) {

			}
		}
	}
	
	public void reportAllStudentsGrades() {
		for( Curs c: cursuri) {
			c.AfisareNumeCurs();
			for ( Student s: c.studenti) {
				String gradeAsString = c.nota.get(s) != null ? c.nota.get(s).toString() : " nu a fost notat";
				System.out.println( s.nume + " " + s.prenume + " are nota: " + gradeAsString);
			}
		}
	}
	
	public void reportGradesOf(Curs unCurs) throws Exception {
		Curs c = this.search(unCurs);
		System.out.println("Media studentilor la cursul " + c.nume + " este:" + c.MediaStudenti());	
	}
	
	public void reportAverageGradesOf(Profesor unProf) {
		float sum = 0;
		int count = 0;
		for( Curs c : cursuri) {
			if ( c.profu == unProf ) {
				sum += c.MediaStudenti();
				count += 1;
			}
		}
		float average = count == 0 ? 0 : sum / (float)count; 
		System.out.println("Mediat notelor date de profesorul: " + unProf.formatForDisplay() + " este: " + average );
	}

	public HashMap<Integer, ArrayList<Student>> ordonareStudentiDupaAn() {
		HashMap<Integer, ArrayList<Student>> grupareaStudentilorDupaAn = new HashMap<Integer, ArrayList<Student>>();
		IDataLoader dataManager = Settings.dataLoaderHashMap.get(Settings.loadType);
		Student[] studenti = dataManager.createStudentsData();
		for (Student s : studenti) {
			int an = s.an;
			ArrayList<Student> stud = grupareaStudentilorDupaAn.get(an);
			if (stud == null) {
				stud = new ArrayList<Student>();
				stud.add(s);
				grupareaStudentilorDupaAn.put(an, stud);
			} else {
				if (!stud.contains(s)) {
					stud.add(s);
				}
			}
		}
		return grupareaStudentilorDupaAn;
	}




	public String viewStudentCurses(Student student, int year){

		String studentCurses = new String();
		for(Curs c:cursuri){
			for(Student s:c.studenti){
				if(s.compareTo(student)==0 && c.an == year)
					studentCurses = studentCurses + "\n" + c.nume + "\n";
			}
		}
		if(studentCurses.length() == 0)
			studentCurses = "Nu sunteti inscris la niciun curs in acest an.";
		return studentCurses;
	}

	public String viewGradesOfCourses(Student student, int year){
		String print = "Notele din anul " + year + " sunt:\n";
		String gradeOfCourses = print;
		for(Curs c:cursuri){
			for(Student s:c.studenti){
				if(s.compareTo(student)==0 && c.an == year) {
					String nota = c.nota.get(s) != null ? c.nota.get(s).toString() : "Studentul nu a fost notat";
					gradeOfCourses = gradeOfCourses + "\n" + c.nume + " - nota: " + nota + "\n";
				}
			}
		}
		if(gradeOfCourses.compareTo(print)==0)
			gradeOfCourses = "Nu sunteti inscris la niciun curs in acest an.";
		return gradeOfCourses;
	}

	public String viewAverage(Student student, int year){
		String print = "Media din anul " + year + " este:\n";
		String average = print;
		int sum = 0, nr = 0;
		for(Curs c:cursuri){
			for(Student s:c.studenti){
				if(s.compareTo(student)==0 && c.an == year) {
					if(c.nota.get(s) != null) {
							sum = sum + c.nota.get(s);
							nr++;
					}
				}
			}
		}
		float avg = 0;
		try {
			avg = sum / nr;
		}catch (Exception e){return "Studentul nu are note la niciun curs.";}
		average = average + avg;
		if(average.compareTo(print) == 0)
			average = "Nu sunteti inscris la niciun curs in acest an.";
		return average;
	}

	public String viewRestanta(Student student, int year){
		String print = "Aveti restante la urmatoarele materii:\n";
		String restanta = print;
		for(Curs c:cursuri){
			for(Student s:c.studenti){
				if(s.compareTo(student)==0 && c.an == year) {
					if(c.nota.get(student) != null && c.nota.get(student) < 5){
						restanta = restanta + c.nume + "\n";

					}
				}
			}
		}
		if(restanta.compareTo(print)==0)
			restanta = "Nu aveti restante.";
		return restanta;
	}

	public String listTeacherCourses(Profesor profesor){
		String printCourses = new String();
		printCourses = "";
		for(Curs c:cursuri){
			if(c.profu.nume.compareTo(profesor.nume) == 0 && c.profu.prenume.compareTo(profesor.prenume) == 0){
				printCourses = printCourses + "\n" + c.nume + "\n";
			}
		}
		if(printCourses == "")
			return "Nu sunteti profesor la niciun curs.";
		return printCourses;
	}

	public String listStudentsOfCourses(Profesor profesor){
		String printStudent = new String();
		printStudent = "";
		for(Curs c:cursuri){
			if(c.profu.nume.compareTo(profesor.nume) == 0 && c.profu.prenume.compareTo(profesor.prenume) == 0){
				for(Student s : c.studenti) {
					printStudent = printStudent + "\n" + s.nume + " " + s.prenume;
				}
			}
		}
		if(printStudent == "")
			return "Nu aveti studenti la curs.";
		return printStudent;
	}

	public String gradeStudent(Profesor profesor, String name, int nota){
		int ok=0;
		String[] splitName = name.split(" ");
		try{
		for(Curs c:cursuri){
			if(c.profu.nume.compareTo(profesor.nume) == 0 && c.profu.prenume.compareTo(profesor.prenume) == 0){
				for(Student s : c.studenti) {
					if (s.nume.compareTo(splitName[0]) == 0 && s.prenume.compareTo(splitName[1]) == 0) {
						ok = 1;
						try {
							c.noteazaStudent(s, nota);
						} catch (Exception ex) {
							return ex.getMessage();
						}
					}
				}
			}
		}
		}catch(Exception e){
			return "Studentul " + name + " nu este inscris la acest curs.";
		}
		if(ok == 0){
			return "Studentul " + name + " nu este inscris la acest curs.";
		}
		IDisplayManager displayManager = Settings.displayHashMap.get(Settings.displayType);
		displayManager.displayCourses(cursuri.toArray(new Curs[cursuri.size()]));
		return "Studentul a fost notat!";
	}



	public List<Curs> getCursuri() {
		return cursuri;
	}
}
