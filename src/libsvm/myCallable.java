/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libsvm;

import java.util.concurrent.Callable;
import static libsvm.svm.svm_train;

/**
 *
 * @author m1gsa00
 */
public class myCallable implements Callable<svm_model>{
    svm_problem theProb;svm_parameter theParam;
    public myCallable(svm_problem prob, svm_parameter param){
            this.theProb=prob;this.theParam=param;
    }
    @Override
    public svm_model call(){
       return(svm_train(theProb,theParam));
    }}