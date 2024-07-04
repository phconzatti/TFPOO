package dados;

public class Domestico extends Robo {

	private int nivel;

	public Domestico(int id, String modelo, double valorDiario, int nivel) {
		super(id, modelo, valorDiario);
		this.nivel = nivel;
	}

	@Override
	public double calculaLocacao(int dias) {
		if (nivel == 1){
			return 10*dias;
		} else if (nivel == 2){
			return 20*dias;
		} else {
			return 50*dias;
		}
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	@Override
	public String toString() {
		return super.toString()+", Domestico, " +
				"nivel= " + nivel +
				";\n";
	}

	public String toString2(){
		return super.toString2()+", Domestico, " +
				"nivel= " + nivel;
	}
}
