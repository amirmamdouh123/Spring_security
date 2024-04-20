package com.example.demo.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.example.demo.Services.TopicService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("topics")
public class TopicController {

    @GetMapping
    public String getAllTopics(){
        return "D5alt";
    }

//	@Autowired
//	TopicService topicService;

	/*
	 * @GetMapping public ResponseEntity<?> getAllTopics(){ return
	 * ResponseEntity.ok(topicService.getAllTopics()); }
	 *
	 * @RequestMapping(method = RequestMethod.GET ,name = "/{id}") public
	 * ResponseEntity<?> getAllTopics(@PathVariable("id") String id){ return
	 * ResponseEntity.ok(topicService.getTopics(id)); }
	 */


//	@GetMapping
//	public List<Topic> getAllTopics(){
//		return topicService.getAllTopics();
//	}


//	@GetMapping("/{id}")
//	public Topic getAllTopics(@PathVariable("id") String id){
//		System.out.println(id);
//		return topicService.getTopics(id);
//	}


}



