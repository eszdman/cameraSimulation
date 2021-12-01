import java.util.Arrays;

public class Camera1D {
    int[] values;
    int resolution;
    int whiteLevel;
    int blackLevel;
    double gain;
    Enviroment currentEnvironment;
    public Camera1D(int resolution, int whiteLevel, int blackLevel){
        this.resolution = resolution;
        values = new int[resolution];
        this.whiteLevel = whiteLevel;
        this.blackLevel = blackLevel;
        gain = 1.0;
    }
    public void setGain(double gain){
        this.gain = Math.max(1.0,gain);
    }
    public void setEnvironment(Enviroment environment){
        this.currentEnvironment = environment;
    }
    public void shot(){
        for(int i =0; i<values.length;i++){
            values[i] = (int)(currentEnvironment.brightness[i]*whiteLevel*gain);
            values[i] = Math.max(Math.min(whiteLevel,values[i]),blackLevel);
        }
    }

    @Override
    public String toString() {
        return "Camera Output:"+Arrays.toString(values);
    }
}
