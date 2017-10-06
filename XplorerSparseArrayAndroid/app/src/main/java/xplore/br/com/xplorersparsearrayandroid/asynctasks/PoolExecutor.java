package xplore.br.com.xplorersparsearrayandroid.asynctasks;

import android.support.annotation.NonNull;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executor;

/**
 * Created by r028367 on 04/10/2017.
 */

public class PoolExecutor implements Executor {

    Queue<Executor> pool;

    public PoolExecutor() {
        pool = new LinkedList<>();
    }

    @Override
    public void execute(@NonNull Runnable runnable) {

    }
}
