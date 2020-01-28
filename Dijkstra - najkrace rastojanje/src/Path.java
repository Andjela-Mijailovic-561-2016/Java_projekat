public class Path
{
    private int source;
    private int destination;
    private int weight;      //distance between two cities
    
    public Path(int source, int destination, int weight)
    {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
    public int getSource()
    {
        return source;
    }
    public int getDestination()
    {
        return destination;
    }
    
    public int getWeight()
    {
        return weight;
    }
    public void setSource(int source)
    {
        this.source = source;
    }
    public void setDestination(int destination)
    {
        this.destination = destination;
    }
    public void setWeight(int weight)
    {
        this.weight = weight;}}

