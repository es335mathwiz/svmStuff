//
// svm_model
//
package libsvm;

public class svm_model implements java.io.Serializable {

    public svm_parameter param;	// parameter
    public int nr_class;		// number of classes, = 2 in regression/one class svm
    public int l;
    public double [] ExpKernSV;
    public svm_node[][] SV;	// SVs (SV[l])
    public double[][] sv_coef;	// coefficients for SVs in decision functions (sv_coef[k-1][l])
    public double[] rho;		// constants in decision functions (rho[k*(k-1)/2])
    public double[] probA;         // pariwise probability information
    public double[] probB;
    public int[] sv_indices;       // sv_indices[0,...,nSV-1] are values in [1,...,num_traning_data] to indicate SVs in the training set
    public double[][] theValueMatrix;
    // for classification only
public int maxIndex;
    public int[] label;		// label of each class (label[k])
    public int[] nSV;		// number of SVs for each class (nSV[k])
    // nSV[0] + nSV[1] + ... + nSV[k-1] = l
    
public svm_model copy(){
  svm_model  theCopy =new svm_model();
  theCopy.param=param.copy();
  theCopy.nr_class=nr_class;
  theCopy.l=l;
  theCopy.ExpKernSV = new double[ExpKernSV.length];
  System.arraycopy(ExpKernSV,0,theCopy.ExpKernSV,0,ExpKernSV.length);
  theCopy.SV=new svm_node[SV.length][SV[0].length];
  int ii;
     for(ii=0;ii<SV.length;ii++){
    System.arraycopy(SV[ii],0,theCopy.SV[ii],0,SV[0].length);}
 theCopy.sv_coef=new double[sv_coef.length][sv_coef[0].length];
     for(ii=0;ii<sv_coef.length;ii++){
    System.arraycopy(sv_coef[ii],0,theCopy.sv_coef[ii],0,sv_coef[0].length);}
 theCopy.rho = new double[rho.length];
    System.arraycopy(rho,0,theCopy.rho,0,rho.length);
 theCopy.rho = new double[rho.length];
    System.arraycopy(rho,0,theCopy.rho,0,rho.length);
    theCopy.rho = new double[rho.length];
    System.arraycopy(rho,0,theCopy.rho,0,rho.length);
    theCopy.probA = new double[probA.length];
    System.arraycopy(probA,0,theCopy.probA,0,probA.length);
    theCopy.probB = new double[probB.length];
    System.arraycopy(probB,0,theCopy.probB,0,probB.length);
  theCopy.sv_indices = new int[sv_indices.length];
    System.arraycopy(sv_indices,0,theCopy.sv_indices,0,sv_indices.length);
    theCopy.theValueMatrix=new double[theValueMatrix.length][theValueMatrix[0].length];
        for(ii=0;ii<theValueMatrix.length;ii++){
    System.arraycopy(theValueMatrix[ii],0,theCopy.theValueMatrix[ii],0,theValueMatrix[0].length);}
  theCopy.maxIndex=maxIndex;
   theCopy.label = new int[label.length];
    System.arraycopy(label,0,theCopy.label,0,label.length);
     theCopy.nSV = new int[nSV.length];
    System.arraycopy(nSV,0,theCopy.nSV,0,nSV.length);
  return(theCopy);
}
    public void SVtoMatrices() {

        int ii, jj;int rows=SV.length;int cols=SV[0].length;
                theValueMatrix = new double[rows][maxIndex];
        for (ii = 0; ii < rows; ii++) {cols=SV[ii].length;
            for (jj = 0; jj < cols; jj++) {
                theValueMatrix[ii][SV[ii][jj].index-1] = SV[ii][jj].value;
            }
        }
    }
};
