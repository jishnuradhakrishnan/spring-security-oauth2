package com.oauth2.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private ObjectMapper objectMapper;

	@RequestMapping()
	public JsonNode primeUrl() {
		ObjectNode objectNode = objectMapper.createObjectNode();
		objectNode.put("message", "application started successfully!!");

		return objectNode;
	}
}
