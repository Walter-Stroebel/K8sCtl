/*
 *  Copyright (c) 2018 by Walter Stroebel and InfComTec.
 */
package nl.infcomtec.jk8sctl.gui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import nl.infcomtec.jk8sctl.Global;
import nl.infcomtec.jk8sctl.K8sCtlCfg;
import org.yaml.snakeyaml.Yaml;

/**
 *
 * @author walter
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form Swing
     */
    public MainFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabsYaml = new javax.swing.JTabbedPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuSaveCfg = new javax.swing.JMenuItem();
        menuResetConfig = new javax.swing.JMenuItem();
        menuLoadYaml = new javax.swing.JMenuItem();
        menuSaveYaml = new javax.swing.JMenuItem();
        menuCloseYaml = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menuPackYaml = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Kubernetes Control GUI");
        setAlwaysOnTop(true);

        jMenu1.setText("File");

        menuSaveCfg.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        menuSaveCfg.setText("Save configuration");
        menuSaveCfg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSaveCfgActionPerformed(evt);
            }
        });
        jMenu1.add(menuSaveCfg);

        menuResetConfig.setText("Reset configuration");
        menuResetConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuResetConfigActionPerformed(evt);
            }
        });
        jMenu1.add(menuResetConfig);

        menuLoadYaml.setText("Load YAML...");
        menuLoadYaml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuLoadYamlActionPerformed(evt);
            }
        });
        jMenu1.add(menuLoadYaml);

        menuSaveYaml.setText("Save YAML");
        menuSaveYaml.setEnabled(false);
        menuSaveYaml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSaveYamlActionPerformed(evt);
            }
        });
        jMenu1.add(menuSaveYaml);

        menuCloseYaml.setText("Close YAML");
        menuCloseYaml.setEnabled(false);
        menuCloseYaml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCloseYamlActionPerformed(evt);
            }
        });
        jMenu1.add(menuCloseYaml);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        menuPackYaml.setText("Pack YAML");
        menuPackYaml.setToolTipText("Also removes comments");
        menuPackYaml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPackYamlActionPerformed(evt);
            }
        });
        jMenu2.add(menuPackYaml);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabsYaml, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabsYaml, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuSaveCfgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSaveCfgActionPerformed
        try {
            Global.getConfig().saveConfig();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.toString());
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuSaveCfgActionPerformed

    private void menuResetConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuResetConfigActionPerformed
        try {
            K8sCtlCfg.defaults().saveConfig();
            Global.loadConfig();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.toString());
        }
    }//GEN-LAST:event_menuResetConfigActionPerformed

    private void menuLoadYamlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuLoadYamlActionPerformed
        JFileChooser jfc = new JFileChooser(Global.workDir);
        jfc.setCurrentDirectory(Global.getYamlDir());
        jfc.setAcceptAllFileFilterUsed(false);
        jfc.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory() || f.getName().toLowerCase().endsWith(".yml") || f.getName().toLowerCase().endsWith(".yaml");
            }
            
            @Override
            public String getDescription() {
                return "YAML files";
            }
        });
        int showOpenDialog = jfc.showOpenDialog(this);
        if (JFileChooser.APPROVE_OPTION == showOpenDialog) {
            Global.setYamlDir(jfc.getSelectedFile().getParentFile());
            YamlFile yamlFile = new YamlFile(jfc.getSelectedFile());
            tabsYaml.addTab(yamlFile.src.getName(), yamlFile);
            menuCloseYaml.setEnabled(true);
            menuSaveYaml.setEnabled(true);
        }
    }//GEN-LAST:event_menuLoadYamlActionPerformed

    private void menuSaveYamlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSaveYamlActionPerformed
        try {
            if (tabsYaml.getSelectedIndex() >= 0) {
                YamlFile sel = (YamlFile) tabsYaml.getSelectedComponent();
                sel.backup();
                {
                    // test-parse the Yaml
                    Yaml yaml = new Yaml();
                    Iterable<Object> loadAll = yaml.loadAll(sel.jta.getText());
                    try (StringWriter sw = new StringWriter()) {
                        yaml.dumpAll(loadAll.iterator(), sw);
                    }
                }
                try (FileWriter sw = new FileWriter(sel.src)) {
                    sw.write(sel.jta.getText().trim() + "\n");
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.toString());
        }
    }//GEN-LAST:event_menuSaveYamlActionPerformed

    private void menuCloseYamlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCloseYamlActionPerformed
        if (tabsYaml.getSelectedIndex() >= 0) {
            tabsYaml.remove(tabsYaml.getSelectedIndex());
        }
        if (tabsYaml.getTabCount() < 1) {
            menuCloseYaml.setEnabled(false);
            menuSaveYaml.setEnabled(false);
        }
    }//GEN-LAST:event_menuCloseYamlActionPerformed

    private void menuPackYamlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPackYamlActionPerformed
        try {
            if (tabsYaml.getSelectedIndex() >= 0) {
                YamlFile sel = (YamlFile) tabsYaml.getSelectedComponent();
                Yaml yaml = new Yaml();
                Iterable<Object> loadAll = yaml.loadAll(sel.jta.getText());
                try (StringWriter sw = new StringWriter()) {
                    yaml.dumpAll(loadAll.iterator(), sw);
                    sel.jta.setText(sw.toString() + "\n");
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.toString());
        }
    }//GEN-LAST:event_menuPackYamlActionPerformed

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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem menuCloseYaml;
    private javax.swing.JMenuItem menuLoadYaml;
    private javax.swing.JMenuItem menuPackYaml;
    private javax.swing.JMenuItem menuResetConfig;
    private javax.swing.JMenuItem menuSaveCfg;
    private javax.swing.JMenuItem menuSaveYaml;
    private javax.swing.JTabbedPane tabsYaml;
    // End of variables declaration//GEN-END:variables
}
