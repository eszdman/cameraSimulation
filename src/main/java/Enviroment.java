import java.util.Arrays;

public class Enviroment {
    double[] brightness;
    public Enviroment(int resolution){
        brightness = new double[resolution];
        for(int i =0; i<resolution;i++){
            brightness[i] = 0.5*(double)i/(resolution - 1.0);
        }
    }

    @Override
    public String toString() {
        return "Enviroment{" +
                "brightness=" + Arrays.toString(brightness) +
                '}';
    }
}
