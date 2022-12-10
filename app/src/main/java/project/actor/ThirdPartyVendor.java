package project.actor;

import project.core.Textbook;

public class ThirdPartyVendor 
{
    public static double getSuggestedPrice(Textbook textbook)
    {
        double suggestedPrice = 0.00;

        if(textbook.isbn == 12345)
        {
            suggestedPrice = 30.00;
        }
        else if(textbook.isbn == 12346)
        {
            suggestedPrice = 20.00;
        }

        return suggestedPrice;
    }
}
