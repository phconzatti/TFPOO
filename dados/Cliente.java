package dados;

public abstract class Cliente {

	private int tipo; //1 para individual, 2 para empresarial.

	private int codigo;

	private String nome;

	private int quantidadeRobos;

	public Cliente (int codigo, String nome){
		this.codigo = codigo;
		this.nome = nome;
	}

	public abstract double calculaDesconto(int quantidadeRobos);


	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQuantidadeRobos() {
		return quantidadeRobos;
	}

	public void setQuantidadeRobos(int quantidadeRobos) {
		this.quantidadeRobos = quantidadeRobos;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return	"codigo= " + codigo +
				", nome= " + nome + " ,";
	}
}
