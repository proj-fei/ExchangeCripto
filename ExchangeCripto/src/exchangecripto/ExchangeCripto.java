package exchangecripto;
/**
 *
 * @author Arthur Leal, Felipe Brum
 */
import view.LoginFrame;
import view.CadastroFrame;
import view.HubFrame;

public class ExchangeCripto {
    public static void main(String[] args) {
        LoginFrame lf = new LoginFrame();
        lf.setVisible(true);
        
        CadastroFrame cf = new CadastroFrame();
        cf.setVisible(true);
        
        HubFrame hf = new HubFrame();
        hf.setVisible(true);
    }    
}
