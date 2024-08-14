package Simply360Part2;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class WorkflowAnalyzer {
    private static void calculateForwardPass(List<Task> tasks) {
        for (Task task : tasks) {
            if (task.dependencies.isEmpty()) {
                task.EST = 0;
                task.EFT = task.duration;
            } else {
                int maxEFT = 0;
                for (Task dep : task.dependencies) {
                    if (dep.EFT > maxEFT) {
                        maxEFT = dep.EFT;
                    }
                }
                task.EST = maxEFT;
                task.EFT = task.EST + task.duration;
            }
        }
    }

    private static void calculateBackwardPass(List<Task> tasks) {
        int maxEFT = 0;
        for (Task task : tasks) {
            if (task.EFT > maxEFT) {
                maxEFT = task.EFT;
            }
        }

        for (int i = tasks.size() - 1; i >= 0; i--) {
            Task task = tasks.get(i);
            if (task.dependencies.isEmpty()) {
                task.LFT = maxEFT;
                task.LST = task.LFT - task.duration;
            } else {
                int minLST = maxEFT;
                for (Task dep : task.dependencies) {
                    if (dep.LST < minLST) {
                        minLST = dep.LST;
                    }
                }
                task.LFT = minLST;
                task.LST = task.LFT - task.duration;
            }
        }
    }

    public static void analyzeWorkflow(List<Task> tasks) {
        calculateForwardPass(tasks);
        calculateBackwardPass(tasks);

        int earliestCompletion = 0;
        int latestCompletion = 0;
        for (Task task : tasks) {
            if (task.EFT > earliestCompletion) {
                earliestCompletion = task.EFT;
            }
            if (task.LFT > latestCompletion) {
                latestCompletion = task.LFT;
            }
        }

        System.out.println("Earliest time all tasks will be completed: " + earliestCompletion);
        System.out.println("Latest time all tasks will be completed: " + latestCompletion);
    }

    public static void main(String[] args) {
        Task tStart = new Task("T_START", 0);
        Task t1 = new Task("T1", 4);
        Task t2 = new Task("T2", 2);
        Task t3 = new Task("T3", 3);
        Task t4 = new Task("T4", 1);

        t1.dependencies.add(tStart);
        t2.dependencies.add(t1);
        t3.dependencies.add(t1);
        t4.dependencies.add(t2);
        t4.dependencies.add(t3);

        List<Task> tasks = new ArrayList<Task>();
        tasks.add(tStart);
        tasks.add(t1);
        tasks.add(t2);
        tasks.add(t3);
        tasks.add(t4);

        analyzeWorkflow(tasks);


        // Complexity

     //   Time Complexity: O(N * D)
     //   Space Complexity: O(N * D)

    //  Where:
    //  N is the number of tasks.
    //  D is the maximum number of dependencies a task can have.

    }
}