package testes;

import exceptions.MFPException;
import exceptions.PDException;
import exceptions.PEException;
import exceptions.PIException;
import exceptions.SIException;
import java.util.ArrayList;
import mytwitter.MyTwitter;
import repositorios.FileRepositorioUsuario;
import tweet.Tweet;
import usuarios.*;

public class Final {
    public static void main(String[] args) {
        FileRepositorioUsuario rep = new FileRepositorioUsuario();
        MyTwitter twitter = new MyTwitter(rep);
        
        PessoaFisica pf1 = new PessoaFisica("PedroHenrique", 12345);
        PessoaFisica pf2 = new PessoaFisica("JoaoVictor", 67890);
        PessoaFisica pf3 = new PessoaFisica("JoseAirton", 54321);
        PessoaFisica pf4 = new PessoaFisica("AnaLeticia", 98760);
        PessoaFisica pf5 = new PessoaFisica("RebecaAlencar", 11111);
        
        PessoaJuridica pj1 = new PessoaJuridica("Uber", 12345);
        PessoaJuridica pj2 = new PessoaJuridica("Ifood", 54321);
        PessoaJuridica pj3 = new PessoaJuridica("McDonalds", 67890);
        
        try { //cadastro de todos os usuarios
            twitter.criarPerfil(pf1);
            System.out.println("Perfil cadastrado!!!");
        } catch(PEException pe) { 
            pe.printStackTrace();
        }
        
        try {
            twitter.criarPerfil(pf2);
            System.out.println("Perfil cadastrado!!!");
        } catch(PEException pe) { 
            pe.printStackTrace();
        }
        
        try {
            twitter.criarPerfil(pf3);
            System.out.println("Perfil cadastrado!!!");
        } catch(PEException pe) { 
            pe.printStackTrace();
        }
        
        try {
            twitter.criarPerfil(pf4);
            System.out.println("Perfil cadastrado!!!");
        } catch(PEException pe) { 
            pe.printStackTrace();
        }
        
        try {
            twitter.criarPerfil(pf5);
            System.out.println("Perfil cadastrado!!!");
        } catch(PEException pe) { 
            pe.printStackTrace();
        }
        
        try {
            twitter.criarPerfil(pj1);
            System.out.println("Perfil cadastrado!!!");
        } catch(PEException pe) { 
            pe.printStackTrace();
        }
        
        try {
            twitter.criarPerfil(pj2);
            System.out.println("Perfil cadastrado!!!");
        } catch(PEException pe) { 
            pe.printStackTrace();
        }
        
        try {
            twitter.criarPerfil(pj3);
            System.out.println("Perfil cadastrado!!!");
        } catch(PEException pe) { 
            pe.printStackTrace();
        }
        
        try { //Perfil ja cadastrado e existente
            twitter.criarPerfil(pf1);
        } catch(PEException pe) { 
            pe.printStackTrace();
        }
        
        try { //cancelar cadastro
            twitter.cancelarPerfil("PedroHenrique");
            System.out.println("Perfil desativado!!!");
        } catch(PDException | PIException e) { 
            e.printStackTrace();
        }
        
        try { //Perfil Inexistente
            twitter.cancelarPerfil("JuliaAlmeida");
        } catch(PDException | PIException e) { 
            e.printStackTrace();
        }
        
        try { //Perfil Desativado
            twitter.cancelarPerfil("PedroHenrique");
        } catch(PDException | PIException e) { 
            e.printStackTrace();
        }
        
        try { //tweetar
            twitter.tweetar("JoseAirton", "Oi, esse eh meu primeiro tweet");
        } catch(PIException | MFPException e) { 
            e.printStackTrace();
        }
        
        try { 
            twitter.tweetar("JoseAirton", "Ola Mundo!!!!");
        } catch(PIException | MFPException e) { 
            e.printStackTrace();
        }
        
        try { 
            twitter.tweetar("JoseAirton", "Bom dia!");
        } catch(PIException | MFPException e) { 
            e.printStackTrace();
        }
        
        try { //mensagem fora do padrao
            twitter.tweetar("JoseAirton", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        } catch(PIException | MFPException e) { 
            e.printStackTrace();
        }
        
        try {//Perfil inexistente 
            twitter.tweetar("Julia", "oi");
        } catch(PIException | MFPException e) { 
            e.printStackTrace();
        }
        
        ArrayList<Tweet> arrayTweetsAux; 
        
        try { //tweets
            arrayTweetsAux = twitter.tweets("JoseAirton");
            System.out.println("Tweets:");
            for(int i = 0; i<arrayTweetsAux.size(); i++) { 
                System.out.println("\n" + arrayTweetsAux.get(i));
            }
        } catch(PIException | PDException e) { 
            e.printStackTrace();
        }
        
        try { //Perfil Inexistente
            arrayTweetsAux = twitter.tweets("Julia");
            for(int i = 0; i<arrayTweetsAux.size(); i++) { 
                System.out.println("\n" + arrayTweetsAux.get(i));
            }
        } catch(PIException | PDException e) { 
            e.printStackTrace();
        }
        
        try { //Perfil Desativado
            arrayTweetsAux = twitter.tweets("PedroHenrique");
            for(int i = 0; i<arrayTweetsAux.size(); i++) { 
                System.out.println("\n" + arrayTweetsAux.get(i));
            }
        } catch(PIException | PDException e) { 
            e.printStackTrace();
        }
        
        try { //seguir
            twitter.seguir("JoaoVictor", "JoseAirton");
        } catch(PIException | SIException | PDException e) { 
            e.printStackTrace();
        }
        
        try { //Seguidor invalido
            twitter.seguir("JoaoVictor", "JoaoVictor");
        } catch(PIException | SIException | PDException e) { 
            e.printStackTrace();
        }
        
        try { //Perfil inexistente
            twitter.seguir("JoaoVictor", "Roberto");
        } catch(PIException | SIException | PDException e) { 
            e.printStackTrace();
        }
        
        try { //Perfil Desativado
            twitter.seguir("JoaoVictor", "PedroHenrique");
        } catch(PIException | SIException | PDException e) { 
            e.printStackTrace();
        }
        
        try { 
            twitter.tweetar("JoaoVictor", "Tenham um bom dia!!");
        } catch(PIException | MFPException e) { 
            e.printStackTrace();
        }
        
        try { //timeline
            arrayTweetsAux = twitter.timeline("JoaoVictor");
            System.out.println("Timeline:");
            for(int i = 0; i<arrayTweetsAux.size(); i++) {
                System.out.println("\n" + arrayTweetsAux.get(i));
            }
        } catch(PIException | PDException e) { 
            e.printStackTrace();
        }
        
        try { 
            arrayTweetsAux = twitter.tweets("JoaoVictor");
            System.out.println("Tweets:");
            for(int i = 0; i<arrayTweetsAux.size(); i++) { 
                System.out.println("\n" + arrayTweetsAux.get(i));
            }
        } catch(PIException | PDException e) { 
            e.printStackTrace();
        }
        
        try { //Perfil inexistente 
            arrayTweetsAux = twitter.timeline("Joao");
            for(int i = 0; i<arrayTweetsAux.size(); i++) {
                System.out.println("\n" + arrayTweetsAux.get(i));
            }
        } catch(PIException | PDException e) { 
            e.printStackTrace();
        }
        
        try { //Perfil Desativado
            arrayTweetsAux = twitter.timeline("PedroHenrique");
            for(int i = 0; i<arrayTweetsAux.size(); i++) {
                System.out.println("\n" + arrayTweetsAux.get(i));
            }
        } catch(PIException | PDException e) { 
            e.printStackTrace();
        }
        
        try { 
            twitter.seguir("JoaoVictor", "Uber");
        } catch(PIException | SIException | PDException e) { 
            e.printStackTrace();
        }
        
        try { 
            twitter.seguir("JoseAirton", "Uber");
        } catch(PIException | SIException | PDException e) { 
            e.printStackTrace();
        }
        
        try { 
            twitter.seguir("RebecaAlencar", "Uber");
        } catch(PIException | SIException | PDException e) { 
            e.printStackTrace();
        }
        
        try { 
            twitter.seguir("AnaLeticia", "Uber");
        } catch(PIException | SIException | PDException e) { 
            e.printStackTrace();
        }
        
        try { 
            twitter.tweetar("Uber", "Baixe o nosso aplicativo!!");
        } catch(PIException | MFPException e) { 
            e.printStackTrace();
        }
        
        try {
            arrayTweetsAux = twitter.timeline("AnaLeticia");
            System.out.println("Timeline:");
            for(int i = 0; i<arrayTweetsAux.size(); i++) {
                System.out.println("\n" + arrayTweetsAux.get(i));
            }
        } catch(PIException | PDException e) { 
            e.printStackTrace();
        }
        
        try { //numero de seguidores
            int aux = twitter.numeroSeguidores("Uber");
            System.out.println("Numero de seguidores: " + aux);
        } catch(PIException | PDException e) { 
            e.printStackTrace();
        }
        
        try { 
            int aux = twitter.numeroSeguidores("Ifood");
            System.out.println("Numero de seguidores: " + aux);
        } catch(PIException | PDException e) { 
            e.printStackTrace();
        }
        
        try { //PI
            int aux = twitter.numeroSeguidores("Roberto");
            System.out.println(aux);
        } catch(PIException | PDException e) { 
            e.printStackTrace();
        }
        
        try { //PD
            int aux = twitter.numeroSeguidores("PedroHenrique");
            System.out.println(aux);
        } catch(PIException | PDException e) { 
            e.printStackTrace();
        }
        
        ArrayList<Perfil> arrayPerfilAux;
        
        try { //seguidores
            arrayPerfilAux = twitter.seguidores("Uber");
            System.out.println("Seguidores:");
            for(int i = 0; i < arrayPerfilAux.size(); i++) {
                System.out.println("\n"+arrayPerfilAux.get(i));
            }
        } catch(PIException | PDException e) { 
            e.printStackTrace();
        }
        
        try { 
            arrayPerfilAux = twitter.seguidores("Ifood");
            System.out.println("Seguidores:");
            for(int i = 0; i < arrayPerfilAux.size(); i++) {
                System.out.println("\n"+arrayPerfilAux.get(i));
            }
        } catch(PIException | PDException e) { 
            e.printStackTrace();
        }
        
        try { //PI
            arrayPerfilAux = twitter.seguidores("Roberto");
            for(int i = 0; i < arrayPerfilAux.size(); i++) {
                System.out.println("\n"+arrayPerfilAux.get(i));
            }
        } catch(PIException | PDException e) { 
            e.printStackTrace();
        }
        
        try { //PD
            arrayPerfilAux = twitter.seguidores("PedroHenrique");
            for(int i = 0; i < arrayPerfilAux.size(); i++) {
                System.out.println("\n"+arrayPerfilAux.get(i));
            }
        } catch(PIException | PDException e) { 
            e.printStackTrace();
        }
        
        try { //seguidos
            arrayPerfilAux = twitter.seguidos(("JoaoVictor"));
            System.out.println("Seguidos:");
            for(int i = 0; i < arrayPerfilAux.size(); i++) { 
                System.out.println("\n"+arrayPerfilAux.get(i));
            }
        } catch(PIException | PDException e) { 
            e.printStackTrace();
        }
        
        try { 
            arrayPerfilAux = twitter.seguidos(("Ifood"));
            System.out.println("Seguidos:");
            for(int i = 0; i < arrayPerfilAux.size(); i++) { 
                System.out.println("\n"+arrayPerfilAux.get(i));
            }
        } catch(PIException | PDException e) { 
            e.printStackTrace();
        }
        
        try { //PI
            arrayPerfilAux = twitter.seguidos(("Joao"));
            for(int i = 0; i < arrayPerfilAux.size(); i++) { 
                System.out.println("\n"+arrayPerfilAux.get(i));
            }
        } catch(PIException | PDException e) { 
            e.printStackTrace();
        }
        
        try { //PD
            arrayPerfilAux = twitter.seguidos(("PedroHenrique"));
            for(int i = 0; i < arrayPerfilAux.size(); i++) { 
                System.out.println("\n"+arrayPerfilAux.get(i));
            }
        } catch(PIException | PDException e) { 
            e.printStackTrace();
        }
    }
}
