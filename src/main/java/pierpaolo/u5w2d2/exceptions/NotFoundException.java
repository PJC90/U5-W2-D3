package pierpaolo.u5w2d2.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(int id){super("Elemento con id " + id + " non trovato!!!");}
}
