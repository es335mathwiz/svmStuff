package libsvm;
public class svm_parameter implements Cloneable,java.io.Serializable
{
	/* svm_type */
	public static final int C_SVC = 0;
	public static final int NU_SVC = 1;
	public static final int ONE_CLASS = 2;
	public static final int EPSILON_SVR = 3;
	public static final int NU_SVR = 4;

	/* kernel_type */
	public static final int LINEAR = 0;
	public static final int POLY = 1;
	public static final int RBF = 2;
	public static final int SIGMOID = 3;
	public static final int PRECOMPUTED = 4;
	public static final int MYPRECOMPUTED = 5;

	public int svm_type;
	public int kernel_type;
	public int degree;	// for poly
	public double gamma;	// for poly/rbf/sigmoid
	public double coef0;	// for poly/sigmoid

	// these are for training only
	public double cache_size; // in MB
	public double eps;	// stopping criteria
	public double C;	// for C_SVC, EPSILON_SVR and NU_SVR
	public int nr_weight;		// for C_SVC
	public int[] weight_label;	// for C_SVC
	public double[] weight;		// for C_SVC
	public double nu;	// for NU_SVC, ONE_CLASS, and NU_SVR
	public double p;	// for EPSILON_SVR
	public int shrinking;	// use the shrinking heuristics
	public int probability; // do probability estimates
public svm_parameter copy(){
    svm_parameter theCopy = new svm_parameter();
    theCopy.svm_type=svm_type;
    theCopy.kernel_type=kernel_type;
    theCopy.degree=degree;
    theCopy.gamma=gamma;
    theCopy.coef0=coef0;
    theCopy.cache_size=cache_size;
    theCopy.eps=eps;
    theCopy.C=C;
    theCopy.nr_weight=nr_weight;
    theCopy.weight_label=new int[weight_label.length];
    System.arraycopy(weight_label,0,theCopy.weight_label,0,weight_label.length);
    theCopy.weight=new double[weight.length];
    System.arraycopy(weight,0,theCopy.weight,0,weight.length);
    theCopy.nu=nu;
    theCopy.p=p;
    theCopy.shrinking=shrinking;
    theCopy.probability=probability;
    return(theCopy);
}
	public Object clone() 
	{
		try 
		{
			return super.clone();
		} catch (CloneNotSupportedException e) 
		{
			return null;
		}
	}

}
