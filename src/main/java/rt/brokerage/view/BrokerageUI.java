/*
 * Copyright (C) 2015 ryantonini
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package rt.brokerage.view;

import java.io.IOException;
import java.io.InputStream;
import javax.swing.table.DefaultTableModel;
import rt.brokerage.manager.AccountItem;
import rt.brokerage.manager.AccountTypeItem;
import rt.brokerage.manager.ClientItem;
import rt.brokerage.manager.QuoteItem;
import rt.brokerage.manager.PortfolioItem;
import rt.brokerage.manager.DbResource;
import rt.brokerage.manager.QueryFunctions;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 * Runs the application and generates the UI.
 * 
 * @author Ryan Tonini
 */

public class BrokerageUI extends javax.swing.JFrame {
        
    private static final int NUMTABLES = 7;
    private static boolean jointStatus = false; // for creating joint accounts
    private int accountOwners = 0;  
    private int acctNoHolder = 0;
    private boolean state = true; // for loading account types in combobox
    private QueryFunctions qf;
    private DbResource dbResource;
    private Connection con;
    
    /**
     * Creates new form BrokerageUI
     */
    public BrokerageUI() {
        
        /* get properties file */
        String resourceName = "db.properties"; // could also be a constant
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        try(InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
            props.load(resourceStream);
        } catch (IOException ex) {
            Logger.getLogger(BrokerageUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        String username = props.getProperty("username");
        String password = props.getProperty("password");
        String host = props.getProperty("server");
        int portNumber = Integer.parseInt(props.getProperty("portnumber"));
        String dbms = props.getProperty("dbms");
        String dbName = props.getProperty("dbname");
     
        dbResource = new DbResource(username, password, host, portNumber,
                                     dbms, dbName);
        try {
            con = dbResource.connect();
            DatabaseMetaData md = con.getMetaData();
            ResultSet rs = md.getTables(null, null, "%", null);
            int tableCount = 0;
            while (rs.next()) {
                tableCount += 1;
            }
            if (tableCount != NUMTABLES) {
                dbResource.populate(con);
            }
            qf = new QueryFunctions(con);
        } catch (SQLException ex) {
            Logger.getLogger(BrokerageUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jDialog1 = new javax.swing.JDialog();
        jToolBar1 = new javax.swing.JToolBar();
        addButton = new javax.swing.JButton();
        viewPortfolioButton = new javax.swing.JButton();
        tradeButton = new javax.swing.JButton();
        marketButton = new javax.swing.JButton();
        optionsButton = new javax.swing.JButton();
        mainPanel = new javax.swing.JPanel();
        defaultPanel = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        addPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        selectAccountTypeBox = new javax.swing.JComboBox();
        firstNameTextField = new javax.swing.JTextField();
        lastNameTextField = new javax.swing.JTextField();
        ssnTextField = new javax.swing.JTextField();
        phoneTextField = new javax.swing.JTextField();
        emailTextField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        addressTextField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cityTextField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        zipTextField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        countryBox = new javax.swing.JComboBox();
        newAccountButton = new javax.swing.JButton();
        stateBox = new javax.swing.JComboBox();
        jLabel35 = new javax.swing.JLabel();
        investmentTextField = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        numOwnersTextField = new javax.swing.JTextField();
        cancelButton = new javax.swing.JButton();
        portfolioPanel = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        searchAccountTextField = new javax.swing.JTextField();
        viewPortfolioSearchButton = new javax.swing.JButton();
        portfolioInfoPanel = new javax.swing.JPanel();
        ownerTextField = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        typeTextField = new javax.swing.JTextField();
        accountValueTextField = new javax.swing.JTextField();
        cashTextField = new javax.swing.JTextField();
        dateOpenTextField = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        stockPortfolioTable = new javax.swing.JTable();
        jLabel33 = new javax.swing.JLabel();
        addAmountButton = new javax.swing.JButton();
        amountTextField = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        tradePanel = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        tradeSymbolTextField = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        tradeAccountNumberTextField = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        tradeQtyTextField = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        tradeBox = new javax.swing.JComboBox();
        tradePriceBox = new javax.swing.JComboBox();
        submitTradeButton = new javax.swing.JButton();
        marketPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        watchListTable = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        addTickerButton = new javax.swing.JButton();
        updateMarketButton = new javax.swing.JButton();
        tickerTextField = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        optionsPanel = new javax.swing.JPanel();
        viewAccountType = new javax.swing.JTabbedPane();
        addAccountTypePanel = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        descrjTitle = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        accountTypeTextField = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        descrTextArea = new javax.swing.JTextArea();
        ownershipBox = new javax.swing.JComboBox();
        minInvestTextField = new javax.swing.JTextField();
        commissionTextField = new javax.swing.JTextField();
        maintenanceTextField = new javax.swing.JTextField();
        periodBox = new javax.swing.JComboBox();
        newTypeButton = new javax.swing.JButton();
        viewAccountTypesPanel = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        accountTypeTable = new javax.swing.JTable();
        updateTypesButton = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        viewUserAccounts = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        firstNameSearchTextField = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        lastNameSearch = new javax.swing.JLabel();
        lastNameSearchTextField = new javax.swing.JTextField();
        searchUserButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane5 = new javax.swing.JScrollPane();
        userAccountTable = new javax.swing.JTable();
        refreshButton = new javax.swing.JButton();
        closeAccountPanel = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        closeAccountNumberTextField = new javax.swing.JTextField();
        closeAccountButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        exitMenuItem = new javax.swing.JMenuItem();
        toolsMenu = new javax.swing.JMenu();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Broker Management System v1.0");
        setPreferredSize(new java.awt.Dimension(688, 515));

        jToolBar1.setBorder(null);
        jToolBar1.setRollover(true);
        jToolBar1.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jToolBar1.setMaximumSize(new java.awt.Dimension(688, 90));

        addButton.setFont(new java.awt.Font("Lucida Grande", 1, 11)); // NOI18N
        addButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        addButton.setText("Open Account");
        addButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        addButton.setFocusable(false);
        addButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addButton.setMaximumSize(new java.awt.Dimension(135, 90));
        addButton.setMinimumSize(new java.awt.Dimension(34, 55));
        addButton.setPreferredSize(new java.awt.Dimension(135, 90));
        addButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(addButton);

        viewPortfolioButton.setFont(new java.awt.Font("Lucida Grande", 1, 11)); // NOI18N
        viewPortfolioButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        viewPortfolioButton.setText("View Portfolio");
        viewPortfolioButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        viewPortfolioButton.setFocusable(false);
        viewPortfolioButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        viewPortfolioButton.setMaximumSize(new java.awt.Dimension(135, 90));
        viewPortfolioButton.setPreferredSize(new java.awt.Dimension(135, 90));
        viewPortfolioButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        viewPortfolioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewPortfolioButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(viewPortfolioButton);

        tradeButton.setFont(new java.awt.Font("Lucida Grande", 1, 11)); // NOI18N
        tradeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/trade.png"))); // NOI18N
        tradeButton.setText("Trade");
        tradeButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tradeButton.setFocusable(false);
        tradeButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tradeButton.setMaximumSize(new java.awt.Dimension(135, 90));
        tradeButton.setPreferredSize(new java.awt.Dimension(135, 90));
        tradeButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tradeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tradeButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(tradeButton);

        marketButton.setFont(new java.awt.Font("Lucida Grande", 1, 11)); // NOI18N
        marketButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/market.png"))); // NOI18N
        marketButton.setText("Market");
        marketButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        marketButton.setFocusable(false);
        marketButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        marketButton.setMaximumSize(new java.awt.Dimension(135, 90));
        marketButton.setPreferredSize(new java.awt.Dimension(135, 90));
        marketButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        marketButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                marketButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(marketButton);

        optionsButton.setFont(new java.awt.Font("Lucida Grande", 1, 11)); // NOI18N
        optionsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/options.png"))); // NOI18N
        optionsButton.setText("Options");
        optionsButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        optionsButton.setFocusable(false);
        optionsButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        optionsButton.setMaximumSize(new java.awt.Dimension(135, 90));
        optionsButton.setPreferredSize(new java.awt.Dimension(135, 90));
        optionsButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        optionsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optionsButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(optionsButton);

        mainPanel.setPreferredSize(new java.awt.Dimension(666, 403));
        mainPanel.setLayout(new java.awt.CardLayout());

        defaultPanel.setBackground(new java.awt.Color(212, 215, 226));
        defaultPanel.setForeground(new java.awt.Color(51, 0, 255));
        defaultPanel.setPreferredSize(new java.awt.Dimension(666, 403));

        jLabel34.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        jLabel34.setText("Welcome");

        jLabel37.setBackground(new java.awt.Color(50, 40, 25));
        jLabel37.setForeground(new java.awt.Color(0, 0, 255));
        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/brokerage_bk.jpg"))); // NOI18N

        jLabel40.setText("Copyright (C) 2015  Ryan Tonini");

        javax.swing.GroupLayout defaultPanelLayout = new javax.swing.GroupLayout(defaultPanel);
        defaultPanel.setLayout(defaultPanelLayout);
        defaultPanelLayout.setHorizontalGroup(
            defaultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(defaultPanelLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(defaultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel37)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        defaultPanelLayout.setVerticalGroup(
            defaultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(defaultPanelLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel40)
                .addContainerGap(158, Short.MAX_VALUE))
        );

        mainPanel.add(defaultPanel, "card7");

        addPanel.setPreferredSize(new java.awt.Dimension(666, 403));

        jLabel1.setText("Account Type");

        jLabel2.setText("Initial Investment   $");

        jLabel3.setText("First Name");

        jLabel4.setText("Last Name");

        jLabel5.setText("SSN");

        jLabel6.setText("Phone");

        jLabel7.setText("Email");

        selectAccountTypeBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select" }));

        jLabel9.setText("Address");

        jLabel10.setText("City");

        jLabel8.setText("Zip/PC");

        jLabel11.setText("State/Prov.");

        jLabel12.setText("Country");

        countryBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select", "United States", "Canada" }));

        newAccountButton.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        newAccountButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/submitadd.png"))); // NOI18N
        newAccountButton.setText("Submit");
        newAccountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newAccountButtonActionPerformed(evt);
            }
        });

        stateBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select", "Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "District Of Columbia", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming", "--- ", "Alberta", "British Columbia", "Manitoba", "New Brunswick", "Newfoundland", "Northwest Territories", "Nova Scotia", "Nunavut", "Ontario", "Prince Edward Island", "Quebec", "Saskatchewan", "Yukon" }));

        jLabel36.setText("Number of Owners");

        cancelButton.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        cancelButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.png"))); // NOI18N
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout addPanelLayout = new javax.swing.GroupLayout(addPanel);
        addPanel.setLayout(addPanelLayout);
        addPanelLayout.setHorizontalGroup(
            addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addPanelLayout.createSequentialGroup()
                .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addPanelLayout.createSequentialGroup()
                        .addGap(263, 263, 263)
                        .addComponent(jLabel35))
                    .addGroup(addPanelLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(newAccountButton)
                            .addGroup(addPanelLayout.createSequentialGroup()
                                .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(addPanelLayout.createSequentialGroup()
                                        .addComponent(firstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(28, 28, 28)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(addPanelLayout.createSequentialGroup()
                                        .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(addPanelLayout.createSequentialGroup()
                                                .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(addressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(stateBox, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(phoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addPanelLayout.createSequentialGroup()
                                                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(5, 5, 5)))
                                        .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(addPanelLayout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(addPanelLayout.createSequentialGroup()
                                                .addComponent(jLabel10)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(27, 27, 27)
                                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(zipTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(addPanelLayout.createSequentialGroup()
                                                .addComponent(jLabel12)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(countryBox, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(28, 28, 28)
                                                .addComponent(jLabel5)
                                                .addGap(18, 18, 18)
                                                .addComponent(ssnTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addGroup(addPanelLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(investmentTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(addPanelLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(selectAccountTypeBox, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jLabel36)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(numOwnersTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        addPanelLayout.setVerticalGroup(
            addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addPanelLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectAccountTypeBox)
                    .addComponent(jLabel36)
                    .addComponent(numOwnersTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(investmentTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(firstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(cityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(zipTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(stateBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12)
                        .addComponent(countryBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ssnTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(phoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newAccountButton)
                    .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainPanel.add(addPanel, "card2");

        portfolioPanel.setForeground(new java.awt.Color(51, 0, 255));
        portfolioPanel.setPreferredSize(new java.awt.Dimension(666, 403));

        jLabel22.setText("Enter Account Number");

        viewPortfolioSearchButton.setForeground(new java.awt.Color(0, 0, 204));
        viewPortfolioSearchButton.setText("View Portfolio");
        viewPortfolioSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewPortfolioSearchButtonActionPerformed(evt);
            }
        });

        portfolioInfoPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        portfolioInfoPanel.setPreferredSize(new java.awt.Dimension(626, 345));

        jLabel28.setText("Account Owner");

        jLabel29.setText("Account Type");

        jLabel30.setText("Account Value  $");

        jLabel31.setText("Cash  $");

        jLabel32.setText("Date Opened");

        stockPortfolioTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Ticker", "Qty", "Purchase Price", "Current Price ", "Total Value", "Total Gain/Loss"
            }
        ));
        jScrollPane3.setViewportView(stockPortfolioTable);

        jLabel33.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(51, 0, 204));
        jLabel33.setText("Stock Portfolio");

        addAmountButton.setForeground(new java.awt.Color(51, 0, 255));
        addAmountButton.setText("Add To Account");
        addAmountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAmountButtonActionPerformed(evt);
            }
        });

        amountTextField.setToolTipText("Enter amount to be inserted into account");

        jLabel39.setText("Enter Cash Amount  $");

        javax.swing.GroupLayout portfolioInfoPanelLayout = new javax.swing.GroupLayout(portfolioInfoPanel);
        portfolioInfoPanel.setLayout(portfolioInfoPanelLayout);
        portfolioInfoPanelLayout.setHorizontalGroup(
            portfolioInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(portfolioInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(portfolioInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(portfolioInfoPanelLayout.createSequentialGroup()
                        .addGroup(portfolioInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(portfolioInfoPanelLayout.createSequentialGroup()
                                .addComponent(jLabel30)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(accountValueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jLabel31)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cashTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(portfolioInfoPanelLayout.createSequentialGroup()
                                .addComponent(jLabel39)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(amountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addAmountButton, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dateOpenTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(portfolioInfoPanelLayout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ownerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(typeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        portfolioInfoPanelLayout.setVerticalGroup(
            portfolioInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(portfolioInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(portfolioInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(ownerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29)
                    .addComponent(typeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(portfolioInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(accountValueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31)
                    .addComponent(cashTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32)
                    .addComponent(dateOpenTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(portfolioInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(amountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addAmountButton))
                .addGap(18, 18, 18)
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout portfolioPanelLayout = new javax.swing.GroupLayout(portfolioPanel);
        portfolioPanel.setLayout(portfolioPanelLayout);
        portfolioPanelLayout.setHorizontalGroup(
            portfolioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(portfolioInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE)
            .addGroup(portfolioPanelLayout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchAccountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(viewPortfolioSearchButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        portfolioPanelLayout.setVerticalGroup(
            portfolioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(portfolioPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(portfolioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(searchAccountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewPortfolioSearchButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(portfolioInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        mainPanel.add(portfolioPanel, "card2");

        tradePanel.setPreferredSize(new java.awt.Dimension(666, 403));

        jLabel13.setText("Stock Symbol");

        jLabel14.setText("Account Number");

        jLabel15.setText("Transaction");

        jLabel16.setText("Quantity");

        jLabel17.setText("Price");

        tradeBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select", "Buy", "Sell" }));

        tradePriceBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Market" }));

        submitTradeButton.setBackground(new java.awt.Color(153, 0, 0));
        submitTradeButton.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        submitTradeButton.setForeground(new java.awt.Color(255, 255, 255));
        submitTradeButton.setText("Submit Order");
        submitTradeButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        submitTradeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitTradeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tradePanelLayout = new javax.swing.GroupLayout(tradePanel);
        tradePanel.setLayout(tradePanelLayout);
        tradePanelLayout.setHorizontalGroup(
            tradePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tradePanelLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(tradePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(submitTradeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(tradePanelLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tradeAccountNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tradePanelLayout.createSequentialGroup()
                        .addGroup(tradePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17))
                        .addGroup(tradePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tradePanelLayout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(tradePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tradeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tradeQtyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tradePriceBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(tradePanelLayout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(tradeSymbolTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(403, Short.MAX_VALUE))
        );
        tradePanelLayout.setVerticalGroup(
            tradePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tradePanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(tradePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(tradeAccountNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tradePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(tradeSymbolTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tradePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(tradeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tradePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(tradeQtyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tradePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tradePriceBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(27, 27, 27)
                .addComponent(submitTradeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(141, Short.MAX_VALUE))
        );

        mainPanel.add(tradePanel, "card2");

        marketPanel.setPreferredSize(new java.awt.Dimension(666, 403));

        watchListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ticker", "Exchange", "Last Trade", "Time", "Date", "Change", "% Change", "Prev "
            }
        ));
        jScrollPane1.setViewportView(watchListTable);

        jLabel18.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stock-market.png"))); // NOI18N
        jLabel18.setText("Market WatchList");

        addTickerButton.setForeground(new java.awt.Color(51, 0, 204));
        addTickerButton.setText("Add");
        addTickerButton.setToolTipText("Add New Quote");
        addTickerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTickerButtonActionPerformed(evt);
            }
        });

        updateMarketButton.setForeground(new java.awt.Color(51, 0, 204));
        updateMarketButton.setText("Update");
        updateMarketButton.setToolTipText("Update Market Quotes");
        updateMarketButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateMarketButtonActionPerformed(evt);
            }
        });

        jLabel19.setText("Enter Ticker");

        javax.swing.GroupLayout marketPanelLayout = new javax.swing.GroupLayout(marketPanel);
        marketPanel.setLayout(marketPanelLayout);
        marketPanelLayout.setHorizontalGroup(
            marketPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(marketPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(marketPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, marketPanelLayout.createSequentialGroup()
                        .addGroup(marketPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addGroup(marketPanelLayout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tickerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addTickerButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(updateMarketButton)))
                        .addGap(20, 20, 20))
                    .addGroup(marketPanelLayout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        marketPanelLayout.setVerticalGroup(
            marketPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(marketPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(marketPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(tickerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addTickerButton)
                    .addComponent(updateMarketButton))
                .addGap(142, 142, 142))
        );

        mainPanel.add(marketPanel, "card2");

        optionsPanel.setPreferredSize(new java.awt.Dimension(666, 403));

        jLabel20.setText("Name");

        jLabel21.setText("Ownership");

        descrjTitle.setText("Description");

        jLabel23.setText("Minimum Investment   $");

        jLabel24.setText("Commission   $");

        jLabel25.setText("Maintenance Fees   $");

        jLabel26.setText("Period");

        descrTextArea.setColumns(20);
        descrTextArea.setRows(5);
        jScrollPane2.setViewportView(descrTextArea);

        ownershipBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select", "Individual", "Joint-JTWROS" }));

        periodBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select", "Yearly ", "Semiannually", "Quarterly", "Monthly", "None" }));
        periodBox.setToolTipText("Period to which maintenance fees are charged.  ");

        newTypeButton.setForeground(new java.awt.Color(51, 0, 204));
        newTypeButton.setText("Add");
        newTypeButton.setToolTipText("Add New Account Type");
        newTypeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newTypeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout addAccountTypePanelLayout = new javax.swing.GroupLayout(addAccountTypePanel);
        addAccountTypePanel.setLayout(addAccountTypePanelLayout);
        addAccountTypePanelLayout.setHorizontalGroup(
            addAccountTypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addAccountTypePanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(addAccountTypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addAccountTypePanelLayout.createSequentialGroup()
                        .addGroup(addAccountTypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addAccountTypePanelLayout.createSequentialGroup()
                                .addComponent(descrjTitle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(addAccountTypePanelLayout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(maintenanceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(addAccountTypePanelLayout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(2, 2, 2)
                        .addComponent(minInvestTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(addAccountTypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addAccountTypePanelLayout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(periodBox, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(addAccountTypePanelLayout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(commissionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addAccountTypePanelLayout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(accountTypeTextField)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ownershipBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))))
            .addGroup(addAccountTypePanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(newTypeButton)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        addAccountTypePanelLayout.setVerticalGroup(
            addAccountTypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addAccountTypePanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(addAccountTypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(accountTypeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(ownershipBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addAccountTypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(descrjTitle)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(addAccountTypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(minInvestTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24)
                    .addComponent(commissionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addAccountTypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addAccountTypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel26)
                        .addComponent(periodBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(addAccountTypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel25)
                        .addComponent(maintenanceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(newTypeButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        viewAccountType.addTab("Add Account Type", addAccountTypePanel);

        accountTypeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Ownership", "Description", "Min Invest, $", "Commission, $ ", "Maintenance, $", "Period"
            }
        ));
        jScrollPane4.setViewportView(accountTypeTable);

        updateTypesButton.setForeground(new java.awt.Color(51, 0, 255));
        updateTypesButton.setText("Update");
        updateTypesButton.setToolTipText("Display Account Types");
        updateTypesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateTypesButtonActionPerformed(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/accounttypes.png"))); // NOI18N
        jLabel38.setText("Account Types");

        javax.swing.GroupLayout viewAccountTypesPanelLayout = new javax.swing.GroupLayout(viewAccountTypesPanel);
        viewAccountTypesPanel.setLayout(viewAccountTypesPanelLayout);
        viewAccountTypesPanelLayout.setHorizontalGroup(
            viewAccountTypesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewAccountTypesPanelLayout.createSequentialGroup()
                .addGroup(viewAccountTypesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(viewAccountTypesPanelLayout.createSequentialGroup()
                        .addGroup(viewAccountTypesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(viewAccountTypesPanelLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jLabel38))
                            .addComponent(updateTypesButton))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        viewAccountTypesPanelLayout.setVerticalGroup(
            viewAccountTypesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewAccountTypesPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel38)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(updateTypesButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        viewAccountType.addTab("View Account Types", viewAccountTypesPanel);

        jLabel41.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel41.setText("User Accounts");

        jLabel42.setText("First Name");

        lastNameSearch.setText("Last Name");

        searchUserButton.setForeground(new java.awt.Color(51, 0, 255));
        searchUserButton.setText("Search User");
        searchUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchUserButtonActionPerformed(evt);
            }
        });

        userAccountTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "AcctNo", "Account Type", "Open Date", "Cash, $", "Total Value, $"
            }
        ));
        jScrollPane5.setViewportView(userAccountTable);

        refreshButton.setForeground(new java.awt.Color(51, 0, 255));
        refreshButton.setText("Refresh");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout viewUserAccountsLayout = new javax.swing.GroupLayout(viewUserAccounts);
        viewUserAccounts.setLayout(viewUserAccountsLayout);
        viewUserAccountsLayout.setHorizontalGroup(
            viewUserAccountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewUserAccountsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(viewUserAccountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(viewUserAccountsLayout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addContainerGap())
                    .addGroup(viewUserAccountsLayout.createSequentialGroup()
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(viewUserAccountsLayout.createSequentialGroup()
                        .addComponent(jLabel42)
                        .addGap(18, 18, 18)
                        .addComponent(firstNameSearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lastNameSearch)
                        .addGap(18, 18, 18)
                        .addComponent(lastNameSearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(searchUserButton)
                        .addGap(56, 56, 56))
                    .addGroup(viewUserAccountsLayout.createSequentialGroup()
                        .addGroup(viewUserAccountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(refreshButton)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 613, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        viewUserAccountsLayout.setVerticalGroup(
            viewUserAccountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewUserAccountsLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(viewUserAccountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNameSearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42)
                    .addComponent(lastNameSearch)
                    .addComponent(lastNameSearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchUserButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(refreshButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        viewAccountType.addTab("View User Accounts", viewUserAccounts);

        jLabel27.setText("Enter Account Number");

        closeAccountNumberTextField.setToolTipText("Ensure all assets (stocks) have been sold.");

        closeAccountButton.setForeground(new java.awt.Color(51, 0, 204));
        closeAccountButton.setText("Close Account");
        closeAccountButton.setToolTipText("Close Account");
        closeAccountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeAccountButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout closeAccountPanelLayout = new javax.swing.GroupLayout(closeAccountPanel);
        closeAccountPanel.setLayout(closeAccountPanelLayout);
        closeAccountPanelLayout.setHorizontalGroup(
            closeAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(closeAccountPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(closeAccountNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(closeAccountButton)
                .addContainerGap(203, Short.MAX_VALUE))
        );
        closeAccountPanelLayout.setVerticalGroup(
            closeAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(closeAccountPanelLayout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(closeAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(closeAccountNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(closeAccountButton))
                .addContainerGap(352, Short.MAX_VALUE))
        );

        viewAccountType.addTab("Close Account", closeAccountPanel);

        javax.swing.GroupLayout optionsPanelLayout = new javax.swing.GroupLayout(optionsPanel);
        optionsPanel.setLayout(optionsPanelLayout);
        optionsPanelLayout.setHorizontalGroup(
            optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, optionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(viewAccountType)
                .addContainerGap())
        );
        optionsPanelLayout.setVerticalGroup(
            optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, optionsPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(viewAccountType, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        mainPanel.add(optionsPanel, "card2");

        fileMenu.setText("File");

        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        jMenuBar1.add(fileMenu);

        toolsMenu.setText("Tools");
        jMenuBar1.add(toolsMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE))
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed

        //remove panel
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        //add panel
        mainPanel.add(addPanel);
        mainPanel.repaint();
        mainPanel.revalidate();

        if (state) {
            List<AccountTypeItem> types = qf.getAccountTypes();
            if (!types.isEmpty()) {
                for (AccountTypeItem type : types) {
                    selectAccountTypeBox.addItem(type.getName());
                }
            }
            state = false;
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void viewPortfolioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewPortfolioButtonActionPerformed

        //remove panel
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        //add panel
        mainPanel.add(portfolioPanel);
        mainPanel.repaint();
        mainPanel.revalidate();

        portfolioInfoPanel.setVisible(false);
    }//GEN-LAST:event_viewPortfolioButtonActionPerformed

    private void tradeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tradeButtonActionPerformed

        //remove panel
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        //add panel
        mainPanel.add(tradePanel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_tradeButtonActionPerformed

    private void marketButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_marketButtonActionPerformed

        //remove panel
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        //add panel
        mainPanel.add(marketPanel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_marketButtonActionPerformed

    private void optionsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionsButtonActionPerformed

        //remove panel
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();

        //add panel
        mainPanel.add(optionsPanel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }//GEN-LAST:event_optionsButtonActionPerformed

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        try {
            con.close();
            System.exit(0);
        } catch (SQLException ex) {
            Logger.getLogger(BrokerageUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void submitTradeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitTradeButtonActionPerformed

        /* read from text fields and combo box */
        int acctNo = Integer.parseInt(tradeAccountNumberTextField.getText());
        String ticker = tradeSymbolTextField.getText();
        String type = (String) tradeBox.getSelectedItem();
        int qty = Integer.parseInt(tradeQtyTextField.getText());
        String price = (String) tradePriceBox.getSelectedItem();

        int val = qf.trade(acctNo, ticker, type, qty, price);
        switch (val) {
            case 0: 
                JOptionPane.showMessageDialog(null, "Order Successful");
                break;
            case 1:
                JOptionPane.showMessageDialog(null, "Buy Transaction Failed: Insufficient Funds");
                break;
            case 2: 
                JOptionPane.showMessageDialog(null, "Sell Transaction Failed: Quantity Overload");
                break;
            case 3:
                JOptionPane.showMessageDialog(null, "Market Closed");
                break;
        }
        
        /* restore default values */
        tradeAccountNumberTextField.setText("");
        tradeSymbolTextField.setText("");
        tradeBox.setSelectedIndex(0);
        tradeQtyTextField.setText("");
    }//GEN-LAST:event_submitTradeButtonActionPerformed

    private void addTickerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTickerButtonActionPerformed
       
        DefaultTableModel model = (DefaultTableModel) watchListTable.getModel();
        String ticker = tickerTextField.getText();
        QuoteItem qi = qf.addQuote(ticker);
       
        /* display on market table */
        model.addRow(new Object[]{qi.getTicker(), qi.getExchange(), qi.getLastTrade(),
            qi.getTradeTime(), qi.getTradeDate(), qi.getChange(),
            qi.getPercentChange(), qi.getPrevClose()});

        /* restore to default */
        tickerTextField.setText("");
    }//GEN-LAST:event_addTickerButtonActionPerformed

    private void newAccountButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newAccountButtonActionPerformed

        String accountType = (String) selectAccountTypeBox.getSelectedItem();
        Object[] items = qf.getAccountType(accountType);
        double minInvest = (double) items[0];
        String ownership = (String) items[1];

        if (!jointStatus) {
            int numOwners = Integer.parseInt(numOwnersTextField.getText());
            accountOwners = numOwners;
            double initialInvest = Double.parseDouble(investmentTextField.getText());
            
            int value = qf.addNewAccount(accountType, initialInvest, minInvest);
            if (value == 0) {
                investmentTextField.setText("");
                JOptionPane.showMessageDialog(null, "Initial Investment Value "
                        + "Does Not Satisfy Account Type Criteria\nAccount Not Created Successfully");
                return; // break from method
            }
            acctNoHolder = value;
        }

        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String address = addressTextField.getText();
        String city = cityTextField.getText();
        String zip = zipTextField.getText().replaceAll("[()\\s-]+", "");
        String region = (String) stateBox.getSelectedItem();
        String country = (String) countryBox.getSelectedItem();
        String ssn = ssnTextField.getText().replaceAll("[()\\s-]+", "");
        String phone = phoneTextField.getText().replaceAll("[()\\s-]+", "");
        String email = emailTextField.getText();

        ClientItem ci = new ClientItem(firstName, lastName, address, city, zip,
                region, country, ssn, phone, email);
        
        qf.addClientToAccount(acctNoHolder, ci);

        /* reset to default values */
        firstNameTextField.setText("");
        lastNameTextField.setText("");
        addressTextField.setText("");
        cityTextField.setText("");
        zipTextField.setText("");
        ssnTextField.setText("");
        phoneTextField.setText("");
        emailTextField.setText("");
        stateBox.setSelectedIndex(0);
        countryBox.setSelectedIndex(0);

        accountOwners--;

        if (ownership.equals("Joint-JTWROS") && accountOwners > 0) {
            jointStatus = true;
            JOptionPane.showMessageDialog(null, "Enter Next Owner Information");
        } else {
            /* reset to default values */
            jointStatus = false;
            investmentTextField.setText("");
            selectAccountTypeBox.setSelectedIndex(0);
            numOwnersTextField.setText("");
            JOptionPane.showMessageDialog(null, "Account Created Successfully");
        }
    }//GEN-LAST:event_newAccountButtonActionPerformed

    private void updateMarketButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateMarketButtonActionPerformed

        DefaultTableModel model = (DefaultTableModel) watchListTable.getModel();
        model.setRowCount(0);
        List<QuoteItem> quoteList = qf.updateMarket();
        for (QuoteItem qi : quoteList) {
            /* update quote in market watchlist table */
            model.addRow(new Object[]{qi.getTicker(), qi.getExchange(), qi.getLastTrade(),
                qi.getTradeTime(), qi.getTradeDate(), qi.getChange(),
                qi.getPercentChange(), qi.getPrevClose()});
        }
    }//GEN-LAST:event_updateMarketButtonActionPerformed

    private void viewPortfolioSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewPortfolioSearchButtonActionPerformed

        DefaultTableModel model = (DefaultTableModel) stockPortfolioTable.getModel();
        model.setRowCount(0);
        
        ownerTextField.setText("");
        typeTextField.setText("");
        accountValueTextField.setText("");
        cashTextField.setText("");
        dateOpenTextField.setText("");

        int acctNo = Integer.parseInt(searchAccountTextField.getText());
        Object[] items = qf.updatePortfolio(acctNo);
        PortfolioItem[] piList = (PortfolioItem[]) items[0];
        AccountItem[] aiList = (AccountItem[]) items[1];
        for (PortfolioItem pi : piList) {
            model.addRow(new Object[]{pi.getTicker(), pi.getQty(), pi.getPurchasePrice(),
                pi.getCurrentPrice(), pi.getTotalValue(), pi.getNet()});
        }
        
        String group = qf.getAccountName(acctNo);
        AccountItem ai = aiList[0];
        ownerTextField.setText(group);
        typeTextField.setText(ai.getType());
        accountValueTextField.setText(String.valueOf(ai.getTotalValue()));
        cashTextField.setText(String.valueOf(ai.getCash()));
        dateOpenTextField.setText(ai.getOpenDate());

        portfolioInfoPanel.setVisible(true);
    }//GEN-LAST:event_viewPortfolioSearchButtonActionPerformed

    private void closeAccountButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeAccountButtonActionPerformed

        int acctNo = Integer.parseInt(closeAccountNumberTextField.getText());
        qf.closeAccount(acctNo);
        JOptionPane.showMessageDialog(null, "Account Closed Successfully");
        /* reset to default values */
        closeAccountNumberTextField.setText("");
    }//GEN-LAST:event_closeAccountButtonActionPerformed

    private void updateTypesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateTypesButtonActionPerformed

        DefaultTableModel model = (DefaultTableModel) accountTypeTable.getModel();
        List<AccountTypeItem> typeList = qf.getAccountTypes();
        model.setRowCount(0);
        for (AccountTypeItem ati : typeList) {
            model.addRow(new Object[]{ati.getName(), ati.getOwnership(), ati.getDesc(),
                ati.getMinInvest(), ati.getCommission(), ati.getMaintenance(),
                ati.getPeriod()});
        }
    }//GEN-LAST:event_updateTypesButtonActionPerformed

    private void newTypeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newTypeButtonActionPerformed

        String typeName = accountTypeTextField.getText();
        String ownership = (String) ownershipBox.getSelectedItem();
        String description = descrTextArea.getText();
        double minInvest = Double.parseDouble(minInvestTextField.getText());
        double commission = Double.parseDouble(commissionTextField.getText());
        double maintenance = Double.parseDouble(maintenanceTextField.getText());
        String period = (String) periodBox.getSelectedItem();

        /* perform changes to AcountTypeTable */
        AccountTypeItem ati = new AccountTypeItem(typeName, ownership, description,
            minInvest, commission,
            maintenance, period);
        int val = qf.addNewAccountType(ati);
        switch (val) {
            case 0:
                 /* add new account type to drop down menu on opening account page */
                selectAccountTypeBox.addItem(typeName);
                JOptionPane.showMessageDialog(null, "Account Type Created Successfully");
                break;
            case 1:
                JOptionPane.showMessageDialog(null, "Account Type Failure");
                break;
        }

        // reset to default values
        accountTypeTextField.setText("");
        ownershipBox.setSelectedIndex(0);
        descrTextArea.setText("");
        minInvestTextField.setText("");
        commissionTextField.setText("");
        maintenanceTextField.setText("");
        periodBox.setSelectedIndex(0);
    }//GEN-LAST:event_newTypeButtonActionPerformed

    private void addAmountButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAmountButtonActionPerformed
        
        double amount = Double.parseDouble(amountTextField.getText());
        double cash = Double.parseDouble(cashTextField.getText());
        double accountValue = Double.parseDouble(accountValueTextField.getText());
        int acctNo = Integer.parseInt(searchAccountTextField.getText());
        
        Double[] newValues = qf.addCash(acctNo, amount, cash, accountValue);
        
        /* update textfields */
        amountTextField.setText("");
        cashTextField.setText(String.valueOf(newValues[0]));
        accountValueTextField.setText(String.valueOf(newValues[1]));
    }//GEN-LAST:event_addAmountButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        
        /* reset to default values */
        firstNameTextField.setText("");
        lastNameTextField.setText("");
        addressTextField.setText("");
        cityTextField.setText("");
        zipTextField.setText("");
        ssnTextField.setText("");
        phoneTextField.setText("");
        emailTextField.setText("");
        stateBox.setSelectedIndex(0);
        countryBox.setSelectedIndex(0);
        investmentTextField.setText("");
        selectAccountTypeBox.setSelectedIndex(0);
        numOwnersTextField.setText("");
        
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void searchUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchUserButtonActionPerformed
        DefaultTableModel model = (DefaultTableModel) userAccountTable.getModel();
        String firstName = firstNameSearchTextField.getText();
        String lastName = lastNameSearchTextField.getText();
        
        List<AccountItem> aiList = qf.getUserAccounts(firstName, lastName);
        for (AccountItem ai: aiList) {
            /* display on market table */
            model.addRow(new Object[]{ai.getAcctNo(), ai.getType(), ai.getOpenDate(), ai.getCash(),
                        ai.getTotalValue()});
        }
    }//GEN-LAST:event_searchUserButtonActionPerformed

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        DefaultTableModel model = (DefaultTableModel) userAccountTable.getModel();
        model.setRowCount(0);
        String firstName = firstNameSearchTextField.getText();
        String lastName = lastNameSearchTextField.getText();
        
        List<AccountItem> aiList = qf.getUserAccounts(firstName, lastName);
        for (AccountItem ai: aiList) {
            /* display on market table */
            model.addRow(new Object[]{ai.getAcctNo(), ai.getType(), ai.getOpenDate(), ai.getCash(),
                        ai.getTotalValue()});
        }
    }//GEN-LAST:event_refreshButtonActionPerformed

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
            java.util.logging.Logger.getLogger(BrokerageUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BrokerageUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BrokerageUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BrokerageUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Loading...");
                    Thread.sleep(2500);
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
                new BrokerageUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable accountTypeTable;
    private javax.swing.JTextField accountTypeTextField;
    private javax.swing.JTextField accountValueTextField;
    private javax.swing.JPanel addAccountTypePanel;
    private javax.swing.JButton addAmountButton;
    private javax.swing.JButton addButton;
    private javax.swing.JPanel addPanel;
    private javax.swing.JButton addTickerButton;
    private javax.swing.JTextField addressTextField;
    private javax.swing.JTextField amountTextField;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField cashTextField;
    private javax.swing.JTextField cityTextField;
    private javax.swing.JButton closeAccountButton;
    private javax.swing.JTextField closeAccountNumberTextField;
    private javax.swing.JPanel closeAccountPanel;
    private javax.swing.JTextField commissionTextField;
    private javax.swing.JComboBox countryBox;
    private javax.swing.JTextField dateOpenTextField;
    private javax.swing.JPanel defaultPanel;
    private javax.swing.JTextArea descrTextArea;
    private javax.swing.JLabel descrjTitle;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JTextField firstNameSearchTextField;
    private javax.swing.JTextField firstNameTextField;
    private javax.swing.JTextField investmentTextField;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lastNameSearch;
    private javax.swing.JTextField lastNameSearchTextField;
    private javax.swing.JTextField lastNameTextField;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTextField maintenanceTextField;
    private javax.swing.JButton marketButton;
    private javax.swing.JPanel marketPanel;
    private javax.swing.JTextField minInvestTextField;
    private javax.swing.JButton newAccountButton;
    private javax.swing.JButton newTypeButton;
    private javax.swing.JTextField numOwnersTextField;
    private javax.swing.JButton optionsButton;
    private javax.swing.JPanel optionsPanel;
    private javax.swing.JTextField ownerTextField;
    private javax.swing.JComboBox ownershipBox;
    private javax.swing.JComboBox periodBox;
    private javax.swing.JTextField phoneTextField;
    private javax.swing.JPanel portfolioInfoPanel;
    private javax.swing.JPanel portfolioPanel;
    private javax.swing.JButton refreshButton;
    private javax.swing.JTextField searchAccountTextField;
    private javax.swing.JButton searchUserButton;
    private javax.swing.JComboBox selectAccountTypeBox;
    private javax.swing.JTextField ssnTextField;
    private javax.swing.JComboBox stateBox;
    private javax.swing.JTable stockPortfolioTable;
    private javax.swing.JButton submitTradeButton;
    private javax.swing.JTextField tickerTextField;
    private javax.swing.JMenu toolsMenu;
    private javax.swing.JTextField tradeAccountNumberTextField;
    private javax.swing.JComboBox tradeBox;
    private javax.swing.JButton tradeButton;
    private javax.swing.JPanel tradePanel;
    private javax.swing.JComboBox tradePriceBox;
    private javax.swing.JTextField tradeQtyTextField;
    private javax.swing.JTextField tradeSymbolTextField;
    private javax.swing.JTextField typeTextField;
    private javax.swing.JButton updateMarketButton;
    private javax.swing.JButton updateTypesButton;
    private javax.swing.JTable userAccountTable;
    private javax.swing.JTabbedPane viewAccountType;
    private javax.swing.JPanel viewAccountTypesPanel;
    private javax.swing.JButton viewPortfolioButton;
    private javax.swing.JButton viewPortfolioSearchButton;
    private javax.swing.JPanel viewUserAccounts;
    private javax.swing.JTable watchListTable;
    private javax.swing.JTextField zipTextField;
    // End of variables declaration//GEN-END:variables
}
