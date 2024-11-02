package exchangecripto;
/**
 *
 * @author Arthur Leal, Felipe Brum
 */
import view.LoginFrame;
import view.HubFrame;

public class ExchangeCripto {
    public static void main(String[] args) {
        HubFrame hf = new HubFrame();
        LoginFrame lf = new LoginFrame(hf);
        
        lf.setVisible(true);
        
    }    
}
