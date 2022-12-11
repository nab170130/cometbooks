package project.core;

import java.util.ArrayList;
import java.util.List;

import project.actor.NoCourseException;
import project.actor.UTDCoursebook;
import project.adapter.BookDBAdapter;
import project.record.CourseRecord;

public class Course 
{
	public long 			courseCode;
	public String 			prefix;
	public String 			name;
	public List<Textbook> 	textbooks;
	
	public Course(CourseRecord courseRecord) throws NoCourseException
	{
		copyRecordAttributes(courseRecord);

		textbooks = new ArrayList<>();
		BookDBAdapter dbAdapter = BookDBAdapter.getInstance();

		long[] isbns = UTDCoursebook.getTextbooks(this);

		for(long isbnNumber : isbns)
		{
			Textbook textbook = dbAdapter.getBookFromISBN(isbnNumber);
			textbooks.add(textbook);
		}
	}
	
	private void copyRecordAttributes(CourseRecord courseRecord) 
	{
		courseCode 	= courseRecord.courseCode;
		prefix 		= courseRecord.prefix;
		name 		= courseRecord.name;
	}
}
