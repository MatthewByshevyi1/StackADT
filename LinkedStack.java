public class LinkedStack<T> implements StackInterface<T> {

    private LLNode<T> top;
    private int size;

    public LinkedStack() {
        this.top = new LLNode<T>(null);
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean empty() {
        return top.getInfo() == null;
    }

    public T push(Object element) {
        T value = (T) element;

        if (empty()) {
            top.setInfo(value);
        }
        else {
            LLNode<T> newNode = new LLNode<T>(value);
            newNode.setLink(top);
            top = newNode;
        }

        size++;

        return value;
    }

    @Override
    public T pop() throws StackUnderflowException {

        if (empty()) {
            throw new StackUnderflowException();
        }

        T element = top.getInfo();

        if (size > 1) {
            top = top.getLink();
        }
        else {
            top.setInfo(null);
        }

        size--;

        return element;
    }

    @Override
    public T peek() throws StackUnderflowException {

        if (empty()) {
            throw new StackUnderflowException();
        }

        return top.getInfo();
    }

    @Override
    public int search(Object thing) {

        T element = (T) thing;

        int index = 1;

        LLNode<T> current = top;
        for (int i = 0; i < size; i++) {
            if (current.getInfo() != null && current.getInfo().equals(element)) {
                return index;
            }
            else if (current.getInfo() == null && element == null) {
                return index;
            }
            index++;
            current = current.getLink();
        }

        return -1;
    }

    @Override
    public void clear() {
        top.setLink(null);
        top.setInfo(null);
        size = 0;
    }
}
