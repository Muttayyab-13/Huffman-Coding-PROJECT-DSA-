/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package labfinaldsa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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

 class LabFinaldsa {

    
    public static void main(String[] args) {
        
        
        
        String filePath = "C:\\Users\\My University\\Desktop\\LabFinaldsa\\src\\labfinaldsa\\newfile.txt";

        // Read file and store content in 'text' variable
        StringBuilder text = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                text.append(line);
            }
        } catch (IOException e) {
        }
        
         // Convert the string to its binary representation
        String binaryOutput = stringToBinary(text.toString());
        
        String outputFilePath = "C:\\Users\\My University\\Desktop\\LabFinaldsa\\src\\labfinaldsa\\binary.txt";

        // Write the binary representation to the output file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath))) {
            bw.write(binaryOutput);
        } catch (IOException e) {
        }

        // Now 'text'is containing the content of the file{converted to string}
        System.out.println("File content: " + text.toString());

        //String text = "ABRACADABRA";

        // Calculating frequency of characters that exist
        int[] Charactersfrequency = new int[256];
        for (int i = 0; i < text.length(); i++) {
            Charactersfrequency[text.charAt(i)]++;
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

        // Building a Huffman Tree
        Node root = buildHuffmanTree(characters, frequenciesOfCharacters);

        // Generatimg  Huffman Codes
        String[] huffmanCodeStorage = new String[256];
        makeHuffmanCode(root, "", huffmanCodeStorage);

        // Printing  Huffman code after generating it.
        for (int i = 0; i < 256; i++) {
            if (huffmanCodeStorage[i] != null) {
                System.out.println((char) i + ": " + huffmanCodeStorage[i]);
            }
        }

        // Encoding  the input text that is combining compression bits
        StringBuilder encodedText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            encodedText.append(huffmanCodeStorage[text.charAt(i)]);
        }
        System.out.println("Encoded Text: " + encodedText.toString());
        
         
         
         
        
        String en= encodedText.toString();
        int numberOfCharacters = en.length();
        System.out.println("Number of Compressed Bits = "+numberOfCharacters);
        
         String outputPath = "C:\\Users\\My University\\Desktop\\LabFinaldsa\\src\\labfinaldsa\\output.txt";

        // Write encoded text to a text file
        try (FileWriter fw = new FileWriter(outputPath)) {
            fw.write(en);
           // System.out.println("Encoded text has been written to File " );
        } catch (IOException e) {
        }
    }
    
    /////////////                   METHODS USED IN MAIN CLASS                               /////////////
    
        // Method to sort nodes by frequency using Bubble Sort(Ascending Order)
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

            // Getting  the two nodes with the lowest frequency after sorting in ascending order using the Sorting algorithm
            Node right = nodes[0];
            Node left = nodes[1];

            // Creating  a new internal node with these two nodes as children and sum of freq of child as value
            
            Node newNode = new Node(left.frequency + right.frequency, left, right);

           //(Deletion and insertion of new node) // we are Moving all nodes one position to the left and insert the new node at the end
            for (int i = 2; i < size; i++) {
                nodes[i - 2] = nodes[i];
            }
            nodes[size - 2] = newNode;

            size--;
        }

        // The remaining node is the root of the Huffman tree that is Root Node that we will return 
        return nodes[0];
    }

 
    public static void makeHuffmanCode(Node n, String charCode, String[] huffmanCodeStore) {
        if (n == null) {
            return;
        }

        // If this is a leaf node we store the code because we have come till last node
        if (n.left == null && n.right == null) {
            huffmanCodeStore[n.character] = charCode;
            return;
        }

        // Recursively calling till we reach the last/leaf node
        
       makeHuffmanCode(n.left, charCode + '0', huffmanCodeStore);
        makeHuffmanCode(n.right, charCode + '1', huffmanCodeStore);
    }
    
   public static String stringToBinary(String input) {
    StringBuilder binaryString = new StringBuilder();
    int bitCount = 0; // Initialize bit count

    for (char character : input.toCharArray()) {
        String binaryChar = Integer.toBinaryString(character);
        
        
        while (binaryChar.length() < 8) {
            binaryChar = "0" + binaryChar;
        }
        
        // Append binaryChar and a space to binaryString
        binaryString.append(binaryChar).append(" ");
        bitCount += 8; // Increment bit count by 8 for each character
    }
    
    // Trim the trailing space and return the result with bit count
    String result = binaryString.toString();
    System.out.println("Total number of bits: " + bitCount);
    return result;
}

        }
    
    


