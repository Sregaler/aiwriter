package com.hdumil.aiwriter.base.service.impl;

import com.hdumil.aiwriter.base.service.AiRunByPythonService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AiRunByPythonServiceImpl implements AiRunByPythonService {

//    public static void main(String[] args){
//        System.out.println("runrunrunr");
//    }

    /**
     * 该类调用python实现的深度学习模型进行智能写作
     * 模拟控制台执行
     */
    public void RunByExec(String[] arguments){
//        String[] arguments = new String[] { "python", "./pythonSrc/time.py", "huzhiwei", "25" };
        try {
            Process process = Runtime.getRuntime().exec(arguments);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            int re = process.waitFor();
            System.out.println(re);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
