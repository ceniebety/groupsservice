package com.arisan.online.groupservice.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arisan.online.groupservice.domain.Group;
import com.arisan.online.groupservice.domain.User;
import com.arisan.online.groupservice.dto.FollowGroupDto;
import com.arisan.online.groupservice.service.GroupService;
import com.arisan.online.groupservice.service.UserService;
import com.arisan.online.groupservice.util.ConstantsVariable;

import net.minidev.json.JSONObject;

@RestController
@RequestMapping("/")
@CrossOrigin
public class GroupController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private GroupService groupService;
	
	@PostMapping("add/{username}")
	public ResponseEntity<?> registerGroup (@RequestBody Group group, @PathVariable("username") String username) {
		JSONObject response = new JSONObject();
		User user = userService.getSelectedUser(username);
		if(user != null) {
			if(user.getGroup() == null) {
				group.setCreatedBy(username);
				group.setCreatedDate(new Date());
				groupService.insertGroup(group);
				user.setGroups(group);
				response.put(ConstantsVariable.CONST_DATA, group);
				response.put(ConstantsVariable.CONST_SUCCESS, true);
				response.put(ConstantsVariable.CONST_ERROR, null);
				response.put(ConstantsVariable.CONST_MESSAGE, "add group sucess");
				return new ResponseEntity<>(response, HttpStatus.OK);
			}else {
				response.put(ConstantsVariable.CONST_DATA, null);
				response.put(ConstantsVariable.CONST_SUCCESS, false);
				response.put(ConstantsVariable.CONST_ERROR, "member only have 1 group");
				response.put(ConstantsVariable.CONST_MESSAGE, "member only have 1 group");
				return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}else {
			response.put(ConstantsVariable.CONST_DATA, null);
			response.put(ConstantsVariable.CONST_SUCCESS, false);
			response.put(ConstantsVariable.CONST_ERROR, "user doesn't exist");
			response.put(ConstantsVariable.CONST_MESSAGE, "user doesn't exist");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("follow/{username}")
	public ResponseEntity<?> followGroup (@RequestBody FollowGroupDto dto, @PathVariable("username") String username) {
		JSONObject response = new JSONObject();
		User user = userService.getSelectedUser(username);
		Group group = groupService.getSelectedGroupByName(dto.getNamaGroup());
		
		if(group.getUsers().size() <= group.getMaxMember()) {
			if(user.getGroup() == null) {
				user.setGroups(group);
				userService.insertUser(user);
				response.put(ConstantsVariable.CONST_DATA, null);
				response.put(ConstantsVariable.CONST_SUCCESS, true);
				response.put(ConstantsVariable.CONST_ERROR, null);
				response.put(ConstantsVariable.CONST_MESSAGE, "follow group sucess");
			}else {
				Group currentGroup = groupService.getSelectedGroupById(user.getGroup().getId());
				boolean isGroupActive = groupService.isGroupActive(currentGroup);
				if(isGroupActive) {
					user.setGroups(group);
					userService.insertUser(user);
					response.put(ConstantsVariable.CONST_DATA, null);
					response.put(ConstantsVariable.CONST_SUCCESS, true);
					response.put(ConstantsVariable.CONST_ERROR, null);
					response.put(ConstantsVariable.CONST_MESSAGE, "follow group sucess");
				}else {
					response.put(ConstantsVariable.CONST_DATA, null);
					response.put(ConstantsVariable.CONST_SUCCESS, false);
					response.put(ConstantsVariable.CONST_ERROR, "member only have 1 group");
					response.put(ConstantsVariable.CONST_MESSAGE, "member only have 1 group");
				}
			}
		}else {
			response.put(ConstantsVariable.CONST_DATA, null);
			response.put(ConstantsVariable.CONST_SUCCESS, false);
			response.put(ConstantsVariable.CONST_ERROR, "group full");
			response.put(ConstantsVariable.CONST_MESSAGE, "group full");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
