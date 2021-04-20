import powermodeler.controller.Controller;
import powermodeler.controller.Util;
import powermodeler.model.Tree;

import java.io.IOException;

class PowerModeler
{
    public static void main(String[] args) throws IOException
    {

        if (args.length < 2)
        {
            System.out.println("Pls input required args");
        } else
        {
            // validate args
            try
            {
                Controller app = new Controller();
//              Tree tree = app.readFile("../resources/data.txt");
//              tree.print();

                Util util = new Util();
                Tree tree3 = new Tree(util.generateData());
                tree3.print();

            } catch (NumberFormatException e)
            {
                System.out.println("Invalid power value: Value given is not a number");
            } catch (IllegalArgumentException e)
            {
                System.out.println(e.getMessage());
            }

        }
    }

//    private void validateArguments(String[] args)
//    {
//        String[] inOptions = {"-g", "-r"};
//        String[] outOptions = {"-d", "-w"};
//
//        String optionOne, optionTwo;
//
//        // option must either be -g, -r, -d, -w
//        if (contains(inOptions, args[0]) || contains(outOptions, args[0]))
//            optionOne = args[0];
//        else
//            throw new IllegalArgumentException("Invalid arguments");
//
//        // options cant be in the same category
//        if ()
//    }

//    private boolean contains(String[] arr, String target)
//    {
//        boolean exists = false;
//        for (String s : arr)
//        {
//            if (s.equals(target))
//                exists = true;
//        }
//        return exists;
//    }
//
//
//    private void displayData()
//    {
//
//    }
}