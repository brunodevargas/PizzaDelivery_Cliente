package principal;
import java.awt.EventQueue;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import model.Cliente_socket;
import view.Pagina_informacoes;
import view.Pagina_pedido;

public class Principal {

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }

        EventQueue.invokeLater(new Runnable() {
         public void run() {
            Pagina_informacoes frame = new Pagina_informacoes();
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setVisible(true);
    }
        });
        
        
    }
}
