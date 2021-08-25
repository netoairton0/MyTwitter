package classes;

public class UJCException extends Exception{
    public UJCException() {
        super("Esse usuario já está cadastrado!");
    }
}
