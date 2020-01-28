
public class Pair
{
    private int value;
    private int source;
    
    public Pair(int value, int source)
    {
        this.value = value;
        this.source = source;
    }
    
    public int getValue()
    {
        return value;
    }
    
    public int getSource()
    {
        return source;
    }
    
    public void setValue(int value)
    {
        this.value = value;
    }
    
    public void setSource(int source)
    {
        this.source = source;
    }
}