package libsvm;

import java.io.*;
import java.util.*;

public class trainGuts {

    public svm_parameter param;		// set by parse_command_line
    public svm_problem prob;		// set by read_problem
    public svm_model model;
    public String input_file_name;		// set by parse_command_line
    public String model_file_name;		// set by parse_command_line
    private String error_msg;
    private int cross_validation;
    private int nr_fold;

    private static double atof(String s) {
        double d = Double.valueOf(s).doubleValue();
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            System.err.print("NaN or Infinity in input\n");
            System.exit(1);
        }
        return (d);
    }

    private static int atoi(String s) {
        return Integer.parseInt(s);
    }

    private void setDefaults() {

        //was parse_command_line
        param = new svm_parameter();
        // default values
        param.svm_type = svm_parameter.C_SVC;
        param.kernel_type = svm_parameter.RBF;
        param.degree = 3;
        param.gamma = 0;	// 1/num_features
        param.coef0 = 0;
        param.nu = 0.5;
        param.cache_size = 100;
        param.C = 1;
        param.eps = 1e-3;
        param.p = 0.1;
        param.shrinking = 1;
        param.probability = 0;
        param.nr_weight = 0;
        param.weight_label = new int[0];
        param.weight = new double[0];
        cross_validation = 0;
    }

    public void read_problem() throws IOException {

        setDefaults();

        prob = oldReadProb();

        postProbProc();
    }

public void mma_read_problemLinear(double[][] xVals, double[] yVals,double [] CEps) throws IOException {

        setDefaults();
param.svm_type=svm_parameter.EPSILON_SVR;
param.C=CEps[0];
param.p=CEps[1];//epsilon value for regression
param.kernel_type=0;//0linear, 1 poly, 2 rbf,3 sigmoid, 4 precomputed
param.coef0=0;
        prob = mmaReadProb(xVals,yVals);

        postProbProc();
    }

public void mma_read_problemPoly(double[][] xVals, double[] yVals,double [] CEps) throws IOException {

        setDefaults();
param.svm_type=svm_parameter.EPSILON_SVR;
param.C=CEps[0];
param.p=CEps[1];//epsilon value for regression
param.kernel_type=1;//0linear, 1 poly, 2 rbf,3 sigmoid, 4 precomputed
param.gamma=CEps[2];
param.coef0=CEps[3];
param.degree=(int)Math.round(CEps[4]);
        prob = mmaReadProb(xVals,yVals);

        postProbProc();
    }

public void mma_read_problemRBF(double[][] xVals, double[] yVals,double [] CEps) throws IOException {

        setDefaults();
param.svm_type=svm_parameter.EPSILON_SVR;
param.C=CEps[0];
param.p=CEps[1];//epsilon value for regression
param.kernel_type=2;//0linear, 1 poly, 2 rbf,3 sigmoid, 4 precomputed
param.gamma=CEps[2];

        prob = mmaReadProb(xVals,yVals);

        postProbProc();
    }

public void mma_read_problemSigmoid(double[][] xVals, double[] yVals,double [] CEps) throws IOException {

        setDefaults();
param.svm_type=svm_parameter.EPSILON_SVR;
param.C=CEps[0];
param.p=CEps[1];//epsilon value for regression
param.kernel_type=3;//0linear, 1 poly, 2 rbf,3 sigmoid, 4 precomputed
param.gamma=CEps[2];
param.coef0=CEps[3];
        prob = mmaReadProb(xVals,yVals);

        postProbProc();
    }

    private void postProbProc() {
        int max_index = prob.findMaxIndex();
        if (param.gamma == 0 && max_index > 0) {
            param.gamma = 1.0 / max_index;
        }

        if (param.kernel_type == svm_parameter.PRECOMPUTED) {
            for (int i = 0; i < prob.l; i++) {
                if (prob.x[i][0].index != 0) {
                    System.err.print("Wrong kernel matrix: first column must be 0:sample_serial_number\n");
                    System.exit(1);
                }
                if ((int) prob.x[i][0].value <= 0 || (int) prob.x[i][0].value > max_index) {
                    System.err.print("Wrong input format: sample_serial_number out of range\n");
                    System.exit(1);
                }
            }
        }
    }


    public svm_problem oldReadProb() throws FileNotFoundException, IOException {
        BufferedReader fp = new BufferedReader(new FileReader(input_file_name));
        Vector<Double> vy = new Vector<Double>();
        Vector<svm_node[]> vx = new Vector<svm_node[]>();
        int max_index = 0;

        while (true) {
            String line = fp.readLine();
            if (line == null) {
                break;
            }

            StringTokenizer st = new StringTokenizer(line, " \t\n\r\f:");

            vy.addElement(atof(st.nextToken()));
            int m = st.countTokens() / 2;
            svm_node[] x = new svm_node[m];
            for (int j = 0; j < m; j++) {
                x[j] = new svm_node();
                x[j].index = atoi(st.nextToken());
                x[j].value = atof(st.nextToken());
            }
            vx.addElement(x);
        }

        fp.close();

        prob = probDef(vx, vy);
        return (prob);

    }

    private svm_problem probDef(Vector<svm_node[]> vx, Vector<Double> vy) {

        prob = new svm_problem();
        prob.l = vy.size();
        prob.x = new svm_node[prob.l][];
        for (int i = 0; i < prob.l; i++) {
            prob.x[i] = vx.elementAt(i);
        }
        prob.y = new double[prob.l];
        for (int i = 0; i < prob.l; i++) {
            prob.y[i] = vy.elementAt(i);
        }
        return (prob);
    }

    public svm_problem mmaReadProb(double[][] xVals, double[] yVals) {
        Vector<Double> vy = new Vector<Double>();
        Vector<svm_node[]> vx = new Vector<svm_node[]>();
        int ii, jj;
        int numObs = yVals.length;
        int numFeatures = xVals[0].length;
        for (ii = 0; ii < numObs; ii++) {
            vy.addElement(yVals[ii]);
        svm_node[] xx = new svm_node[numFeatures];
        for (jj = 0; jj < numFeatures; jj++) {
            xx[jj] = new svm_node();
            xx[jj].index = jj+1;
            xx[jj].value = xVals[ii][jj];
        }          vx.addElement(xx);}
        prob = probDef(vx, vy);
        return (prob);
    }

}
