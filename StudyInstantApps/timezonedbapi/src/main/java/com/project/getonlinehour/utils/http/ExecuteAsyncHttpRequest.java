package com.project.getonlinehour.utils.http;

/**
 * Created by r028367 on 09/06/2017.
 */

public interface ExecuteAsyncHttpRequest<T> {
    public T executeTask();
    public void executeAfterAsyncTask();
}
