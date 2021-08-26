package classes;

import classes.exceptions.SIException;
import classes.exceptions.MFPException;
import classes.exceptions.PDException;
import classes.exceptions.PEException;
import classes.exceptions.PIException;
import java.util.ArrayList;

public interface ITwitter {
    public void criarPerfil(Perfil usuario) throws PEException;
    
    public void cancelarPerfil(String usuario) throws PIException, PDException;
    
    public void tweetar(String usuario, String mensagem) throws PIException, MFPException;
    
    public ArrayList<Tweet> timeline(String usuario) throws PIException, PDException;
    
    public ArrayList<Tweet> tweets(String usuario) throws PIException, PDException;
    
    public void seguir(String seguidor, String seguido) throws PIException, PDException, SIException;
    
    public int numeroSeguidores(String usuario) throws PIException, PDException;
    
    public ArrayList<Perfil> seguidores(String usuario) throws PIException, PDException;
            
    public ArrayList<Perfil> seguidos(String usuario) throws PIException, PDException;
}
