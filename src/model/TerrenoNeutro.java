package model;

class TerrenoNeutro extends Terreno{
	public TerrenoNeutro(TipoTerreno carta) {
		switch(carta) {
		case Inicio:
			aluguelAtual = 200;
			break;
		case SorteReves:
			break;
		case Prisao:
			break;
		case LucrosOuDividendos:
			aluguelAtual = 200;
			break;
		case ParadaLivre:
			break;
		case Imposto:
			aluguelAtual = 200;
			break;
		case VaPrisao:
			break;
		}
	}

}
