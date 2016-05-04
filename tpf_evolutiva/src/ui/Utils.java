package ui;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Utils {

	public static String getHourDate(){
		//Instanciamos el objeto Calendar
		Calendar fecha = new GregorianCalendar();
		//hour, minute y second del sistema
		//usando el método get y el parámetro correspondiente
		int year = fecha.get(Calendar.YEAR);
		int month = fecha.get(Calendar.MONTH);
		int day = fecha.get(Calendar.DAY_OF_MONTH);
		int hour = fecha.get(Calendar.HOUR_OF_DAY);
		int minute = fecha.get(Calendar.MINUTE);
		int second = fecha.get(Calendar.SECOND);
		return "" + day + "/" + (month+1) + "/" + year + " a las "+hour+":"+minute+":"+second +".";
	}

	public static String gethourDateFile() {
		//Instanciamos el objeto Calendar
		Calendar fecha = new GregorianCalendar();
		//usando el método get y el parámetro correspondiente
		int year = fecha.get(Calendar.YEAR);
		int month = fecha.get(Calendar.MONTH);
		int day = fecha.get(Calendar.DAY_OF_MONTH);
		int hour = fecha.get(Calendar.HOUR_OF_DAY);
		int minute = fecha.get(Calendar.MINUTE);
		int second = fecha.get(Calendar.SECOND);
		return "Soluci�n_" + day + "_" + (month+1) + "_" +year + "__"+hour+"hs_"+minute+"min_"+second +"segs";
	}

}
