package powermodeler.model;

import java.util.*;

public class CompositeNode extends Interface
{
    //    private String name;
//    private CompositeNode parent;
    private List<Interface> children;

    public CompositeNode()
    {
        super();
        children = new LinkedList<>();
    }

    public CompositeNode(String name)
    {
        super(name);
        children = new LinkedList<>();
    }

    public CompositeNode(String name, CompositeNode parent)
    {
        super(name, parent);
        children = new LinkedList<>();
    }

    @Override
    public Interface find(String name)
    {
        Interface target = null;
        for (Interface child : children)
        {
            target = child.find(name);
        }
        return target;
    }

    @Override
    public String toString()
    {
        String str = super.getName();
        if (super.getParent() != null)
        {
            str += ", " + super.getParent().getName();
        }
        return str;
    }


    public void addChild(Interface child)
    {
        children.add(child);
    }

    public List<Interface> getChildren()
    {
        return children;
    }

}