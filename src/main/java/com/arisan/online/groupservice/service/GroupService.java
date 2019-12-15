package com.arisan.online.groupservice.service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arisan.online.groupservice.domain.Group;
import com.arisan.online.groupservice.repository.GroupRepository;

@Service
public class GroupService {

	@Autowired
	private GroupRepository groupRepository;
	
	/** Save **/
	public void insertGroup(Group group) {
		groupRepository.save(group);
	}
	
	/** Selected Element By group Name */
	public Group getSelectedGroupByName(String groupName) {
		Optional<Group> result = groupRepository.findByNamaGroup(groupName);
		if (result.isPresent()) {
			return result.get();
		} else {
			return null;
		}
	}
	
	/** Selected Element By group id */
	public Group getSelectedGroupById(Long id) {
		Optional<Group> result = groupRepository.findById(id);
		if (result.isPresent()) {
			return result.get();
		} else {
			return null;
		}
	}
	
	/**Check Group Active or NOT */
	public boolean isGroupActive(Group group) {
		Calendar cCreatedDate = Calendar.getInstance();
		cCreatedDate.setTime(group.getCreatedDate());
		int day = cCreatedDate.get(Calendar.DAY_OF_MONTH);
		int month = cCreatedDate.get(Calendar.MONTH);
		int year = cCreatedDate.get(Calendar.YEAR);
		if(day >= group.getTanggalArisan()) {
			if(month >= 12) {
				month = 1;
				year += 1;
			}else {
				month += 1;
			}
		}else {
			day = group.getTanggalArisan();
		}
		
		Calendar calEndArisan = Calendar.getInstance();
		calEndArisan.set(year, month, day);
		calEndArisan.add(Calendar.MONTH, group.getMaxMember());
		
		if(calEndArisan.getTime().before(new Date())) {
			return true;
		}else {
			return false;
		}
		
		
	}
}
