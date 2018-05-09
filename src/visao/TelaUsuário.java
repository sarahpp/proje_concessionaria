/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import java.sql.*;
import controle.ModuloConexao;
import javax.swing.JOptionPane;

/**
 *
 * @author Notebook
 */
public class TelaUsuário extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public TelaUsuário() {
        initComponents();
        conexao = ModuloConexao.conector();

    }
//método para consultar funcionários

    private void consultar() {
        String sql = "select * from tbusuarios where login=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuLogin.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                txtUsuNome.setText(rs.getString(4));
                cboUsuPerfil.setSelectedItem(rs.getString(3));
                txtUsuSenha.setText(rs.getString(2));
                txtUsuEmail.setText(rs.getString(5));
                txtUsuTel.setText(rs.getString(6));
                txtUsuEnd.setText(rs.getString(7));
                txtUsuCPF.setText(rs.getString(8));
                txtUsuPIS.setText(rs.getString(9));
                txtUsuRG.setText(rs.getString(10));

            } else {
                JOptionPane.showMessageDialog(null, "Usuário não cadastrado!");
                txtUsuLogin.setText(null);
                txtUsuNome.setText(null);
                txtUsuTel.setText(null);
                txtUsuSenha.setText(null);
                txtUsuCPF.setText(null);
                txtUsuEmail.setText(null);
                txtUsuRG.setText(null);
                txtUsuPIS.setText(null);
                txtUsuEnd.setText(null);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
// metodo para adicionar funcionarios

    private void adicionar() {
        String sql = "insert into tbusuarios (login, senha, perfil, nomeusu, emailusu, telefoneusu, enderecousu, cpfusu, numepis, rgusu) values (?, ?, ?,?,?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuLogin.getText());
            pst.setString(2, txtUsuSenha.getText());
            pst.setString(3, (String) cboUsuPerfil.getSelectedItem());
            pst.setString(4, txtUsuNome.getText());
            pst.setString(5, txtUsuEmail.getText());
            pst.setString(6, txtUsuTel.getText());
            pst.setString(7, txtUsuEnd.getText());
            pst.setString(8, txtUsuCPF.getText());
            pst.setString(9, txtUsuPIS.getText());
            pst.setString(10, txtUsuRG.getText());
// validação dos campos obrigatórios
            if ((txtUsuLogin.getText().isEmpty()) || (txtUsuNome.getText().isEmpty()) || txtUsuSenha.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
            } else {

// a estrutura abaixo é usada para confirmar a inserção dos dados na tabela
// a linha abaixo atualiza a tabela usuario com os dados do formulario
                int adicionado = pst.executeUpdate();
//a linha abaixo serve de apoio à logica
//System.out.println(adicionado);
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário adicionado com sucesso.");
                    txtUsuNome.setText(null);
                    txtUsuTel.setText(null);
                    txtUsuSenha.setText(null);
                    txtUsuCPF.setText(null);
                    txtUsuEmail.setText(null);
                    txtUsuRG.setText(null);
                    txtUsuPIS.setText(null);
                    txtUsuEnd.setText(null);
                } else {
                }

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }
    }
// metodo para alterar dados do usuario

    private void alterar() {
        String sql = "update tbusuarios set senha = ?, perfil= ?, nomeusu = ?, emailusu= ?, telefoneusu= ?, enderecousu= ?, cpfusu= ?, numepis= ?, rgusu= ? where login = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuSenha.getText());
            pst.setString(2, cboUsuPerfil.getSelectedItem().toString());
            pst.setString(3, txtUsuNome.getText());
            pst.setString(4, txtUsuEmail.getText());
            pst.setString(5, txtUsuTel.getText());
            pst.setString(6, txtUsuEnd.getText());
            pst.setString(7, txtUsuCPF.getText());
            pst.setString(8, txtUsuPIS.getText());
            pst.setString(9, txtUsuRG.getText());
            pst.setString(10, txtUsuLogin.getText());
            if ((txtUsuLogin.getText().isEmpty()) || (txtUsuNome.getText().isEmpty()) || txtUsuSenha.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
            } else {

// a estrutura abaixo é usada para confirmar a alteração dos dados na tabela
// a linha abaixo atualiza a tabela usuario com os dados do formulario
                int adicionado = pst.executeUpdate();
//a linha abaixo serve de apoio à logica
//System.out.println(adicionado);
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do usuário modificados com sucesso.");
                    txtUsuNome.setText(null);
                    txtUsuTel.setText(null);
                    txtUsuSenha.setText(null);
                    txtUsuCPF.setText(null);
                    txtUsuEmail.setText(null);
                    txtUsuRG.setText(null);
                    txtUsuPIS.setText(null);
                    txtUsuEnd.setText(null);
                } else {
                }

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
//método responsável pela remoção de usuários

    private void remover() {
        // a estrutra abaixo confirma a remoção do usuário
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este usuário?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tbusuarios where login=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtUsuLogin.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0){
                    JOptionPane.showMessageDialog(null, "Usuário removido com sucesso!");
                    txtUsuNome.setText(null);
                    txtUsuTel.setText(null);
                    txtUsuSenha.setText(null);
                    txtUsuCPF.setText(null);
                    txtUsuEmail.setText(null);
                    txtUsuRG.setText(null);
                    txtUsuPIS.setText(null);
                    txtUsuEnd.setText(null);

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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtUsuLogin = new javax.swing.JTextField();
        txtUsuTel = new javax.swing.JTextField();
        txtUsuNome = new javax.swing.JTextField();
        txtUsuCPF = new javax.swing.JTextField();
        txtUsuEmail = new javax.swing.JTextField();
        txtUsuRG = new javax.swing.JTextField();
        txtUsuPIS = new javax.swing.JTextField();
        txtUsuEnd = new javax.swing.JTextField();
        txtUsuSenha = new javax.swing.JTextField();
        cboUsuPerfil = new javax.swing.JComboBox<>();
        btnUsuCreate = new javax.swing.JButton();
        btnUsuRead = new javax.swing.JButton();
        btnUsuUpdate = new javax.swing.JButton();
        btnUsuDelete = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("S.M. - Configuração de Usuários");
        setPreferredSize(new java.awt.Dimension(640, 480));
        getContentPane().setLayout(null);

        jLabel1.setText("Login *:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 110, 38, 14);

        jLabel2.setText("Senha*:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(10, 150, 40, 14);

        jLabel3.setText("Perfil*:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(10, 190, 34, 14);

        jLabel4.setText("Nome* :");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(10, 230, 40, 14);

        jLabel5.setText("Email:");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(10, 270, 28, 14);

        jLabel6.setText("CPF:");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(10, 310, 23, 14);

        jLabel7.setText("Telefone:");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(10, 344, 50, 20);

        jLabel8.setText("RG:");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(360, 230, 18, 14);

        jLabel9.setText("PIS:");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(360, 270, 20, 14);

        jLabel10.setText("Endereço:");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(340, 320, 50, 14);

        txtUsuLogin.setAutoscrolls(false);
        getContentPane().add(txtUsuLogin);
        txtUsuLogin.setBounds(60, 110, 260, 20);

        txtUsuTel.setAutoscrolls(false);
        getContentPane().add(txtUsuTel);
        txtUsuTel.setBounds(62, 340, 200, 20);

        txtUsuNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuNomeActionPerformed(evt);
            }
        });
        getContentPane().add(txtUsuNome);
        txtUsuNome.setBounds(60, 230, 270, 20);

        txtUsuCPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuCPFActionPerformed(evt);
            }
        });
        getContentPane().add(txtUsuCPF);
        txtUsuCPF.setBounds(62, 300, 200, 20);

        txtUsuEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuEmailActionPerformed(evt);
            }
        });
        getContentPane().add(txtUsuEmail);
        txtUsuEmail.setBounds(62, 270, 270, 20);
        getContentPane().add(txtUsuRG);
        txtUsuRG.setBounds(390, 230, 180, 20);
        getContentPane().add(txtUsuPIS);
        txtUsuPIS.setBounds(390, 270, 180, 20);

        txtUsuEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuEndActionPerformed(evt);
            }
        });
        getContentPane().add(txtUsuEnd);
        txtUsuEnd.setBounds(390, 320, 180, 20);
        getContentPane().add(txtUsuSenha);
        txtUsuSenha.setBounds(60, 150, 180, 20);

        cboUsuPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "user" }));
        cboUsuPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboUsuPerfilActionPerformed(evt);
            }
        });
        getContentPane().add(cboUsuPerfil);
        cboUsuPerfil.setBounds(60, 190, 53, 20);

        btnUsuCreate.setText("Adicionar");
        btnUsuCreate.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, java.awt.Color.gray, java.awt.Color.gray));
        btnUsuCreate.setContentAreaFilled(false);
        btnUsuCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuCreateActionPerformed(evt);
            }
        });
        getContentPane().add(btnUsuCreate);
        btnUsuCreate.setBounds(20, 399, 60, 20);

        btnUsuRead.setText("Visualizar");
        btnUsuRead.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuReadActionPerformed(evt);
            }
        });
        getContentPane().add(btnUsuRead);
        btnUsuRead.setBounds(150, 400, 77, 23);

        btnUsuUpdate.setText("Alterar");
        btnUsuUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuUpdateActionPerformed(evt);
            }
        });
        getContentPane().add(btnUsuUpdate);
        btnUsuUpdate.setBounds(320, 400, 77, 23);

        btnUsuDelete.setText("Remover");
        btnUsuDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuDeleteActionPerformed(evt);
            }
        });
        getContentPane().add(btnUsuDelete);
        btnUsuDelete.setBounds(470, 400, 77, 23);

        jLabel11.setForeground(new java.awt.Color(204, 0, 0));
        jLabel11.setText("* Campos Obrigatórios");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(20, 50, 109, 14);

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/9.jpg"))); // NOI18N
        jLabel12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 3, true));
        getContentPane().add(jLabel12);
        jLabel12.setBounds(0, 2, 600, 454);

        setBounds(0, 0, 616, 480);
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuNomeActionPerformed

    private void txtUsuEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuEmailActionPerformed

    private void cboUsuPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboUsuPerfilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboUsuPerfilActionPerformed

    private void txtUsuCPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuCPFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuCPFActionPerformed

    private void btnUsuReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuReadActionPerformed
        // TODO add your handling code here:
        consultar();
    }//GEN-LAST:event_btnUsuReadActionPerformed

    private void btnUsuCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuCreateActionPerformed
        // TODO add your handling code here:
        //chamando o metodo adicionar
        adicionar();

    }//GEN-LAST:event_btnUsuCreateActionPerformed

    private void btnUsuUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuUpdateActionPerformed
        // TODO add your handling code here:
        alterar();
    }//GEN-LAST:event_btnUsuUpdateActionPerformed

    private void btnUsuDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuDeleteActionPerformed
        // TODO add your handling code here:
        remover();
        
    }//GEN-LAST:event_btnUsuDeleteActionPerformed

    private void txtUsuEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuEndActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuEndActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUsuCreate;
    private javax.swing.JButton btnUsuDelete;
    private javax.swing.JButton btnUsuRead;
    private javax.swing.JButton btnUsuUpdate;
    private javax.swing.JComboBox<String> cboUsuPerfil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtUsuCPF;
    private javax.swing.JTextField txtUsuEmail;
    private javax.swing.JTextField txtUsuEnd;
    private javax.swing.JTextField txtUsuLogin;
    private javax.swing.JTextField txtUsuNome;
    private javax.swing.JTextField txtUsuPIS;
    private javax.swing.JTextField txtUsuRG;
    private javax.swing.JTextField txtUsuSenha;
    private javax.swing.JTextField txtUsuTel;
    // End of variables declaration//GEN-END:variables
}
