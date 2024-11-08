/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labfinalprojectdsa;

/**
 *
 * @author My University
 */
public class Huffman
{
    public static int[] calfreq(String str)
    {
        int [] frequencyArray=new int[256];
        for(int i=0;i<str.length();i++)
        {
           char ch=str.charAt(i);
           frequencyArray[ch]++;
           
        }
        return frequencyArray;
    }  
//    public int[] calfreq(String str)
//    {
//        int [] frequencyArray=new int[256];
//        for(int i=0;i<str.length();i++)
//        {
//           char ch=str.charAt(i);
//           frequencyArray[ch]++;
//           
//        }
//        return frequencyArray;
//    }
//   
//     calfreq("MOMIN");
//    
}
