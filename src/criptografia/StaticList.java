package criptografia;

public class StaticList<E> implements List<E> {

    protected E[] elements;
    public int numElements;

    public StaticList(int maxSize) {
        elements = (E[]) new Object[maxSize];
        numElements = 0;
    }

    public int numElements() {
        return numElements;
    }

    public boolean isEmpty() {
        return numElements == 0;
    }

    public boolean isFull() {
        return numElements == elements.length;
    }

    public void insert(E element, int pos) {
        // verifica se há espaço na lista
        if (isFull()) {
            throw new StackOverflowError();
        }

        // verifica se a posição é válida
        if (pos < 0 || pos > numElements) {
            throw new IndexOutOfBoundsException();
        }

        // desloca para a direita os elementos necessários,
        // abrindo espaço para o novo elemento
        for (int i = numElements - 1; i >= pos; i--) {
            elements[i + 1] = elements[i];
        }

        // armazena o novo elemento e ajusta o total
        elements[pos] = element;
        numElements++;
    }

    public E remove(int pos) {
        // verifica se a posição é válida
        if (pos < 0 || pos >= numElements) {
            throw new IndexOutOfBoundsException();
        }

       // guarda uma referência temporária ao elemento removido
        E element = elements[pos];

      // desloca para a esquerda os elementos necessários,
      // sobrescrevendo a posição que está sendo removida
        for (int i = pos; i < numElements - 1; i++) {
            elements[i] = elements[i + 1];
        }

// define para null a posição antes ocupada pelo último,
// para que a coleta de lixo possa atuar, e ajusta o total
        elements[numElements - 1] = null;
        numElements--;
        return element;
    }

    public E get(int pos) {
        // verifica se a posição é válida
        if (pos < 0 || pos >= numElements) {
            throw new IndexOutOfBoundsException();
        }
        return elements[pos];
    }

    public int search(E element) {
        for (int i = 0; i < numElements; i++) {
            if (element.equals(elements[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < numElements; i++) {
            s += elements[i] + " ";
        }
        return s;
    }
}
