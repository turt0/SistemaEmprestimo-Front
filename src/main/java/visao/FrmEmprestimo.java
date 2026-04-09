package visao;

import dao.AmigoDAO;
import dao.EmprestimoDAO;
import dao.FerramentaDAO;
import modelo.Amigo;
import modelo.Emprestimo;
import modelo.Ferramenta;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

/**
 * Tela responsável por cadastrar e registrar devoluções de empréstimos.
 */
public class FrmEmprestimo extends javax.swing.JFrame {

    private List<Amigo> amigos;

    /**
     * Creates new form FrmEmprestimo.
     */
    public FrmEmprestimo() {
        initComponents();
        loadAmigos();
        loadFerramentas();
        btnDelete.setText("Registrar devolução");

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEmprestimo(e);
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteEmprestimo(e);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblDataDevolucao = new javax.swing.JLabel();
        lblAmigo = new javax.swing.JLabel();
        lblFerramenta = new javax.swing.JLabel();
        txtDataEmprestimo = new javax.swing.JTextField();
        txtDataDevolucao = new javax.swing.JTextField();
        cmbAmigo = new javax.swing.JComboBox();
        cmbFerramenta = new javax.swing.JComboBox<String>();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        lblDataEmprestimo = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Realizar Empréstimo");

        lblDataDevolucao.setText("Data de Devolução:");

        lblAmigo.setText("Amigo:");

        lblFerramenta.setText("Ferramenta:");

        txtDataEmprestimo.setText("Data emprestimo");
        txtDataEmprestimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDataEmprestimoActionPerformed(evt);
            }
        });

        txtDataDevolucao.setText("Data devolucao");

        cmbAmigo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbAmigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAmigoActionPerformed(evt);
            }
        });

        cmbFerramenta.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnAdd.setText("Adicionar");

        btnDelete.setText("Deletar");

        lblDataEmprestimo.setText("Data de Empréstimo:");

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jTextField1.setEditable(false);
        jTextField1.setText("dia/mês/ano");

        jTextField2.setEditable(false);
        jTextField2.setText("dia/mês/ano");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDataEmprestimo)
                    .addComponent(txtDataEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDataDevolucao)
                        .addGap(58, 58, 58))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtDataDevolucao)
                        .addGap(33, 33, 33)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAmigo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbFerramenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFerramenta))
                .addGap(40, 40, 40))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(btnAdd)
                        .addGap(69, 69, 69)
                        .addComponent(btnCancelar)
                        .addGap(74, 74, 74)
                        .addComponent(btnDelete))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDataEmprestimo)
                    .addComponent(lblDataDevolucao)
                    .addComponent(lblAmigo)
                    .addComponent(lblFerramenta))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDataDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDataEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbFerramenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnDelete)
                    .addComponent(btnCancelar))
                .addGap(33, 33, 33))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDataEmprestimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataEmprestimoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataEmprestimoActionPerformed

    private void cmbAmigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAmigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbAmigoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
         dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    /**
     * Carrega os amigos no combo box.
     */
    private void loadAmigos() {
        AmigoDAO amigoDAO = new AmigoDAO();
        amigos = amigoDAO.getMinhaLista();
        cmbAmigo.removeAllItems();
        for (Amigo amigo : amigos) {
            cmbAmigo.addItem(amigo.getNome());
        }
    }

    /**
     * Carrega as ferramentas no combo box.
     */
    private void loadFerramentas() {
        FerramentaDAO ferramentaDAO = new FerramentaDAO();
        List<Ferramenta> ferramentas = ferramentaDAO.getMinhaLista();
        cmbFerramenta.removeAllItems();
        for (Ferramenta ferramenta : ferramentas) {
            cmbFerramenta.addItem(ferramenta.getNome());
        }
    }

    /**
     * Adiciona um novo empréstimo no banco.
     */
    private void addEmprestimo(java.awt.event.ActionEvent evt) {
        String dataEmprestimoText = txtDataEmprestimo.getText();
        String dataDevolucaoText = txtDataDevolucao.getText();
        String nomeAmigoSelecionado = (String) cmbAmigo.getSelectedItem();
        String nomeFerramentaSelecionada = (String) cmbFerramenta.getSelectedItem();

        if (dataEmprestimoText.isEmpty() || dataDevolucaoText.isEmpty() || nomeAmigoSelecionado == null || nomeFerramentaSelecionada == null) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Date dataEmprestimo;
        Date dataDevolucao;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            dataEmprestimo = sdf.parse(dataEmprestimoText);
            dataDevolucao = sdf.parse(dataDevolucaoText);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Formato de data inválido. Use dd/MM/yyyy.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Procura o amigo selecionado no combo box.
        Amigo amigoSelecionado = null;
        for (Amigo amigo : amigos) {
            if (amigo.getNome().equals(nomeAmigoSelecionado)) {
                amigoSelecionado = amigo;
                break;
            }
        }

        if (amigoSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Amigo não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Procura a ferramenta selecionada no combo box.
        Ferramenta ferramentaSelecionada = null;
        FerramentaDAO ferramentaDAO = new FerramentaDAO();
        List<Ferramenta> ferramentas = ferramentaDAO.getMinhaLista();
        for (Ferramenta ferramenta : ferramentas) {
            if (ferramenta.getNome().equals(nomeFerramentaSelecionada)) {
                ferramentaSelecionada = ferramenta;
                break;
            }
        }

        if (ferramentaSelecionada == null) {
            JOptionPane.showMessageDialog(this, "Ferramenta não encontrada.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Cria o objeto de empréstimo com devolução real nula.
        Emprestimo emprestimo = new Emprestimo(0, dataEmprestimo, dataDevolucao, null, amigoSelecionado, ferramentaSelecionada);
        EmprestimoDAO dao = new EmprestimoDAO();
        boolean sucesso = dao.insertEmprestimoBD(emprestimo);

        if (sucesso) {
            JOptionPane.showMessageDialog(this, "Empréstimo adicionado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            txtDataEmprestimo.setText("");
            txtDataDevolucao.setText("");
            cmbAmigo.setSelectedIndex(-1);
            cmbFerramenta.setSelectedIndex(-1);
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar empréstimo.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Registra a devolução de um empréstimo pelo ID.
     */
    private void deleteEmprestimo(java.awt.event.ActionEvent evt) {
        String idText = JOptionPane.showInputDialog(this, "Digite o ID do empréstimo a ser devolvido:");
        if (idText == null || idText.isEmpty()) {
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        EmprestimoDAO dao = new EmprestimoDAO();
        boolean sucesso = dao.registrarDevolucaoBD(id);

        if (sucesso) {
            JOptionPane.showMessageDialog(this, "Devolução registrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao registrar devolução.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmEmprestimo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnDelete;
    private javax.swing.JComboBox cmbAmigo;
    private javax.swing.JComboBox<String> cmbFerramenta;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel lblAmigo;
    private javax.swing.JLabel lblDataDevolucao;
    private javax.swing.JLabel lblDataEmprestimo;
    private javax.swing.JLabel lblFerramenta;
    private javax.swing.JTextField txtDataDevolucao;
    private javax.swing.JTextField txtDataEmprestimo;
    // End of variables declaration//GEN-END:variables
}
