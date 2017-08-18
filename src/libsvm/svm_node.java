package libsvm;
public class svm_node implements java.io.Serializable
{
	public int index;
	public double value;
     
        public svm_node copy(){
            svm_node theCopy = new svm_node();
            theCopy.index=index;
            theCopy.value = value;
            return(theCopy);
        }
        
        
}
