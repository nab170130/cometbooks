package project.record;

public class CourseRecord 
{
    public long     courseCode;
	public String   prefix;
	public String   name;

    public CourseRecord(long courseCode_, String prefix_, String name_)
    {
        courseCode = courseCode_;
        prefix  = prefix_;
        name = name_;
    }
}
