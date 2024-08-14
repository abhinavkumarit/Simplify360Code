package Simply360Part2;

import java.util.ArrayList;
import java.util.List;


public class Task {
    String name;
    int duration;
    List<Task> dependencies;
    int EST, LST, EFT, LFT;

    public Task(String name, int duration) {
        this.name = name;
        this.duration = duration;
        this.dependencies = new ArrayList<>();
        this.EST = this.LST = this.EFT = this.LFT = 0;
    }
}