package model;

class TerrenoNeutro extends Terreno{
	
	TerrenoNeutro(TipoTerreno carta) {
		this.tipo = 2;
		this.dono= -2; // não pode ser comprado
		switch(carta) {
		case Inicio:
			this.nome = "voltou para o inicio - ganhe 200!";
			aluguelAtual = 200;
			break;
		case SorteReves:
			this.nome = "SorteOuReves";
			aluguelAtual = -2;
			break;
		case Prisao:
			this.nome = "visitando seus amigos, que fofo.";
			aluguelAtual = 0;
			break;
		case LucrosOuDividendos:
			this.nome = "OPA! Tomai 200tao.";
			aluguelAtual = 200;
			break;
		case ParadaLivre:
			this.nome = "hora da calma. Respira, relaxa um pouco. Parada livre ai.";
			aluguelAtual = 0;
			break;
		case Imposto:
			this.nome = "HORA DO PEDACINHO DO GOVERNO! Pague 200 de imposto";
			aluguelAtual = -200;
			break;
		case VaPrisao:
			this.nome = "VaPrisao";
			aluguelAtual = -1;
			break;
		}
	}

}
