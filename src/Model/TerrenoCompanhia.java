package Model;

import Model.TerrenoAvenida.Territorio;

public class TerrenoCompanhia {
	enum Companhia {
		Ferroviaria, Viacao, Taxi, Navegacao, Aviacao,
		TaxiAereo
	}
	private final String image;
	private final int aluguel;
	private String dono;
	private final int preco;
	
	int getAluguel() {
		return aluguel;
	}
	int getPreco() {
		return preco;
	}
	String getDono() {
		return dono;
	}
	String getImage() {
		return image;
	}
	void compraCompanhia(String jogador) {
		this.dono = jogador;
	}
	void vendeCompanhia() {
		this.dono = "";
	}
	
	public TerrenoCompanhia(Companhia carta) {
		switch(carta) {
		case Ferroviaria:
			aluguel = 50;
			preco = 200;
			image = "company1.png";
			break;
		case Viacao:
			aluguel = 50;
			preco = 200;
			image = "company2.png";
			break;
		case Taxi:
			aluguel = 40;
			preco = 150;
			image = "company3.png";
			break;
		case Navegacao:
			aluguel = 40;
			preco = 150;
			image = "company4.png";
			break;
		case Aviacao:
			aluguel = 50;
			preco = 200;
			image = "company5.png";
			break;
		case TaxiAereo:
			aluguel = 50;
			preco = 200;
			image = "company6.png";
			break;
		default:
			preco = 0;
			image = "";
			aluguel = 0;
		
		}
	}

}
