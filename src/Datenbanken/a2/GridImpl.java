package Datenbanken.a2;

import java.util.Iterator;

public class GridImpl<E> implements Grid<E> {

    GridElement<E>[] elementList;
    int rowLength, columnHeight;

    class GridElement<E> {
        public E data;

        public GridElement(E data) {
            this.data = data;
        }
    }

    public GridImpl(int rows, int columns) {
        elementList = new GridElement[rows*columns];
        for (int i = 0; i < (rows*columns); i++) {
            elementList[i] = new GridElement<E>(null);
        }


        rowLength = columns;
        columnHeight = rows;
    }

    // Hilfsmethode
    public int getAccessField(int row, int column) {
        return ((row * rowLength) + column);
    }

    public int GetSize() {
        return elementList.length;
    }

    @Override
    public void insert(E element, int row, int column) {
        try {
            elementList[getAccessField(row, column)].data = element;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Index out of bounds!!");
        }
    }

    @Override
    public E remove(int row, int column) {
        try {
            E returnValue = elementList[getAccessField(row, column)].data;
            elementList[getAccessField(row, column)].data = null;
            return returnValue;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Index out of bounds!!");
            return null;
        }
    }

    @Override
    public E get(int row, int column) {
        try {
            return elementList[getAccessField(row, column)].data;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Index out of bounds!!");
            return null;
        }
    }

    @Override
    public int getMaxColumnSize() {
        return columnHeight;
    }

    @Override
    public int getMaxRowSize() {
        return rowLength;
    }

    @Override
    public boolean isEmpty() {
        boolean returnValue = false;
        for (GridElement element : elementList) {
            if (element.data != null) {
                returnValue = true;
            }
        }
        return returnValue;
    }

    @Override
    public int size() {
        return elementList.length;
    }

    @Override
    public Iterator<E> rowIterator() {
        return new Iterator<E>() {

            int accessField = -1;

            @Override
            public boolean hasNext() {
                try {
                    return (elementList[accessField+1] != null);
                } catch (ArrayIndexOutOfBoundsException e) {
                    return false;
                }
            }

            @Override
            public E next() {
                try {
                    return (elementList[++accessField].data);
                } catch (ArrayIndexOutOfBoundsException e) {
                    return null;
                }
            }
        };
    }

    @Override
    public Iterator<E> columnIterator() {

        return new Iterator<E>() {
            int currentColumn = 0;
            int currentRow = 0;

            @Override
            public boolean hasNext() {
                return currentColumn  < rowLength-1 || currentRow < columnHeight;
            }

            @Override
            public E next() {
                if (hasNext()) {

                    // if (currentRow + 1 > columnHeight-1 && currentColumn+1 <= rowLength ) { currentRow = 0; currentColumn++; }
                    if (currentRow == columnHeight) { currentRow = 0; currentColumn++; }
                    return elementList[getAccessField(currentRow++, currentColumn)].data;
                }
                else return null;
            }
        };
    };

    @Override
    public Iterator<E> iterator() {
        return rowIterator();
    }
}
