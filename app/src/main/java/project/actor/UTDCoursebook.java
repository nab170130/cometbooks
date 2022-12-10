package project.actor;

import javax.lang.model.util.ElementScanner14;

import project.core.Course;
import project.core.Schedule;
import project.record.CourseRecord;

public class UTDCoursebook 
{
    public static Schedule getUserSchedule(String netID, String password)
    {
        Schedule userSchedule = new Schedule();

        if(netID == "abc123456" && password == "pass1234")
        {
            userSchedule.semester = "Fall 2022";

            CourseRecord firstCourseRecord  = new CourseRecord(10000, "SE 6329", "OOSE");
            Course firstCourse              = new Course(firstCourseRecord);

            CourseRecord secondCourseRecord = new CourseRecord(20000, "CS 1336", "Introduction to C++");
            Course secondCourse             = new Course(secondCourseRecord);

            userSchedule.courses.add(firstCourse);
            userSchedule.courses.add(secondCourse);
        }
        else if(netID == "zyx654321" && password == "pass4321")
        {
            userSchedule.semester = "Spring 2022";

            CourseRecord firstCourseRecord  = new CourseRecord(10000, "SE 6329", "OOSE");
            Course firstCourse              = new Course(firstCourseRecord);

            userSchedule.courses.add(firstCourse);
        }

        return userSchedule;
    }    

    public static long[] getTextbooks(Course course)
    {
        if(course.courseCode == 10000)
        {
            return new long[]{12345, 12346};
        }
        else if(course.courseCode == 20000)
        {
            return new long[]{12347};
        }
        else
        {
            return null;
        }
    }
}
