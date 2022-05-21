package Model;

class TerrenoNeutro extends Terreno{
	enum TipoTerreno {
		Inicio, SorteReves, Prisao, LucrosOuDividendos, ParadaLivre,
		Imposto, VaPrisao
	}
	public TerrenoNeutro(TipoTerreno carta) {
		switch(carta) {
		case Inicio:
			aluguelAtual = 200;
			break;
		case SorteReves:
			break;
		case Prisao:
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
