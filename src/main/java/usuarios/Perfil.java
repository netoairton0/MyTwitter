package usuarios;

import java.util.ArrayList;
import tweet.Tweet;

public abstract class Perfil {
    private String usuario;
    private boolean ativo;
    private ArrayList<Perfil> seguidos;
    private ArrayList<Perfil> seguidores;
    private ArrayList<Tweet> timeline;
    
    public Perfil(String usuario) {
        this.usuario = usuario;
        this.ativo = true;
        seguidos = new ArrayList<>();
        seguidores = new ArrayList<>();
        timeline = new ArrayList<>();
    }

    public String getUsuario() {
        return usuario;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public ArrayList<Perfil> getSeguidos() {
        return seguidos;
    }

    public ArrayList<Perfil> getSeguidores() {
        return seguidores;
    }

    public ArrayList<Tweet> getTimeline() {
        return timeline;
    }
    
    public void setAtivo(boolean valor) {
        this.ativo = valor;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    public void addTweet(Tweet tweet) {
        timeline.add(tweet); 
    }
    
    public void addSeguidor(Perfil usuario) {
        seguidores.add(usuario);
    }
    
    public void addSeguido(Perfil usuario) {
        seguidos.add(usuario);
    }

    @Override
    public String toString() {
        return "Perfil{" + "usuario=" + usuario + ", ativo=" + ativo + "}";
    }
}
