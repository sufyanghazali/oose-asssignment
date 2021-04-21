package powermodeler.controller;

import powermodeler.model.CompositeNode;
import powermodeler.model.Interface;
import powermodeler.model.LeafNode;
import powermodeler.model.Tree;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Controller
{
    public Tree readFile(String fileName) throws IOException
    {
        Tree tree;
        Map<String, Interface> nodes = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();

        CompositeNode root = new CompositeNode(line);

        nodes.put(line, root);
        line = reader.readLine();

        while (line != null)
        {
            Interface node;

            // split line
            String[] parts = line.split(",");
            String name = parts[0];
            if (nodes.containsKey(name))
                throw new IllegalArgumentException("Node already exists");

            CompositeNode parent = (CompositeNode) nodes.get(parts[1]);

            // create node
            if (parts.length < 3)
            {
                node = new CompositeNode(name);
            } else
            {
                node = new LeafNode(name);
                for (int i = 2; i < parts.length; i++)
                {
                    String[] powerConsumption = parts[i].split("=");

                    String category = powerConsumption[0];

                    // TODO: need to validate if this is a number
                    double power = Double.parseDouble(powerConsumption[1]);

                    ((LeafNode) node).addPowerConsumption(category, power);
                }
            }

            // set parent/child relationship
            node.setParent(parent);
            parent.addChild(node);

            // add new node to map
            nodes.put(name, node);

            line = reader.readLine();
        }

        tree = new Tree(root);
        return tree;
    }

    public void writeFile(Tree tree, String filename)
    {
        FileOutputStream fileStream = null;
        PrintWriter pw;

        try
        {
            fileStream = new FileOutputStream(filename);
            pw = new PrintWriter(fileStream);
            tree.write(pw);
            pw.close();
        } catch (IOException e)
        {
            if (fileStream != null)
            {
                try
                {
                    fileStream.close();
                } catch (IOException e2)
                {
                }
            }
            System.out.println("Error in writing to file: " + e.getMessage());
        }
    }
}