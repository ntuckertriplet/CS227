package lab6;

public class SimpleTests 
{
	public String name;
    public String output;
	
    public void checkpoint1(String name) 
    {
        this.name = name;
        output = "";
    }

    public String getInitials() 
    {
    	output = "";
    	output += name.substring(0, 1);
        for (int i = 0; i < name.length(); i++) 
        {
            if (name.substring(i, i + 1).equals(" ")) 
            {
                output += name.substring(i + 1, i + 2);
            }
        }
        return output;
    }
    public String firstVowel() 
    {
        String vowelOutput = "";
        boolean found = false; 

        for (int i = 0; i < name.length(); i++) 
        {
            if (found != true && "aeiouAEIOU".indexOf(name.charAt(i)) >= 0) 
            {
                vowelOutput = Integer.toString(i);
                found = true;
            }
        }
        if (found == false) 
        {
            vowelOutput = "-1";
        }
        return vowelOutput;
    }
}

