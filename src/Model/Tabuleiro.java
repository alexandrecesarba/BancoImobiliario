package Model;

import Model.TerrenoAvenida.Territorio;
import Model.TerrenoCompanhia.Companhia;
import Model.TerrenoNeutro.TipoTerreno;

class Tabuleiro {
	
	public static Terreno tabuleiro[] = {
			new TerrenoNeutro(TipoTerreno.Inicio),
			new TerrenoAvenida(Territorio.Leblon),
			new TerrenoNeutro(TipoTerreno.SorteReves),
			new TerrenoAvenida(Territorio.AvPresidenteVargas),
			new TerrenoAvenida(Territorio.AvNossaSraCopacabana),			
			new TerrenoCompanhia(Companhia.Ferroviaria),
			new TerrenoAvenida(Territorio.AvBrigadeiro),
			new TerrenoCompanhia(Companhia.Viacao),
			new TerrenoAvenida(Territorio.AvReboucas),
			new TerrenoAvenida(Territorio.Av9Julho),
			new TerrenoNeutro(TipoTerreno.Prisao),
			new TerrenoAvenida(Territorio.AvEuropa),
			new TerrenoNeutro(TipoTerreno.SorteReves),
			new TerrenoAvenida(Territorio.RuaAugusta),
			new TerrenoAvenida(Territorio.AvPacaembu),
			new TerrenoCompanhia(Companhia.Taxi),
			new TerrenoNeutro(TipoTerreno.SorteReves),
			new TerrenoAvenida(Territorio.Interlagos),
			new TerrenoNeutro(TipoTerreno.LucrosOuDividendos),
			new TerrenoAvenida(Territorio.Morumbi),
			new TerrenoNeutro(TipoTerreno.ParadaLivre),
			new TerrenoAvenida(Territorio.Flamengo),
			new TerrenoNeutro(TipoTerreno.SorteReves),
			new TerrenoAvenida(Territorio.Botafogo),
			new TerrenoNeutro(TipoTerreno.Imposto),
			new TerrenoCompanhia(Companhia.Navegacao),
			new TerrenoAvenida(Territorio.AvBrasil),
			new TerrenoNeutro(TipoTerreno.SorteReves),
			new TerrenoAvenida(Territorio.AvPaulista),
			new TerrenoAvenida(Territorio.JardimEuropa),
			new TerrenoNeutro(TipoTerreno.VaPrisao),
			new TerrenoAvenida(Territorio.Copacabana),
			new TerrenoCompanhia(Companhia.Aviacao),
			new TerrenoAvenida(Territorio.AvVieiraSouto),			
			new TerrenoAvenida(Territorio.AvAtlantica),
			new TerrenoCompanhia(Companhia.TaxiAereo),
			new TerrenoAvenida(Territorio.Ipanema),
			new TerrenoNeutro(TipoTerreno.SorteReves),
			new TerrenoAvenida(Territorio.JardimPaulista),
			new TerrenoAvenida(Territorio.Brooklin)
		};
	
	Terreno getTerreno(int pos) {
		return tabuleiro[pos];
	}
}
