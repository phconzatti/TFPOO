package dados;

public abstract class Robo {

	private int tipo;//1 para domestico, 2 para industrial, 3 para agricola.

	private int id;

	private String modelo;

	private double valorDiario;

	private int dias;

	public Robo(int id, String modelo, double valorDiario) {
		this.id = id;
		this.modelo = modelo;
		this.valorDiario = valorDiario;
	}

	public abstract double calculaLocacao(int dias);


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public double getValorDiario() {
		return valorDiario;
	}

	public void setValorDiario(double valorDiario) {
		this.valorDiario = valorDiario;
	}

	public int getDias() {
		return dias;
	}

	public void setDias(int dias) {
		this.dias = dias;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "id= " + id +
				", modelo= " + modelo +
				", valorDiario= " + valorDiario;
	}

	public String toString2(){
		return "id= " + id +
				", modelo= " + modelo +
				", valorDiario= " + valorDiario;
	}
}
