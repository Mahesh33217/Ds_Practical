import java.util.ArrayList;
import java.util.List;

public class BullyAlgorithm {
    public static void main(String[] args) {
        List<Process> processes = new ArrayList<>();
        processes.add(new Process(1));
        processes.add(new Process(2));
        processes.add(new Process(3));
        processes.add(new Process(4));
        processes.add(new Process(5));

        for (Process p : processes) {
            p.setProcesses(processes);
        }

        // Simulate failure of highest process
        processes.get(4).alive = false; // Process 5 failed
        System.out.println("Process 5 has failed.\n");

        // Process 2 initiates the election
        processes.get(1).startElection();
    }
}

