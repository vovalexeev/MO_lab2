public class Main {

    static public double[][] A = {{1, 2, -1, 2, 4}, {0, -1, 2, 1, 3}, {1, -3, 2, 2, 0}};
    static public double[] b = {1, 3, 4};
    static public double[] c = {1, -3, 2, 1, 4};
    static double[][] d = {{0, 0, 1, 2, 3, 4, 5, 6, 7, 8},
            {6, b[0], A[0][0],  A[0][1], A[0][2], A[0][3], A[0][4], 1, 0, 0},
            {7, b[1], A[1][0],  A[1][1], A[1][2], A[1][3], A[1][4], 0, 1, 0},
            {8, b[2], A[2][0],  A[2][1], A[2][2], A[2][3], A[2][4], 0, 0, 1},
            {0, 0, -c[0],  -c[1], -c[2], -c[3], -c[4], 0, 0, 0}};
    static int count = 0;


    public static double function(double x1, double x2, double x3, double x4, double x5){
        return c[0] * x1 + c[1] * x2 + c[2] * x3 + c[3] * x4 + c[4] * x5;
    }

    public static void simplexMethod(double[][] d){

        if(count == 0){
            if(d[4][2] >= 0 && d[4][3] >= 0 && d[4][4] >= 0 && d[4][5] >= 0 && d[4][6] >= 0 && d[4][7] >= 0 && d[4][8] >= 0 && d[4][9] >= 0) {
                double[] coefficient = {0, 0, 0, 0, 0};
                for(int i = 1; i < 4; i++){
                    if(d[i][0] < 6){
                        coefficient[(int)d[i][0] - 1] = d[i][1];
                    }
                }
                System.out.println("x1 = " + coefficient[0] + " x2 = " + coefficient[1] +" x3 = " + coefficient[2] +" x4 = " + coefficient[3] +" x5 = " + coefficient[4]);
                System.out.println("Function(x1, x2, x3, x4, x5) = " + function(coefficient[0], coefficient[1], coefficient[2], coefficient[3], coefficient[4]));
            }
        }

        count++;
        int n = 2, k = 1;
        double min2 = d[4][2];
        double min;

        for(int i = 3; i < 10; i++){
            if(d[4][i] <= min2){
                min2 = d[4][i];
                n = i;
            }
        }


        if(d[k][1] > 0 && d[k][n] < 0){
            min = 10000000;
        }
        else{
            min = d[k][1] / d[k][n];
        }

        for(int i = 1; i < 4; i++){
            if(d[i][1] > 0 && d[i][n] < 0){
                continue;
            }
            else{
                if(d[i][1] / d[i][n] < min){
                    min = d[i][1] / d[i][n];
                    k = i;
                }
            }
        }

        // System.out.println(k +" " + n + " " + d[k][n]);
        double main = d[k][n];
        d[k][0] = d[0][n];

        for(int i = 1; i < 5; i++){
            if( i != k){
                for (int j = 1; j < 10; j++){
                    if(j != n){
                        d[i][j] -= d[k][j] * d[i][n] / main;
                    }
                }
            }
        }

        for(int i = 1; i < 10; i++){
            d[k][i] /= main;
        }

        for(int i = 1; i < 5; i++){
            if(i != k){
                d[i][n] = 0;
            }
        }

        if(d[4][2] < 0 || d[4][3] < 0 || d[4][4] < 0 || d[4][5] < 0 || d[4][6] < 0 || d[4][7] < 0 || d[4][8] < 0 || d[4][9] < 0) {
            simplexMethod(d);
        }
        else{
            double[] coefficient = {0, 0, 0, 0, 0};
            for(int i = 1; i < 4; i++){
                if(d[i][0] < 6){
                    coefficient[(int)d[i][0] - 1] = d[i][1];
                }
            }
            System.out.println("x1 = " + coefficient[0] + " x2 = " + coefficient[1] +" x3 = " + coefficient[2] +" x4 = " + coefficient[3] +" x5 = " + coefficient[4]);
            System.out.println("Function(x1, x2, x3, x4, x5) = " + function(coefficient[0], coefficient[1], coefficient[2], coefficient[3], coefficient[4]));
        }

        if (count == 4){
            for(int u = 0; u < 5; u++){
                for(int j = 0; j < 10; j++){
                    System.out.print(d[u][j] + " ");
                }
                System.out.println();
            }
            return;
        }
    }
    public static void main(String args[]){
        simplexMethod(d);
    }
}