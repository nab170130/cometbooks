package project.rest;

public class PriceResponseJSONObject
{
    ResultField result;

    
    class ResultField
    {
        OffersField offers;

        class OffersField
        {
            BooksRunField booksrun;

            class BooksRunField
            {
                NewField new_;

                class NewField
                {
                    double price;
                }
            }
        }
    }


    public double getPrice()
    {
        return result.offers.booksrun.new_.price;
    }
}
