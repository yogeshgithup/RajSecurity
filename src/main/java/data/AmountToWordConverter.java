
package data;


public class AmountToWordConverter {
    static final String[] tensNames =
 {
  "", "", "TWENTY", "THIRTY", "FORTY", "FIFTY", "SIXTY", "SEVENTY",
  "EIGHTY", "NINETY"
 };
 
 /** String array of words for ones Names */
 static final String[] onesNames =
 {
  "", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT",
  "NINE", "TEN", "ELEVEN", "TWELVE", "THIRTEEN", "FOURTEEN", "FIFTEEN",
  "SIXTEEN", "SEVENTEEN", "EIGHTEEN", "NINETEEN"
 };
 
 /**
  * Creates a new AmountToWordConverter object.
  */
 public AmountToWordConverter()
 {
 }
 
 /**
  * This method converts the given amount string into words
  *
  * @param aStrAmount String Amount
  *
  * @return String output
  *
  * @throws Exception
  */
 public String converter(String aStrAmount)
 throws Exception
 {
  String output = "";
 
  try
  {
   String totalAmount = aStrAmount;
   int index = totalAmount.indexOf(".");
 
   String beforeDecimal = totalAmount;
 
   if (index > -1)
   {
    beforeDecimal = totalAmount.substring(
      0,
      index
    );
   }
 
   output = evaluate(beforeDecimal) + " RUPEES ";
 
   if (index > -1)
   {
    String afterDecimal = totalAmount.substring((index + 1));
    output += (" AND " + evaluate(afterDecimal) + " PAISE");
   }
   
  }
  catch (Exception exception)
  {
      System.out.println("Error in AmountToWordConverter.converter(String aStrAmount) method" );
      throw exception;
  }
 
  return output;
 }
 
 /**
  * This method converts the given amount string into words
  *
  * @param aStrAmount String Amount
  *
  * @return String output
  *
  * @throws Exception
  */
 public String converter(int iAmount)
 throws Exception
 {
  String output = "";
  Integer i = null;
  try
  {
   i = new Integer(iAmount);
   output = converter(i.toString());
  }
  catch (Exception exception)
  {
      System.out.println("Error in AmountToWordConverter.converter(int iAmount) method" );
      throw exception;
  }
  return output;
 }
 /**
  * This method is used to validate the number in string format
  *
  * @param text String
  *
  * @return String
  *
  * @throws Exception
  */
 private String evaluate(String text)
 throws Exception
 {
  long number = 0;
 
  try
  {
   number = Long.parseLong(text);
  }
  catch (NumberFormatException eNumberFormatException)
  {
      System.out.println("Error in AmountToWordConverter.evaluate(String text) method" );
      throw eNumberFormatException;
  }
 
  return evaluate(number);
 }
 
 /**
  * This method is used to compute the number in words
  *
  * @param number long
  *
  * @return String
  */
 private String evaluate(long number)
 {
  long temp = number;
 
  long crore = temp / 10000000;
  temp %= 10000000;
 
  long lakh = temp / 100000;
  temp %= 100000;
 
  long thousands = temp / 1000;
  temp %= 1000;
 
  long hundreds = temp / 100;
  temp %= 100;
 
  StringBuffer result = new StringBuffer(30);
 
  if (crore > 0)
  {
   result.append(evaluate(crore) + " CRORE ");
  }
 
  if (lakh > 0)
  {
   result.append(evaluate(lakh) + " LAKH ");
  }
 
  if (thousands > 0)
  {
   result.append(evaluate(thousands) + " THOUSAND ");
  }
 
  if (hundreds > 0)
  {
   result.append(evaluate(hundreds) + " HUNDRED ");
  }
 
  if (temp != 0)
  {
   if (number >= 100)
   {
    result.append("AND ");
   }
 
   if ((0 < temp) && (temp <= 19))
   {
    result.append(onesNames[( int ) temp]);
   }
   else
   {
    long tens = temp / 10;
    long ones = temp % 10;
    result.append(tensNames[( int ) tens] + " ");
    result.append(onesNames[( int ) ones]);
   }
  }
 
  if ((result
     .toString()).trim()
     .equals(""))
  {
   result.append(" ZERO ");
  }
 
  return result.toString();
 }
}
