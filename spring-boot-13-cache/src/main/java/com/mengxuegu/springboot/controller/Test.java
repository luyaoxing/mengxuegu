//package com.mengxuegu.springboot.controller;
//
//import java.util.Arrays;
//import java.util.Scanner;
//
///**
// * @program: spring-boot-10-bill
// * @description: 测试实验课代码
// * @author: Lunatic Princess
// * @create: 2019-03-13
// * Detailed time at 10:40
// **/
//
//public class Test {
//
//    public static void main(String[] args) {
//        double chang[] = new double[3];
//        Scanner sc = new Scanner(System.in);
//        boolean flag = true;
//        int n = 0;
//        System.out.println("--------开始--------");
//        while(flag){
//            if(n > 0){
//                System.out.println("是否继续：F-停止，T-继续  tip:已忽略大小写");
//                String str = sc.next();
//                if("F".equals(str)){
//                    System.out.println("--------结束--------");
//                    break;
//                }else  if(!"T".equals(str)){
//                    System.out.println("操作不合法，请重新输入：");
//                    continue;
//                }
//            }
//            System.out.println("请输入三角形三条边：");
//            for (int i = 0; i < chang.length; i++) {
//                chang[i] = sc.nextDouble();
//            }
//            n++;
//            Arrays.sort(chang); //从小到大排序
//            if(chang[0] + chang[1] > chang[2]){  //两边之和大于第三边
//                //System.out.println(Math.sqrt(2)); //1.4142135623730951
//                //System.out.println(Math.pow(1.4142135623730951,2)); //2.0000000000000004
//                //由于不能输入根号2，就用1.4142135623730951代替，模拟一下直角三角形，平方之后是2.0000000000000004
//                if((int)(chang[2]*chang[2]) == (int)(chang[1]*chang[1]) + (int)(chang[0]*chang[0])){
//                    if(chang[2] == chang[1] || chang[2] == chang[0] || chang[1] == chang[0]){
//                        System.out.println("该三角形是等腰直角三角形");
//                        continue;
//                    }else{
//                        System.out.println("该三角形是直角三角形");
//                        continue;
//                    }
//                }else if(chang[0] == chang[1] && chang[1]== chang[2]){
//                    System.out.println("该三角形是等边三角形");
//                    continue;
//                }else if(chang[2] == chang[1] || chang[2] == chang[0] || chang[1] == chang[0]){
//                    System.out.println("该三角形是等腰三角形");
//                    continue;
//                }else{
//                    System.out.println("该三角形是普通三角形");
//                    continue;
//                }
//            }else{
//                System.out.println("不是三角形");
//                continue;
//            }
//        }
//
//    }
//}
