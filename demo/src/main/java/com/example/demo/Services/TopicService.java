//package com.example.demo.Services;
//
//import java.util.Arrays;
//import java.util.List;
//
//import org.springframework.stereotype.Service;
//
//@Service
//public class TopicService {
//
//	List<Topic> topics= Arrays.asList(new Topic("topic1","Amir"),
//								  new Topic("topic2","Soso"),
//										new Topic("tobic3","mamdouh"));
//
//
//	public List<Topic> getAllTopics(){
//		return topics;
//	}
//
//
//	public Topic getTopics(String id){
//		return topics.stream().filter(topic-> topic.getId().equals(id)).findFirst().get();
//	}
//
//}
