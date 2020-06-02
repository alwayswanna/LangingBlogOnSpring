package alwayswanna.blogversion.controllers;

import alwayswanna.blogversion.models.Post;
import alwayswanna.blogversion.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Controller
public class BlogController {

    @Autowired
    private PostRepository postRepository;


    @Value("${upload.path}")
    private String uploadPath;


//Показ листа постов пользователю
    @GetMapping("/posts")
    public String blogMain(Map<String, Object> model) {
        Iterable<Post> posts = postRepository.findAll();
        model.put("posts", posts);
        return "posts";
    }
//Показ поста пользователю
    @GetMapping("/posts/{id}")
    public String blogDetails(@PathVariable(value = "id") Integer id, Map<String, Object> model) {
        if (!postRepository.existsById(id)) {
            return "redirect:/posts";
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.put("post", res);
        return "blog-details";
    }
}

