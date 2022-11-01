import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ThreadSafeList<T> {

    private final List<T> safeList = new CopyOnWriteArrayList<>();


    public boolean addToList(T e) {
        Iterator<T> iterator = safeList.iterator();
        return safeList.add(e);
    }

    public void remove(T remove) {
        for (T e : safeList) {
            if (e.equals(remove)) {
                safeList.remove(e);
            }
        }
    }

    public T get(int index) {
        return safeList.get(index);
    }
}
