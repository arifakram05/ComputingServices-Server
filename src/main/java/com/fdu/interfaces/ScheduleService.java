package com.fdu.interfaces;

import java.util.List;

import com.fdu.model.StaffSchedule;

public interface ScheduleService {

	List<StaffSchedule> getStaffSchedule();

	void saveStaffSchedule(List<StaffSchedule> staffschedule);

	void updateStaffSchedule(StaffSchedule staffschedule);

	void updateManyEvents(StaffSchedule staffschedule);

	void deleteStaffSchedule(String eventId);

	void deleteManyEvents(String groupId);

}
