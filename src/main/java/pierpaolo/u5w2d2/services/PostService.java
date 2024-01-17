package pierpaolo.u5w2d2.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pierpaolo.u5w2d2.entities.Post;
import pierpaolo.u5w2d2.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

@Service
public class PostService {
    private List<Post> posts = new ArrayList<>();
    public List<Post> getPosts(){ return this.posts;}
    public List<Post> getPostsByCategory(String category){
        List<Post> filteredList = new ArrayList<>();
        for(Post post : posts){
            if(post.getCategoria().equals(category)){
                filteredList.add(post);
            }
        }
        return filteredList;}
    public Post save(Post body){
        Random rm = new Random();
        body.setId(rm.nextInt(1,100));
        this.posts.add(body);
        return body;
    }
    public Post findById(int id){
        Post found = null;
        for (Post post : this.posts){
            if(post.getId() == id){
                found = post;
            }
        }
        if(found == null)
            throw new NotFoundException(id);
        return found;
    }

    public Post findByIdAndUpdate(int id, Post body){
        Post found = null;
        for(Post post: this.posts){
            if(post.getId() == id){
                found = post;
                found.setId(id);
                found.setCategoria(body.getCategoria());
                found.setContenuto(body.getContenuto());
                found.setTitolo(body.getTitolo());
                found.setCover(body.getCover());
                found.setTempoDiLettura(body.getTempoDiLettura());
            }
        }
        if(found == null)
            throw new NotFoundException(id);
        return found;
    }


    public void findByIdAndDelete(int id){
        Iterator<Post> iterator = this.posts.iterator();
        while (iterator.hasNext()){
            Post current = iterator.next();
            if(current.getId() == id){
                iterator.remove();
            }
        }
    }
}
