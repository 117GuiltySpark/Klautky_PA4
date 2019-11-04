package Klautky_p2;

import java.io.*;
import java.util.*;
import java.lang.String;
import java.util.Scanner;

class DuplicateCounter
{
    private Integer wordCounter;
    private Map<String, Integer> mapOfWordsAndCount;

    public DuplicateCounter()
    {
        this.wordCounter = 0;
        this.mapOfWordsAndCount = new HashMap<>();
    }

    public void count(String dataFile)
    {
        try
        {
            Scanner input = new Scanner(new File(dataFile));

            while (input.hasNext())
            {
                String theWord = input.next();
                String[] data = theWord.split("[\\W]+");

            for (String word : data)
            {
                this.wordCounter = mapOfWordsAndCount.get(word);
                this.wordCounter = (this.wordCounter == null) ? 1 : ++ this.wordCounter; // nice little shorthand trick
                mapOfWordsAndCount.put(word, this.wordCounter);
            }

            }
            input.close();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }
    }

    public void write(String outputFile)
    {
        try
        {
            File newFileMade = new File(outputFile);

            if (newFileMade.exists())
            {
                FileWriter writeInFile = new FileWriter(newFileMade, false);

                for (String num : mapOfWordsAndCount.keySet())
                {
                    writeInFile.write(num + " --- Was used " + mapOfWordsAndCount.get(num) + " time(s)\n");
                }
                writeInFile.flush();
                writeInFile.close();

                System.out.println("Unique Words and their count obtained from " + outputFile + ":");
                Scanner scan = new Scanner(newFileMade);
                while (scan.hasNextLine()) // just reads the newly made file and prints the contents
                {
                    System.out.println(scan.nextLine());
                }
                scan.close();
            }

            else
            {
                newFileMade.createNewFile();
                FileWriter writeInFile = new FileWriter(newFileMade);

                for (String k : mapOfWordsAndCount.keySet())
                {
                    writeInFile.write(k + " " + mapOfWordsAndCount.get(k) + "\n");
                }
                writeInFile.flush();
                writeInFile.close();

                System.out.println("Unique Words and their count obtained from " + outputFile + ":");
                Scanner scan = new Scanner(newFileMade);
                while (scan.hasNextLine()) // just reads the newly made file and prints the contents
                {
                    System.out.println(scan.nextLine());
                }
                scan.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class Application
{
    public static void main(String[] args) throws IOException
    {
        DuplicateCounter duplicateCounter = new DuplicateCounter();
        duplicateCounter.count("problem2.txt");
        duplicateCounter.write("unique_word_counts.txt");
    }
}
// Made by Noah Klautky, COP3330-19, TueThu 6:00PM - 7:15PM