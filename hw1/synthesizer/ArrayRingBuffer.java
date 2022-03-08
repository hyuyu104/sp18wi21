// TODO: Make sure to make this class a part of the synthesizer package
package synthesizer;

import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend synthesizer.AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new synthesizer.ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from synthesizer.AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        this.capacity = capacity;
        this.first = 0;
        this.last = 0;
        this.fillCount = 0;
        this.rb = (T[]) new Object[capacity];
    }

    /**
     * Check whether the new index equal to the capacity; if yes, reset
     * to zero.
     */
    private int indexRefactor(int index, int increment) {
        if (index + increment < this.capacity)
            return index + increment;
        return (index + increment) % this.capacity;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
        if (this.isFull())
            throw new RuntimeException("Ring buffer overflow");
        this.rb[this.last] = x;
        this.last = indexRefactor(this.last, 1);
        this.fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update
        if (this.isEmpty())
            throw new RuntimeException("Ring buffer underflow");
        T oldestItem = this.rb[this.first];
        this.rb[this.first] = null;
        this.first = indexRefactor(this.first, 1);
        this.fillCount -= 1;
        return oldestItem;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.
        return this.rb[this.first];
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.

    private class RingIterator implements Iterator<T>{
        private int position;
        private int size;
        public RingIterator(int size) {
            this.position = 0;
            this.size = size;
        }
        public boolean hasNext() {
            return this.position < this.size;
        }
        public T next() {
            T returnItem = rb[this.position];
            this.position = this.position + 1;
            return returnItem;
        }
    }

    public Iterator<T> iterator() {
        return new RingIterator(this.fillCount);
    }
}
