package dados;

public class Individual extends Cliente {

	private String cpf;

	public Individual(int codigo, String nome, String cpf){
		super (codigo, nome);
		this.cpf = cpf;
	}

	@Override
	public double calculaDesconto(int quantidadeRobos) {
		if (quantidadeRobos > 1){
			return 0.95;
		} else {
			return 1;
		}
	}

	@Override
	public String toString() {
		return super.toString()+ " Individual, " + "CPF= " + cpf + ";";
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
