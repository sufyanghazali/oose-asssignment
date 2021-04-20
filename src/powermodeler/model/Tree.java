package powermodeler.model;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class Tree
{
    private CompositeNode root;

    public Tree(CompositeNode root)
    {
        this.root = root;
    }

    public void print()
    {
        printTree(root, 0);
        printPower();
    }

    private void printTree(Interface node, int tabs)
    {
        System.out.println("    ".repeat(tabs) + node.toString());

        if (!node.isLeaf())
        {
            List<Interface> children = ((CompositeNode) node).getChildren();
            for (Interface child : children)
            {
                printTree(child, tabs + 1);
            }
        }
    }

    private void printPower()
    {
        System.out.println("\nPOWER CONSUMPTION");
        System.out.println("=====================");

        PowerConsumption total = new PowerConsumption();
        getTotalPower(root, total);
        System.out.println(total.print());
    }

    private PowerConsumption getTotalPower(Interface node, PowerConsumption total)
    {
        if (node.isLeaf())
        {
            Map<String, Double> nodeValues = ((LeafNode) node).getPowerConsumption().getValues();
            for (String category : nodeValues.keySet())
            {
                total.addOn(category, nodeValues.get(category));
            }
        } else
        {
            List<Interface> children = ((CompositeNode) node).getChildren();
            for (Interface child : children)
            {
                getTotalPower(child, total);
            }
        }

        return total;
    }

    public void write(PrintWriter pw)
    {
        writeRecursive(root, pw);
    }

    public void writeRecursive(Interface node, PrintWriter pw)
    {
        pw.println(node.toString().replace(" ", ""));

        if (!node.isLeaf())
        {
            List<Interface> children = ((CompositeNode) node).getChildren();
            for (Interface child : children)
            {
                writeRecursive(child, pw);
            }
        }
    }
}