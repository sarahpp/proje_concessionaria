/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import java.sql.*;
import controle.ModuloConexao;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Notebook
 */
public class TelaCliente extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaCliente
     */
    public TelaCliente() {
        initComponents();
        conexao = ModuloConexao.conector();

    }
// método para adicionar clientes

    private void adicionar() {
        String sql = "insert into tbclientes (nome, email, cpf, telefone, rg, cidade, estado, cnh) values (?,?,?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtCliNome.getText());
            pst.setString(2, txtCliEmail.getText());
            pst.setString(3, txtCliCPF.getText());
            pst.setString(4, txtCliTel.getText());
            pst.setString(5, txtCliRG.getText());
            pst.setString(6, txtCliCidade.getText());
            pst.setString(7, txtCliEstado.getText());
            pst.setString(8, txtCliCNH.getText());
// validação dos campos obrigatórios
            if ((txtCliNome.getText().isEmpty()) || (txtCliCPF.getText().isEmpty()) || txtCliRG.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
            } else {

// a estrutura abaixo é usada para confirmar a inserção dos dados na tabela
// a linha abaixo atualiza a tabela usuario com os dados do formulario
                int adicionado = pst.executeUpdate();
//a linha abaixo serve de apoio à logica
//System.out.println(adicionado);
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso.");

                    txtCliNome.setText(null);
                    txtCliEmail.setText(null);
                    txtCliRG.setText(null);
                    txtCliCPF.setText(null);
                    txtCliCidade.setText(null);
                    txtCliEstado.setText(null);
                    txtCliCNH.setText(null);
                    txtCliTel.setText(null);
                } else {
                }

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }
    }
// metodo que pesquisa clientes pelo nome com filtro

    private void pesquisar_cliente() {
        String sql = "select * from tbclientes where nome like ?";
        try {
            pst = conexao.prepareStatement(sql);
// passando o conteudo da caixa de pesquisa para o ?
// atençao ao % - continuação da String sql
            pst.setString(1, txtCliPesquisar.getText() + "%");
            rs = pst.executeQuery();
// a linha abaixa usa a biblioteca rs2xml.jar para preencher a tabela
            tblClientes.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }
    }
// metodo para settar os campos do formulário com o conteúdo da tabela

    public void setarcampos() {
        int settar = tblClientes.getSelectedRow();
        txtCliNome.setText((String) tblClientes.getModel().getValueAt(settar, 1));
        txtCliEmail.setText((String) tblClientes.getModel().getValueAt(settar, 2));
        txtCliCPF.setText((String) tblClientes.getModel().getValueAt(settar, 3));
        txtCliRG.setText((String) tblClientes.getModel().getValueAt(settar, 4));
        txtCliCidade.setText((String) tblClientes.getModel().getValueAt(settar, 5));
        txtCliEstado.setText((String) tblClientes.getModel().getValueAt(settar, 6));
        txtCliTel.setText((String) tblClientes.getModel().getValueAt(settar, 7));
        txtCliCNH.setText((String) tblClientes.getModel().getValueAt(settar, 8));
//a linha abaixo desabilita o botao adicionar
        btnAdicionar.setEnabled(false);
    }
// metodo para alterar dados do cliente

    private void alterar() {
        String sql = "update tbclientes set nome=?, email=?, cpf=?, telefone=?, rg=?, cidade=?, estado=?, cnh=? where idcliente =?";

        try {
            pst = conexao.prepareStatement(sql);

            // Variável que auxilia a pegar da tabela dinâmica o idcli do cliente e usar para realizar o update no banco de dados
            int setar = tblClientes.getSelectedRow();

            pst.setString(1, txtCliNome.getText());
            pst.setString(2, txtCliEmail.getText());
            pst.setString(3, txtCliCPF.getText());
            pst.setString(4, txtCliTel.getText());
            pst.setString(5, txtCliRG.getText());
            pst.setString(6, txtCliCidade.getText());
            pst.setString(7, txtCliEstado.getText());
            pst.setString(8, txtCliCNH.getText());
            pst.setString(9, tblClientes.getModel().getValueAt(setar, 0).toString());

            // txtCliNome.setText(tblClientes.getModel().getValueAt(setar, 1).toString());
            //txtCliNome.setText(tblClientes.getModel().getValueAt(setar, 1).toString());
            if (txtCliNome.getText().isEmpty() || txtCliTel.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios", "Atenção", JOptionPane.WARNING_MESSAGE);
            } else {

                // A linha abaixo executa o update no banco de dados
                // A linha abaixo é usada para confirmar a alteração dos dados na tabela
                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {

                    JOptionPane.showMessageDialog(null, "Dados do cliente alterados com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    // A linha abaixo deixa em branco os campos de texto na tabela cliente

                    txtCliNome.setText(null);
                    txtCliEmail.setText(null);
                    txtCliCPF.setText(null);
                    txtCliRG.setText(null);
                    txtCliCidade.setText(null);
                    txtCliEstado.setText(null);
                    txtCliTel.setText(null);
                    txtCliCNH.setText(null);
                    btnAdicionar.setEnabled(true);

                    pesquisar_cliente();

                }

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
// método que exclui um cliente

    private void remover() {
        //a estrutura abaixo confirma a remoção do cliente
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir esse cliente?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tbclientes where nome = ?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtCliNome.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso!");

                    txtCliNome.setText(null);
                    txtCliEmail.setText(null);
                    txtCliCPF.setText(null);
                    txtCliRG.setText(null);
                    txtCliCidade.setText(null);
                    txtCliEstado.setText(null);
                    txtCliTel.setText(null);
                    txtCliCNH.setText(null);
                    btnAdicionar.setEnabled(true);

                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);

            }

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtCliRG = new javax.swing.JTextField();
        txtCliCidade = new javax.swing.JTextField();
        txtCliEstado = new javax.swing.JTextField();
        txtCliCNH = new javax.swing.JTextField();
        txtCliNome = new javax.swing.JTextField();
        txtCliEmail = new javax.swing.JTextField();
        txtCliCPF = new javax.swing.JTextField();
        txtCliTel = new javax.swing.JTextField();
        btnAdicionar = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        txtCliPesquisar = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtCliId = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();

        jLabel5.setText("jLabel5");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Clientes");
        setPreferredSize(new java.awt.Dimension(640, 480));
        getContentPane().setLayout(null);

        jLabel1.setText("* Nome:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 240, 50, 14);

        jLabel2.setText("Email:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(20, 280, 28, 14);

        jLabel3.setText("* CPF:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(10, 320, 32, 14);

        jLabel4.setText("Telefone:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(10, 360, 50, 14);

        jLabel6.setText("* RG:");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(330, 200, 27, 14);

        jLabel7.setText("Cidade:");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(330, 240, 37, 14);

        jLabel8.setText("Estado:");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(323, 280, 40, 14);

        jLabel9.setText("CNH:");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(330, 320, 30, 14);
        getContentPane().add(txtCliRG);
        txtCliRG.setBounds(370, 200, 160, 20);
        getContentPane().add(txtCliCidade);
        txtCliCidade.setBounds(370, 240, 160, 20);
        getContentPane().add(txtCliEstado);
        txtCliEstado.setBounds(370, 280, 160, 20);
        getContentPane().add(txtCliCNH);
        txtCliCNH.setBounds(370, 320, 160, 20);

        txtCliNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCliNomeActionPerformed(evt);
            }
        });
        getContentPane().add(txtCliNome);
        txtCliNome.setBounds(80, 240, 210, 20);
        getContentPane().add(txtCliEmail);
        txtCliEmail.setBounds(80, 280, 210, 20);
        getContentPane().add(txtCliCPF);
        txtCliCPF.setBounds(80, 320, 210, 20);
        getContentPane().add(txtCliTel);
        txtCliTel.setBounds(80, 360, 210, 20);

        btnAdicionar.setText("Adicionar");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdicionar);
        btnAdicionar.setBounds(70, 410, 77, 23);

        btnAlterar.setText("Alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAlterar);
        btnAlterar.setBounds(250, 410, 65, 23);

        btnRemover.setText("Remover");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });
        getContentPane().add(btnRemover);
        btnRemover.setBounds(450, 410, 75, 23);

        txtCliPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCliPesquisarActionPerformed(evt);
            }
        });
        txtCliPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCliPesquisarKeyReleased(evt);
            }
        });
        getContentPane().add(txtCliPesquisar);
        txtCliPesquisar.setBounds(161, 11, 207, 20);

        jLabel11.setForeground(new java.awt.Color(255, 51, 0));
        jLabel11.setText("* Campos Obrigatórios");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(30, 170, 120, 14);

        jLabel10.setText("Pesquisar:");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(107, 14, 50, 14);

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblClientes);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(10, 42, 510, 91);

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel12.setText("Ou cadastre um novo cliente aqui:");
        getContentPane().add(jLabel12);
        jLabel12.setBounds(140, 144, 245, 22);

        jLabel13.setText("ID Cliente:");
        getContentPane().add(jLabel13);
        jLabel13.setBounds(10, 200, 51, 14);

        txtCliId.setEnabled(false);
        getContentPane().add(txtCliId);
        txtCliId.setBounds(80, 200, 150, 20);

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/9.jpg"))); // NOI18N
        getContentPane().add(jLabel14);
        jLabel14.setBounds(0, 0, 600, 450);

        setBounds(0, 0, 614, 478);
    }// </editor-fold>//GEN-END:initComponents

    private void txtCliNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCliNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCliNomeActionPerformed

    private void txtCliPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCliPesquisarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCliPesquisarActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
//metodo para adicionar clientes
        adicionar();
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void txtCliPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCliPesquisarKeyReleased
// o evento abaixo é do tipo "enquanto for digitando
        pesquisar_cliente();
    }//GEN-LAST:event_txtCliPesquisarKeyReleased

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
// evento que será usado para setar os campos da tabela clicando com o botao esquerdo do mouse
        setarcampos();
    }//GEN-LAST:event_tblClientesMouseClicked

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
//chamando o metodo para alterar clientes
        alterar();

    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        // TODO add your handling code here:
// o código abaixo chama o método para excluir clientes
        remover();
    }//GEN-LAST:event_btnRemoverActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtCliCNH;
    private javax.swing.JTextField txtCliCPF;
    private javax.swing.JTextField txtCliCidade;
    private javax.swing.JTextField txtCliEmail;
    private javax.swing.JTextField txtCliEstado;
    private javax.swing.JTextField txtCliId;
    private javax.swing.JTextField txtCliNome;
    private javax.swing.JTextField txtCliPesquisar;
    private javax.swing.JTextField txtCliRG;
    private javax.swing.JTextField txtCliTel;
    // End of variables declaration//GEN-END:variables
}
