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

    static boolean checkForVowels(String a){
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

    static boolean inAnswer(String [] a, String b){
        for (int i = 0;i< a.length;i++)
        {
            if(b == a[i])
                return true;
        }
        return false;
    };

    static String[] toAnswer(String[] a, String b, int i) {
        String[] new_a = new String[i];
        for(int j =0; j< a.length; j++)
        {
            new_a[j]=a[j];
        };
        new_a[i-1]=b;
        a=new_a;
        return a;
    };

    public static void main(String args[])
    {

        Scanner in = new Scanner(System.in);
        System.out.println("Enter path to your text file:");
        String path = in.nextLine();
        try(FileInputStream fis = new FileInputStream(path))
        {
            int i=1;
            int l;
            String word = "";
            String[] answer = new String[] {};
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
                    if(!(checkLength(word))) {
                        word = word.substring(0, 30);
                    }
                    if(checkForVowels(word)) {
                        if(!(inAnswer(answer, word)))
                        {
                            answer=toAnswer(answer, word, i);
                            i++;
                        }
                    }
                    word = " ";
                }
                if (l == -1)
                    break;
            }
            fis.close();
            System.out.print("\n");
            System.out.print("Founded words:"+ "\n");
            for (String s : answer) System.out.print(s + "\n");
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}