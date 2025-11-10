package MODELE;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class test {

	
	public static void main(String[] args) {
		LocalDateTime date = LocalDateTime.of(2025, 11, 9, 12, 00, 00);
		System.out.println(date.getDayOfWeek() == DayOfWeek.SUNDAY);

	}

}
