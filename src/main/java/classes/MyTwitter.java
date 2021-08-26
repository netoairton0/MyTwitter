package classes;

import classes.exceptions.UJCException;
import classes.exceptions.PDException;
import classes.exceptions.SIException;
import classes.exceptions.PIException;
import classes.exceptions.PEException;
import classes.exceptions.MFPException;
import classes.exceptions.UNCException;
import java.util.ArrayList;

public class MyTwitter implements ITwitter{
    
    private final IRepositorioUsuario repositorio;
    
    public MyTwitter(IRepositorioUsuario repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public void criarPerfil(Perfil usuario) throws PEException{
        
        try {
            if(repositorio.buscar(usuario.getUsuario()) != usuario) {
                repositorio.cadastrar(usuario);
                System.out.println("Perfil cadastrado");
            }
            else {
                throw new UJCException();
            }
        }
        catch(UJCException e) {
            e.printStackTrace();
            throw new PEException();
        }
        
    }

    @Override
    public void cancelarPerfil(String usuario) throws PIException, PDException{
        Perfil perfilAux = repositorio.buscar(usuario);
        
        if(perfilAux == null) {
            throw new PIException();
        }
        else if(!perfilAux.isAtivo()) {
            throw new PDException();
        }
        
        perfilAux.setAtivo(false);
        
        try {
            repositorio.atualizar(perfilAux);
            System.out.println("Perfil desativado");
        } catch (UNCException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void tweetar(String usuario, String mensagem) throws PIException, MFPException{
        Perfil perfilAux = repositorio.buscar(usuario);
        Tweet tweetAux = new Tweet(usuario, mensagem);
        ArrayList<Perfil> seguidoresAux = perfilAux.getSeguidores();
        
        if(perfilAux == null) {
            throw new PIException();
        }
        else if((tweetAux.getMensagem().length() > 140)) {
            throw new MFPException();
        }

        perfilAux.addTweet(tweetAux);
        
        try {
            repositorio.atualizar(perfilAux);
        } catch (UNCException ex) {
            ex.printStackTrace();
        }

        for(Perfil p : seguidoresAux) {
            p.addTweet(tweetAux);
            try {
                repositorio.atualizar(p);
            } catch (UNCException ex) {
                ex.printStackTrace();
            }
        }
        
        System.out.println("Tweet publicado!!");
    }

    @Override
    public ArrayList<Tweet> timeline(String usuario) throws PIException, PDException{
        Perfil perfilAux = repositorio.buscar(usuario);
        
        if(perfilAux == null) {
            throw new PIException();
        }
        else if(!(perfilAux.isAtivo())) {
            throw new PDException();
        }
        
        return perfilAux.getTimeline();
    }

    @Override
    public ArrayList<Tweet> tweets(String usuario) throws PIException, PDException{
        Perfil perfilAux = repositorio.buscar(usuario);
        ArrayList<Tweet> timelineAux = perfilAux.getTimeline();
        ArrayList<Tweet> tweetsDoUsuario = new ArrayList<>();
        
        if(perfilAux == null) {
            throw new PIException();
        }
        else if(!perfilAux.isAtivo()) {
            throw new PDException();
        }

        for(Tweet t : timelineAux) { 
            if(t.getUsuario().equals(perfilAux.getUsuario())) {
                tweetsDoUsuario.add(t);
            }
        }

        return tweetsDoUsuario;
    }

    @Override
    public void seguir(String seguidor, String seguido) throws PIException, PDException, SIException{
        Perfil seguidorAux = repositorio.buscar(seguidor);
        Perfil seguidoAux = repositorio.buscar(seguido);
        
        if(seguidorAux == null || seguidoAux == null) { 
            throw new PIException();
        }
        else if(seguidorAux.isAtivo() == false || seguidoAux.isAtivo() == false) { 
            throw new PDException();
        }
        else if(seguidor.equals(seguido)) { 
            throw new SIException();
        }

        seguidoAux.addSeguidor(seguidorAux);
        seguidorAux.addSeguido(seguidoAux);
        
        try {
            repositorio.atualizar(seguidoAux);
            repositorio.atualizar(seguidorAux);
        } catch (UNCException ex) {
            ex.printStackTrace();
        }
        
        System.out.println("Operação realizada com sucesso!!");
    }

    @Override
    public int numeroSeguidores(String usuario) throws PIException, PDException{
        Perfil perfilAux = repositorio.buscar(usuario);
        ArrayList<Perfil> seguidoresAux = perfilAux.getSeguidores();
        int cont = 0;
        
        if(perfilAux == null) {
            throw new PIException();
        }
        else if(!perfilAux.isAtivo()) {
            throw new PDException();
        }
            
        for(Perfil p : seguidoresAux) { 
            if(p.isAtivo()) { 
                cont++;
            }
        }
        
        return cont;
    }

    @Override
    public ArrayList<Perfil> seguidores(String usuario) throws PIException, PDException{
        Perfil perfilAux = repositorio.buscar(usuario);
        ArrayList<Perfil> seguidoresAux = perfilAux.getSeguidores();
        ArrayList<Perfil> seguidoresAtivos = new ArrayList<>();
        
        if(perfilAux == null) {
            throw new PIException();
        }
        else if(!perfilAux.isAtivo()) {
            throw new PDException();
        }
            
        for(Perfil p : seguidoresAux) { 
            if(p.isAtivo()) {
                seguidoresAtivos.add(p);
            }
        }
            
        return seguidoresAtivos;
    }

    @Override
    public ArrayList<Perfil> seguidos(String usuario) throws PIException, PDException{
        Perfil perfilAux = repositorio.buscar(usuario);
        ArrayList<Perfil> seguidosAux = perfilAux.getSeguidos();
        ArrayList<Perfil> seguidosAtivos = new ArrayList<>();
        
        if(perfilAux == null) {
            throw new PIException();
        }
        else if(!perfilAux.isAtivo()) {
            throw new PDException();
        }
            
        for(Perfil p : seguidosAux) { 
            if(p.isAtivo()) {
                seguidosAtivos.add(p);
            }
        }
            
        return seguidosAtivos;
    }
    
}
