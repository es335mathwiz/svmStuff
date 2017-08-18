/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libsvm;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author m1gsa00
 */
public class jobManager implements Callable<svm_model> {
    protected long timeout;
    protected TimeUnit timeUnit;
    protected Callable<svm_model> job;
    public jobManager(long timeout,TimeUnit timeUnit,Callable<svm_model> job){
      this.timeout=timeout;  this.timeUnit=timeUnit;this.job=job;
    }
    @Override
            public svm_model call(){
                svm_model result=new svm_model();
    ExecutorService exec=Executors.newSingleThreadExecutor();
    try {
        result = exec.submit(job).get(timeout,timeUnit);
    } catch (InterruptedException|ExecutionException|TimeoutException ee) {
        if(ee instanceof TimeoutException) {
        //    System.out.print("Timeout get for " + job.toString());
        } else {
    System.out.println("exception get for "+job.toString()+"  : " + ee.getMessage() );
}
    }
    exec.shutdown();
    return result;
            }   
}