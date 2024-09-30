package print.Lora.Post.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import print.Lora.Post.Dto.PostMapper;
import print.Lora.Post.Dto.PostRequestDto;
import print.Lora.Post.Dto.PostRespanceDto;
import print.Lora.Post.Entity.Post;
import print.Lora.Post.Repository.PostRepository;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostMapper postMapper;

    @Override
    public PostRespanceDto createPost(PostRequestDto postRequestDto) {
        Post post = postMapper.requestToEntity(postRequestDto);
        Post savedPost = postRepository.save(post);
        return postMapper.entityToRespance(savedPost);
    }

    @Override
    public PostRespanceDto getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
        return postMapper.entityToRespance(post);
    }

    @Override
    public List<PostRespanceDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(postMapper::entityToRespance)
                .collect(Collectors.toList());
    }

    @Override
    public PostRespanceDto updatePost(Long id, PostRequestDto postRequestDto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));

        post.setDescription(postRequestDto.getDescription());
        post.setReactId(postRequestDto.getReactId());
        post.setContexte(postRequestDto.getContexte());

        Post updatedPost = postRepository.save(post);
        return postMapper.entityToRespance(updatedPost);
    }

    @Override
    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
        postRepository.delete(post);
    }
}
