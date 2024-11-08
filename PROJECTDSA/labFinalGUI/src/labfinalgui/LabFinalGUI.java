/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package labfinalgui;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

class Node {
    char character;
    int frequency;
    Node left;
    Node right;

    // LEAF NODE k liye//
    Node(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }

    // INTERNAL NODE k liye//
    Node(int frequency, Node left, Node right) {
        this.character = '\0';
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }
}

public class LabFinalGUI extends JFrame {
    private JTextField inputField;
    private JTextArea binaryOutput;
    private JTextArea huffmanOutput;

    public LabFinalGUI() {
        
        setTitle("Muttayyab");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

       
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

      
        inputField = new JTextField("Enter text");
        inputField.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(inputField);

        
        binaryOutput = new JTextArea("Binary Output");
        binaryOutput.setFont(new Font("Arial", Font.PLAIN, 20));
        binaryOutput.setBackground(Color.LIGHT_GRAY);
        binaryOutput.setEditable(false);
        binaryOutput.setLineWrap(true);
        binaryOutput.setWrapStyleWord(true);
        panel.add(new JScrollPane(binaryOutput));

        
        huffmanOutput = new JTextArea("Huffman Output");
        huffmanOutput.setFont(new Font("Arial", Font.PLAIN, 20));
        huffmanOutput.setBackground(Color.LIGHT_GRAY);
        huffmanOutput.setEditable(false);
        huffmanOutput.setLineWrap(true);
        huffmanOutput.setWrapStyleWord(true);
        panel.add(new JScrollPane(huffmanOutput));

       
        add(panel, BorderLayout.CENTER);

        // Button to process the input
        JButton processButton = new JButton("Process");
        processButton.setFont(new Font("Arial", Font.BOLD, 20));
        processButton.setBackground(Color.GREEN);
        add(processButton, BorderLayout.SOUTH);
        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = inputField.getText();
                binaryOutput.setText(stringToBinary(inputText));
                huffmanOutput.setText(encodeHuffman(inputText));
            }
        });
    }

    private String stringToBinary(String input) {
        StringBuilder binaryString = new StringBuilder();
        for (char character : input.toCharArray()) {
            String binaryChar = Integer.toBinaryString(character);
            while (binaryChar.length() < 8) {
                binaryChar = "0" + binaryChar;
            }
            binaryString.append(binaryChar);
        }
        return binaryString.toString();
    }

    // Function to encode a string using Huffman encoding
    private String encodeHuffman(String input) {
        int[] Charactersfrequency = new int[256];
        for (int i = 0; i < input.length(); i++) {
            Charactersfrequency[input.charAt(i)]++;
        }

        // counting and extratring characters and their frequencies
        int uniqueCharactersCount = 0;
        for (int i = 0; i < 256; i++) {
            if (Charactersfrequency[i] > 0) {
                uniqueCharactersCount++;
            }
        }

        char[] characters = new char[uniqueCharactersCount];
        int[] frequenciesOfCharacters = new int[uniqueCharactersCount];
        int index = 0;
        for (int i = 0; i < 256; i++) {
            if (Charactersfrequency[i] > 0) {
                characters[index] = (char) i;
                frequenciesOfCharacters[index] = Charactersfrequency[i];
                index++;
            }
        }

        // Build Huffman Tree
        Node root = buildHuffmanTree(characters, frequenciesOfCharacters);

        // Generate Huffman Codes
        String[] huffmanCode = new String[256];
        generateCodes(root, "", huffmanCode);

        // Encode the input text
        StringBuilder encodedText = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            encodedText.append(huffmanCode[input.charAt(i)]);
        }
        return encodedText.toString();
    }

    // Method to sort nodes by frequency
    public static void bubblesortNodes(Node[] nodes, int size) {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (nodes[j].frequency > nodes[j + 1].frequency) {
                    Node temp = nodes[j];
                    nodes[j] = nodes[j + 1];
                    nodes[j + 1] = temp;
                }
            }
        }
    }

    // Making the Huffman tree
    public static Node buildHuffmanTree(char[] characters, int[] frequencies) {
        int size = characters.length;
        Node[] nodes = new Node[size];

        for (int i = 0; i < size; i++) {
            nodes[i] = new Node(characters[i], frequencies[i]);
        }

        while (size > 1) {
            // Sort nodes by frequency
            bubblesortNodes(nodes, size);

            // Getting the two nodes with the lowest frequency after sorting in ascending order using the Sorting algorithm
            Node right = nodes[0];
            Node left = nodes[1];

            // Creating a new internal node with these two nodes as children and sum of freq of child as value
            Node newNode = new Node(left.frequency + right.frequency, left, right);

            // Move all nodes one position to the left and insert the new node at the end
            for (int i = 2; i < size; i++) {
                nodes[i - 2] = nodes[i];
            }
            nodes[size - 2] = newNode;

            size--;
        }

        // The remaining node is the root of the Huffman tree
        return nodes[0];
    }

    public static void generateCodes(Node n, String code, String[] huffmanCode) {
        if (n == null) {
            return;
        }

        // If this is a leaf node we store the code because we have come till last node
        if (n.left == null && n.right == null) {
            huffmanCode[n.character] = code;
            return;
        }

        // Recursively calling till we reach the last/leaf node
        generateCodes(n.left, code + '0', huffmanCode);
        generateCodes(n.right, code + '1', huffmanCode);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
        {
            new LabFinalGUI().setVisible(true);
        });
    }
}
