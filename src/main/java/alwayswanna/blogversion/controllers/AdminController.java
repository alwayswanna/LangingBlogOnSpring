package alwayswanna.blogversion.controllers;

import alwayswanna.blogversion.models.Post;
import alwayswanna.blogversion.models.Role;
import alwayswanna.blogversion.models.User;
import alwayswanna.blogversion.repository.AdminRepo;
import alwayswanna.blogversion.repository.PostRepository;
import alwayswanna.blogversion.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;


@Controller
public class AdminController {

    // Registration and login for pages.
    @Autowired
    private AdminRepo adminRepo;
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @GetMapping("/login")
    public String loginAdmin(){
        return "login";
    }

    @PostMapping("/login")
    public String sendLogin(){
        return "redirect:/adminposts";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model){
        if(!userService.addUser(user)){
            model.put("message", "User exists!");
            return "registration";
        }
        return "redirect:/login";
    }





    // Add, delete, edit post.

    @Autowired
    private PostRepository postRepository;
    @Value("${upload.path}")
    private String uploadPath;


    @GetMapping("/adminblog/add")
    @PreAuthorize("hasAuthority('USER')")
    public String blogAdd(Map<String, Object> model) {
        return "adminblog-add";
    }

    @RequestMapping( value = "/adminblog/add", method = RequestMethod.POST)
    public String blogPostAdd(@RequestParam String title,
                              @RequestParam String anons,
                              @RequestParam String fulltext,
                              @RequestParam("file") MultipartFile file,
                              Map<String, Object> model)
            throws IOException {

        Post post = new Post(title, anons, fulltext);

        if (file!=null && !file.getOriginalFilename().isEmpty()){
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()){
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFileName));
            post.setFilename(resultFileName);
        }
        postRepository.save(post);
        return "redirect:/adminposts";

    }
    @GetMapping("/adminposts/{id}/edit")
    @PreAuthorize("hasAuthority('USER')")
    public String blogEdit(@PathVariable(value = "id") Integer id, Map<String, Object> model) {
        if (!postRepository.existsById(id)) {
            return "redirect:/posts";
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.put("post", res);
        return "adminblog-edit";
    }
    @PostMapping("/adminposts/{id}/edit")
    @PreAuthorize("hasAuthority('USER')")
    public String blogPostUpdate(@PathVariable(value = "id") Integer id,
                                 @RequestParam String title,
                                 @RequestParam String anons,
                                 @RequestParam String fulltext,
                                 Map<String, Object> model) {
        Post post = postRepository.findById(id).orElseThrow();
        post.setTitle(title);
        post.setAnons(anons);
        post.setFulltext(fulltext);

        postRepository.save(post);
        return "redirect:/adminposts";
    }

    @RequestMapping(value = "/adminposts/{id}/remove", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('USER')")
    public String blogPostRemove(@PathVariable(value = "id") Integer id, Model model) {
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
        return "redirect:/adminposts";
    }

    @GetMapping("/adminposts")
    public String adminPosts(Map<String, Object> model) {
        Iterable<Post> posts = postRepository.findAll();
        model.put("posts", posts);
        return "adminposts";
    }
}
