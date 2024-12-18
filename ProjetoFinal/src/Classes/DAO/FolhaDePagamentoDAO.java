package Classes.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Classes.BO.UsuarioBO;
import Classes.Conexao.Conexao;
import Classes.DTO.FolhaDePagamento;
import Classes.DTO.FuncionarioCLT;
import Classes.DTO.FuncionarioComissionado;
import Classes.DTO.FuncionarioHorista;
import Classes.DTO.StatusUsuario;
import Classes.DTO.TipoUsuario;
public class FolhaDePagamentoDAO {
	final String NOMEDATABELA = "folha_pagamento";
	UsuarioBO usuarioBO = new UsuarioBO();
	
	public boolean inserir(FolhaDePagamento folhaDePagamento, int idFuncionario) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "INSERT INTO " + NOMEDATABELA + " (funcionario_id, data_geracao, periodo_inicio, periodo_fim, valor_pago, observacoes) VALUES (" + idFuncionario + ", ?, ?, ?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, java.sql.Date.valueOf(folhaDePagamento.getDataGeracao()));
            ps.setDate(2, java.sql.Date.valueOf(folhaDePagamento.getPeriodoInicio()));
            ps.setDate(3, java.sql.Date.valueOf(folhaDePagamento.getPeriodoFim()));
            ps.setDouble(4, folhaDePagamento.getValorPago());
            ps.setString(5, folhaDePagamento.getObservacoes());
            ps.executeUpdate();
            ps.close();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
	/*
    public boolean alterar(Usuario marca) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "UPDATE " + NOMEDATABELA + " SET descricao = ? WHERE codigo = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, marca.getDescricao());
            ps.setInt(2, marca.getCodigo());
            ps.executeUpdate();
            ps.close();
            conn.close();
            return true;
        } catch (Exception e) {
        	 e.printStackTrace();
             return false;
        }
    }
    
    public boolean excluir(Usuario marca) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "DELETE FROM " + NOMEDATABELA + " WHERE codigo = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, marca.getCodigo());
            ps.executeUpdate();
            ps.close();
            conn.close();
            return true;
        } catch (Exception e) {
        	 e.printStackTrace();
             return false;
        }
    }
    
    public Usuario procurarPorCodigo(Usuario marca) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE codigo = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, marca.getCodigo());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Marca obj = new Marca();
                obj.setCodigo(rs.getInt(1));
                obj.setDescricao(rs.getString(2));
                ps.close();
                rs.close();
                conn.close();
                return obj;
            } else {
                ps.close();
                rs.close();
                conn.close();
                return null;
            }
        } catch (Exception e) {
        	 e.printStackTrace();
             return null;
        }
    }
    
    public Usuario procurarPorDescricao(Usuario marca) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE descricao = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, marca.getDescricao());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	Usuario obj = new Usuario();
                obj.setCodigo(rs.getInt(1));
                obj.setDescricao(rs.getString(2));
                ps.close();
                rs.close();
                conn.close();
                return obj;
            } else {
                ps.close();
                rs.close();
                conn.close();
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
    
    public boolean existe(Banco banco, int idUsuario) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM usuario, funcionario, " + NOMEDATABELA + " WHERE usuario.id = " + idUsuario + " AND usuario.id = funcionario.id AND funcionario.id = funcionario_clt.id;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ps.close();
                rs.close();
                conn.close();
                return true;
            }
        } catch (Exception e) {
           e.printStackTrace();
            return false;
        }
        return false;
    }
    */
    public List<FolhaDePagamento> pesquisarTodos(int idFuncionario) {
        try {
            Connection conn1 = Conexao.conectar();
            String sql1 = "SELECT data_geracao, periodo_inicio, periodo_fim, valor_pago, observacoes FROM " + NOMEDATABELA + " WHERE funcionario_id = " + idFuncionario + ";";
            PreparedStatement ps1 = conn1.prepareStatement(sql1);
            ResultSet rs1 = ps1.executeQuery();
            int funcTipo = usuarioBO.verificaFuncTipo(idFuncionario);
            List<FolhaDePagamento> listObj = new ArrayList<FolhaDePagamento>();
            if (funcTipo == 1) {
            	Connection conn2 = Conexao.conectar();
            	String sql2 = "SELECT nome, email, senha, cargo, salario_mensal FROM usuario, funcionario, funcionario_clt WHERE funcionario_clt.id = " + idFuncionario + " AND usuario.id = funcionario.id AND funcionario.id = funcionario_clt.id;";
                PreparedStatement ps2 = conn2.prepareStatement(sql2);
                ResultSet rs2 = ps2.executeQuery();
                while (rs2.next()) {
                	FuncionarioCLT obj = new FuncionarioCLT(rs2.getString(1), rs2.getString(2), rs2.getString(3), TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO);
                    obj.setNome(rs2.getString(1));
                    obj.setEmail(rs2.getString(2));
                    obj.setSenha(rs2.getString(3));
                    obj.setTipo(TipoUsuario.FUNCIONARIO);
                    obj.setStatus(StatusUsuario.ATIVADO);
                    obj.setCargo(rs2.getString(4));
                    obj.setSalarioMensal(rs2.getDouble(5));
                    listObj = montarListaCLT(rs1, obj);
                }
            } else if (funcTipo == 2) {
            	Connection conn2 = Conexao.conectar();
            	String sql2 = "SELECT nome, email, senha, cargo, salario_hora, horas_trabalhadas, horas_extras FROM usuario, funcionario, funcionario_horista WHERE funcionario_horista.id = " + idFuncionario + " AND usuario.id = funcionario.id AND funcionario.id = funcionario_horista.id;";
                PreparedStatement ps2 = conn2.prepareStatement(sql2);
                ResultSet rs2 = ps2.executeQuery();
                while (rs2.next()) {
                	FuncionarioHorista obj = new FuncionarioHorista(rs2.getString(1), rs2.getString(2), rs2.getString(3), TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO);
	            	obj.setNome(rs2.getString(1));
	                obj.setEmail(rs2.getString(2));
	                obj.setSenha(rs2.getString(3));
	                obj.setTipo(TipoUsuario.FUNCIONARIO);
	                obj.setStatus(StatusUsuario.ATIVADO);
	                obj.setCargo(rs2.getString(4));
	                obj.setSalarioHora(rs2.getDouble(5));
	                obj.setHorasTrabalhadas(rs2.getDouble(6));
	                obj.setHorasExtras(rs2.getDouble(7));
	                obj.getValorHorasExtras();
	                listObj = montarListaHorista(rs1, obj);
                }
            } else if (funcTipo == 3) {
            	Connection conn2 = Conexao.conectar();
            	String sql2 = "SELECT nome, email, senha, cargo, salario_base, comissao_percentual, vendas_realizadas, bonus FROM usuario, funcionario, funcionario_clt WHERE funcionario_comissionado.id = " + idFuncionario + " AND usuario.id = funcionario.id AND funcionario.id = funcionario_comissionado.id;";
                PreparedStatement ps2 = conn2.prepareStatement(sql2);
                ResultSet rs2 = ps2.executeQuery();
                while (rs2.next()) {
                	FuncionarioComissionado obj = new FuncionarioComissionado(rs2.getString(1), rs2.getString(2), rs2.getString(3), TipoUsuario.FUNCIONARIO, StatusUsuario.ATIVADO);
                    obj.setNome(rs2.getString(1));
                    obj.setEmail(rs2.getString(2));
                    obj.setSenha(rs2.getString(3));
                    obj.setTipo(TipoUsuario.FUNCIONARIO);
                    obj.setStatus(StatusUsuario.ATIVADO);
                    obj.setCargo(rs2.getString(4));
                    obj.setSalarioBase(rs2.getDouble(5));
                    obj.setComissao(rs2.getDouble(5));
                    obj.setVendasRealizadas(rs2.getDouble(5));
                    obj.setBonus(rs2.getDouble(5));
                    listObj = montarListaComissionado(rs1, obj);
                }
            }
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<FolhaDePagamento> montarListaCLT(ResultSet rs, FuncionarioCLT func) {
        List<FolhaDePagamento> listObj = new ArrayList<FolhaDePagamento>();
        try {
            while (rs.next()) {
            	FolhaDePagamento obj = new FolhaDePagamento();
                obj.setFuncionarioCLT(func);
                obj.setDataGeracao(rs.getDate(1).toLocalDate());
                obj.setPeriodoInicio(rs.getDate(2).toLocalDate());
                obj.setPeriodoFim(rs.getDate(3).toLocalDate());
                obj.setValorPago(rs.getDouble(4));
                obj.setObservacoes(rs.getString(5));
                listObj.add(obj);
            }
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<FolhaDePagamento> montarListaHorista(ResultSet rs, FuncionarioHorista func) {
        List<FolhaDePagamento> listObj = new ArrayList<FolhaDePagamento>();
        try {
            while (rs.next()) {
            	FolhaDePagamento obj = new FolhaDePagamento();
            	obj.setFuncionarioHorista(func);
            	obj.setDataGeracao(rs.getDate(1).toLocalDate());
                obj.setPeriodoInicio(rs.getDate(2).toLocalDate());
                obj.setPeriodoFim(rs.getDate(3).toLocalDate());
                obj.setValorPago(rs.getDouble(4));
                obj.setObservacoes(rs.getString(5));
                listObj.add(obj);
            }
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<FolhaDePagamento> montarListaComissionado(ResultSet rs, FuncionarioComissionado func) {
        List<FolhaDePagamento> listObj = new ArrayList<FolhaDePagamento>();
        try {
            while (rs.next()) {
            	FolhaDePagamento obj = new FolhaDePagamento();
            	obj.setFuncionarioComissionado(func);
            	obj.setDataGeracao(rs.getDate(1).toLocalDate());
                obj.setPeriodoInicio(rs.getDate(2).toLocalDate());
                obj.setPeriodoFim(rs.getDate(3).toLocalDate());
                obj.setValorPago(rs.getDouble(4));
                obj.setObservacoes(rs.getString(5));
                listObj.add(obj);
            }
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
