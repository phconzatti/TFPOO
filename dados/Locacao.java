package dados;

import java.util.ArrayList;
import java.util.Date;

public class Locacao {

	private String cliente;

	private int robo;

	private double valorFinal;

	private int totalDias;

	private int numero;

	private Status situacao;

	private Date dataInicio;

	private int dataFim;

	public Locacao (int numero, Status situacao, Date dataInicio, int dataFim){
		this.numero = numero;
		this.situacao = situacao;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		robo = 0;
		cliente = null;
	}

	public double calculaValorFinal(Cliente c, Robo r) {
		valorFinal = c.calculaDesconto(c.getQuantidadeRobos())*r.calculaLocacao(r.getDias());
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

	public int getTotalDias() {
		return totalDias;
	}

	public void setTotalDias(int totalDias) {
		this.totalDias = totalDias;
	}

	public double getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(double valorFinal) {
		this.valorFinal = valorFinal;
	}

	public void setDataFim(int dataFim) {
		this.dataFim = dataFim;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cLiente) {
		this.cliente = cLiente;
	}

	public int getRobo() {
		return robo;
	}

	public void setRobo(int robo) {
		this.robo = robo;
	}



	@Override
	public String toString() {
		return	"Número="+numero+
				", cliente=" + cliente +
				", robo=" + robo +
				", valorFinal=" + valorFinal +
				", totalDias=" + totalDias +
				", situacao=" + situacao+"\n";
	}
}
