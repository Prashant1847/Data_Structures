# 🧠 Java Collections Framework (JCF) Overview

This repository provides a clear visual and conceptual understanding of the **Java Collections Framework (JCF)** — including its core interfaces, implementing classes, and hierarchy.

---

## 🔷 1. Overview

The **Java Collections Framework** provides a unified architecture for representing and manipulating collections — groups of objects such as lists, sets, and maps.  
It includes **interfaces**, **implementing classes**, and **algorithms** that simplify data handling with consistent and efficient data structures.

---


## 1. Java Collection interfaces and classes

```text
Iterable<E>
   └── Collection<E>

Collection<E>
├── List<E>
│     ├── ArrayList<E>
│     ├── LinkedList<E>
│     ├── Vector<E> (legacy)
│     │     └── Stack<E> (legacy)
|
│
├── Set<E>
│     ├── HashSet<E>
│     │     └── LinkedHashSet<E>
│     ├── TreeSet<E> (implements NavigableSet)
│     ├── EnumSet<E>
│     
│
└── Queue<E>
      ├── PriorityQueue<E>
      ├── ArrayDeque<E>
      ├── LinkedList<E> (also implements List)

```


### 🗂 Map Hierarchy (clean)

                          Map
                           ▲
                           │
         ┌─────────────────┴─────────────────┐
         │                                   │
       HashMap                            SortedMap
         │                                   │
         │                                   │
      LinkedHashMap                        TreeMap




## Iterable<E> — Root Interface

**What it is:**  
Root interface for all collections; enables sequential access to elements.

**Key method:**  
- `iterator()` → returns an `Iterator` to traverse elements.

**Other methods:**  
- `forEach()` → perform an action on each element using lambda.  
- `spliterator()` → supports parallel iteration.

**Why useful:**  
- Allows all collections to be used in **for-each loops** and standard iteration patterns.  
- Defines a contract for iteration without storing data.

**Example:**
```java
import java.util.*;

public class IterableExample {
    public static void main(String[] args) {
        List<String> list = List.of("A", "B", "C");

        // Using iterator
        Iterator<String> it = list.iterator();
        while(it.hasNext()) System.out.println(it.next());

        // Using for-each loop
        for(String s : list) System.out.println(s);
    }
}
```
## Collection<E> — Root of All Collections

**What it is:**  
`Collection<E>` extends `Iterable<E>` and is the **root interface for all standard Java collections** (`List`, `Set`, `Queue`). It defines **core operations** on groups of elements, providing a **uniform API** across different collection types.

**Key Methods:**  
- `add(E e)` → adds an element to the collection  
- `remove(Object o)` → removes an element  
- `size()` → returns the number of elements  
- `isEmpty()` → checks if the collection is empty  
- `contains(Object o)` → checks if an element exists  
- `iterator()` → inherited from `Iterable` for traversal  

**Why Useful:**  
- Provides **uniformity** — all collection types share the same set of core operations.  
- Enables **polymorphic code** — methods can accept any `Collection<E>` without knowing the concrete class.  
- Supports **generic algorithms** that work with any collection.

---

### Example
```java
import java.util.*;

public class CollectionExample {
    public static void main(String[] args) {
        Collection<String> coll = new ArrayList<>();

        // Add elements
        coll.add("Apple");
        coll.add("Banana");
        coll.add("Cherry");

        // Check size and existence
        System.out.println("Size: " + coll.size());
        System.out.println("Contains Banana? " + coll.contains("Banana"));

        // Remove an element
        coll.remove("Banana");

        // Iterate elements
        for(String s : coll) {
            System.out.println(s);
        }
    }
}
```

## List<E> — Ordered Collection with Positional Access

**What it is:**  
`List<E>` extends `Collection<E>` and represents an **ordered sequence of elements**. It allows **duplicate elements** and provides **positional access** to elements.

**Key Methods:**  

- **Access & Modification**
  - `get(int index)` → Retrieve element at a specific index.
  - `set(int index, E element)` → Replace element at a specific index.
  - `add(int index, E element)` → Insert element at a specific index.
  - `remove(int index)` → Remove element at a specific index.

- **Search**
  - `indexOf(Object o)` → Returns index of first occurrence of an element.
  - `lastIndexOf(Object o)` → Returns index of last occurrence of an element.


## ArrayList<E> — Internal Working & Add Methods

**What it is:**  
`ArrayList` is a **resizable array implementation** of the `List<E>` interface. It stores elements in a **contiguous array** internally and grows dynamically when more capacity is needed.

**Internal Working (Simplified):**  
- Uses a **backing array** (`elementData`) to store elements.  
- **`size`** tracks the number of elements currently in the list.  
- When adding a new element:
  1. If the array has space → element is inserted at the current `size` index.  
  2. If the array is full → a **new larger array** is allocated, old elements are **copied**, and the old array is discarded.  
- This allows `ArrayList` to **grow dynamically**, even though Java arrays are fixed in size.

**Add Methods:**
- `add(E e)` → Adds an element at the end of the list.  
- `add(int index, E element)` → Inserts an element at a specific index, shifting subsequent elements.


## LinkedList<E> — Quick Revision

**What it is:**  
`LinkedList<E>` is a **doubly-linked list** implementation of `List<E>`. Stores elements in **nodes** connected via `prev` and `next`, maintaining insertion order and allowing duplicates.

**Internal Working:**  
- Each element is a **Node**: `Node<E> { E data; Node<E> next; Node<E> prev; }`  
- `first` and `last` references allow efficient insertion/removal at ends.  
- No resizing is needed; adding/removing at ends is **O(1)**, accessing or inserting by index is **O(n)** due to traversal.

**Extra Methods beyond List:**  
- `addFirst(E e)`, `addLast(E e)`  
- `getFirst()`, `getLast()`  
- `removeFirst()`, `removeLast()`


## Vector<E> — Quick Revision

**What it is:**  
`Vector<E>` is a **resizable array** implementation from **early Java (legacy, pre-Collections Framework)**. Elements are stored in a **backing array** and it grows automatically when capacity is exceeded.

**Internal Working:**  
- Uses a **resizable array** internally.  
- Default growth: **doubles the current capacity** when full.  
- All methods are **synchronized**, making it **thread-safe** but slower than `ArrayList`.  

**Extra Methods beyond List:**  
- `addElement(E e)` → Adds element to the end (legacy method).  
- `elementAt(int index)` → Returns element at specified index.  
- `removeElement(Object o)` → Removes first occurrence of element.  
- `insertElementAt(E e, int index)` → Inserts element at a specific index.  

---

## Stack<E> — 

**What it is:**  
`Stack<E>` is a **legacy LIFO stack** implementation that **extends Vector**. Provides standard stack operations on top of the Vector array.

**Internal Working:**  
- Uses **Vector’s resizable array** internally.  
- Operations like `push`, `pop`, `peek` operate on the **end of the array** (top of stack).  
- Methods are **synchronized** due to Vector inheritance.  

**New Methods added by Stack:**  
- `push(E e)` → Adds element on top.  
- `pop()` → Removes and returns the top element.  
- `peek()` → Returns top element without removing.  
- `empty()` → Checks if stack is empty.  
- `search(Object o)` → Returns 1-based position from top of stack.



## Set<E> 

**What it is:**  
`Set<E>` is a **collection of unique elements**. It extends `Collection<E>` but **does not allow duplicates** and **does not provide positional access**.

**What it adds beyond Collection:**  
- **Uniqueness enforcement:** Automatically prevents duplicate elements.  
- No new methods are added; `Set` mainly **specializes Collection behavior** for uniqueness.



## Queue<E> — Quick Revision

**What it is:**  
`Queue<E>` is a **collection designed for holding elements prior to processing**, typically in **FIFO (first-in-first-out) order**. Extends `Collection<E>`.

**What it adds beyond Collection:**  
- `offer(E e)` → Adds element to the tail; returns false if insertion fails (safe alternative to `add`).  
- `poll()` → Removes and returns the head; returns null if empty (safe alternative to `remove`).  
- `remove()` → Removes and returns the head; throws exception if empty.  
- `peek()` → Returns the head without removing; returns null if empty.  
- `element()` → Returns the head without removing; throws exception if empty.

**One-liner why:**  
- Adds **head/tail-specific methods** for FIFO semantics and safe alternatives to exceptions.


## PriorityQueue<E> — Quick Revision

**What it is:**  
`PriorityQueue<E>` is a **queue that orders elements by priority** (natural ordering or a custom Comparator). Elements with highest priority are always at the head.

**Internal Working & Data Structure:**  
- Backed by a **binary heap**, which is implemented using a **resizable array**.  
- Copying of elements are done when array is full and replaced by new array by doubling the old capacity

**Time Complexity:**  
- Insertion and removal: **O(log n)** because of heap operations.  
- Peek (accessing head): **O(1)**.  


## LinkedList<E> as Queue — Quick Revision

**Internal Data Structure:**  
- Doubly-linked list: `Node<E> { E data; Node<E> next; Node<E> prev; }`  
- Maintains **first** and **last** references for efficient **head/tail insertion and removal**.  
- Insertion/removal at ends: **O(1)**.

**Queue-Specific Methods Added:**  
- `offer(E e)` → Adds element at the tail.  
- `poll()` → Removes and returns the head; returns null if empty.  
- `remove()` → Removes and returns the head; throws exception if empty.  
- `peek()` → Returns head without removing; returns null if empty.  
- `element()` → Returns head without removing; throws exception if empty.  


## ArrayDeque<E> — Quick Revision

**Internal Data Structure:**  
- Backed by a **resizable circular array**.  
- Maintains **head** and **tail** indices for O(1) insertion/removal at both ends.  
- Array is resized and elements copied when full.  
- Cache-friendly due to contiguous memory; better performance than LinkedList for end operations.

**Methods for Normal Queue Behavior (FIFO):**  
- `offer(E e)` → Adds element at tail; safe alternative to `add`.  
- `poll()` → Removes and returns head; safe alternative to `remove`.  
- `remove()` → Removes and returns head; throws exception if empty.  
- `peek()` → Returns head without removing; returns null if empty.  
- `element()` → Returns head without removing; throws exception if empty.  

**Methods for Deque Behavior (Double-Ended Queue):**  
- `addFirst(E e)`, `offerFirst(E e)` → Insert at head  
- `addLast(E e)`, `offerLast(E e)` → Insert at tail  
- `removeFirst()`, `pollFirst()`, `removeLast()`, `pollLast()` → Remove from head/tail  
- `peekFirst()`, `peekLast()` → View head/tail without removing  

**Methods for Stack-Like Behavior (LIFO):**  
- `push(E e)` → Same as `addFirst(E e)`  
- `pop()` → Same as `removeFirst()`  
- `peek()` → Same as `peekFirst()`  

**One-liner difference:**  
- `offer`/`poll` are **safe alternatives** to `add`/`remove` — they **return a status or null instead of throwing exceptions**.


## Map<K, V> — Quick Revision

**What it is:**  
`Map<K, V>` stores **key–value pairs** where **keys are unique** and values may repeat.  
Provides **fast key-based lookup, insertion, deletion, and updates**.  
(It does **not** extend `Collection` because it deals with pairs, not single elements.)

**Core Methods:**

- **Insert / Update**
  - `put(K key, V value)` → Adds or replaces a key–value pair.  
  - `putIfAbsent(K key, V value)` → Adds only if the key is not already present.

- **Access**
  - `get(Object key)` → Returns value for the key (null if missing).  
  - `getOrDefault(Object key, V defaultValue)` → Returns value or a default fallback.

- **Delete**
  - `remove(Object key)` → Removes entry by key.  
  - `remove(Object key, Object value)` → Removes only if key maps to the given value.

- **Check**
  - `containsKey(Object key)` → Key existence check.  
  - `containsValue(Object value)` → Value existence check.

- **Views**
  - `keySet()` → Returns a `Set` of keys.  
  - `values()` → Returns a `Collection` of values.  
  - `entrySet()` → Returns a `Set` of key–value pairs (`Map.Entry<K, V>`).

**One-liner what Map does:**  
Provides **unique-key storage with fast lookup** and convenient views (`keySet`, `values`, `entrySet`) for iteration.



## HashMap<K, V> — Quick Revision

**Internal Working:**  
- Backed by a **hash table**: `Node<K,V>[] table`, so uses and array internally for nodes storage where each bucket holds:
- Each `Node` stores: `hash`, `key`, `value`, and `next` (enabling linked lists).
- Before java 8 upto 8 nodes we use linked list, after java 8 above 8 nodes uses a balanced red-black tree which means searching O(0) to log(n)

**Flow of Insertion (put):**  
1. Compute the **hash** of the key using `hash()` (spreads bits to reduce collisions).  
2. Compute **bucket index**: `(hash & (capacity - 1))`.  
3. If bucket is **empty** → create node and insert.  
4. If key already exists in bucket → **update value**.  
5. If collision occurs → append to **linked list** or convert to **tree** if chain is long.  
6. If size exceeds `capacity × loadFactor (0.75)` → **resize** by doubling capacity and **rehashing** entries.


**Time Complexity:**  
- `put`, `get`, `remove` → **O(1)** average (hash-based).  
- Worst case → **O(log n)** with tree bins (or **O(n)** without treeification).



## LinkedHashMap<K, V> — Quick Revision

**What it is:**  
`LinkedHashMap` is a `HashMap` with a **doubly-linked list** added to every entry, preserving **insertion order** (or **access order** when enabled).  
It extends `HashMap`, reusing all hashing and bucket logic.

---

### Internal Working

- Uses the same **hash table** structure as `HashMap`:  
  `Node<K,V>[] table`  
- But each entry is a **LinkedHashMap.Entry**, which extends `HashMap.Node` and adds:  
  - `before` → previous entry in iteration order  
  - `after` → next entry in iteration order  
- These pointers form a **global doubly-linked list** connecting **all entries**, regardless of bucket.

Result:  
Two internal structures exist simultaneously:
1. **Hash bucket chain** (via `next`) → for O(1) lookups  
2. **Doubly-linked list** (via `before`/`after`) → for predictable iteration order  

---

### Flow of Insertion (put)

1. Compute **hash** and bucket index (same as HashMap).  
2. Insert entry into the correct bucket (handle collisions).  
3. Link the entry at the **end of the doubly-linked list**:  
   - `tail.after = newEntry`  
   - `newEntry.before = tail`  
   - `tail = newEntry`  
4. If `accessOrder = true`, a `get()` will also move that entry to the end (LRU-like behavior).  
5. Resizing happens exactly like HashMap (capacity doubles, entries rehashed).

---

### What LinkedHashMap Adds (beyond HashMap)

- **Ordering guarantee**:
  - Insertion order (default)  
  - Access order (`new Lin

### Time Complexity

- `put()` → **O(1)** average  
- `get()` → **O(1)** average  
- `remove()` → **O(1)** average  
- Iteration → **O(n)** but **ordered** (via linked list)  

**Reason:**  
Hash lookups are O(1), and linking/unlinking from the doubly-linked list is O(1).






