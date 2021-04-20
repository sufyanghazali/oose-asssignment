package powermodeler.model;

import java.util.*;

public class CompositeNode implements Interface
{
    private String name;
    private Interface parent;
    private List<Interface> children;

    public CompositeNode()
    {
        name = "";
        parent = null;
        children = new LinkedList<>();
    }

    public CompositeNode(String name)
    {
        this();
        this.name = name;
    }

    public CompositeNode(String name, CompositeNode parent)
    {
        this();
        this.name = name;
        this.parent = parent;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public void setParent(CompositeNode parent)
    {
        this.parent = parent;
    }

    @Override
    public LeafNode find(String name)
    {
        LeafNode target = null;

        for (Interface child : children)
        {
            target = (LeafNode) child.find(name);
        }
        return target;
    }

    @Override
    public String toString()
    {
        String str = name;
        if (parent != null)
        {
            str += ", " + parent.getName();
        }
        return str;
    }

    @Override
    public boolean isLeaf()
    {
        return false;
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