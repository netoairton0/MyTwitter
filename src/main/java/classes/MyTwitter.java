package classes; //NÃO TRATAR AS EXCEPTIONS AQUI, MUDAR ISSO NO PROXIMO COMMIT

import java.util.ArrayList;

public class MyTwitter implements ITwitter{
    
    private IRepositorioUsuario repositorio;
    
    public MyTwitter(IRepositorioUsuario repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public void criarPerfil(Perfil usuario) throws PEException{
        
        try {
            if(repositorio.buscar(usuario.getUsuario()) != usuario) {
                repositorio.cadastrar(usuario);
            }
            else {
                throw new UJCException();
            }
        }
        catch(UJCException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cancelarPerfil(String usuario) throws PIException, PDException{
        Perfil perfilAux = repositorio.buscar(usuario);
        
        try {
            if(perfilAux == null) {
                throw new PIException();
            }
            else if(!perfilAux.isAtivo()) {
                throw new PDException();
            }
            perfilAux.setAtivo(false);
        }
        catch(PIException | PDException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void tweetar(String usuario, String mensagem) throws PIException, MFPException{
        Perfil perfilAux = repositorio.buscar(usuario);
        Tweet tweetAux = new Tweet(usuario, mensagem);
        ArrayList<Perfil> seguidoresAux = perfilAux.getSeguidores();
        
        try{
            if(perfilAux == null) {
                throw new PIException();
            }
            else if((tweetAux.getMensagem().length() > 140)) {
                throw new MFPException();
            }
            
            perfilAux.addTweet(tweetAux);
            
            for(Perfil p : seguidoresAux) {
                p.addTweet(tweetAux);
            }
        }
        catch(PIException | MFPException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Tweet> timeline(String usuario) throws PIException, PDException{
        Perfil perfilAux = repositorio.buscar(usuario);
        
        try{
            if(perfilAux == null) {
                throw new PIException();
            }
            else if(!(perfilAux.isAtivo())) {
                throw new PDException();
            }
            return perfilAux.getTimeline();
        }
        catch(PIException | PDException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Tweet> tweets(String usuario) throws PIException, PDException{
        Perfil perfilAux = repositorio.buscar(usuario);
        ArrayList<Tweet> timelineAux = perfilAux.getTimeline();
        ArrayList<Tweet> tweetsDoUsuario = new ArrayList<>();
        
        try { 
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
        catch(PIException | PDException e) { 
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void seguir(String seguidor, String seguido) throws PIException, PDException, SIException{
        Perfil seguidorAux = repositorio.buscar(seguidor);
        Perfil seguidoAux = repositorio.buscar(seguido);
        
        try { 
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
        }
        catch(PIException | PDException | SIException e) { 
            e.printStackTrace();
        }
    }

    @Override
    public int numeroSeguidores(String usuario) throws PIException, PDException{
        Perfil perfilAux = repositorio.buscar(usuario);
        ArrayList<Perfil> seguidoresAux = perfilAux.getSeguidores();
        int cont = 0;
        
        try { 
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
        catch(PIException | PDException e) { 
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public ArrayList<Perfil> seguidores(String usuario) throws PIException, PDException{
        Perfil perfilAux = repositorio.buscar(usuario);
        ArrayList<Perfil> seguidoresAux = perfilAux.getSeguidores();
        ArrayList<Perfil> seguidoresAtivos = new ArrayList<>();
        
        try { 
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
        catch(PIException | PDException e) { 
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Perfil> seguidos(String usuario) throws PIException, PDException{
        Perfil perfilAux = repositorio.buscar(usuario);
        ArrayList<Perfil> seguidosAux = perfilAux.getSeguidos();
        ArrayList<Perfil> seguidosAtivos = new ArrayList<>();
        
        try { 
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
        catch(PIException | PDException e) { 
            e.printStackTrace();
            return null;
        }
    }
    
}
