package dados;

public class Domestico extends Robo {

	private int nivel;

	public Domestico(int id, String modelo, double valorDiario, int nivel) {
		super(id, modelo, valorDiario);
		this.nivel = nivel;
	}

	@Override
	public double calculaLocacao(int dias) {
		return 0;
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
				"nivel=" + nivel +
				"}\n";
	}
}
