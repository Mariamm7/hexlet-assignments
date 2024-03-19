package exercise.controller.users;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import exercise.model.Post;
import exercise.Data;

// BEGIN


@RestController
@RequestMapping("/api/users")
@ResponseStatus(HttpStatus.OK)
public class PostsController {

    private List<Post> posts = Data.getPosts();

    @GetMapping("/{userId}/posts")
    public List<Post> getPostByUserId(@PathVariable int userId) {

        var post = posts.stream()
                .filter(p -> p.getUserId() == (userId))
                .toList();
        return post;
    }

    @PostMapping("/{userId}/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Post> create(@PathVariable int userId, @RequestBody Post post) {
        post.setUserId(userId);
        posts.add(post);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(post);
    }
}

// END
