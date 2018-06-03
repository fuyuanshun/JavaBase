package com.fys.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 查询某个网段有多少个可用的ip地址
 */
public class GetAddress {

    public static void main(String[] args) {
        String ip = getAddress();
        howMuchAddress(ip);
    }

    /**
     * 判断本网段有多少可用的IP地址
     */
    private static void howMuchAddress(String ip) {
        String ipRange = ip.substring(0, ip.lastIndexOf("."));
        System.out.println("本机ip地址：" + ip);
        System.out.println("网段是: " + ipRange);

        //线程安全的List
        List<String> ips = Collections.synchronizedList(new ArrayList<>());
        //线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 15, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        //线程安全的Integer
        AtomicInteger number = new AtomicInteger();

        for (int i = 0; i < 255; i++) {
            String testIP = ipRange + "." + (i+1);
            threadPoolExecutor.execute(() ->{
                if (isReachable(testIP)) {
                    ips.add(testIP);
                }
                synchronized (number) {
                    System.out.println("已经有:"+ number.incrementAndGet() + "个地址完成测试");
                }
            });
        }

        System.out.println("如下IP可以连接:");
        for (String IP : ips) {
            System.out.println(IP);
        }

        threadPoolExecutor.shutdown();

        //等待线程池关闭，最多等待一小时
        try {
            if (threadPoolExecutor.awaitTermination(1, TimeUnit.HOURS)) {
                System.out.println("如下ip地址可以连接:");
                for (String IP : ips) {
                    System.out.println(IP);
                }
                System.out.println("总共有: " + ips.size() + "个");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static boolean isReachable(String ip) {
        if (ping(ip).contains("TTL")) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * ping命令
     */
    private static String ping(String ip) {
        try {
            Process process = Runtime.getRuntime().exec("ping " + ip);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String str = "";
            while (null != (str = bufferedReader.readLine())) {
                if (str.length() != 0) {
                    stringBuilder.append(str + "\r\n");
                }
            }
            return stringBuilder.toString();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获得本机IP
     * @return
     */
    private static String getAddress() {
        String ip = "";
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            ip = inetAddress.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return ip;
    }
}