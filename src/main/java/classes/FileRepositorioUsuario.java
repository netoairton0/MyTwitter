package classes;

import classes.exceptions.UNCException;
import classes.exceptions.UJCException;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileRepositorioUsuario implements IRepositorioUsuario{
    private final File diretorioPF = new File("C:\\Users\\netoa\\OneDrive\\Documentos\\UsuariosMyTwitter\\PessoaFisica");
    private final File diretorioPJ = new File("C:\\Users\\netoa\\OneDrive\\Documentos\\UsuariosMyTwitter\\PessoaJuridica");
    private final XStream xstream = new XStream(new DomDriver());

    @Override
    public void cadastrar(Perfil usuario) throws UJCException{
        if(buscar(usuario.getUsuario()) == null) {
            
            String xml = xstream.toXML(usuario);
            if(usuario instanceof PessoaFisica) {
                arquivaPessoaFisica(usuario, xml);
            }
            else {
                arquivaPessoaJuridica(usuario, xml);
            }
            
        } 
        else { 
            throw new UJCException();
        }
    }

    @Override
    public void atualizar(Perfil usuario) throws UNCException{
        if(buscar(usuario.getUsuario()) == null) { 
            throw new UNCException();
        }
        else { 
            String xml = xstream.toXML(usuario);
            if(usuario instanceof PessoaFisica) { 
                arquivaPessoaFisica(usuario, xml);
            }
            arquivaPessoaJuridica(usuario, xml);
        }
    }

    @Override
    public Perfil buscar(String usuario) { //finalizar
        String usuarioPF = "C:\\Users\\netoa\\OneDrive\\Documentos\\UsuariosMyTwitter\\PessoaFisica\\" + usuario + ".xml";
        String usuarioPJ = "C:\\Users\\netoa\\OneDrive\\Documentos\\UsuariosMyTwitter\\PessoaJuridica\\" + usuario + ".xml";
        
        try {
            FileReader fr = new FileReader(usuarioPF);
            return ((PessoaFisica)xstream.fromXML());
        } catch (FileNotFoundException ex) {
            try {
                FileReader fr = new FileReader(usuarioPJ);
            } catch (FileNotFoundException ex1) {
                return null;
            }
        }
    }
    
    private void arquivaPessoaFisica(Perfil usuario,String xml) { 
        File file = new File(diretorioPF, usuario.getUsuario() + ".xml");
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(xml);
            fw.flush();
            fw.close();
        } 
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void arquivaPessoaJuridica(Perfil usuario,String xml) { 
        File file = new File(diretorioPJ, usuario.getUsuario() + ".xml");
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(xml);
            fw.flush();
            fw.close();
        } 
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
