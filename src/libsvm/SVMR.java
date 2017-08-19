package libsvm;


import libsvm.trainGuts;
import libsvm.svm;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author m1gsa00
 */
public class SVMR {
trainGuts originalTG;   
double[] thePred;
double[] theRes; 
double[] theOSPred;
double[] theOSRes;
svm_model svmMod;

double[][][] hvTrXs;
double[][][] hvValXs;
double[][] hvTrYs;
double[][] hvValYs;

public SVMR(trainGuts  theTG) {
    originalTG=theTG;
}  
    
public double[] doSVMRLinear(double [] params,int maxTime){
  trainGuts tg = originalTG.copy();
tg.param.C=params[0];
tg.param.p=params[1];//epsilon value for regression
tg.param.kernel_type=0;//0linear, 1 poly, 2 rbf,3 sigmoid, 4 precomputed
tg.param.coef0=0;
tg.postProbProc();
svm anSVM = new svm();
int numObs=tg.prob.y.length;
svmMod=anSVM.svm_train(tg.prob,tg.param,maxTime);
int ii;
theRes = new double[numObs];
thePred = new double[numObs];
for(ii=0;ii<numObs;ii++){
    thePred[ii]=svm.svm_predict(svmMod,tg.prob.x[ii]);
    theRes[ii]=tg.prob.y[ii]-thePred[ii];
}
return(theRes);
        }
    
 
 public void readAllHVBlocksSplit(double [][][]trXs,double[][]trYs, double[][][]valXs, double[][] valYs){
     

hvTrXs=trXs;
hvValXs=valXs;
hvTrYs=trYs;
hvValYs=valYs;

 }
 

}