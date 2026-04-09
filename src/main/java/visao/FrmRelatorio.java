package visao;

import dao.EmprestimoDAO;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 * Tela responsável por exibir o relatório textual de empréstimos.
 */
public class FrmRelatorio extends JFrame {

    // Área de texto usada para mostrar o relatório.
    private JTextArea areaRelatorio;

    /**
     * Creates new form FrmRelatorio.
     */
    public FrmRelatorio() {
        initComponents();
        carregarRelatorio();
    }

    /**
     * Método responsável por montar a interface da tela.
     */
    private void initComponents() {
        areaRelatorio = new JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Relatorio");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Define a área de texto como somente leitura.
        areaRelatorio.setEditable(false);
        add(new JScrollPane(areaRelatorio), BorderLayout.CENTER);
    }

    /**
     * Carrega o texto do relatório na tela.
     */
    private void carregarRelatorio() {
        EmprestimoDAO dao = new EmprestimoDAO();
        areaRelatorio.setText(dao.gerarResumoRelatorio());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FrmRelatorio().setVisible(true);
            }
        });
    }
}
