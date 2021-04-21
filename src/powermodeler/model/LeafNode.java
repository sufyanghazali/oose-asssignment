package powermodeler.model;

import java.text.DecimalFormat;
import java.util.Map;

public class LeafNode extends Interface
{
    private PowerConsumption powerConsumption;

    public LeafNode()
    {
        super();
        powerConsumption = new PowerConsumption();
    }

    public LeafNode(String name)
    {
        super(name);
        powerConsumption = new PowerConsumption();
    }

    public LeafNode(String name, CompositeNode parent)
    {
        super(name, parent);
        powerConsumption = new PowerConsumption();
    }


    @Override
    public String toString()
    {
        DecimalFormat df = new DecimalFormat("#######.#");

        String str = super.getName() + ", " + super.getParent().getName();
        Map<String, Double> pc = powerConsumption.getValues();

        for (String category : pc.keySet())
        {
            double power = pc.get(category);
            str += ", " + category + "=" + df.format(power);
        }

        return str;
    }

    @Override
    public Interface find(String name)
    {
        Interface found = null;
        if (super.getName().equals(name))
        {
            found = this;
        }
        return found;
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

