package print.Lora.Post.Service;

import print.Lora.Post.Dto.PostRequestDto;
import print.Lora.Post.Dto.PostRespanceDto;
import print.Lora.Post.Entity.Post;

import java.util.List;

public interface PostService {

    PostRespanceDto createPost(PostRequestDto postRequestDto);

    PostRespanceDto getPostById(Long id);

    List<PostRespanceDto> getAllPosts();

    PostRespanceDto updatePost(Long id, PostRequestDto postRequestDto);

    void deletePost(Long id);
}
