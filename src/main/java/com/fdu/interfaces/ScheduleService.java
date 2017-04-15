package com.fdu.interfaces;

import java.util.List;

import com.fdu.model.LabSchedule;

public interface ScheduleService {

	List<LabSchedule> getLabSchedule();

	void saveLabSchedule(List<LabSchedule> labschedule);

	void updateLabSchedule(LabSchedule labschedule);

}
