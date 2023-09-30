package com.eschool;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.eschool.beans.EventPermission;

public interface EventPermissionRepository extends CrudRepository<EventPermission, Integer> {
	
	List<EventPermission> findByAdminId(String adminId);
	
	EventPermission findByAdminIdAndEventId(String adminId, int eventId);
	
	List<EventPermission> findAll();


}
