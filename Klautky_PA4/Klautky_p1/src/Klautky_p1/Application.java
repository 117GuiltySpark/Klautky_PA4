package Klautky_p1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

class DuplicateRemover {
    private Set<String> uniqueWords;

    public void remove(String dataFile)
    {

        try
        {
            String theWord;
            uniqueWords = new HashSet<>();  // A nice way of ignoring duplicated words.
            Scanner input = new Scanner(new File(dataFile));

            while (input.hasNext())         // hasNext will check to see if there are more words to cycle through.
            {
                theWord = input.next();     // find and return the next token, and stick it into the variable.
                uniqueWords.add(theWord);   // take that variable, and stick it into the HashSet.
            }
            input.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void Write(String outputFile) throws IOException
    {

        File newFileMade = new File(outputFile);
        Iterator cycleThroughWords = uniqueWords.iterator();  // Like the name suggests, this is going to read
                                                              // from uniqueWords.
        if (newFileMade.exists())
        {   // overwrites the existing file and starts fresh.
            FileWriter writeInFile = new FileWriter(newFileMade, false);

            while (cycleThroughWords.hasNext())
            {
                String string = (String) cycleThroughWords.next();
                writeInFile.write(string + "\n");
            }
            writeInFile.flush();
            writeInFile.close();

            System.out.println("Unique words obtained from " + outputFile + ":");
            Scanner scan = new Scanner(newFileMade);

            while (scan.hasNextLine())
            {
                System.out.println(scan.nextLine());
            }
            scan.close();

        }

        else
        {
            newFileMade.createNewFile();
            FileWriter writeInFile = new FileWriter(newFileMade);

            while (cycleThroughWords.hasNext())
            {
                String string = (String) cycleThroughWords.next();
                writeInFile.write(string + "\n");
            }
            writeInFile.flush();
            writeInFile.close();

            System.out.println("Unique words obtained from " + outputFile + ":");
            Scanner scan = new Scanner(newFileMade);

            while (scan.hasNextLine()) // just reads the newly made file and prints the contents
            {
                System.out.println(scan.nextLine());
            }
            scan.close();
        }

    }
}

public class Application
{
    public static void main(String[] args) throws IOException
    {
        DuplicateRemover duplicateRemover = new DuplicateRemover();
        duplicateRemover.remove("problem1.txt");
        duplicateRemover.Write("unique_words.txt");
    }
}
// Made by Noah Klautky, COP3330-19, TueThu 6:00PM - 7:15PM