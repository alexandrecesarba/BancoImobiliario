package model;

class TerrenoNeutro extends Terreno{
	TerrenoNeutro(TipoTerreno carta) {
		this.tipo = 2;
		this.dono= -2; // não pode ser comprado
		switch(carta) {
		case Inicio:
			this.nome = "Inicio - ganhe 200!";
			aluguelAtual = 200;
			break;
		case SorteReves:
			aluguelAtual = -2;
			break;
		case Prisao:
			this.nome = "Visitando seus amigos, que fofo.";
			aluguelAtual = 0;
			break;
		case LucrosOuDividendos:
			this.nome = "OPA! Tomai 200tao.";
			aluguelAtual = 200;
			break;
		case ParadaLivre:
			this.nome = "Calma, respira, relaxa um pouco. Parada livre ai.";
			aluguelAtual = 0;
			break;
		case Imposto:
			this.nome = "HORA DO PEDACINHO DO GOVERNO! Pague 200 de imposto";
			aluguelAtual = -200;
			break;
		case VaPrisao:
			aluguelAtual = -1;
			break;
		}
	}

}
