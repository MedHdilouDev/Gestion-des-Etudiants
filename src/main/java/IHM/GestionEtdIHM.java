/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package IHM;

import Entite.Etudiant;
import Entite.etdClass;
import client.Client;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 *
 * @author Mohamed Hdilou
 */
public class GestionEtdIHM extends javax.swing.JFrame {
     private ArrayList<etdClass> classes; // Variable pour stocker les classes
    private Client client; // Variable pour stocker l'instance Client
    
    
    

    /**
     * Creates new form GestionEtudiantsIHM
     */
    public GestionEtdIHM() throws RemoteException {
        initComponents();
        client = new Client();
        chargerClasses();
        chargerTableEtudiants();
        initTableSelectionListener(); 
    }
    
    
    private void chargerTableEtudiants() {
        try {
            ArrayList<Etudiant> etudiants = client.afficherEtudiants();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); 

            for (Etudiant etudiant : etudiants) {
                Object[] row = {
                    etudiant.getId(),
                    etudiant.getNom(),
                    etudiant.getPrenom(),
                    etudiant.getEmail(),
                    etudiant.getNomFiliere(), 
                    etudiant.getNomClasse()  
                };
                model.addRow(row);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(GestionEtdIHM.class.getName()).log(Level.SEVERE, null, ex);
            javax.swing.JOptionPane.showMessageDialog(this, "Erreur de récupération des étudiants.", "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    
  
    
    
    
    
    
    
    private void chargerClasses() throws RemoteException {
        classes = client.chargercmbClass();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (etdClass classe : classes) {
            model.addElement(classe.getNomClasse()); 
        }
        cmbClass.setModel(model);
    }
    
    
     private void initTableSelectionListener() {
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        afficherEtudiantSelectionne(selectedRow);
                    }
                }
            }
        });
    }
     
     
      private void afficherEtudiantSelectionne(int selectedRow) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int idEtudiant = (int) model.getValueAt(selectedRow, 0);
        String nom = (String) model.getValueAt(selectedRow, 1);
        String prenom = (String) model.getValueAt(selectedRow, 2);
        String email = (String) model.getValueAt(selectedRow, 3);
        String nomFiliere = (String) model.getValueAt(selectedRow, 4);
        String nomClasse = (String) model.getValueAt(selectedRow, 5);

        txtNom.setText(nom);
        txtPrenom.setText(prenom);
        txtEmail.setText(email);

        try {
            ArrayList<Etudiant> etudiants = client.afficherEtudiants();
            for (Etudiant etudiant : etudiants) {
                if (etudiant.getId() == idEtudiant) {
                    txtDateN.setDate(etudiant.getDateNaissance());
                    break;
                }
            }
        } catch (RemoteException ex) {
            Logger.getLogger(GestionEtdIHM.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < classes.size(); i++) {
            if (classes.get(i).getNomClasse().equals(nomClasse)) {
                cmbClass.setSelectedIndex(i);
                break;
            }
        }
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtNom = new javax.swing.JTextField();
        txtPrenom = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnModifer = new javax.swing.JButton();
        btndellEtudiant = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cmbClass = new javax.swing.JComboBox<>();
        txtDateN = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        btnAfficherParClasse = new javax.swing.JButton();
        btnImprimer = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(102, 204, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Gestion Des Etudinats");

        jButton1.setBackground(new java.awt.Color(51, 204, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("<");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(328, 328, 328)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "CodeEtudiant", "Nom", "Prenom", "Email", "Filier", "Classe"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);

        btnAdd.setBackground(new java.awt.Color(51, 204, 0));
        btnAdd.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("Ajouter ");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnModifer.setBackground(new java.awt.Color(255, 102, 0));
        btnModifer.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnModifer.setForeground(new java.awt.Color(255, 255, 255));
        btnModifer.setText("Modifer");
        btnModifer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModiferActionPerformed(evt);
            }
        });

        btndellEtudiant.setBackground(new java.awt.Color(255, 51, 51));
        btndellEtudiant.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btndellEtudiant.setForeground(new java.awt.Color(255, 255, 255));
        btndellEtudiant.setText("Supremmer");
        btndellEtudiant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndellEtudiantActionPerformed(evt);
            }
        });

        jLabel2.setText("Nom ");

        jLabel3.setText("Prenom");

        jLabel4.setText("Email");

        cmbClass.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbClass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbClassActionPerformed(evt);
            }
        });

        jLabel5.setText("date d'ennesanse");

        btnAfficherParClasse.setBackground(new java.awt.Color(0, 153, 255));
        btnAfficherParClasse.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        btnAfficherParClasse.setForeground(new java.awt.Color(255, 255, 255));
        btnAfficherParClasse.setText("Étudiants de la classe :");
        btnAfficherParClasse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAfficherParClasseActionPerformed(evt);
            }
        });

        btnImprimer.setBackground(new java.awt.Color(102, 153, 255));
        btnImprimer.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        btnImprimer.setForeground(new java.awt.Color(255, 255, 255));
        btnImprimer.setText("Imprimer");
        btnImprimer.setActionCommand("impirimer");
        btnImprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimerActionPerformed(evt);
            }
        });

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 153, 153));
        jLabel6.setText("rechercher un Etudiant par Nom :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtNom)
                                .addComponent(cmbClass, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btndellEtudiant, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                .addComponent(btnModifer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(btnAfficherParClasse)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtPrenom, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(73, 73, 73)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(73, 73, 73))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(18, 18, 18)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDateN, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 907, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(btnImprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(72, Short.MAX_VALUE))))
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrenom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAfficherParClasse)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtDateN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cmbClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(btnModifer, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(btndellEtudiant, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnImprimer)
                .addContainerGap(65, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
try {
        String nom = txtNom.getText().trim();
        String prenom = txtPrenom.getText().trim();
        String email = txtEmail.getText().trim();
        java.util.Date dateUtil = txtDateN.getDate();
        
        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || dateUtil == null || cmbClass.getSelectedIndex() == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.", "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        java.sql.Date dateNaissance = new java.sql.Date(dateUtil.getTime());
        
        int index = cmbClass.getSelectedIndex();
        int idClasse = classes.get(index).getIdClasse();
        
        boolean success = client.ajouterEtudiant(nom, prenom, email, dateNaissance, idClasse);
        
        if (success) {
            javax.swing.JOptionPane.showMessageDialog(this, "Étudiant ajouté avec succès !", "Succès", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            chargerTableEtudiants();
            
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Échec de l'ajout de l'étudiant.", "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    } catch (RemoteException ex) {
        Logger.getLogger(GestionEtdIHM.class.getName()).log(Level.SEVERE, null, ex);
        javax.swing.JOptionPane.showMessageDialog(this, "Erreur de connexion au serveur.", "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnAddActionPerformed

    private void cmbClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbClassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbClassActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        client = new Client(); 
         try {
             chargerClasses();
             chargerTableEtudiants();
         } catch (RemoteException ex) {
             Logger.getLogger(GestionEtdIHM.class.getName()).log(Level.SEVERE, null, ex);
         }
    }//GEN-LAST:event_formWindowOpened

    private void btnModiferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModiferActionPerformed
  int selectedRow = table.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Veuillez sélectionner un étudiant à modifier.", "Erreur", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        int idEtudiant = (int) table.getValueAt(selectedRow, 0);
        String nom = txtNom.getText().trim();
        String prenom = txtPrenom.getText().trim();
        String email = txtEmail.getText().trim();

        java.util.Date utilDate = txtDateN.getDate();
        if (utilDate == null) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une date de naissance.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        int idClasse = classes.get(cmbClass.getSelectedIndex()).getIdClasse();

        int confirmation = JOptionPane.showConfirmDialog(this, "Confirmer la modification de l'étudiant ?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            boolean success = client.modifierEtudiant(idEtudiant, nom, prenom, email, sqlDate, idClasse);

            if (success) {
                JOptionPane.showMessageDialog(this, "Étudiant modifié avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
                chargerTableEtudiants();
            } else {
                JOptionPane.showMessageDialog(this, "Échec de la modification de l'étudiant.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (RemoteException ex) {
        Logger.getLogger(GestionEtdIHM.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(this, "Erreur de connexion au serveur.", "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    
    }//GEN-LAST:event_btnModiferActionPerformed

    private void btndellEtudiantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndellEtudiantActionPerformed
 int selectedRow = table.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Veuillez sélectionner un étudiant à supprimer.", "Erreur", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        int idEtudiant = (int) table.getValueAt(selectedRow, 0);

        int confirmation = JOptionPane.showConfirmDialog(this, "Confirmer la suppression de l'étudiant ?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            boolean success = client.supprimerEtudiant(idEtudiant);

            if (success) {
                JOptionPane.showMessageDialog(this, "Étudiant supprimé avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
                chargerTableEtudiants(); // Mettre à jour le tableau après la suppression
            } else {
                JOptionPane.showMessageDialog(this, "Échec de la suppression de l'étudiant.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (RemoteException ex) {
        Logger.getLogger(GestionEtdIHM.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(this, "Erreur de connexion au serveur.", "Erreur", JOptionPane.ERROR_MESSAGE);
    }    }//GEN-LAST:event_btndellEtudiantActionPerformed

    private void btnAfficherParClasseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAfficherParClasseActionPerformed
 try {
        int idClasse = classes.get(cmbClass.getSelectedIndex()).getIdClasse();

        ArrayList<Etudiant> etudiants = client.afficherEtudiantsParClasse(idClasse);

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        for (Etudiant etudiant : etudiants) {
            Object[] row = {
                etudiant.getId(),
                etudiant.getNom(),
                etudiant.getPrenom(),
                etudiant.getEmail(),
                etudiant.getNomFiliere(),
                etudiant.getNomClasse()
            };
            model.addRow(row);
        }
    } catch (RemoteException ex) {
        Logger.getLogger(GestionEtdIHM.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(this, "Erreur de connexion au serveur.", "Erreur", JOptionPane.ERROR_MESSAGE);
    }    }//GEN-LAST:event_btnAfficherParClasseActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            this.dispose();
            new AdminAccueilIHM().setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnImprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimerActionPerformed
int selectedRow = table.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, 
            "Veuillez sélectionner un étudiant à imprimer", 
            "Erreur", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    try {
        int idEtudiant = (int) table.getValueAt(selectedRow, 0);
        ArrayList<Etudiant> etudiants = client.afficherEtudiants();
        
        for (Etudiant etudiant : etudiants) {
            if (etudiant.getId() == idEtudiant) {
                genererPDF(etudiant);
                break;
            }
        }
    } catch (RemoteException ex) {
        Logger.getLogger(GestionEtdIHM.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(this, 
            "Erreur de connexion au serveur", 
            "Erreur", JOptionPane.ERROR_MESSAGE);
    }    }//GEN-LAST:event_btnImprimerActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        try {
            ArrayList<Etudiant> etudiants = client.searchETD(txtSearch.getText().trim());
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); 

            for (Etudiant etudiant : etudiants) {
                Object[] row = {
                    etudiant.getId(),
                    etudiant.getNom(),
                    etudiant.getPrenom(),
                    etudiant.getEmail(),
                    etudiant.getNomFiliere(), 
                    etudiant.getNomClasse()  
                };
                model.addRow(row);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(GestionEtdIHM.class.getName()).log(Level.SEVERE, null, ex);
            javax.swing.JOptionPane.showMessageDialog(this, "Erreur de récupération des étudiants.", "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_txtSearchKeyReleased

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
 try {
            ArrayList<Etudiant> etudiants = client.searchETD(txtSearch.getText().trim());
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); 

            for (Etudiant etudiant : etudiants) {
                Object[] row = {
                    etudiant.getId(),
                    etudiant.getNom(),
                    etudiant.getPrenom(),
                    etudiant.getEmail(),
                    etudiant.getNomFiliere(), 
                    etudiant.getNomClasse()  
                };
                model.addRow(row);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(GestionEtdIHM.class.getName()).log(Level.SEVERE, null, ex);
            javax.swing.JOptionPane.showMessageDialog(this, "Erreur de récupération des étudiants.", "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
        }    }//GEN-LAST:event_txtSearchActionPerformed
    
    
    private void genererPDF(Etudiant etudiant) {
    Document document = new Document();
    try {
        
        File dossier = new File("etudiants_pdf");
        if (!dossier.exists()) {
            dossier.mkdir();
        }
        
        String filename = "etudiants_pdf/Etudiant_" + etudiant.getNom()+"_"+etudiant.getPrenom() +".pdf";
        PdfWriter.getInstance(document, new FileOutputStream(filename));
        document.open();
        
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLUE);
        Paragraph title = new Paragraph("Fiche Étudiant", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(20f);
        document.add(title);
        
        
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(80);
        table.setSpacingBefore(20f);
        table.setSpacingAfter(30f);
        
        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
        Font cellFont = FontFactory.getFont(FontFactory.HELVETICA, 12);
        
        addRow(table, "ID Étudiant", String.valueOf(etudiant.getId()), headerFont, cellFont);
        addRow(table, "Nom", etudiant.getNom(), headerFont, cellFont);
        addRow(table, "Prénom", etudiant.getPrenom(), headerFont, cellFont);
        addRow(table, "Email", etudiant.getEmail(), headerFont, cellFont);
        addRow(table, "Date de Naissance", etudiant.getDateNaissance().toString(), headerFont, cellFont);
        addRow(table, "Classe", etudiant.getNomClasse(), headerFont, cellFont);
        addRow(table, "Filière", etudiant.getNomFiliere(), headerFont, cellFont);
        
        document.add(table);
        
        Font footerFont = FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 10);
        Paragraph footer = new Paragraph("Généré le " + new java.util.Date(), footerFont);
        footer.setAlignment(Element.ALIGN_RIGHT);
        document.add(footer);
        
        JOptionPane.showMessageDialog(this, 
            "PDF généré avec succès!\nFichier: " + new File(filename).getAbsolutePath(),
            "Succès", JOptionPane.INFORMATION_MESSAGE);
    } catch (DocumentException | IOException e) {
        JOptionPane.showMessageDialog(this, 
            "Erreur lors de la génération du PDF: " + e.getMessage(),
            "Erreur", JOptionPane.ERROR_MESSAGE);
    } finally {
        if (document.isOpen()) {
            document.close();
        }
    }
}

private void addRow(PdfPTable table, String header, String value, Font headerFont, Font cellFont) {
    PdfPCell cell1 = new PdfPCell(new Phrase(header, headerFont));
    PdfPCell cell2 = new PdfPCell(new Phrase(value, cellFont));
    table.addCell(cell1);
    table.addCell(cell2);
}
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */                                                                                                                                                                         
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GestionEtdIHM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestionEtdIHM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestionEtdIHM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestionEtdIHM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new GestionEtdIHM().setVisible(true);
                } catch (RemoteException ex) {
                    Logger.getLogger(GestionEtdIHM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAfficherParClasse;
    private javax.swing.JButton btnImprimer;
    private javax.swing.JButton btnModifer;
    private javax.swing.JButton btndellEtudiant;
    private javax.swing.JComboBox<String> cmbClass;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    private com.toedter.calendar.JDateChooser txtDateN;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNom;
    private javax.swing.JTextField txtPrenom;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
