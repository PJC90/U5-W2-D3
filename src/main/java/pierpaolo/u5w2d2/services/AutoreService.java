package pierpaolo.u5w2d2.services;

import org.springframework.stereotype.Service;
import pierpaolo.u5w2d2.entities.Autore;
import pierpaolo.u5w2d2.entities.Post;
import pierpaolo.u5w2d2.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

@Service
public class AutoreService {
    private List<Autore> autori = new ArrayList<>();
    public List<Autore> getAutori(){ return this.autori;}
    public Autore save(Autore body){
        Random rm = new Random();
        body.setId(rm.nextInt(1,100));
        this.autori.add(body);
        return body;
    }
    public Autore findById(int id){
        Autore found = null;
        for (Autore autori : this.autori){
            if(autori.getId() == id){
                found = autori;
            }
        }
        if(found == null)
            throw new NotFoundException(id);
        return found;
    }

    public Autore findByIdAndUpdate(int id, Autore body){
        Autore found = null;
        for(Autore autori: this.autori){
            if(autori.getId() == id){
                found = autori;
                found.setId(id);
                found.setNome(body.getNome());
                found.setCognome(body.getCognome());
                found.setEmail(body.getEmail());
                found.setDataDiNascita(body.getDataDiNascita());
                found.setAvatar(body.getAvatar());
            }
        }
        if(found == null)
            throw new NotFoundException(id);
        return found;
    }


    public void findByIdAndDelete(int id){
        Iterator<Autore> iterator = this.autori.iterator();
        while (iterator.hasNext()){
            Autore current = iterator.next();
            if(current.getId() == id){
                iterator.remove();
            }
        }
    }
}
