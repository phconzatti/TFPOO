package dados;

public class Empresarial extends Cliente {

	private int ano;

	public Empresarial(int codigo, String nome, int ano) {
		super(codigo, nome);
		this.ano = ano;
	}

	@Override
	public double calculaDesconto(int quantidadeRobos) {
		if (quantidadeRobos >= 2 && quantidadeRobos <= 9){
			return 0.97;
		} else if (quantidadeRobos > 10){
			return 0.93;
		} else {
			return 1;
		}
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	@Override
	public String toString() {
		return super.toString()+"Empresarial, " +
				"ano=" + ano+";\n";
	}
}
