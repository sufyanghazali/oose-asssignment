package powermodeler.controller;

import powermodeler.model.CompositeNode;
import powermodeler.model.Interface;
import powermodeler.model.LeafNode;

import static powermodeler.model.PowerConsumption.CATEGORIES;

public class Util
{
    public CompositeNode generateData()
    {
        // generate random int 1 - 5
        int numChildren = getRandomInt(0, 5);

        // generate root node
        CompositeNode root = new CompositeNode("ROOT");

        for (int i = 0; i < numChildren; i++)
        {
            Interface child = generateDataRecursive(root, getRandomInt(0, 1), 2);
            root.addChild(child);
        }

        return root;
    }

    private Interface generateDataRecursive(CompositeNode parent, int nodeType, int curDepth)
    {
        Interface node;
        String name = generateName(4); // TODO: need to make sure name hasn't already been generated
//        String categories = CATEGORIES;

        if (nodeType == 0 || curDepth == 5)
        {
            node = new LeafNode(name, parent);

            // generate power categories
            for (String category : CATEGORIES)
            {
                int toggle = getRandomInt(0, 1);

                if (toggle == 1)
                {
                    double power = getRandomDouble(0.0, 1000.0);
                    ((LeafNode) node).addPowerConsumption(category, power);
                }
            }
        } else
        {
            int numChildren = getRandomInt(2, 5);
            node = new CompositeNode(name, parent);
            for (int i = 0; i < numChildren; i++)
            {
                int nextDepth = curDepth + 1;
                Interface child = generateDataRecursive((CompositeNode) node, getRandomInt(0, 1), nextDepth);
                ((CompositeNode) node).addChild(child);
            }
        }

        return node;
    }

    public String generateName(int length)
    {
        char[] charset = "ABCDEFGHIKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        char[] result = new char[length];

        for (int i = 0; i < length; i++)
        {
            int randIndex = getRandomInt(0, charset.length - 1);
            result[i] = charset[randIndex];
        }

        return new String(result);
    }

    private int getRandomInt(int min, int max)
    {

        return (int) ((Math.random() * (max - min + 1) + min));
    }

    private double getRandomDouble(double min, double max)
    {
        return Math.random() * (max - min + 1) + min;
    }
}