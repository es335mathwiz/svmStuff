/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author m1gsa00
 */
import java.io.IOException;
import libsvm.*;
public class Runsvm {
 public static void main(String argv[]) throws IOException
 {double[] trgt = new double[20];
    System.out.println("Runsvm:");
    trainGuts svmt= new trainGuts();
    svmt.input_file_name="/msu/home/m1gsa00/git/manchesterCS/61011/libsvm/heart_scale";
    svmt.read_problem();
    svm svmDoer = new svm();
    svm_model mod = svmDoer.svm_train(svmt.prob,svmt.param);
    mod.SVtoMatrices();
    double[][]xVals=
{{0.0493478, -0.0493478}, {0.0507249, -0.0507249}, {0.0489914, 
-0.00213031}, {0.047456, -0.047456}, {0.0483545, 
  0.0360249}, {0.0559741, 0.0634779}, {0.0500678, 
  0.057631}, {0.0497707, 0.0148758}, {0.0498304, 
  0.0279544}, {0.0493962, 0.0641766}, {0.0435397, 
  0.00327617}, {0.0406275, -0.0406275}, {0.0438465, -0.0438465}, 
{0.046158, -0.046158}, {0.047529, -0.047529}, {0.0455712, 
-0.0455712}, {0.0511321, -0.0511321}, {0.0563688, -0.0563688}, 
{0.0531343, -0.0531343}, {0.0570107, -0.0570107}};
double[]yVals={0.0376203, 0.0349057, 0.034414, 0.034392, 0.0389237, 0.0362779, 
0.0409276, 0.0425446, 0.0414093, 0.038497, 0.0392389, 0.0365843, 
0.0375253, 0.0361161, 0.0375617, 0.0383195, 0.0393241, 0.0411728, 
0.040942, 0.0432861};
double [] svmrArgs={1,.01};
svmt.mma_read_problemLinear(xVals, yVals,svmrArgs);
svmDoer=new svm();
mod=svmDoer.svm_train(svmt.prob,svmt.param);
double predRes=svm.svm_predict(mod,xVals[1]);
double [] svmrArgsPoly={1,.01};
svmt.mma_read_problemPoly(xVals, yVals,svmrArgs);
svmDoer=new svm();
mod=svmDoer.svm_train(svmt.prob,svmt.param);
double predRes=svm.svm_predict(mod,xVals[1]);
svm.svm_cross_validation(svmt.prob,svmt.param,3,trgt);
 }
    
}
