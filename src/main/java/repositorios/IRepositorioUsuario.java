package repositorios;

import usuarios.Perfil;
import exceptions.UJCException;
import exceptions.UNCException;

public interface IRepositorioUsuario {
    public void cadastrar(Perfil usuario) throws UJCException;
    
    public void atualizar(Perfil usuario) throws UNCException;
    
    public Perfil buscar(String usuario);
}
