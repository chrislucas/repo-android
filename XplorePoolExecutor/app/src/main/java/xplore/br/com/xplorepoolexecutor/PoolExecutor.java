package xplore.br.com.xplorepoolexecutor;

import android.support.annotation.NonNull;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executor;

/**
 * Created by r028367 on 04/10/2017.
 */

public class PoolExecutor implements Executor {

    Queue<Runnable> queue;

    public PoolExecutor() {
        queue = new LinkedList<>();
    }

    @Override
    public void execute(@NonNull Runnable runnable) {
        queue.add(
            new Runnable() {
                @Override
                public void run() {

                }
            }
        );
    }
}
