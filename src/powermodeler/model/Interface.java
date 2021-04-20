package powermodeler.model;

public interface Interface
{

    String getName();

    void setParent(CompositeNode node);

    Interface find(String name);

    boolean isLeaf();
}