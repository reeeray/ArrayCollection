import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class ArrayCollection<T> implements Collection<T> {

    private T[] m = (T[])new Object [1];

    private int size;

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        for(int i=0; i<size; i++) {
            if(o.equals(m[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new ElementIterator();
    }

    @Override
    public Object[] toArray() {
        final Object[] newM = new Object[size];
        System.arraycopy(m, 0, newM, 0, size);

        return newM;
    }

    @Override
    public <T1> T1[] toArray(T1[] t1s) {
        if(t1s.length >= size()) {
            System.arraycopy(m,0, t1s, 0, size());
            return t1s;
        } else {
            return (T1[]) Arrays.copyOf(this.m, this.size());
        }
    }

    @Override
    public boolean add(T t) {
        if(this.size() == m.length) {
            m = Arrays.copyOf(m, m.length*2);
        }
        m[size++] = t;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if(o.equals(m[size-1])) {
            size--;
            return true;
        }
        for(int i=0; i<size; i++) {
            if(o.equals(m[i])) {
                System.arraycopy(m, i+1, m, i, this.size-i-1);
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for(final Object item : collection) {
            if(!this.contains(item)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        for(final T item : collection) {
            this.add(item);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        for(final Object item : collection) {
            this.remove(item);
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        for(int i=0; i<this.size; i++) {
            if(!collection.contains(m[i])) {
                this.remove(m[i--]);
            }
        }
        return true;
    }

    @Override
    public void clear() {
        m = (T[])new Object[1];
        this.size = 0;
    }

    private class ElementIterator implements Iterator<T> {

        private int index;

        @Override
        public boolean hasNext() {
            return ArrayCollection.this.size() > index;
        }

        @Override
        public T next() {
            return ArrayCollection.this.m[index++];
        }
    }
}
