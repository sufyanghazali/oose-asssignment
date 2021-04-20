package powermodeler.model;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class PowerConsumption
{
    public static final String[] CATEGORIES = {"dm", "da", "de", "em", "ea", "ee", "h", "s"};
    private static final String[] CAT_STRINGS = {
            "Weekday morning",
            "Weekday afternoon",
            "Weekday evening",
            "Weekend morning",
            "Weekend afternoon",
            "Weekend evening",
            "Heatwave",
            "Special event"
    };

    private Map<String, Double> powerConsumption;


    public PowerConsumption()
    {
        powerConsumption = new HashMap<>();
    }

    public void add(String category, double power)
    {
        if (!isValidCategory(category))
            throw new IllegalArgumentException("Invalid power category");
        if (power < 0.0)
            throw new IllegalArgumentException("Invalid power value: Value cannot be less than 0.0");
        if (powerConsumption.containsKey(category))
            throw new IllegalArgumentException("Entry for power category already exists");


        powerConsumption.put(category, power);
    }

    public void addOn(String category, double power)
    {
        double newValue = power;

        if (powerConsumption.containsKey(category))
        {
            // add power to current value
            double currentValue = powerConsumption.get(category);
            newValue += currentValue;

            // replace value in tree
            powerConsumption.put(category, newValue);

        }
        powerConsumption.put(category, newValue);

    }

    private boolean isValidCategory(String category)
    {
        boolean found = false;
        int i = 0;

        while (!found && i < CATEGORIES.length)
        {
            found = category.equals(CATEGORIES[i]);
            i++;
        }

        return found;
    }

    public Map<String, Double> getValues()
    {
        return powerConsumption;
    }

    public String print()
    {
        String str = "";
        for (int i = 0; i < CAT_STRINGS.length; i++)
        {

            DecimalFormat df = new DecimalFormat("#######.#");
            double total = 0.0;

            if (powerConsumption.get(CATEGORIES[i]) != null)
                total += powerConsumption.get(CATEGORIES[i]);

//            str += CAT_STRINGS[i] + "\t\t: " + df.format(total) + "\n";
            String line = CAT_STRINGS[i];

            while (line.length() < 19)
            {
                line += " ";
            }

            str += line + ": " + df.format(total) + "\n";
        }

        return str;
    }
