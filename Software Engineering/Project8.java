import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Project8 extends JFrame
{
    private JTextArea textArea = new JTextArea(10,10);  //text area for results
    //each double in the prices array corresponds to the itemname in the same position
    private String [] itemNames = {"bobblehead", "thunder stick", "foam paw", "tee shirt", "sweat shirt","cap", "knit hat", "mug", "pennant" };
    private double [] prices = {12.0,9.0,5.0,15.0,25.0,5.0,10.0,8.0,12.0,3.0};
    //each double in the ticketprices array corresponds to the teamname in the same position
    //and to the logo in the same positon
    private String [] teamNames = {"Redwings","Lions","Tigers","Pistons"};
    private String [] logos2 = {"redwings.gif","lions.gif","tigers.gif","pistons.gif"};
    private ImageIcon [] logos = new ImageIcon[4];
    private double [] ticketprices = {50.0,35.0,100.0,40.0};
    private Icon [] teamIcon = new ImageIcon[4];  //icons
    private double [] seatCharge = {25.0, 50.0, 75.0};
    private int [] indexOfTeam; // storing selection of teams 
    private int teamIndex = 0;   //store selection from JComboBox (index into array of team names)
    private int surChargeIndex = 0; 
    private double surCharge; //store extra charge based on seating choice
    private int numTickets; //to store input from JSlider
    private int [] itemIndex; //to store indices of all selected souvenirs in the JList   
    private String seating; //store selection from JRadioButtons
    private JScrollPane scrollPane; 
    private JList itemList; 
    private JSlider slider; 
    private JComboBox comboBox; 
    private JRadioButton lower;
    private JRadioButton upper;
    private JRadioButton luxury; 
    private JButton clearButton; 
    private double subTotal; 
    private int cntind; 
    private double ticketPrice = ticketprices[teamIndex]; 
    private double seatPrice = seatCharge[surChargeIndex]; 
    private double sub; 
    public static void main(String[] args)
        {
            Project8 p8 = new Project8();
            p8.setVisible(true);
            p8.setSize(1000,500);
        }

    Project8() //default constructor
    { 
            JPanel northPanel = new JPanel();      
            northPanel.setBackground(new Color(16, 114, 65));        
            northPanel.setLayout(new BorderLayout());
            setContentPane(northPanel);
        
            JPanel westPanel = new JPanel();      
            westPanel.setBackground(new Color(32, 21, 104));        
            westPanel.setPreferredSize(new Dimension(150,150));
            westPanel.setLayout(new FlowLayout());
            setContentPane(westPanel);
        
            JPanel centerPanel = new JPanel();    
            centerPanel.setBackground(new Color(21, 121, 164));        
            centerPanel.setLayout(new FlowLayout());
            setContentPane(centerPanel);
        
            JPanel eastPanel = new JPanel();    
            eastPanel.setBackground(new Color(32, 21, 104));       
            eastPanel.setPreferredSize(new Dimension(200,150)); 
            eastPanel.setLayout(new FlowLayout());
            setContentPane(eastPanel);
        
            JPanel southPanel = new JPanel();    
            southPanel.setBackground(new Color(16, 114, 65));        
            southPanel.setLayout(new BorderLayout());
            setContentPane(southPanel);

            JPanel newPanel = new JPanel();    
            newPanel.setBackground(new Color(15, 155, 42));        
            newPanel.setLayout(new BorderLayout());
            setContentPane(newPanel);

            add(southPanel, BorderLayout.SOUTH); 
            add(northPanel, BorderLayout.NORTH);
            add(eastPanel, BorderLayout.EAST);
            add(westPanel, BorderLayout.WEST);
            add(centerPanel, BorderLayout.CENTER);
            
            for (int i = 0; i < logos.length; i++)  
            {                
                logos[i] = new ImageIcon(logos2[i]);        
            }      
            textArea.setFont(new Font("Serif", Font.BOLD, 18));

            JLabel ticket = new JLabel("      TICKETS     TICKETS     TICKETS     ");                  // THE NORTH
            ticket.setForeground(new Color(50, 153, 255));                            
            ticket.setFont(new Font("Serif", Font.BOLD, 50));
            northPanel.add(ticket, BorderLayout.NORTH); 
            
            JLabel seatSelection = new JLabel("Seat Selection:");                                   // THE WEST
            seatSelection.setForeground(new Color(255, 255, 255));          
            seatSelection.setFont(new Font("Serif", Font.BOLD, 20));
            westPanel.add(seatSelection);

            lower = new JRadioButton("lower deck", true);
            lower.setFont(new Font("TimesNewRoman", Font.BOLD, 20));
            lower.setBackground(new Color(32, 21, 104));
            lower.setForeground(new Color(51, 153, 255));
            lower.setPreferredSize(new Dimension(150,80));
            westPanel.add(lower);

            upper = new JRadioButton("upper deck");
            upper.setFont(new Font("TimesNewRoman", Font.BOLD, 20));
            upper.setForeground(new Color(51, 153, 255));
            upper.setBackground(new Color(32, 21, 104));
            upper.setPreferredSize(new Dimension(150,80));
            westPanel.add(upper);

            luxury = new JRadioButton("luxury box");
            luxury.setForeground(new Color(51, 153, 255));
            luxury.setFont(new Font("TimesNewRoman", Font.BOLD, 20));
            luxury.setBackground(new Color(32, 21, 104));
            luxury.setPreferredSize(new Dimension(150,80));
            westPanel.add(luxury);

            ButtonGroup radioGroup = new ButtonGroup();
            radioGroup.add(lower);
            radioGroup.add(upper);
            radioGroup.add(luxury);

            lower.addItemListener(new java.awt.event.ItemListener() 
            {
                public void itemStateChanged(java.awt.event.ItemEvent evt)
                {               
                    lowerItemStateChanged(evt);            
                }        
            });

            upper.addItemListener(new java.awt.event.ItemListener() 
            {
                public void itemStateChanged(java.awt.event.ItemEvent evt)
                {               
                    upperItemStateChanged(evt);            
                }        
            });

            luxury.addItemListener(new java.awt.event.ItemListener() 
            {
                public void itemStateChanged(java.awt.event.ItemEvent evt)
                {               
                    luxuryItemStateChanged(evt);            
                }        
            });

            JLabel teamAndSouvenir = new JLabel("     Team             Souvenirs (CTRL Click)");                   // THE CENTER
            teamAndSouvenir.setForeground(new Color(51, 153, 255));
            teamAndSouvenir.setFont(new Font("Serif", Font.BOLD, 30));
            centerPanel.add(teamAndSouvenir);

            comboBox = new JComboBox(teamNames); 
            comboBox.setMaximumRowCount(4); //set max rows to dixplay all four teams
            comboBox.setPreferredSize(new Dimension(250,150));
            centerPanel.add(comboBox);
            
           comboBox.addItemListener(new ItemListener()
             {
             public void itemStateChanged(ItemEvent e)
                 {
                      if (e.getStateChange() == ItemEvent.SELECTED)
                      {            
                      teamIndex = comboBox.getSelectedIndex();
                      }
                 }
             });

            itemList = new JList(itemNames);
            itemList.setPreferredSize(new Dimension(250,150));
            itemList.setVisibleRowCount(5); 
            scrollPane = new JScrollPane(itemList); //rows to display
            centerPanel.add(scrollPane);   

            itemList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);    
            itemList.addListSelectionListener(new javax.swing.event.ListSelectionListener()
            {
                public void valueChanged(javax.swing.event.ListSelectionEvent evt) 
                {                
                    itemListValueChanged(evt);    
                }        
            });
            centerPanel.add(itemList);

            JLabel Selection = new JLabel("Click Selection");                               // THE EAST
            Selection.setForeground(new Color(51, 153, 255));
            Selection.setBackground(new Color(10, 10, 129));
            Selection.setFont(new Font("TimesNewRoman", Font.ITALIC, 20));
            Selection.setPreferredSize(new Dimension(140,75));
            eastPanel.add(Selection);
            
            JButton addToCart = new JButton("AddToCart");
            addToCart.setForeground(new Color(51, 153, 255));
            addToCart.setBackground(new Color(10, 10, 129));
            addToCart.setFont(new Font("TimesNewRoman", Font.BOLD, 20));
            addToCart.setPreferredSize(new Dimension(250,85));
            eastPanel.add(addToCart);

            clearButton = new JButton("Clear Selection");
            clearButton.setForeground(new Color(51, 153, 255));
            clearButton.setBackground(new Color(10, 10, 129));
            clearButton.setFont(new Font("TimesNewRoman", Font.BOLD, 20));
            clearButton.setPreferredSize(new Dimension(250,85));
            eastPanel.add(clearButton);  //to repaint the interface;  
            
            JButton exitButton = new JButton("Exit");
            exitButton.setForeground(new Color(51, 153, 255));
            exitButton.setBackground(new Color(10, 10, 129));
            exitButton.setFont(new Font("TimesNewRoman", Font.BOLD, 20));
            exitButton.setPreferredSize(new Dimension(250,85));
            eastPanel.add(exitButton);

            clearButton.addActionListener(new ActionListener( ) 
            {
                public void actionPerformed(ActionEvent e) 
                {
                    itemList.clearSelection();  //to clear the JList
                    itemIndex = null;
                    lower.setSelected(true);
                    slider.setValue(0);
                    lower.setSelected(false); //do for each
                    upper.setSelected(false); //do for each
                    luxury.setSelected(false); //do for each
                    surCharge = 0.0; 
                    textArea.selectAll(); 
                    textArea.replaceSelection("");
                    repaint();
                }
            });

            addToCart.addActionListener(new ActionListener( ) 
            {
                public void actionPerformed(ActionEvent e) 
                {
                     
                    textArea.append("\nTEAM   "+ teamNames[teamIndex]);
                    textArea.append("\nTicket Cost $" + ticketprices[teamIndex]); 
                    textArea.append("\nSurcharge $" + seatCharge[surChargeIndex]); 
                    if(e.getSource() == addToCart)
                    {
                    textArea.append("\nItems Ordered: \n"); 
                    displayItems();
                    }
                    textArea.append("\nSubTotal: $" + sub); 
                    textArea.append("\n"); 
                    textArea.append("\nSets Ordered: " + numTickets);
                    textArea.append("\n");
                    textArea.append("\nTotal Cost: $" + (sub * numTickets));
                    JOptionPane.showMessageDialog(null,textArea,"YOUR ORDER",JOptionPane.INFORMATION_MESSAGE,logos[teamIndex]);
                }
            });

            exitButton.addActionListener(e -> System.exit(0));

            JLabel numOfTickets = new JLabel("Select number of tickets:");     // THE SOUTH
            numOfTickets.setForeground(new Color(50, 153, 255));
            numOfTickets.setFont(new Font("TImesNewRoman", Font.BOLD, 30));
            southPanel.add(numOfTickets, BorderLayout.WEST);
            
            slider = new JSlider(numTickets);
            slider.setValue(1);
            slider.setMajorTickSpacing(1);
            slider.setMaximum(10); //set max number to select      
            slider.setMinimum(1);//set min      
            slider.setPaintLabels(true);//allow labels and ticks to be        
            slider.setPaintTicks(true);//displayed      
            slider.setForeground(new Color(50, 153, 255));
            slider.setBackground(new Color(16, 114, 65));
            slider.setPreferredSize(new Dimension(550,50));
            southPanel.add(slider, BorderLayout.EAST);

            slider.addChangeListener(new javax.swing.event.ChangeListener()
            {
                public void stateChanged(javax.swing.event.ChangeEvent evt)
                {    
                sliderStateChanged(evt);
                }
            });
    } 
        private void itemListValueChanged(javax.swing.event.ListSelectionEvent evt) // methods for handlers
            {        
                itemIndex = itemList.getSelectedIndices(); //itemindex is an array which will now contain the index of each item selected from the list    }//end: itemListValueChanged
            } 
        private void sliderStateChanged(javax.swing.event.ChangeEvent evt)
            {
                numTickets = slider.getValue();
            }
        private void lowerItemStateChanged(java.awt.event.ItemEvent evt) 
            {
                seating = ("lower deck seating"); 
            } 
        private void upperItemStateChanged(java.awt.event.ItemEvent evt) 
            {
                seating = ("upper deck seating"); 
            } 
        private void luxuryItemStateChanged(java.awt.event.ItemEvent evt) 
            {
                seating = ("luxury deck seating"); 
            } 
            public void displayItems()
            {	   
                for (int i = 0; i < itemIndex.length; i++)
                {
                    textArea.append(itemNames[itemIndex[i]] + " $" + (prices[itemIndex[i]])+ "\n");
                    subTotal += prices[itemIndex[i]];
                    sub = ticketPrice + seatPrice + subTotal; 
                    cntind++;
                }// index of team, team names 
            }
            
} 
