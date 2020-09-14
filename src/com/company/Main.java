package com.company;

import java.util.Scanner;
import java.io.*;





public class Main {

    final static int word_size = 30;

    static char[] vowelsList = {'a','e', 'o', 'u', 'i'};

    static boolean isLetter (char a){
        return  Character.isLetter(a);
    };

    static boolean checkLength (String a){
        return (a.length() <= word_size);
    };

    static boolean checkForМowels(String a){
        Boolean b = false;
        char[] arr = a.toCharArray();
        for(int i =1; i<arr.length; i++) {
            b = false;
            for (int j = 0; j<vowelsList.length; j++)
            {
                if(arr[i]==vowelsList[j])
                    b=true;
            }
            if (b==false)
                break;
        }
        return b;
    };

    public static void main(String args[])
    {

        Scanner in = new Scanner(System.in);
        System.out.println("Enter path to your text file:");
        String path = in.nextLine();
        try(FileInputStream fis = new FileInputStream(path))
        {
            int i=-1;
            int l;
            String word = "";
            String answer ="";
            if((l = fis.read())==-1) {
                System.out.print("File is empty!");
                System.exit(0);
            }

            while(true){
                l=fis.read();
                if (isLetter((char)l)) {
                    word = word + (char)l;
                }
                else {
                    if(!(checkLength(word)))
                        word = word.substring(0, 31);
                    if(checkForМowels(word)) {
                        if (!(answer.contains(word))) {
                            answer = answer + word;
                        }
                    }
                    word = " ";
                }
                if (l == -1)
                    break;
            }
            System.out.print("\n");
            System.out.print("Founded words:" + answer + ".");
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}