package project.actor;

import javax.lang.model.util.ElementScanner14;

import project.core.Course;
import project.core.Schedule;
import project.record.CourseRecord;

public class UTDCoursebook 
{
    public static Schedule getUserSchedule(String netID, String password) throws NoScheduleException, NoCourseException
    {
        Schedule userSchedule = null;

        if(netID.equals("abc123456") && password.equals("pass1234"))
        {
            userSchedule = new Schedule();
            userSchedule.semester = "Fall 2022";

            CourseRecord firstCourseRecord  = new CourseRecord(10000, "SE 6329", "OOSE");
            Course firstCourse              = new Course(firstCourseRecord);

            CourseRecord secondCourseRecord = new CourseRecord(20000, "CS 1336", "Introduction to C++");
            Course secondCourse             = new Course(secondCourseRecord);

            userSchedule.courses.add(firstCourse);
            userSchedule.courses.add(secondCourse);
        }
        else if(netID.equals("zyx654321") && password.equals("pass4321"))
        {
            userSchedule = new Schedule();
            userSchedule.semester = "Spring 2022";

            CourseRecord firstCourseRecord  = new CourseRecord(10000, "SE 6329", "OOSE");
            Course firstCourse              = new Course(firstCourseRecord);

            userSchedule.courses.add(firstCourse);
        }
        else
        {
            throw new NoScheduleException();
        }

        return userSchedule;
    }    

    public static long[] getTextbooks(Course course) throws NoCourseException
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
            throw new NoCourseException();
        }
    }
}
