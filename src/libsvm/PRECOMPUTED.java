/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libsvm;

/**
 *
 * @author m1gsa00
 */
public abstract class PRECOMPUTED {
  public abstract  double [] testExpKern(double[] xx);
  public abstract  double [] testKern(double[] xx);
}