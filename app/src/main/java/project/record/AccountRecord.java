package project.record;

public class AccountRecord 
{
    public String name;
	public String displayName;
	public String netID;
	public long utdID;
	public String password;
	public int currentAcademicYear;

    
    public AccountRecord(String name_, String displayName_, String netID_, long utdID_, String password_, int currentAcademicYear_)
    {
        name = name_;
        displayName = displayName_;
        netID = netID_;
        utdID = utdID_;
        password = password_;
        currentAcademicYear = currentAcademicYear_;
    }
}
