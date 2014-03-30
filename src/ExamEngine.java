import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.io.*;

public class ExamEngine {
    static CinReader sc = new CinReader();

    public static void main(String[] args)
    {
        String name;
        String answer;
        String rightWords = "";
        int score = 0;
        int[] wordOrder = shufflewords();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date date = new Date();

        System.out.println("Please enter your name");
        name = sc.readString(false);
        name = name.toLowerCase();
        rightWords = name + "\r\n" + dateFormat.format(date) + "\r\nCorrect Words:\r\n";
        System.out.println("Welcome to the spelling test " + name + " here is your first word and the remaining 49 will follow\n");
        for (int i = 0; i < wordOrder.length; i++)
        {
            System.out.println(getDef(wordOrder[i]) + wordOrder[i]);
            answer = sc.readString(false);
            if(answer.equalsIgnoreCase(getWord(wordOrder[i])))
            {
                System.out.println("Correct!\n");
                rightWords += getWord(wordOrder[i]) + "\r\n";
                score++;
            }
            else
            {
                System.out.println("Incorrect.\n");
            }
        }
        stringToFile(rightWords, name);
        System.out.println(name + " your score was " + score + " of 50!");

    }

    private static String getDef(int defNum)
    {
        String def = "";
        try {
            FileInputStream fs = new FileInputStream("defs.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fs));
            for (int i = 0; i < defNum; i++)
            {
                br.readLine();
            }
            def = br.readLine();
        }
        catch (IOException e)
        {
            System.out.println("Error: " + e);
        }
        return def;
    }

    private static String getWord(int wordNum)
    {
        String word = "";
        try {
            FileInputStream fs = new FileInputStream("words.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fs));
            for (int i = 0; i < wordNum; i++)
            {
                br.readLine();
            }
            word = br.readLine();
        }
        catch (IOException e)
        {
            System.out.println("Error: " + e);
        }
        return word;
    }

    public static int[] shufflewords()
    {
        Random rnd = new Random();
        int[] words = new int[50];

        for (int i = 0; i < words.length; i++) {
            words[i] = i;
        }

        for (int i = 0; i < words.length; i++) {
            int position = i + rnd.nextInt(words.length - i);
            int temp = words[i];
            words[i] = words[position];
            words[position] = temp;
        }
        return words;
    }

    private static void stringToFile(String content, String fileName)
    {
        try
        {
            File file = new File(fileName + ".txt");

            if (!file.exists())
            {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
