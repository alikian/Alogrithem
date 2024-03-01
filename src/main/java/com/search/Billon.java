package com.search;

public class Billon {
    public static void main(String[] args) {
        float[] growthRates = {1.1f, 1.2f, 1.3f};
        System.out.println(getBillionUsersDay(growthRates));
    }
    static int getBillionUsersDay(float[] growthRates) {
        // Write your code here
        // 1.1 ^ 40 =10000000
        int left =0;
        int right = 1000;
        int mid=0;
        while(left<right){
            long total=0;
            mid=left+(right-mid)/2;
            for(int i=0;i<growthRates.length;i++){
                total += Math.pow(growthRates[i],mid);
            }
            if(total>1000000000){
                right=mid-1;
            }else{
                left=mid+1;
            }
            System.out.println("left: "+left+" right: "+right+" mid: "+mid);
        }
        return mid;
    }

}
