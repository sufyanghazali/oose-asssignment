package powermodeler.model;

import java.text.DecimalFormat;
import java.util.Map;

public class LeafNode implements Interface
{

    private String name;
    private CompositeNode parent;
    private PowerConsumption powerConsumption;

    public LeafNode()
    {
        name = "";
        parent = null;
        powerConsumption = new PowerConsumption();
    }

    public LeafNode(String name)
    {
        this();
        this.name = name;
    }

    public LeafNode(String name, CompositeNode parent)
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
        LeafNode found = null;
        if (this.name.equals(name))
        {
            found = this;
        }
        return found;
    }

    @Override
    public String toString()
    {
        DecimalFormat df = new DecimalFormat("#######.#");

        String str = name + ", " + parent.getName();
        Map<String, Double> pc = powerConsumption.getValues();

        for (String category : pc.keySet())
        {
            double power = pc.get(category);
            str += ", " + category + "=" + df.format(power);
        }

        return str;
    }

    @Override
    public boolean isLeaf()
    {
        return true;
    }

    public void addPowerConsumption(String category, double power)
    {
        powerConsumption.add(category, power);
    }

    public PowerConsumption getPowerConsumption()
    {
        return powerConsumption;
    }
}

