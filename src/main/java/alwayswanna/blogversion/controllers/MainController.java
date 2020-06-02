package alwayswanna.blogversion.controllers;

import alwayswanna.blogversion.models.Post;
import alwayswanna.blogversion.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {

@Autowired
private PostRepository postRepository;


    @GetMapping("/")
        public String homepage( Map<String, Object> model) {
        Iterable<Post> posts = postRepository.findByIdLessThan(47);
        model.put("posts", posts);
        return "homepage";
        }
    }


