package concurrent.trouble;

/**
 * Created by jinzd on 2016/4/14.
 */
public class ClassWithTreadingProblem {
    int nextId;

    public int takeNextId() {
        return nextId++;
    }
}
