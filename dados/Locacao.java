package dados;

import java.util.Date;

public class Locacao {

	private int numero;

	private Status situacao;

	private Date dataInicio;

	private int dataFim;

	public Locacao (int numero, Status situacao, Date dataInicio, int dataFim){
		this.numero = numero;
		this.situacao = situacao;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
	}

	public double calculaValorFinal(Cliente c, Robo r) {
		return c.calculaDesconto(c.getQuantidadeRobos())*r.calculaLocacao(r.getDias());
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Status getSituacao() {
		return situacao;
	}

	public void setSituacao(Status situacao) {
		this.situacao = situacao;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public int getDataFim() {
		return dataFim;
	}

	public void setDataFim(int dataFim) {
		this.dataFim = dataFim;
	}
}
