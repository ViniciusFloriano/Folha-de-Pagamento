package Classes.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Classes.DTO.StatusUsuario;
import Classes.DTO.TipoUsuario;
import Classes.DTO.Usuario;
import Classes.Conexao.Conexao;
public class UsuarioDAO {
    final String NOMEDATABELA = "usuario";
    
    public boolean inserir(Usuario usuario) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "INSERT INTO " + NOMEDATABELA + " (nome, email, senha, tipo, status) VALUES (?, ?, ?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());
            ps.setString(4, usuario.getTipoString());
            ps.setString(5, usuario.getStatusString());
            ps.executeUpdate();
            ps.close();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean alterar(Usuario usuario, int idUsuario) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "UPDATE " + NOMEDATABELA + " SET nome = ?, email = ?, senha = ? WHERE id = " + idUsuario + ";";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());
            ps.executeUpdate();
            ps.close();
            conn.close();
            return true;
        } catch (Exception e) {
        	 e.printStackTrace();
             return false;
        }
    }
    
    public boolean excluir(Usuario usuario) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "DELETE FROM " + NOMEDATABELA + " WHERE email = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, usuario.getEmail());
            ps.executeUpdate();
            ps.close();
            conn.close();
            return true;
        } catch (Exception e) {
        	 e.printStackTrace();
             return false;
        }
    }
    
    public Usuario procurarPorNome(Usuario usuario, String pesquisa) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE nome LIKE '%" + pesquisa + "%';";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	Usuario obj = new Usuario();
                obj.setNome(rs.getString(2));
                obj.setEmail(rs.getString(3));
                obj.setSenha(rs.getString(4));
                obj.setTipo(usuario.getTipoTipo());
                obj.setStatus(usuario.getStatusStatus());
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
    
    public Usuario procurarPorEmail(Usuario usuario, String pesquisa) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE email LIKE '%" + pesquisa + "%';";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	Usuario obj = new Usuario();
                obj.setNome(rs.getString(2));
                obj.setEmail(rs.getString(3));
                obj.setSenha(rs.getString(4));
                obj.setTipo(usuario.getTipoTipo());
                obj.setStatus(usuario.getStatusStatus());
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
    
    public boolean existe(Usuario usuario) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE email = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, usuario.getEmail());
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
    
    public List<Usuario> pesquisarTodosFuncionariosSemBanco() {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE tipo = 'FUNCIONARIO' AND status = 'ATIVADO';";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Usuario> listObj = montarListaSemBanco(rs);
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Usuario> montarListaSemBanco(ResultSet rs) {
        List<Usuario> listObj = new ArrayList<Usuario>();
        try {
            while (rs.next()) {
            	Connection conn = Conexao.conectar();
            	String sql2 = "SELECT * FROM " + NOMEDATABELA + ", banco WHERE email LIKE ? AND usuario.id = ? AND usuario.id = banco.funcionario_id;";
                PreparedStatement ps2 = conn.prepareStatement(sql2);
                ps2.setString(1, rs.getString(3));
                ps2.setInt(2, rs.getInt(1));
                ResultSet rs2 = ps2.executeQuery();
                Usuario obj = new Usuario();
                obj.setNome(rs.getString(2));
                obj.setEmail(rs.getString(3));
                obj.setSenha(rs.getString(4));
                if ("ADMINISTRADOR".equals(rs.getString(5))) {
                	obj.setTipo(TipoUsuario.ADMINISTRADOR);
                } else if ("FUNCIONARIO".equals(rs.getString(5))) {
                	obj.setTipo(TipoUsuario.FUNCIONARIO);
                }
                if ("ATIVADO".equals(rs.getString(6))) {
                	obj.setStatus(StatusUsuario.ATIVADO);
                } else if ("DESATIVADO".equals(rs.getString(6))) {
                	obj.setStatus(StatusUsuario.DESATIVADO);
                }
                listObj.add(obj);
                if (rs2.next()) {
                	listObj.remove(obj);
                }
                
            }
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Usuario> pesquisarTodosFuncionariosCLT() {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + ", funcionario_clt WHERE tipo = 'FUNCIONARIO' AND status = 'ATIVADO' AND usuario.id = funcionario_clt.id;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Usuario> listObj = montarListaCLT(rs);
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Usuario> montarListaCLT(ResultSet rs) {
        List<Usuario> listObj = new ArrayList<Usuario>();
        try {
            while (rs.next()) {
                Usuario obj = new Usuario();
                obj.setNome(rs.getString(2));
                obj.setEmail(rs.getString(3));
                obj.setSenha(rs.getString(4));
                if ("ADMINISTRADOR".equals(rs.getString(5))) {
                	obj.setTipo(TipoUsuario.ADMINISTRADOR);
                } else if ("FUNCIONARIO".equals(rs.getString(5))) {
                	obj.setTipo(TipoUsuario.FUNCIONARIO);
                }
                if ("ATIVADO".equals(rs.getString(6))) {
                	obj.setStatus(StatusUsuario.ATIVADO);
                } else if ("DESATIVADO".equals(rs.getString(6))) {
                	obj.setStatus(StatusUsuario.DESATIVADO);
                }
                listObj.add(obj);
                
            }
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Usuario> pesquisarTodosFuncionarios() {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + " WHERE tipo = 'FUNCIONARIO' AND status = 'ATIVADO';";
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
                Usuario obj = new Usuario();
                obj.setNome(rs.getString(2));
                obj.setEmail(rs.getString(3));
                obj.setSenha(rs.getString(4));
                if ("ADMINISTRADOR".equals(rs.getString(5))) {
                	obj.setTipo(TipoUsuario.ADMINISTRADOR);
                } else if ("FUNCIONARIO".equals(rs.getString(5))) {
                	obj.setTipo(TipoUsuario.FUNCIONARIO);
                }
                if ("ATIVADO".equals(rs.getString(6))) {
                	obj.setStatus(StatusUsuario.ATIVADO);
                } else if ("DESATIVADO".equals(rs.getString(6))) {
                	obj.setStatus(StatusUsuario.DESATIVADO);
                }
                listObj.add(obj);
            }
            return listObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public int pegarId(Usuario usuario) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT id FROM " + NOMEDATABELA + " WHERE email LIKE ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, usuario.getEmail());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            ps.close();
            rs.close();
            conn.close();
        } catch (Exception e) {
           e.printStackTrace();
            return 0;
        }
        return 0;
    }
    
    public boolean desativar(int idUsuario) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "UPDATE " + NOMEDATABELA + " SET status = 'DESATIVADO' WHERE id = " + idUsuario + ";";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
            conn.close();
            return true;
        } catch (Exception e) {
        	 e.printStackTrace();
             return false;
        }
    }
    
    public boolean ativar(int idUsuario) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "UPDATE " + NOMEDATABELA + " SET status = 'ATIVADO' WHERE id = " + idUsuario + ";";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
            conn.close();
            return true;
        } catch (Exception e) {
        	 e.printStackTrace();
             return false;
        }
    }
    
    public static Usuario logarSemSenha(String email) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM usuario WHERE email LIKE ? AND senha LIKE '' AND status like 'ATIVADO';";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	return new Usuario(rs.getString("nome"), rs.getString("email"), "", rs.getString("tipo"), rs.getString("status"));
            }
            ps.close();
            rs.close();
            conn.close();
        } catch (Exception e) {
           return null;
        }
        return null;
    }
    
    public static Usuario logar(String email, String senha) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM usuario WHERE email LIKE ? AND senha LIKE ? AND status like 'ATIVADO';";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, senha);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	return new Usuario(rs.getString("nome"), rs.getString("email"), rs.getString("senha"), rs.getString("tipo"), rs.getString("status"));
            }
            ps.close();
            rs.close();
            conn.close();
        } catch (Exception e) {
           return null;
        }
        return null;
    }
    
    public static boolean verificaSenha(String email) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM usuario WHERE email LIKE ? AND isnull(senha) AND status like 'ATIVADO';";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	return true;
            }
            ps.close();
            rs.close();
            conn.close();
        } catch (Exception e) {
           return false;
        }
        return false;
    }
    
    public boolean atualizarSenha(String senha, int idUsuario) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "UPDATE " + NOMEDATABELA + " SET senha = ? WHERE id = " + idUsuario + ";";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, senha);
            ps.executeUpdate();
            ps.close();
            conn.close();
            return true;
        } catch (Exception e) {
        	 e.printStackTrace();
             return false;
        }
    }
    
    public int verificaFuncTipo(int idUsuario) {
    	int tipo = 0;
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + ", funcionario, funcionario_clt WHERE usuario.id = ? AND usuario.id = funcionario.id AND funcionario.id = funcionario_clt.id;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	tipo = 1;
            } else {
            	sql = "SELECT * FROM " + NOMEDATABELA + ", funcionario, funcionario_horista WHERE usuario.id = ? AND usuario.id = funcionario.id AND funcionario.id = funcionario_horista.id;";
            	ps = conn.prepareStatement(sql);
            	ps.setInt(1, idUsuario);
                rs = ps.executeQuery();
                if (rs.next()) {
                	tipo = 2;
                } else {
                	sql = "SELECT * FROM " + NOMEDATABELA + ", funcionario, funcionario_comissionado WHERE usuario.id = ? AND usuario.id = funcionario.id AND funcionario.id = funcionario_comissionado.id;";
                	ps = conn.prepareStatement(sql);
                	ps.setInt(1, idUsuario);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                    	tipo = 3;
                    }
                }
            }
            ps.close();
            rs.close();
            conn.close();
        } catch (Exception e) {
           tipo = 0;
        }
        return tipo;
    }
    
    public boolean temBanco(Usuario usuario, int idUsuario) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + NOMEDATABELA + ", banco WHERE email LIKE ? AND usuario.id = ? AND usuario.id = banco.funcionario_id;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, usuario.getEmail());
            ps.setInt(2, idUsuario);
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
    
}