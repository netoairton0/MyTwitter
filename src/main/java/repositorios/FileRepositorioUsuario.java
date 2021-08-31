package repositorios;

import usuarios.Perfil;
import exceptions.UNCException;
import exceptions.UJCException;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import tweet.Tweet;
import usuarios.PessoaFisica;
import usuarios.PessoaJuridica;

public class FileRepositorioUsuario implements IRepositorioUsuario{
    private final File diretorio = new File("C:\\Users\\netoa\\OneDrive\\Documentos\\UsuariosMyTwitter");

    @Override
    public void cadastrar(Perfil usuario) throws UJCException{
        //xstream.alias("Tweet", Tweet.class);
            
        if(buscar(usuario.getUsuario()) == null) { 
            XStream xstream = new XStream(new DomDriver());
            String xml = xstream.toXML(usuario);
            arquivarPessoa(usuario, xml);
        } 
        else { 
            throw new UJCException();
        }
    }

    @Override
    public void atualizar(Perfil usuario) throws UNCException{
        
        //xstream.alias("Tweet", Tweet.class);
        
        if(buscar(usuario.getUsuario()) == null) { 
            throw new UNCException();
        }
        else { 
            XStream xstream = new XStream(new DomDriver());
            String xml = xstream.toXML(usuario);
            arquivarPessoa(usuario, xml);
        }
    }

    @Override
    public Perfil buscar(String usuario) { 
        File file = new File("C:\\Users\\netoa\\OneDrive\\Documentos\\UsuariosMyTwitter\\" + usuario + ".xml");
        
        
        
        try {
            XStream xstream = new XStream(new DomDriver());
            xstream.addPermission(AnyTypePermission.ANY);
            FileReader fr = new FileReader(file);
            xstream.alias("Tweet", Tweet.class);
            return ((Perfil)xstream.fromXML(file));
        } catch (FileNotFoundException ex) {
            return null;
        }
    }
    
    private void arquivarPessoa(Perfil usuario,String xml) { 
        File file = new File(diretorio, usuario.getUsuario() + ".xml");
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
