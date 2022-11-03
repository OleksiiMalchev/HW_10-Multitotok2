
import java.util.Arrays;


public class ThreadSafeList<T> {
    final transient Object lock = new Object();
    private T[] array;
    private int size = 0;

    public ThreadSafeList() {
        setArray((T[]) new Object[0]);
    }

    private T[] getArray() {
        return array;
    }

    private void setArray(T[] t) {
        array = t;
    }

    public boolean add(T t) {
        synchronized (lock) {
            T[] extendedArray = getArray();
            int length = extendedArray.length;
            extendedArray = Arrays.copyOf(extendedArray, length + 1);
            extendedArray[length] = t;
            setArray(extendedArray);
            System.out.println(Thread.currentThread().getName());
            size++;
            return true;
        }
    }

    public T remove(int index) {
        if (index > array.length - 1 || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index not in array range");
        }
        synchronized (lock) {
            T[] truncatedArray = getArray();
            int length = truncatedArray.length;
            T oldValue = truncatedArray[index];
            int numMoved = length - index - 1;
            T[] newArray;
            if (numMoved == 0) {
                newArray = Arrays.copyOf(truncatedArray, length - 1);
                size--;
            } else {
                newArray = (T[]) new Object[length - 1];
                System.arraycopy(truncatedArray, 0, newArray, 0, index);
                System.arraycopy(truncatedArray, index + 1, newArray, index, numMoved);
                size--;
            }
            setArray(newArray);
            return oldValue;
        }
    }

    public boolean removeByObject(T t) {
        synchronized (lock) {
            T[] oldArray = getArray();
            for (int i = 0; i < oldArray.length; i++)
                if (t.equals(oldArray[i])) {
                    remove(i);
                    return true;
                }
            return false;
        }
    }

    public T get(int index) {
        if (index > array.length - 1 || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index not in array range");
        }
        return array[index];
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "ThreadSafeList{" +
                "array=" + Arrays.toString(array) +
                '}';
    }
}
