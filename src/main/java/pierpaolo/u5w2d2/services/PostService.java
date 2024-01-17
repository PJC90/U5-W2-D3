package pierpaolo.u5w2d2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pierpaolo.u5w2d2.entities.Autore;
import pierpaolo.u5w2d2.entities.Post;
import pierpaolo.u5w2d2.exceptions.BadRequestException;
import pierpaolo.u5w2d2.exceptions.NotFoundException;
import pierpaolo.u5w2d2.repositories.AutoreDAO;
import pierpaolo.u5w2d2.repositories.PostDAO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

@Service
public class PostService {
    @Autowired
    private PostDAO postDAO;
    @Autowired
    private AutoreDAO autoreDAO;

    public List<Post> getPosts(){ return this.postDAO.findAll();}

    public Post save(Post body, long id){
        Autore found = autoreDAO.findById(id).orElseThrow(()->new NotFoundException(id));
        body.setAutore(found);
        postDAO.findByTitolo(body.getTitolo()).ifPresent(post -> {throw new BadRequestException("Titolo " + post.getTitolo() + " già in uso!");});
         return postDAO.save(body);
    }
    public Post findById(long id){
        return postDAO.findById(id).orElseThrow(()->new NotFoundException(id));
    }

    public Post findByIdAndUpdate(long id, Post body){
        Post found = this.findById(id);
        found.setCategoria(body.getCategoria());
        found.setTitolo(body.getTitolo());
        found.setCover(body.getCover());
        found.setContenuto(body.getContenuto());
        found.setTempoDiLettura(body.getTempoDiLettura());
        return postDAO.save(found);
    }


    public void findByIdAndDelete(int id){
        Post found = this.findById(id);
        postDAO.delete(found);
    }

    //    public List<Post> getPostsByCategory(String category){
//        List<Post> filteredList = new ArrayList<>();
//        for(Post post : postDAO){
//            if(post.getCategoria().equals(category)){
//                filteredList.add(post);
//            }
//        }
//        return filteredList;
//    }
}
