package searching;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import searching.Algo_BruteForce;

import searching.ResultModel;
import javax.swing.JScrollBar;
public class DataEntry extends JFrame {

    private JPanel contentPane;
    private JTextField inputField;
    private JLabel SelectAlgorithmLabel;
    private JTextArea ImportText;
    private final JFileChooser openFileChooser;
    private JTextArea ResultArea;
    /**
     * Launch the application.
     */


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DataEntry frame = new DataEntry();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public DataEntry() {
        openFileChooser = new JFileChooser();
        openFileChooser.setCurrentDirectory(new File("c:\\temp"));
        openFileChooser.setFileFilter(new FileNameExtensionFilter("TXT file","txt"));
        openFileChooser.setFileFilter(new FileNameExtensionFilter("fna file","fna"));

        openFileChooser.setFileFilter(new FileNameExtensionFilter("fn file","fn"));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(550, 550, 700, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);


        inputField = new JTextField();
        inputField.setBounds(180, 65, 139, 23);
        contentPane.add(inputField);
        inputField.setColumns(10);

        JLabel SearchStringLabel = new JLabel("Search String");
        SearchStringLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        SearchStringLabel.setBounds(37, 65, 133, 21);
        contentPane.add(SearchStringLabel);

        SelectAlgorithmLabel = new JLabel("Select Algorithm");
        SelectAlgorithmLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        SelectAlgorithmLabel.setBounds(37, 100, 133, 21);
        contentPane.add(SelectAlgorithmLabel);

        JScrollPane ImportTextscrollPane = new JScrollPane();
        ImportTextscrollPane.setBounds(382, 29, 279, 393);
        contentPane.add(ImportTextscrollPane);

        ImportText = new JTextArea();
        ImportTextscrollPane.setViewportView(ImportText);
        ImportText.setLineWrap(true);

        JScrollPane ResultAreascrollPane = new JScrollPane();
        ResultAreascrollPane.setBounds(37, 222, 318, 203);
        contentPane.add(ResultAreascrollPane);


        ResultArea = new JTextArea();
        ResultAreascrollPane.setViewportView(ResultArea);
        ResultArea.setWrapStyleWord(true);
        ResultArea.setLineWrap(true);
        ResultArea.setEditable(false);

        String[] algoString = {"Brute Force", "Rabin Karp", "KMP"};
        JComboBox AlgorithmDropDown = new JComboBox(algoString);
        AlgorithmDropDown.setBounds(180, 102, 139, 23);
        contentPane.add(AlgorithmDropDown);

        JButton SearchButton = new JButton("Search");
        SearchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if(inputField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please key in search string!");
                }else {
                    String SearchSubString = inputField.getText();
                    String SearchMainString = ImportText.getText();
                    String algo = String.valueOf(AlgorithmDropDown.getSelectedItem());
                    int found;
                    if(algo == "Brute Force") {
                        ResultModel result = Algo_BruteForce.search(SearchMainString,SearchSubString);
                        found = result.getFoundCount();
                        String resultString ="\n------------------------------------------ \n" + "Search by Brute Force Algorithm \n"
                                + found + " items found in given text \nPosition: " + result.getPosition() +"\nTotalTime: " + result.getExecutionTime() +"milliseconds";
                        ResultArea.append(resultString);
                    }else if(algo == "Rabin Karp") {
                        ResultModel result = Algo_Rabin_Karp.search(SearchMainString,SearchSubString);
                        found = result.getFoundCount();
                        String resultString ="\n------------------------------------------ \n" + "Search by Rabin Karp Algorithm \n"
                                + found + " items found in given text \nPosition: " + result.getPosition() + "\nTotalTime: " + result.getExecutionTime() +"milliseconds";
                        ResultArea.append(resultString);
                    }else if(algo == "KMP") {
                        ResultModel result = Algo_KMP.search(SearchMainString,SearchSubString);
                        found = result.getFoundCount();
                        String resultString ="\n------------------------------------------ \n" + "Search by KMP Algorithm \n"
                                + found + " items found in given text \nPosition: " + result.getPosition() + "\nTotalTime: " + result.getExecutionTime() +"milliseconds";;
                        ResultArea.append(resultString);
                    }


                }


            }
        });


        SearchButton.setForeground(Color.DARK_GRAY);
        SearchButton.setFont(new Font("Tahoma", Font.PLAIN, 18));

        SearchButton.setBounds(37, 152, 133, 21);
        contentPane.add(SearchButton);



        JLabel ReadFileLabel = new JLabel("Read from file");
        ReadFileLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        ReadFileLabel.setBounds(37, 29, 133, 19);
        contentPane.add(ReadFileLabel);

        JButton ReadFileButton = new JButton("Open file...");
        ReadFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int returnValue = openFileChooser.showOpenDialog(SearchButton);
                BufferedReader br = null;
                if(returnValue == JFileChooser.APPROVE_OPTION) {

                    try {
                        br = new BufferedReader(new FileReader(openFileChooser.getSelectedFile()));
                        Scanner scan = new Scanner(br);
                        String ss = "";
                        while(scan.hasNextLine()) {
                            ss += scan.nextLine();
                        }
                        ImportText.setText(ss);

                    } catch (FileNotFoundException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    } catch (NumberFormatException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }finally {
                        try {
                            br.close();
                        }catch(Exception e1){
                            System.out.println(""+e1);
                        }
                    }

                }else {

                }
            }
        });
        ReadFileButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
        ReadFileButton.setBounds(180, 30, 139, 25);
        contentPane.add(ReadFileButton);



        JLabel lblNewLabel = new JLabel("Result:");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel.setBounds(37, 183, 109, 29);
        contentPane.add(lblNewLabel);


    }
}
