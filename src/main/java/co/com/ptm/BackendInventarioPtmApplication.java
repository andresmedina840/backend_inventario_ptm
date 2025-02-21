package co.com.ptm;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendInventarioPtmApplication {

	public static void main(String[] args) {
		Locale locale = Locale.of("es", "CO");

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy", locale);
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT-5:00"));

		SimpleDateFormat sdf = new SimpleDateFormat("EEEEE dd/MM/yyyy hh:mm:ss a", locale);
		sdf.setTimeZone(TimeZone.getTimeZone("GMT-5:00"));
		
		SpringApplication.run(BackendInventarioPtmApplication.class, args);
		
		System.out.println("Funcionando Inventario PTM: " + dateFormat.format(new Date())+ " - " + sdf.format(new Date()));
	}

}
