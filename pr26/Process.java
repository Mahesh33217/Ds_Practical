import java.util.List;

public class Process {
    int pid;
    boolean alive;
    List<Process> processes;
    static boolean coordinatorElected = false;  // Shared flag to indicate if a coordinator is already elected

    public Process(int pid) {
        this.pid = pid;
        this.alive = true;
    }

    public void setProcesses(List<Process> processes) {
        this.processes = processes;
    }

    public void startElection() {
        if (coordinatorElected) {
            // If coordinator is already elected, do not start another election
            return;
        }

        System.out.println("Process " + pid + " starts election.");
        boolean higherAlive = false;

        for (Process p : processes) {
            if (p.pid > this.pid && p.alive) {
                System.out.println("Process " + pid + " sends ELECTION to " + p.pid);
                p.receiveElection(this);
                higherAlive = true;
            }
        }

        if (!higherAlive) {
            System.out.println("Process " + pid + " becomes the coordinator.");
            coordinatorElected = true;  // Mark that election is complete
            announceCoordinator();
        }
    }

    public void receiveElection(Process sender) {
        System.out.println("Process " + pid + " received ELECTION from " + sender.pid);
        startElection();
    }

    public void announceCoordinator() {
        for (Process p : processes) {
            if (p.pid != this.pid && p.alive) {
                System.out.println("Process " + pid + " sends COORDINATOR to " + p.pid);
            }
        }
    }
}

