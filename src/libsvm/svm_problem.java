package libsvm;


public class svm_problem implements java.io.Serializable
{
	public int l;
	public double[] y;
	public svm_node[][] x;

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
