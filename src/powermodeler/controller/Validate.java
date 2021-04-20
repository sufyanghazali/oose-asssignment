package powermodeler.controller;

public class Validate
{
    public void validateLine()
    {

    }

    public boolean isAlphaNumeric(String s)
    {
        return s != null && s.matches("^[a-zA-Z0-9]*$");
    }
}