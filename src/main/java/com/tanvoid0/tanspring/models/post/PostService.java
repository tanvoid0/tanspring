package com.tanvoid0.tanspring.models.post;

import com.tanvoid0.tanspring.models.post.PostVO;
import com.tanvoid0.tanspring.models.post.PostResponse;

public interface PostService {
  PostVO createPost(PostVO postVO);

  PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

  PostVO getPostById(long id);

  PostVO updatePost(PostVO postVO, long id);

  void deletePostById(long id);
}
