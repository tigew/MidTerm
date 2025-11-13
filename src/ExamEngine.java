import java.nio.file.*;
import java.time.*;
import java.time.format.*;
import java.util.*;
import java.util.stream.*;

public class ExamEngine {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        List<String> w = Files.readAllLines(Paths.get("words.txt")), d = Files.readAllLines(Paths.get("defs.txt"));
        List<Integer> idx = IntStream.range(0, w.size()).boxed().collect(Collectors.toList());
        Collections.shuffle(idx);
        System.out.println("Please enter your name");
        String n = sc.nextLine().toLowerCase(), r = n + "\r\n" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss")) + "\r\nCorrect Words:\r\n";
        int s = 0;
        System.out.println("Welcome to the spelling test " + n + " here is your first word and the remaining " + (w.size()-1) + " will follow\n");
        for (int i : idx) {
            System.out.println(d.get(i) + i);
            if (sc.nextLine().equalsIgnoreCase(w.get(i))) {
                System.out.println("Correct!\n");
                r += w.get(i) + "\r\n";
                s++;
            } else System.out.println("Incorrect.\n");
        }
        Files.write(Paths.get(n + ".txt"), r.getBytes());
        System.out.println(n + " your score was " + s + " of " + w.size() + "!");
    }
}
