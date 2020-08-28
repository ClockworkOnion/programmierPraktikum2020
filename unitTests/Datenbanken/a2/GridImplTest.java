package Datenbanken.a2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class GridImplTest {

    GridImpl testGrid = new GridImpl(3, 4);

    @BeforeEach
    void insert() {

        // Initialisieren.... ueberfluessig?? (Ist eh schon alles null?)
        int value = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                // System.out.println("Setting: "+ value + " at position " + i + "," + j);
                testGrid.insert(value++, i, j);
            }

        }

        // testGrid.insert(23, 50, 50); // out of bounds
    }

    @Test
    void remove() {
        assertEquals(10, testGrid.remove(2,2));
        assertEquals(null, testGrid.get(2,2));
        assertEquals(7, testGrid.remove(1,3));
        assertEquals(null, testGrid.get(1,3));

        assertEquals(null, testGrid.remove(50, 50));
    }

    @Test
    void get() {
        assertEquals(10, testGrid.get(2,2));
        assertEquals(7, testGrid.get(1,3));
        assertEquals(11, testGrid.get(2,3));
        assertEquals(4, testGrid.get(1,0));

        assertEquals(null, testGrid.get(50, 50));
    }

    @Test
    void rowIterator() {

        Iterator testIterator = testGrid.rowIterator();
        assertTrue(testIterator.hasNext());
        for (int i = 0; i < testGrid.GetSize(); i++) {
            System.out.println(testIterator.next());
        }
        assertFalse(testIterator.hasNext());
        assertEquals(null, testIterator.next());

    }

    @Test
    void columnIterator() {

        Iterator testIterator = testGrid.columnIterator();
        assertTrue(testIterator.hasNext());
        for (int i = 0; i < testGrid.GetSize(); i++) {
            System.out.println(testIterator.next());
        }
        assertFalse(testIterator.hasNext());
        assertEquals(null, testIterator.next());
    }
}