package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;


public class DateFormatUtils {
	
	public static String toISO8601UTC(Date date) {
		TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:00.000'Z'");
		df.setTimeZone(tz);
		return df.format(date);
	}
	
	public static String getTimePlusDays(Integer days) {
		Calendar c=new GregorianCalendar();
		c.add(Calendar.DATE, days);
		Date d=c.getTime();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(d);
	}
	
	public static String getTimeLessDays(Integer days) {
		Calendar c=new GregorianCalendar();
		c.add(Calendar.DATE, -days);
		Date d=c.getTime();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(d);
	}
	
	public static String toFormatDate(String date) {
		return toFormatDate(fromyyyyMMddToDate(date));
	}
	
	public static String toFormatDate(Date date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);
	}
	
	public static Date fromyyyyMMddToDate(String dateStr) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		try {
			return df.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date fromISO8601UTC(String dateStr) {
		TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:00.000'Z'");
		df.setTimeZone(tz);

		try {
			return df.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public static String getDateTimeNow() {
		TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:00.000'Z'"); // Quoted "Z" to indicate UTC, no timezone offset
		df.setTimeZone(tz);
		String nowAsISO = df.format(new Date());
		String dataFinal = nowAsISO.substring(0, 18) + "0.000Z";
		return dataFinal;
	}
	
	public static String returnDataStringIfNotNull(String data) {
		String datalocal = "null";
		if(data != null) {
			datalocal = String.valueOf(data).trim().substring(0, 13);
		}
		return datalocal;
	}
	
	public static String returnDataStringWithoutTimeIfNotNull(String data) {
		String datalocal = "null";
		if(data != null) {
			datalocal = String.valueOf(data).trim().substring(0, 11);
		}
		return datalocal;
	}
	
	public static String returnDataString(String data) {
		String datalocal = "null";
		if(data != null) {
			datalocal = String.valueOf(data).trim().substring(0, 10);
		}
		return datalocal;
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public static List<LocalDate> diasUteisRestantes(final LocalDate hoje, final LocalDate dataProximaCarga, List<String> feriados) {

        final int calculoDiasProximaCargaAfter = hoje.getMonth() == dataProximaCarga.getMonth() ? hoje.lengthOfMonth() - hoje.getDayOfMonth() : 
             hoje.lengthOfMonth() - hoje.getDayOfMonth() + dataProximaCarga.getDayOfMonth();

        final int diasProximoMes = dataProximaCarga.isAfter(hoje) ? calculoDiasProximaCargaAfter : hoje.lengthOfMonth() + dataProximaCarga.getDayOfMonth();
        
        return Stream.iterate(hoje, date -> date.plusDays(1)).limit(diasProximoMes)
              .filter(data -> data.getDayOfWeek().getValue() != SATURDAY.getValue())
              .filter(data -> data.getDayOfWeek().getValue() != SUNDAY.getValue())
              .filter(data -> (feriados.stream().filter(feriado -> feriado.equals(data))
			          .count())<1)
			.collect(Collectors.toList());
	}
}
