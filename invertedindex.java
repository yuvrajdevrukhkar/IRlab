package com.company.invertedindex;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class invertedindex {
    public static void displayIndex(ArrayList<String> invertedData, int[][] docno) {
        for (int i = 0; i < invertedData.size(); i++) {
            System.out.print(invertedData.get(i) + "\t");
            for (int j = 1; j <= docno[i][0]; j++) {
                System.out.print(docno[i][j] + "\t");
            }
            System.out.print("\n");
        }
    }

    public static void indexing(String fname, ArrayList<String> invertedData, int[][] docno, int fileno) {
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(fname));
            String data = "", line = br.readLine();
            while (line != null) {
                data += line + " ";
                line = br.readLine();
            }
            String[] st = data.split("[ ,.]");
            String currenttoken = null;
            int i = 0;
            while (i < st.length) {
                currenttoken = st[i];
                int indx = invertedData.indexOf(currenttoken);
                if (indx == -1) {
                    invertedData.add(currenttoken);
                    indx = invertedData.indexOf(currenttoken);
                    docno[indx][0] = 1;
                    docno[indx][1] = fileno;
                } else {
                    docno[indx][docno[indx][0] + 1] = fileno;
                    docno[indx][0] += 1;
                }
                i += 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        String fname = "";
        ArrayList<String> invertedData = new ArrayList<>();
        int docno[][] = new int[100][10];
        InputStreamReader ins = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ins);
        System.out.println("\nENTER TOTAL NO OF FILES:");
        int no = Integer.parseInt(br.readLine());
        int i = 1;
        while (i - 1 != no) {
            System.out.println("\nENTER FILE " + i + " NAME:");
            fname = br.readLine();
            indexing(fname, invertedData, docno, i);
            i += 1;
        }
        displayIndex(invertedData, docno);
    }
}
