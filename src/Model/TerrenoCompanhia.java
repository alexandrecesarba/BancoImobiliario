package Model;

public class TerrenoCompanhia extends Terreno{
	enum Companhia {
		Ferroviaria, Viacao, Taxi, Navegacao, Aviacao,
		TaxiAereo
	}
	public TerrenoCompanhia(Companhia carta) {
		switch(carta) {
		case Ferroviaria:
			aluguelAtual = 50;
			preco = 200;
			image = "company1.png";
			break;
		case Viacao:
			aluguelAtual = 50;
			preco = 200;
			image = "company2.png";
			break;
		case Taxi:
			aluguelAtual = 40;
			preco = 150;
			image = "company3.png";
			break;
		case Navegacao:
			aluguelAtual = 40;
			preco = 150;
			image = "company4.png";
			break;
		case Aviacao:
			aluguelAtual = 50;
			preco = 200;
			image = "company5.png";
			break;
		case TaxiAereo:
			aluguelAtual = 50;
			preco = 200;
			image = "company6.png";
			break;
		}
	}

}
