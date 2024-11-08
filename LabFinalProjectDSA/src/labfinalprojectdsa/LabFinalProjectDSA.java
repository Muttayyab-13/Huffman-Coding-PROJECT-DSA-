/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package labfinalprojectdsa;

/**
 *
 * @author My University
 */
public class LabFinalProjectDSA
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
       
        int[] frequencyArray=calfreq("MOMINmmmmiiii");
        

      int count=0;
           for (int i = 0; i < frequencyArray.length; i++) {
            if (frequencyArray[i] > 0) {
                 count++;
                System.out.println("Character: " + (char) i + " Frequency: " + frequencyArray[i]);
            }
}     System.out.println(count);

/*
         
          int[] strArray=new int[count];
          
          for(int i=0;i<frequencyArray.length;i++)
          {
              if(frequencyArray[i]>0)
              {
                  int j=0;
                  strArray[j++]=frequencyArray[i];
              }
          }
          
                 selectionSort(strArray);
                 
                 for(int i=0;i<strArray.length;i++)
                 {
                     System.out.println(strArray[i]);
                 }
          
         
           
           
          */ 
           
           
           
    }
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
 
 public static void selectionSort(int [] arr)
 {
     int n=arr.length;
     for(int i=0;i<n-1;i++)
     {
         int min=i;
         for(int j=i+1;j<n;j++)
         {
             if(arr[j]<arr[min])
             {
                 min=j;
             }
             
              
            
         }
            int t=arr[i];
                 arr[i]=arr[min];
                 arr[min]=t;
     }
 }
}
