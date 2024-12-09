package Classes.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import Classes.Conexao.Conexao;
import Classes.DTO.FolhaDePagamento;
public class FolhaDePagamentoDAO {
	final String NOMEDATABELA = "folha_pagamento";
	
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
    
    public List<Usuario> pesquisarTodos() {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + ";";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Usuario> listObj = montarLista(rs);
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Usuario> montarLista(ResultSet rs) {
        List<Usuario> listObj = new ArrayList<Usuario>();
        try {
            while (rs.next()) {
                Marca obj = new Marca();
                obj.setCodigo(rs.getInt(1));
                obj.setDescricao(rs.getString(2));
                listObj.add(obj);
            }
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }*/
}
