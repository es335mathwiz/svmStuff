package libsvm;


public class svm_problem implements java.io.Serializable
{
	public int l;
	public double[] y;
	public svm_node[][] x;
        
public svm_problem copy(){
    svm_problem theCopy=new svm_problem();
    theCopy.l=l;
    theCopy.y=new double[y.length];
    System.arraycopy(y, 0,theCopy.y, 0, y.length);
    
        theCopy.x=new svm_node[x.length][x[0].length];
     int ii;
     for(ii=0;ii<x.length;ii++){
    System.arraycopy(x[ii],0,theCopy.x[ii],0,x[0].length);}
    return(theCopy);
}
    public int findMaxIndex() {
        int ii;
        int numObs = this.l;
        int max_index = 0;
        for (ii = 0; ii < numObs; ii++) {
            max_index = Math.max(max_index, this.x[ii].length);
        }
        return max_index;
    }
}
