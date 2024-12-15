package Classes.DTO;
import java.time.LocalDate;
public class FolhaDePagamento {
    private FuncionarioCLT funcionarioCLT;
    private FuncionarioHorista funcionarioHorista;
    private FuncionarioComissionado funcionarioComissionado;
    private LocalDate dataGeracao;
    private LocalDate periodoInicio;
    private LocalDate periodoFim;
    private double valorPago;
    private String observacoes;

    // Construtor
    public FolhaDePagamento() {
    	
    }
    
    public FolhaDePagamento(FuncionarioCLT funcionarioCLT, LocalDate dataGeracao, LocalDate periodoInicio, LocalDate periodoFim, double valorPago, String observacoes) {
        this.funcionarioCLT = funcionarioCLT;
        this.dataGeracao = dataGeracao;
        this.periodoInicio = periodoInicio;
        this.periodoFim = periodoFim;
        this.valorPago = valorPago;
        this.observacoes = observacoes;
    }
    
    public FolhaDePagamento(FuncionarioHorista funcionarioHorista, LocalDate dataGeracao, LocalDate periodoInicio, LocalDate periodoFim, double valorPago, String observacoes) {
        this.funcionarioHorista = funcionarioHorista;
        this.dataGeracao = dataGeracao;
        this.periodoInicio = periodoInicio;
        this.periodoFim = periodoFim;
        this.valorPago = valorPago;
        this.observacoes = observacoes;
    }
    
    public FolhaDePagamento(FuncionarioComissionado funcionarioComissionado, LocalDate dataGeracao, LocalDate periodoInicio, LocalDate periodoFim, double valorPago, String observacoes) {
        this.funcionarioComissionado = funcionarioComissionado;
        this.dataGeracao = dataGeracao;
        this.periodoInicio = periodoInicio;
        this.periodoFim = periodoFim;
        this.valorPago = valorPago;
        this.observacoes = observacoes;
    }

    // Getters e Setters
	
    public FuncionarioCLT getFuncionarioCLT() {
		return funcionarioCLT;
	}

	public void setFuncionarioCLT(FuncionarioCLT funcionarioCLT) {
		this.funcionarioCLT = funcionarioCLT;
	}
    
	public FuncionarioHorista getFuncionarioHorista() {
		return funcionarioHorista;
	}
	
	public void setFuncionarioHorista(FuncionarioHorista funcionarioHorista) {
		this.funcionarioHorista = funcionarioHorista;
	}
	
	public FuncionarioComissionado getFuncionarioComissionado() {
		return funcionarioComissionado;
	}
	
	public void setFuncionarioComissionado(FuncionarioComissionado funcionarioComissionado) {
		this.funcionarioComissionado = funcionarioComissionado;
	}
	
	public LocalDate getDataGeracao() {
		return dataGeracao;
	}

	public void setDataGeracao(LocalDate dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

	public LocalDate getPeriodoInicio() {
		return periodoInicio;
	}

	public void setPeriodoInicio(LocalDate periodoInicio) {
		this.periodoInicio = periodoInicio;
	}

	public LocalDate getPeriodoFim() {
		return periodoFim;
	}

	public void setPeriodoFim(LocalDate periodoFim) {
		this.periodoFim = periodoFim;
	}

	public double getValorPago() {
		return valorPago;
	}

	public void setValorPago(double valorPago) {
		this.valorPago = valorPago;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
    
}
