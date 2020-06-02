package alwayswanna.blogversion.repository;

import alwayswanna.blogversion.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Integer> {
        Iterable<Post> findByIdLessThan(int i);
        //Iterable<Post> findAllById(int i);
}
