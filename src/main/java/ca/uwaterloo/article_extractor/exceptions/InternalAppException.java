package ca.uwaterloo.article_extractor.exceptions;

public class InternalAppException extends Exception
{
    public InternalAppException()
    {
        super();
    }

    public InternalAppException(String s)
    {
        super(s);
    }

    public InternalAppException(String s, Throwable throwable)
    {
        super(s, throwable);
    }

    public InternalAppException(Throwable throwable)
    {
        super(throwable);
    }

    protected InternalAppException(String s, Throwable throwable, boolean b, boolean b1)
    {
        super(s, throwable, b, b1);
    }
}
