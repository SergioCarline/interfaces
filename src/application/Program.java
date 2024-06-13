package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import entities.CarRental;
import entities.Vehicle;
import services.BrazilTaxService;
import services.RentalService;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		System.out.println("Entre com os dados do aluguel");
		System.out.print("Modelo do carro: ");
		String carModel = sc.nextLine();
		System.out.print("Retirada (dd/MM/yyyy): ");
		LocalDateTime start = LocalDateTime.parse(sc.nextLine(),fmt);
		System.out.print("Retorno (dd/MM/yyyy): ");
		LocalDateTime finish = LocalDateTime.parse(sc.nextLine(),fmt);
		System.out.print("Entre com o preço por hora: ");
		double priceHour = sc.nextDouble();
		System.out.print("Entre com o preço por dia: ");
		double priceDay = sc.nextDouble();
		
		CarRental carRental = new CarRental(start, finish, new Vehicle(carModel));
		
		RentalService rentalService = new RentalService(priceHour, priceDay, new BrazilTaxService());
		
		rentalService.processInvoice(carRental);
		
		System.out.println("FATURA:");
		System.out.println("Pagamento básico: " + String.format("%.2f", carRental.getInvoice().getBasicPayment()));
		System.out.println("Imposto: " + String.format("%.2f", carRental.getInvoice().getTax()));
		System.out.print("Pagamento total: " + String.format("%.2f", carRental.getInvoice().totalPayment()));
		

		sc.close();
	}

}
