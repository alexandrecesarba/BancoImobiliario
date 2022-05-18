package Model;

class Construcao {
	enum TipoConstrucao{
		CASA, HOTEL;
	}
	private int custo;
	private int aluguel;
	TipoConstrucao tipo;
	public Construcao(TipoConstrucao tipo, int custo, int aluguel, int qntdCasas) { //custo de construcao e aluguel varia pelo terreno 
		// construtor default
		if(qntdCasas == 0 && tipo != TipoConstrucao.CASA) {
			
			
		}
		this.custo = custo;
		this.aluguel = aluguel;
		
	}
	

}
