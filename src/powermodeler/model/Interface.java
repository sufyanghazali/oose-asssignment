package powermodeler.model;

public abstract class Interface
{
    private String name;
    private CompositeNode parent;

    public Interface()
    {
        this.name = "";
        this.parent = null;
    }

    public Interface(String name)
    {
        this.name = name;
        parent = null;
    }

    public Interface(String name, CompositeNode parent)
    {
        this(name);
        this.parent = parent;
    }

    public String getName()
    {
        return name;
    }

    public CompositeNode getParent()
    {
        return parent;
    }

    public void setParent(CompositeNode parent)
    {
        this.parent = parent;
    }

    public boolean isLeaf()
    {
        return false;
    }

    abstract Interface find(String name);
}
