public class ArrayStack<T> implements StackInterface<T> {
    
    private T[] stack;
    private int size;

    public ArrayStack() {
        this.stack = (T[]) new Object[4];
        this.size = 0;
    }

    public boolean full() {
        return size == stack.length;
    }

    @Override
    public boolean empty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void copyStack(T[] ar) {
        for (int i = 0; i < size; i++) {
            ar[i] = stack[i];
        } 
    }

    @Override
    public T push(T element) {
        
        if (full()) {

            T[] holder = (T[]) new Object[stack.length*2];

            copyStack(holder);
            stack = holder;
        }

        stack[size] = element;
        size++;

        return element;
    }

    @Override
    public T pop() throws StackUnderflowException {

        if (empty()) {
            throw new StackUnderflowException();
        }

        T element = stack[size-1];
        stack[size-1] = null;

        size--;

        if (size <= stack.length / 4 && stack.length >= 4) {
            T[] holder = (T[]) new Object[stack.length/2];

            copyStack(holder);
            stack = holder;
        }

        return element;
    }

    @Override
    public T peek() throws StackUnderflowException {

        if (empty()) {
            throw new StackUnderflowException();
        }

        return stack[size-1];
    }

    @Override
    public int search(Object element) {

        int index = 1;
        
        for (int i = size-1; i >= 0; i--) {

            if (stack[i] != null && stack[i].equals(element)) {
                return index;
            }
            else if (stack[i] == null && element == null) {
                return index;
            }
            index++;
        }

        return -1;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            stack[i] = null;
        }
        size = 0;
    }
}