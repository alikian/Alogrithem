package com;

class Pascal{
    public static void main(String args[]){
        Pascal pascal=new Pascal();
        pascal.print(12);
    }

    void print(int n){
        System.out.printf("%6d\n", 1);
        System.out.printf("%6d%6d\n", 1,1);
        int preLine[]={1,1};
        int currentLine[];
        for(int line=2;line<n;line++){
            currentLine = new int[line+1];
            currentLine[0]=1;
            currentLine[line]=1;
            for(int i=1;i<line;i++){
                currentLine[i] = preLine[i-1]+preLine[i];
            }
            preLine= currentLine;
            for(int i=0;i<=line;i++){
                System.out.printf("%6d", preLine[i]);
            }
            System.out.println();
        }
    }
}