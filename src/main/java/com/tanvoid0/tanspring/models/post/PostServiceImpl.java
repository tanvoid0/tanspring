package com.tanvoid0.tanspring.models.post;

import com.tanvoid0.tanspring.common.exception.ResourceNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
  private PostRepository postRepository;

  private ModelMapper mapper;

  public PostServiceImpl(PostRepository postRepository, ModelMapper mapper) {
    this.postRepository = postRepository;
    this.mapper = mapper;
  }

  @Override
  public PostVO createPost(PostVO postVO) {
    // convert DTO to entity
    Post post = mapToEntity(postVO);
    Post newPost = postRepository.save(post);

    // convert entity to DTO
    PostVO postResponse = mapToDTO(newPost);
    return postResponse;
  }

  @Override
  public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
    Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

    // create pageable instance
    final Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

    Page<Post> posts = postRepository.findAll(pageable);

    // get content from page object
    List<Post> listOfPosts = posts.getContent();
    List<PostVO> content = listOfPosts.stream().map(this::mapToDTO).collect(Collectors.toList());

    PostResponse postResponse = new PostResponse();
    postResponse.setContent(content);
    postResponse.setPageNo(posts.getNumber());
    postResponse.setPageSize(posts.getSize());
    postResponse.setTotalElements(posts.getTotalElements());
    postResponse.setTotalPages(posts.getTotalPages());
    postResponse.setLast(posts.isLast());

    return postResponse;
  }

  @Override
  public PostVO getPostById(long id) {
    Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
    return mapToDTO(post);
  }

  @Override
  public PostVO updatePost(PostVO postVO, long id) {
    // get post by id from the database
    Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

    post.setTitle(postVO.getTitle());
    post.setDescription(postVO.getDescription());
    post.setContent(postVO.getContent());

    Post updatedPost = postRepository.save(post);
    return mapToDTO(updatedPost);
  }

  @Override
  public void deletePostById(long id) {
    // get post by id from the database
    Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
    postRepository.delete(post);
  }

  // convert entity into DTO
  private PostVO mapToDTO(Post post) {
    PostVO postVO = mapper.map(post, PostVO.class);
    return postVO;
  }

  // convert DTO to Entity
  private Post mapToEntity(PostVO postVO) {
    Post post = mapper.map(postVO, Post.class);
    return post;
  }
}
