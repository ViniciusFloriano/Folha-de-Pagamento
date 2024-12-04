package FolhaDePagamento;
import Funcionario.*;
import java.time.LocalDate;
public class FolhaDePagamento {
    private int id;
    private Funcionario funcionario;
    private LocalDate dataGeracao;
    private LocalDate periodoInicio;
    private LocalDate periodoFim;
    private double valorPago;
    private String observacoes;

    // Construtor
    public FolhaDePagamento(int id, Funcionario funcionario, LocalDate dataGeracao, LocalDate periodoInicio, LocalDate periodoFim, double valorPago, String observacoes) {
        this.id = id;
        this.funcionario = funcionario;
        this.dataGeracao = dataGeracao;
        this.periodoInicio = periodoInicio;
        this.periodoFim = periodoFim;
        this.valorPago = valorPago;
        this.observacoes = observacoes;
    }

    // Getters e Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
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
