import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int resolution = 32;
        Camera1D camera = new Camera1D(resolution,256,0);
        Enviroment enviroment = new Enviroment(resolution);
        camera.setEnvironment(enviroment);
        camera.setGain(1);
        camera.shot();
        System.out.println(camera);
        compareEnvArr(bracketing(camera),enviroment);
    }
    public static double[] readDoubleCamera(Camera1D camera){
        double[] output = new double[camera.resolution];
        for(int i =0; i<output.length;i++){
            output[i] = (camera.values[i]-camera.blackLevel)/(double)(camera.whiteLevel);
        }
        return output;
    }
    public static double[] bracketing(Camera1D camera){
        int[] buffer = new int[camera.resolution];
        double[] norm = new double[camera.resolution];
        int stepCount = 9;
        for(int step =0; step<stepCount;step++){
            double gain = Math.pow(2.0,step);
            System.out.println("gain:"+gain);
            camera.setGain(gain);
            camera.shot();
            for(int i =0; i<buffer.length;i++){
                int in = camera.values[i]-camera.blackLevel;
                double merge = 1.0;
                if((in == camera.whiteLevel || in == 0) && step != 0)
                    merge = 0.0;
                buffer[i] += in*merge;
                norm[i] += gain*merge;
                //if(i == camera.resolution-1) System.out.println("buff:"+buffer[i]+" norm:"+norm[i]);
            }
        }
        double[] output = new double[camera.resolution];
        for(int i =0; i<output.length;i++){
            output[i] = buffer[i];
            output[i] /= norm[i];
            output[i] /= (camera.whiteLevel-camera.blackLevel);
        }
        return output;
    }
    public static void compareEnvArr(double[] synthOutput, Enviroment enviroment){
        double error = 0.0;
        for(int i =0; i< synthOutput.length;i++){
            error += Math.abs(synthOutput[i]-enviroment.brightness[i]);
        }
        System.out.println("ErrValue:"+ error);
    }
}
