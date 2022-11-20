package cn.onenine.algorithms.linkedList;

/**
 * Description：自实现Linked List
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @since 2022/11/20 10:59
 */
public class LinkedList<E> {

    private Node<E> first;

    private Node<E> last;

    private int size;

    private static class Node<E> {
        E item;
        private Node<E> prev;
        private Node<E> next;

        public Node(Node<E> prev, E item, Node<E> next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    /**
     * 头插
     *
     * @param e
     */
    public void addFirst(E e) {
        final Node<E> f = first;
        //创建新的节点，由于是头结点，因此prev是null，next则是原来的头结点
        Node<E> newNode = new Node<E>(null, e, f);
        //当前节点变身头结点
        first = newNode;
        if (f == null) {
            //如果头结点为null，说明当前插入的是第一个元素，需要将当前节点同时设置为尾结点
            last = first;
        } else {
            //说明不是插入的第一个元素，需要将新节点设置为原来的头节点的prev
            f.prev = newNode;
        }
        size++;
    }

    /**
     * 尾插
     */
    public void addLast(E e) {
        Node<E> l = last;
        Node<E> newNode = new Node<E>(last, e, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    public boolean remove(Object o) {
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if(x.item == null){
                    unLink(x);
                    return true;
                }
            }
        }else {
            for (Node<E> x = first; x != null; x = x.next) {
                if(o.equals(x.item)){
                    unLink(x);
                    return true;
                }
            }
        }
        return false;
    }

    E unLink(Node<E> e) {
        final E element = e.item;
        Node<E> prev = e.prev;
        Node<E> next = e.next;
        if(prev == null){
            first = next;
        }else {
            prev.next = next;
            e.prev = null;
        }

        if(next == null){
            last = prev;
        }else {
            next.prev = prev;
            e.next = null;
        }

        size--;
        return element;
    }

    public void printList(){
        for (Node<E> x = first; x != null; x = x.next) {
            System.out.println(x.item);
        }
    }

}
