package com.hdumil.aiwriter.test;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
//    public static class ListNode {
//        int val;
//        ListNode next;
//        ListNode pre;
//        Map<Integer,Integer> next_list = new HashMap();
//        public ListNode() {}
//        public ListNode(int val) { this.val = val; }
//        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
//    }
//Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] nums = new int[n][2];
        int res = 0;
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
        for (int i = 0; i < n; i++) {
            nums[i][0] = scanner.nextInt();
            nums[i][1] = scanner.nextInt();
            for (int[] tmp:queue) {
                if(nums[i][0]+tmp[0]<=m){
                    if(nums[i][1]+tmp[1]>res)
                        res = nums[i][1]+tmp[1];
                    break;
                }
            }
            queue.offer(nums[i]);
        }
        System.out.println(res);
    }

    public static void solve(int[] value_nums,int now_indx,int chiyou,int benjin) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[n];
        int[][] dp = new int[n][2];
        int res = 1;
        for (int i = 0; i < n; i++) {
            int x = scanner.nextInt();
            nums[i] = x;
            dp[i][0] = 1;
            dp[i][1] = 1;
            if(i!=0&&nums[i]>nums[i-1]){
                dp[i][0] = Math.max(dp[i][0],dp[i-1][0]+1);
                dp[i][1] = Math.max(dp[i][1],dp[i-1][1]+1);
            }
            for (int j = i-2; j >= 0; j--) {
                if(nums[i]>nums[j]){
                    dp[i][1] = Math.max(dp[i][1],dp[j][0]+1);
                }
            }
            res = Math.max(res,dp[i][0]);
            res = Math.max(res,dp[i][1]);
        }
        System.out.println(res);
    }
}
