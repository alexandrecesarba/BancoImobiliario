package model;

class TerrenoNeutro extends Terreno{
	TerrenoNeutro(TipoTerreno carta) {
		this.tipo = 2;
		switch(carta) {
		case Inicio:
			aluguelAtual = 200;
			break;
		case SorteReves:
			aluguelAtual = -2;
			break;
		case Prisao:
			aluguelAtual = 0;
			break;
		case LucrosOuDividendos:
			aluguelAtual = -200;
			break;
		case ParadaLivre:
			aluguelAtual = 0;
			break;
		case Imposto:
			aluguelAtual = -200;
			break;
		case VaPrisao:
			aluguelAtual = -1;
			break;
		}
	}

}
