package fr.eni.papeterie.ihm;

import fr.eni.papeterie.bll.BLLException;
import fr.eni.papeterie.bll.CatalogueManager;
import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Couleurs;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GUI extends JFrame {
    private JPanel panneauPrincipal;
    private JPanel panelType, panelGrammage, panelCouleur, panelBoutons;
    private JTextField reference;
    private JLabel labelRef = new JLabel("Référence");
    private JTextField designation;
    private JLabel labelDes = new JLabel("Désignation");
    private JTextField marque;
    private JLabel labelMar = new JLabel("Marque");
    private JTextField stock;
    private JLabel labelStk = new JLabel("Stock");
    private JTextField prix;
    private JLabel labelPx = new JLabel("Prix");
    private JRadioButton typeR;
    private JRadioButton typeS;
    private JLabel labelType = new JLabel("Type");
    private JCheckBox gram80;
    private JCheckBox gram100;
    private JLabel labelGram = new JLabel("Grammage");
    private JComboBox<Couleurs> couleur;
    private JLabel labelCoul = new JLabel("Couleur");
    private JButton flecheG;
    private JButton bCreation;
    private JButton bSave;
    private JButton bDelete;
    private JButton flecheD;
    private Article articleAAfficher;
    ArrayList<Article> listeDArticle;
    private int index = 0;

    public GUI() {
        this.setLocationRelativeTo(null); //Null pour centrer sur l'écran
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Pour fermer la fenêtre
        this.setContentPane(getPanneauPrincipal()); //Je colle le panneau principal
        this.setSize(400,500);
        this.setResizable(false); //Pour que la fenêtre ne soit pas redimensionné manuellement
        this.setAlwaysOnTop(true); //pour que la fenêtre reste ouverte tous le temps
        this.setTitle("Papeterie");
        this.pack(); //Pour que la fenêtre s'adapte au contenu
        this.setVisible(true);
        listeDArticle = new ArrayList<>();
        CatalogueManager catalogueManager = CatalogueManager.getInstance();
        try {
            listeDArticle = (ArrayList<Article>) catalogueManager.getCatalogue();
        } catch (BLLException e) {
            System.out.println(e.getMessage());
        }
        if (!listeDArticle.isEmpty()){
            articleAAfficher = listeDArticle.get(0);
            getDesignation().setText(articleAAfficher.getDesignation());
            if (articleAAfficher instanceof Ramette) {
                getCouleur().setEnabled(false);
                getTypeR().setSelected(true);
                if (((Ramette) articleAAfficher).getGrammage() ==80){
                    getGram80().setSelected(true);
                } else {
                    getGram100().setSelected(true);
                }
            }
            if (articleAAfficher instanceof Stylo) {
                getTypeS().setSelected(true);
                getCouleur().setSelectedItem(Couleurs.valueOf(((Stylo) articleAAfficher).getCouleur()));
            }
        }
    }

    public JPanel getPanneauPrincipal(){
        if (panneauPrincipal == null) {
            panneauPrincipal = new JPanel();
            panneauPrincipal.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets (5,5,5,5);
            gbc.gridx = 0;
            gbc.gridy = 0;
            panneauPrincipal.add(getLabelRef(), gbc);
            gbc.gridx = 1;
            gbc.gridy = 0;
            panneauPrincipal.add(getReference(),gbc);
            gbc.gridx = 0;
            gbc.gridy = 1;
            panneauPrincipal.add(getLabelDes(), gbc);
            gbc.gridx = 1;
            gbc.gridy = 1;
            panneauPrincipal.add(getDesignation(), gbc);
            gbc.gridx = 0;
            gbc.gridy = 2;
            panneauPrincipal.add(getLabelMar(), gbc);
            gbc.gridx = 1;
            gbc.gridy = 2;
            panneauPrincipal.add(getMarque(), gbc);
            gbc.gridx = 0;
            gbc.gridy = 3;
            panneauPrincipal.add(getLabelStk(), gbc);
            gbc.gridx = 1;
            gbc.gridy = 3;
            panneauPrincipal.add(getStock(), gbc);
            gbc.gridx = 0;
            gbc.gridy = 4;
            panneauPrincipal.add(getLabelPx(), gbc);
            gbc.gridx = 1;
            gbc.gridy = 4;
            panneauPrincipal.add(getPrix(), gbc);
            gbc.gridx = 0;
            gbc.gridy = 5;
            panneauPrincipal.add(getLabelType(), gbc);
            gbc.gridx = 1;
            panneauPrincipal.add(getPanelType(), gbc);
            gbc.gridx = 0;
            gbc.gridy = 6;
            panneauPrincipal.add(getLabelGram(), gbc);
            gbc.gridx = 1;
            panneauPrincipal.add(getPanelGrammage(), gbc);
            gbc.gridx = 0;
            gbc.gridy = 7;
            panneauPrincipal.add(getLabelCoul(), gbc);
            gbc.gridx = 1;
            gbc.gridy = 7;
            panneauPrincipal.add(getCouleur(), gbc);
            gbc.gridx = 0;
            gbc.gridy = 8;
            gbc.gridwidth = 2;
            panneauPrincipal.add(getPanelBoutons(), gbc);
            panneauPrincipal.setBackground(new Color(0x067246));
        }
        return panneauPrincipal;
    }

    public JLabel getLabelRef() {
        if (labelRef == null) {
            labelRef = new JLabel();
        }
        return labelRef;
    }

    public JTextField getReference() {
        if (reference == null) {
            reference = new JTextField(25);
        }
        return reference;
    }

    public JLabel getLabelDes() {
        if (labelDes == null) {
            labelDes = new JLabel();
        }
        return labelDes;
    }

    public JTextField getDesignation() {
        if (designation == null) {
            designation = new JTextField(25);
        }
        return designation;
    }

    public JLabel getLabelMar() {
        if (labelMar == null) {
            labelMar = new JLabel();
        }
        return labelMar;
    }

    public JTextField getMarque() {
        if (marque == null) {
            marque = new JTextField(25);
        }
        return marque;
    }

    public JLabel getLabelStk() {
        if (labelStk == null) {
            labelStk = new JLabel();
        }
        return labelStk;
    }

    public JTextField getStock() {
        if (stock == null) {
            stock = new JTextField(25);
        }
        return stock;
    }

    public JLabel getLabelPx() {
        if (labelPx == null) {
            labelPx = new JLabel();
        }
        return labelPx;
    }

    public JTextField getPrix() {
        if (prix == null) {
            prix = new JTextField(25);
        }
        return prix;
    }
    public JPanel getPanelType() {
        if (panelType == null) {
            panelType = new JPanel();
            panelType.setLayout(new BoxLayout(panelType, BoxLayout.Y_AXIS));
            panelType.add(getTypeR());
            panelType.add(getTypeS());
            ButtonGroup bg = new ButtonGroup();
            bg.add(getTypeR());
            bg.add(getTypeS());
        }
        return panelType;
    }

    public JLabel getLabelType() {
        if (labelType == null) {
            labelType = new JLabel();
        }
        return labelType;
    }

    public JRadioButton getTypeR() {
        if (typeR == null) {
            typeR = new JRadioButton("Ramette");
            typeR.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    getCouleur().setEnabled(false);
                    getGram80().doClick();
                }
            });
        }
        return typeR;
    }

    public JRadioButton getTypeS() {
        if (typeS == null) {
            typeS = new JRadioButton("Stylo");
            typeS.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    getCouleur().setEnabled(true);
                }
            });
        }
        return typeS;
    }

    public JPanel getPanelGrammage() {
        if (panelGrammage == null) {
            panelGrammage = new JPanel();
            panelGrammage.setLayout(new BoxLayout(panelGrammage, BoxLayout.Y_AXIS));
            panelGrammage.add(getGram80());
            panelGrammage.add(getGram100());
            ButtonGroup bg = new ButtonGroup();
            bg.add(getGram80());
            bg.add(getGram100());
        }
        return panelGrammage;
    }

    public JLabel getLabelGram() {
        if (labelGram == null) {
            labelGram = new JLabel();
        }
        return labelGram;
    }

    public JCheckBox getGram80() {
        if (gram80 == null) {
            gram80 = new JCheckBox("80 grammes");
        }
        return gram80;
    }

    public JCheckBox getGram100() {
        if (gram100 == null) {
            gram100 = new JCheckBox("100 grammes");
        }
        return gram100;
    }

    public JLabel getLabelCoul() {
        if (labelCoul == null) {
            labelCoul = new JLabel();
        }
        return labelCoul;
    }

    public JComboBox<Couleurs> getCouleur() {
        if (couleur == null) {
            couleur = new JComboBox<>(Couleurs.values());
            //couleur.addItem("Rouge");
            //couleur.addItem("Vert");
        }
        return couleur;
    }

    public JPanel getPanelBoutons() {
        if (panelBoutons == null) {
            panelBoutons = new JPanel();
            panelBoutons.add(getFlecheG());
            panelBoutons.add(getBCreation());
            panelBoutons.add(getBSave());
            panelBoutons.add(getbDelete());
            panelBoutons.add(getFlecheD());
        }
        return panelBoutons;
    }

    public Component getFlecheG() {
        if (flecheG == null) {
            Icon icon = new ImageIcon("Images/Back24.gif");
            flecheG = new JButton(icon);
            flecheG.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(index <= 0) {
                        index = listeDArticle.size() -1;
                    }else {
                        index--;
                    }
                    articleAAfficher = listeDArticle.get(index);
                    getReference().setText(String.valueOf(articleAAfficher.getReference()));
                    getDesignation().setText(articleAAfficher.getDesignation());
                    getMarque().setText(articleAAfficher.getMarque());
                    getStock().setText(String.valueOf(articleAAfficher.getQteStock()));
                    getPrix().setText(String.valueOf(articleAAfficher.getPrixUnitaire()));
                }
            });
        }
        return flecheG;
    }

    public JButton getBCreation(){
        if (bCreation == null) {
            Icon icon = new ImageIcon("Images/New24.gif");
            bCreation = new JButton(icon);
            bCreation.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    getReference().setText("");
                    getDesignation().setText("");
                    getMarque().setText("");
                    getStock().setText("");
                    getPrix().setText("");
                    getTypeR().setSelected(false);
                    getGram80().setSelected(false);
                    getGram100().setSelected(false);
                }
            });
        }
        return bCreation;
    }
    public JButton getBSave() {
        if (bSave == null) {
            Icon icon = new ImageIcon("Images/Save24.gif");
            bSave = new JButton(icon);
            bSave.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CatalogueManager cm = CatalogueManager.getInstance();
                    if (articleAAfficher != null) {
                        try {
                            cm.updateArticle(articleAAfficher);
                        } catch (BLLException bllException) {
                            bllException.printStackTrace();
                        }
                    } else {
                        Article article = construitLArticle();
                        try {
                            cm.addArticle(article);
                        } catch (BLLException bllException) {
                            bllException.printStackTrace();
                        }
                    }
                }
            });
            }
        return bSave;
    }

    private Article construitLArticle() {
        Article article = null;
        if (getTypeS().isSelected()) {
            article = new Stylo(
                    getMarque().getText(),
                    getReference().getText(),
                    getDesignation().getText(),
                    Float.parseFloat(getPrix().getText()),
                    Integer.parseInt(getStock().getText()),
                    getCouleur().getSelectedItem().toString()
            );
        }
        if (getTypeR().isSelected()) {
            article = new Ramette(
                    getMarque().getText(),
                    getReference().getText(),
                    getDesignation().getText(),
                    Float.parseFloat(getPrix().getText()),
                    Integer.parseInt(getStock().getText()),
                    (getGram80().isSelected() ? 80 : 100)
            );
        }
        return article;
    }

    public JButton getbDelete() {
        if (bDelete == null) {
            Icon icon = new ImageIcon("Images/Delete24.gif");
            bDelete = new JButton(icon);
            bDelete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CatalogueManager cm = CatalogueManager.getInstance();
                    try {
                        cm.removeArticle(articleAAfficher.getIdArticle());
                    } catch (BLLException bllException) {
                        System.out.println(bllException.getMessage());
                    }
                    listeDArticle.remove(articleAAfficher);
            getFlecheD().doClick();
                    }
            });
        }
        return bDelete;
    }
    public JButton getFlecheD() {
        if (flecheD == null) {
            Icon icon = new ImageIcon("Images/Forward24.gif");
            flecheD = new JButton(icon);
            flecheD.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(index >= listeDArticle.size() -1) {
                        index = 0;
                    }else {
                        index++;
                    }
                    articleAAfficher = listeDArticle.get(index);
                    getReference().setText(String.valueOf(articleAAfficher.getReference()));
                    getDesignation().setText(articleAAfficher.getDesignation());
                    getMarque().setText(articleAAfficher.getMarque());
                    getStock().setText(String.valueOf(articleAAfficher.getQteStock()));
                    getPrix().setText(String.valueOf(articleAAfficher.getPrixUnitaire()));
                }
            });
        }
        return flecheD;
    }
}

