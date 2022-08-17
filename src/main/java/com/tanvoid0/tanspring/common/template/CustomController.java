package com.tanvoid0.tanspring.common.template;

import java.util.List;

public interface CustomController<T, N, U> {
    public List<T> get();

    public T get(long id);

    public T add(N newVO);

    public T update(long id, U updateVO);

    public boolean delete(long id);
}
