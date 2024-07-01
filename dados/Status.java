package dados;

public enum Status {

	CADASTRADA ("Cadastrada"),

	EXECUTANDO ("Executando"),

	FINALIZADA ("Finalizada"),

	CANCELADA ("Cancelada");

	private final String status;

	Status(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

}
