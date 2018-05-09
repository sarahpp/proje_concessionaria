/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import controle.ModuloConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Notebook
 */
public class TelaVeiculos extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public TelaVeiculos() {
        initComponents();
        conexao = ModuloConexao.conector();
    }
// metodo para adicionar veiculos

    private void adicionar() {
        String sql = "insert into tbveiculos (modelo, fabricante, cor, ano, preco, chassi) values (?, ?, ?, ?, ?, ?) ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtModelo.getText());
            pst.setString(2, txtFabricante.getText());
            pst.setString(3, txtCor.getText());
            pst.setString(4, txtAno.getText());
            pst.setString(5, txtPreco.getText());
            pst.setString(6, txtChassi.getText());

            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Veículo adicionado com sucesso!");
                txtModelo.setText(null);
                txtFabricante.setText(null);
                txtCor.setText(null);
                txtAno.setText(null);
                txtPreco.setText(null);
                txtChassi.setText(null);
            } else {
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtModelo = new javax.swing.JTextField();
        txtFabricante = new javax.swing.JTextField();
        txtCor = new javax.swing.JTextField();
        txtAno = new javax.swing.JTextField();
        txtPreco = new javax.swing.JTextField();
        txtChassi = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("S.M. - Configurações de Veículos");
        setPreferredSize(new java.awt.Dimension(640, 480));
        getContentPane().setLayout(null);

        jLabel1.setText("* Modelo do Veículo:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 120, 98, 14);

        jLabel2.setText("Fabricante:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(20, 180, 55, 14);

        jLabel3.setText("Cor do Veículo:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(20, 224, 72, 30);

        jLabel4.setText("Ano:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(300, 120, 23, 14);

        jLabel5.setText("Preço:");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(290, 180, 31, 14);

        jLabel6.setText("* Nº do Chassi:");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(260, 230, 80, 14);
        getContentPane().add(txtModelo);
        txtModelo.setBounds(120, 180, 130, 20);
        getContentPane().add(txtFabricante);
        txtFabricante.setBounds(120, 230, 130, 20);
        getContentPane().add(txtCor);
        txtCor.setBounds(120, 120, 130, 20);
        getContentPane().add(txtAno);
        txtAno.setBounds(340, 120, 120, 20);

        txtPreco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecoActionPerformed(evt);
            }
        });
        getContentPane().add(txtPreco);
        txtPreco.setBounds(340, 180, 120, 20);
        getContentPane().add(txtChassi);
        txtChassi.setBounds(340, 230, 120, 20);

        jButton1.setText("Salvar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(400, 300, 63, 23);

        jLabel7.setForeground(new java.awt.Color(204, 0, 0));
        jLabel7.setText("* Campos Obrigatórios");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(10, 40, 109, 14);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/9.jpg"))); // NOI18N
        jLabel8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 3, true));
        getContentPane().add(jLabel8);
        jLabel8.setBounds(0, 0, 500, 360);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPrecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        adicionar();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField txtAno;
    private javax.swing.JTextField txtChassi;
    private javax.swing.JTextField txtCor;
    private javax.swing.JTextField txtFabricante;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextField txtPreco;
    // End of variables declaration//GEN-END:variables
}
