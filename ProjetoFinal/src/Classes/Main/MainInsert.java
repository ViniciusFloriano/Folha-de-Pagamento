package Classes.Main;
import Classes.BO.*;
import Classes.DTO.*;
import java.time.*;
public class MainInsert {
	public static void main(String[] args) {
		
		//Usuarios
		UsuarioBO usuarioBO = new UsuarioBO();
		Usuario usuarioADM = new Usuario("Vinicius da Silva Floriano", "vinicius.floriano@gmail.com", "123eq123", TipoUsuario.ADMINISTRADOR, StatusUsuario.ATIVADO);
		Usuario usuario1 = new Usuario("Teste CLT", "CLT@gmail.com", "123eq123", TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO);
		Usuario usuario2 = new Usuario("Teste Horista", "Horista@gmail.com", "123eq123", TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO);
		Usuario usuario3 = new Usuario("Teste Comissionado", "Comissionado@gmail.com", "123eq123", TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO);
		
		//Inserção dos usuarios no banco de dados
		if (usuarioBO.inserir(usuarioADM) && usuarioBO.inserir(usuario1) && usuarioBO.inserir(usuario2) && usuarioBO.inserir(usuario3)) {
			System.out.println("Inserido com Sucesso");
		} else {
			System.out.println("Erro ao Inserir");			
		}
		
		//Coleta de id dos usuarios
		int idUsuario1 = usuarioBO.pegarId(usuario1);
		int idUsuario2 = usuarioBO.pegarId(usuario2);
		int idUsuario3 = usuarioBO.pegarId(usuario3);
		
		/*
		//Funcionarios
		FuncionarioCLTBO funcionariocltBO = new FuncionarioCLTBO();
		FuncionarioCLT funcionarioclt = new FuncionarioCLT(usuario1.getNome(), usuario1.getEmail(), usuario1.getSenha(), TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO, "Analista de TI", 2000.00);
		
		FuncionarioHoristaBO funcionarioHoristaBO = new FuncionarioHoristaBO();
		FuncionarioHorista funcionarioHorista = new FuncionarioHorista(usuario2.getNome(), usuario2.getEmail(), usuario2.getSenha(), TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO, "Freelancer", 50.00);
		
		FuncionarioComissionadoBO funcionarioComissionadoBO = new FuncionarioComissionadoBO();
		FuncionarioComissionado funcionarioComissionado = new FuncionarioComissionado(usuario3.getNome(), usuario3.getEmail(), usuario3.getSenha(), TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO, "Vendedor", 1500.00, 15.00);
		
		//Inserção dos funcionario clt, horista e comissionado no banco de dados
		if (funcionariocltBO.inserir(funcionarioclt, idUsuario1) && funcionarioHoristaBO.inserir(funcionarioHorista, idUsuario2) && funcionarioComissionadoBO.inserir(funcionarioComissionado, idUsuario3)) {
			System.out.println("Inserido com Sucesso");
		} else {
			System.out.println("Erro ao Inserir");			
		}
		
		//Coleta de id dos funcionarios
		int idFuncionarioCLT1 = funcionariocltBO.pegarId(idUsuario1);
		int idFuncionarioHorista1 = funcionarioHoristaBO.pegarId(idUsuario2);
		int idFuncionarioComissionado1 = funcionarioComissionadoBO.pegarId(idUsuario3);
		
		//Beneficios e Descontos
		BeneficioDescontoBO beneficioDescontoBO = new BeneficioDescontoBO();
		
		//Beneficios CLT
		BeneficioDesconto beneficioDesconto1 = new BeneficioDesconto("Vale Transporte", 800, TipoBenDes.BENEFICIO);
		funcionarioclt.adicionarBeneficio("Vale Transporte", 800);
		BeneficioDesconto beneficioDesconto2 = new BeneficioDesconto("Vale Alimentação", 800, TipoBenDes.BENEFICIO);
		funcionarioclt.adicionarBeneficio("Vale Alimentação", 800);
		
		//Descontos obrigatórios CLT
		BeneficioDesconto inss = new BeneficioDesconto("INSS", 0, TipoBenDes.DESCONTO);
		if (funcionarioclt.getSalarioMensal() <= 1412.00) {
			inss.setValor(funcionarioclt.getSalarioMensal() * 0.075);
			funcionarioclt.adicionarDesconto("INSS", funcionarioclt.getSalarioMensal() * 0.075);
		} else if (funcionarioclt.getSalarioMensal() >= 1412.01 && funcionarioclt.getSalarioMensal() <= 2666.68) {
			inss.setValor(funcionarioclt.getSalarioMensal() * 0.09);
			funcionarioclt.adicionarDesconto("INSS", funcionarioclt.getSalarioMensal() * 0.09);
		} else if (funcionarioclt.getSalarioMensal() >= 2666.69 && funcionarioclt.getSalarioMensal() <= 4000.03) {
			inss.setValor(funcionarioclt.getSalarioMensal() * 0.12);
			funcionarioclt.adicionarDesconto("INSS", funcionarioclt.getSalarioMensal() * 0.12);
		} else if (funcionarioclt.getSalarioMensal() >= 4000.04 && funcionarioclt.getSalarioMensal() <= 7768.02) {
			inss.setValor(funcionarioclt.getSalarioMensal() * 0.14);
			funcionarioclt.adicionarDesconto("INSS", funcionarioclt.getSalarioMensal() * 0.14);
		}
		
		BeneficioDesconto fgts = new BeneficioDesconto("FGTS", funcionarioclt.getSalarioMensal() * 0.08, TipoBenDes.DESCONTO);
		funcionarioclt.adicionarDesconto("FGTS", funcionarioclt.getSalarioMensal() * 0.08);
		
		BeneficioDesconto irrf = new BeneficioDesconto("IRRF", 0, TipoBenDes.DESCONTO); //Imposto de Renda Retido na Fonte
		if (funcionarioclt.getSalarioMensal() >= 1903.99 && funcionarioclt.getSalarioMensal() <= 2826.65) {
			irrf.setValor(funcionarioclt.getSalarioMensal() * 0.075);
			funcionarioclt.adicionarDesconto("IRRF", funcionarioclt.getSalarioMensal() * 0.075);
		} else if (funcionarioclt.getSalarioMensal() >= 2826.66 && funcionarioclt.getSalarioMensal() <= 3751.05) {
			irrf.setValor(funcionarioclt.getSalarioMensal() * 0.15);
			funcionarioclt.adicionarDesconto("IRRF", funcionarioclt.getSalarioMensal() * 0.15);
		} else if (funcionarioclt.getSalarioMensal() >= 3751.06 && funcionarioclt.getSalarioMensal() <= 4664.68) {
			irrf.setValor(funcionarioclt.getSalarioMensal() * 0.225);
			funcionarioclt.adicionarDesconto("IRRF", funcionarioclt.getSalarioMensal() * 0.225);
		} else if (funcionarioclt.getSalarioMensal() >= 4664.69) {
			irrf.setValor(funcionarioclt.getSalarioMensal() * 0.275);
			funcionarioclt.adicionarDesconto("IRRF", funcionarioclt.getSalarioMensal() * 0.275);
		}
		
		//Inserção do Benefico/Desconto do funcionario clt no banco de dados
		if (beneficioDescontoBO.inserir(beneficioDesconto1, idFuncionarioCLT1) && beneficioDescontoBO.inserir(beneficioDesconto2, idFuncionarioCLT1) && beneficioDescontoBO.inserir(inss, idFuncionarioCLT1) && beneficioDescontoBO.inserir(fgts, idFuncionarioCLT1) && beneficioDescontoBO.inserir(irrf, idFuncionarioCLT1)) {
			System.out.println("Inserido com Sucesso");
		} else {
			System.out.println("Erro ao Inserir");			
		}
		
		//Banco
		BancoBO bancoBO = new BancoBO();
		Banco banco1 = new Banco(funcionarioclt, "Banco do Brasil", "111-1", "123132123", TipoConta.CORRENTE);
		Banco banco2 = new Banco(funcionarioHorista, "Banco do Brasil", "111-1", "546873966", TipoConta.CORRENTE);
		Banco banco3 = new Banco(funcionarioComissionado, "Banco do Brasil", "111-1", "246781658", TipoConta.CORRENTE);
		
		//Inserção dos bancos no banco de dados
		if (bancoBO.inserir(banco1, idFuncionarioCLT1) && bancoBO.inserir(banco2, idFuncionarioHorista1) && bancoBO.inserir(banco3, idFuncionarioComissionado1)) {
			System.out.println("Inserido com Sucesso");
		} else {
			System.out.println("Erro ao Inserir");			
		}
		
		//Folha de Pagamaneto
		FolhaDePagamentoBO folhaDePagamentoBO = new FolhaDePagamentoBO();
		
		LocalDate hoje = LocalDate.now();
		YearMonth mesAnterior = YearMonth.from(hoje.minusMonths(1));
		LocalDate primeiroDia = mesAnterior.atDay(1);
		LocalDate ultimoDia = mesAnterior.atEndOfMonth();
		double salarioLiquidoCLT = funcionarioclt.calcularPagamento();
		
		FolhaDePagamento folha1 = new FolhaDePagamento(funcionarioclt, hoje, primeiroDia, ultimoDia, salarioLiquidoCLT, "Beneficios: \nVale Transporte: R$ " + beneficioDesconto1.getValor() + "\nVale Alimentação: R$ " + beneficioDesconto2.getValor() + "\n\nDesconto: \nINSS: R$ "  + inss.getValor() + "\nFGTS: R$ "  + fgts.getValor() + "\nIRRF: R$ " + irrf.getValor());
		
		//Inserção da folha de pagamento no banco de dados
		if (folhaDePagamentoBO.inserir(folha1, idFuncionarioCLT1)) {
			System.out.println("Inserido com Sucesso");
		} else {
			System.out.println("Erro ao Inserir");			
		}*/
	}
}
