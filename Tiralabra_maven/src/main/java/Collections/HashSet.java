package Collections;

/**
 * A set of items, provides average constant time operations for add, remove and
 * contains. Guaranteed to not contain nulls.
 *
 * @param <T> The member type.
 */
public final class HashSet<T> {

    private static final float fillRation = 0.5f;
    private static final int vectorMultiplier = 2;
    private Vector<LinkedList<T>> array;
    private int count;

    /**
     * Creates a new hashset with specified size.
     *
     * @param size The set size.
     */
    public HashSet(final int size) {
        this.array = createList(size);
    }

    /**
     * Creates a new hashset with default size.
     */
    public HashSet() {
        this(25);
    }

    /**
     * Adds a new item to the set.
     *
     * @param item The item to add.
     */
    public void add(final T item) {
        if (item == null) {
            return;
        }
        final int hash = hash(item);
        if (array.get(hash) == null) {
            array.setAtt(hash, new LinkedList<T>());
        }
        final LinkedList<T> list = array.get(hash);
        if (!list.contains(item)) {
            list.add(item);
            count++;
        }
        if (isOverFilled()) {
            reHash();
        }
    }

    private Vector<LinkedList<T>> createList(final int size) {
        return (Vector<LinkedList<T>>) new Vector<>(new LinkedList<T>().getClass(), size);
    }

    private boolean isOverFilled() {
        return count * 1f / array.size() > fillRation;
    }

    private void reHash() {
        final HashSet<T> newSet = new HashSet<>(array.size() * vectorMultiplier);
        for (int i = 0; i < array.size(); i++) {
            LinkedList<T> list = array.get(i);
            if (list != null) {
                for (final T item : list) {
                    newSet.add(item);
                }
            }
        }
        array = newSet.array;
        count = newSet.count;
    }

    private int hash(final Object item) {
        return item.hashCode() % array.size();
    }

    /**
     * Removes an item from the set.
     *
     * @param item Item to remove.
     */
    public void remove(final T item) {
        if (item == null) {
            return;
        }
        final int hash = hash(item);
        if (array.get(hash) == null) {
            return;
        }
        final LinkedList<T> list = array.get(hash);
        list.remove(item);
        if (list.size() == 0) {
            array.setAtt(hash, null);
        }
        count--;
    }

    /**
     * Does the item exist in the set?
     *
     * @param item The item to look.
     * @return Does the item exits.
     */
    public boolean contains(final T item) {
        if (item == null) {
            return false;
        }
        final int hash = hash(item);
        if (array.get(hash) == null) {
            return false;
        }
        final LinkedList<T> list = array.get(hash);
        return list.contains(item);
    }

    /**
     * The size of the set.
     *
     * @return The size of the set.
     */
    public int size() {
        return count;
    }
}