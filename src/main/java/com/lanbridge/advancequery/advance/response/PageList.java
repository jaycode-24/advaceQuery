package com.lanbridge.advancequery.advance.response;


import lombok.Data;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @Description 返回结果集
 * @Author wangcheng
 * @Date 2022/10/25 22:46
 */
@Data
public class PageList<T> implements List<T> {

    private List<T> resultSetList;

    private Integer totalCount;

    private Integer totalPage;

    public PageList(List<T> resultSetList, Integer totalCount, Integer totalPage) {
        this.resultSetList = resultSetList;
        this.totalCount = totalCount;
        this.totalPage = totalPage;
    }

    @Override
    public int size() {
        return resultSetList.size();
    }

    @Override
    public boolean isEmpty() {
        return resultSetList.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return resultSetList.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return resultSetList.listIterator();
    }

    @Override
    public Object[] toArray() {
        return resultSetList.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return resultSetList.toArray(a);
    }

    @Override
    public boolean add(T t) {
        return resultSetList.add(t);
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return resultSetList.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return resultSetList.addAll(index,c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return resultSetList.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return resultSetList.retainAll(c);
    }

    @Override
    public void clear() {
        resultSetList.clear();
    }

    @Override
    public T get(int index) {
        return resultSetList.get(index);
    }

    @Override
    public T set(int index, T element) {
        return resultSetList.set(index,element);
    }

    @Override
    public void add(int index, T element) {
        resultSetList.add(index,element);
    }

    @Override
    public T remove(int index) {
        return resultSetList.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return resultSetList.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return resultSetList.lastIndexOf(o);
    }

    @Override
    public ListIterator<T> listIterator() {
        return resultSetList.listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return resultSetList.listIterator(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return resultSetList.subList(fromIndex,toIndex);
    }
}
