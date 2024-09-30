package print.Lora.Post.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import print.Lora.Post.Dto.PostRequestDto;
import print.Lora.Post.Dto.PostRespanceDto;
import print.Lora.Post.Entity.Post;
import print.Lora.Post.Service.PostService;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostService postService;

    // Endpoint to create a new Post
    @PostMapping("/create")
    public ResponseEntity<PostRespanceDto> createPost(@RequestBody PostRequestDto postRequestDto) {
        PostRespanceDto responseDto = postService.createPost(postRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    // Endpoint to get a Post by ID
    @GetMapping("/{id}")
    public ResponseEntity<PostRespanceDto> getPostById(@PathVariable Long id) {
        PostRespanceDto responseDto = postService.getPostById(id);
        return responseDto != null ?
                new ResponseEntity<>(responseDto, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Endpoint to get all Posts
    @GetMapping("/all")
    public ResponseEntity<List<PostRespanceDto>> getAllPosts() {
        List<PostRespanceDto> posts = postService.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    // Endpoint to update a Post
    @PutMapping("/update/{id}")
    public ResponseEntity<PostRespanceDto> updatePost(
            @PathVariable Long id, @RequestBody PostRequestDto postRequestDto) {
        PostRespanceDto updatedPost = postService.updatePost(id, postRequestDto);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    // Endpoint to delete a Post by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

