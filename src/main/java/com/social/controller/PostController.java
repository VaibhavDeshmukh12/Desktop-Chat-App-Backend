package com.social.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.social.models.Post;
import com.social.models.User;
import com.social.respose.ApiResponse;
import com.social.service.PostService;
import com.social.service.UserService;

@RestController
public class PostController {
	
	@Autowired
	private PostService postService;
	@Autowired
	private UserService service;
	
	@PostMapping("/api/posts")
	public ResponseEntity<Post> createPost(@RequestHeader("Authorization") String token,@RequestBody Post post) throws Exception{
		
		User reqUser = service.findUserByJwt(token);
		
		Post post2 = this.postService.createPost(post, reqUser.getId());
		
		return new ResponseEntity<>(post2,HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping("/api/posts/{pid}/user")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable int pid,@RequestHeader("Authorization") String token) throws Exception{
		User reqUser = service.findUserByJwt(token);
		String msg = this.postService.deletePost(pid, reqUser.getId());
		ApiResponse response = new ApiResponse(msg,true);
		
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);		
	}
	
	@GetMapping("/api/posts/{id}")
	public ResponseEntity<Post> findPostById(@PathVariable int id) throws Exception{
		
		Post post = this.postService.findPostById(id);
		return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/api/posts/users/{uid}")
	public ResponseEntity<List<Post>> findUsersPost(@PathVariable int uid){
		
		List<Post> list = this.postService.findPostByUserId(uid);
		
		return new ResponseEntity<List<Post>>(list,HttpStatus.OK);
		
	}
	
	@GetMapping("/api/posts")
	public ResponseEntity<List<Post>> findAllPost(){
		
		List<Post> list = this.postService.findAllPost();
		
		return new ResponseEntity<List<Post>>(list,HttpStatus.OK);
		
	}
	
	@PutMapping("/api/posts/save/{pid}")
	public ResponseEntity<Post> savedPostHandler(@PathVariable int pid,@RequestHeader("Authorization") String token) throws Exception{
		User reqUser = service.findUserByJwt(token);
		Post post = this.postService.savedPost(pid, reqUser.getId());
		return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
	}
	
	
	@PutMapping("/api/posts/like/{pid}")
	public ResponseEntity<Post> likedPostHandler(@PathVariable int pid,@RequestHeader("Authorization") String token) throws Exception{
		User reqUser = service.findUserByJwt(token);
		Post post = this.postService.likePost(pid, reqUser.getId());
		return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
	}
	
}
