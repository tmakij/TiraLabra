package Collections;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public final class HashSetTest {

    private HashSet<String> set;

    @BeforeClass
    public static void setUpStatic() {
        HashSet<Integer> set = new HashSet<>();
        final int iters = 100000;
        for (int i = 0; i < iters; i++) {
            set.add(i);
        }
        for (int i = 0; i < iters; i++) {
            set.contains(i);
            set.remove(i);
            set.contains(i);
            set.remove(i);
        }
    }

    @Before
    public void setUp() {
        set = new HashSet<>();
    }

    @Test
    public void testCreationSize() {
        assertEquals(0, set.size());
    }

    @Test
    public void testAddOne() {
        final String toAdd = "asd";
        set.add(toAdd);
        assertEquals(1, set.size());
    }

    @Test
    public void testAddOneContains() {
        final String toAdd = "asd";
        set.add(toAdd);
        assertTrue(set.contains(toAdd));
    }

    @Test
    public void testAddOneAndRemove() {
        final String toAdd = "asd";
        set.add(toAdd);
        set.remove(toAdd);
        assertFalse(set.contains(toAdd));
    }

    @Test
    public void testAddMany() {
        final int size = 125;
        HashSet<Integer> intSet = new HashSet<>();
        for (int i = 0; i < size; i++) {
            intSet.add(i);
        }
        assertEquals(size, intSet.size());
    }

    @Test
    public void testDontAddSame() {
        final int size = 125;
        HashSet<Integer> intSet = new HashSet<>();
        for (int i = 0; i < size; i++) {
            intSet.add(23443);
        }
        assertEquals(1, intSet.size());
    }

    @Test
    public void testAddManyContainAll() {
        final int size = 125;
        HashSet<Integer> intSet = new HashSet<>();
        for (int i = 0; i < size; i++) {
            intSet.add(i);
        }
        for (int i = 0; i < size; i++) {
            if (!intSet.contains(i)) {
                fail(i + " was added but was not found");
            }
        }
    }

    @Test
    public void testAddRemoveManyContainNone() {
        final int size = 125;
        HashSet<Integer> intSet = new HashSet<>();
        for (int i = 0; i < size; i++) {
            intSet.add(i);
        }
        //Twice intented!
        for (int i = 0; i < size; i++) {
            intSet.remove(i);
        }
        for (int i = 0; i < size; i++) {
            intSet.remove(i);
        }
        for (int i = 0; i < size; i++) {
            if (intSet.contains(i)) {
                fail(i + " should be removed but was found");
            }
        }
    }

    @Test
    public void testAddNull() {
        set.add(null);
        assertEquals(0, set.size());
    }

    @Test
    public void testContainNull() {
        set.add(null);
        assertFalse(set.contains(null));
    }

    @Test
    public void testContainsNotObjectThatArentAdded() {
        set.add("ASDDSASD");
        assertFalse(set.contains("ASDRT%&¤%/(%"));
    }

    @Test
    public void testRemoveNullNoException() {
        set.remove(null);
    }

    @Test
    public void testAddRemoveSameHashObjects() {
        HashSet<hashSameButNotEquals> hs = new HashSet<>();
        hashSameButNotEquals first = new hashSameButNotEquals(23);
        hashSameButNotEquals second = new hashSameButNotEquals(44);
        hs.add(first);
        hs.add(second);
        hs.remove(first);
        hs.remove(second);
    }

    @Test
    public void testAddSameHashObjectsContains() {
        HashSet<hashSameButNotEquals> hs = new HashSet<>();
        hashSameButNotEquals first = new hashSameButNotEquals(23);
        hashSameButNotEquals second = new hashSameButNotEquals(44);
        hs.add(first);
        hs.add(second);
        assertTrue(hs.contains(first));
    }

    @Test
    public void testAddOneHashObjectSameHash() {
        HashSet<hashSameButNotEquals> hs = new HashSet<>();
        hashSameButNotEquals first = new hashSameButNotEquals(23);
        hashSameButNotEquals second = new hashSameButNotEquals(44);
        hs.add(first);
        assertFalse(hs.contains(second));
    }

    @Test
    public void testAddReplacePrevious() {
        HashSet<alwaysEquals> hs = new HashSet<>();
        final int firstNumber = 1;
        final int secondNumber = 2;
        alwaysEquals first = new alwaysEquals(firstNumber);
        alwaysEquals second = new alwaysEquals(secondNumber);
        hs.add(first);
        hs.add(second);
        assertEquals(secondNumber, hs.get(first).getNumber());
    }

    private final class hashSameButNotEquals {

        private final int number;

        public hashSameButNotEquals(final int number) {
            this.number = number;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null || !obj.getClass().equals(hashSameButNotEquals.class)) {
                return false;
            }
            return ((hashSameButNotEquals) obj).number == number;
        }

        @Override
        public int hashCode() {
            return 1;
        }
    }

    private final class alwaysEquals {

        private final int number;

        public alwaysEquals(final int number) {
            this.number = number;
        }

        public int getNumber() {
            return number;
        }

        @Override
        public boolean equals(Object obj) {
            return true;
        }

        @Override
        public int hashCode() {
            return 0;
        }
    }
}
