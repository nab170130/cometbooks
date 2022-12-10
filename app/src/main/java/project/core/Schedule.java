package project.core;

import java.util.ArrayList;

import project.actor.UTDCoursebook;

public class Schedule 
{
	public String semester;
	public ArrayList<Course> courses = new ArrayList<>();

	public Schedule()
	{
	}

	public Schedule(String netID, String password) 
	{
		Schedule schedule = UTDCoursebook.getUserSchedule(netID, password);
		semester 	= schedule.semester;
		courses 	= schedule.courses;
	}
}
