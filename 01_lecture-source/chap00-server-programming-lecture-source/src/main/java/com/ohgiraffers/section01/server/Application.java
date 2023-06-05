package com.ohgiraffers.section01.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Application {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket=new ServerSocket(12341);

        Socket client;
        while((client=serverSocket.accept())!=null){
//            Socket finalClient=client;
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//try {
//                    BufferedReader reader=new BufferedReader(new InputStreamReader(finalClient.getInputStream()));
//
//                    String line;
//                    while ((line=reader.readLine())!=null){
//                        System.out.println("line:"+line);
//                    }
//
//                    reader.close();
//                    finalClient.close();
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//                }
//            }).start();
            //위아래가 같은 코드 람다를 이용해 간단하게 코드를 만듬
            Socket finalClient=client;
            new Thread(()->{
                try {
                    BufferedReader reader=new BufferedReader(new InputStreamReader(finalClient.getInputStream()));

//                    String line;
//                    while ((line=reader.readLine())!=null){
//                        System.out.println("line:"+line);
//                    }  //전체출력
                    
                    String generalHeader=reader.readLine(); //한줄읽기,첫번째 줄이 generalHeader
                    System.out.println("generalHeader = " + generalHeader);

                    String requestMethod=generalHeader.split(" ")[0];
                    String requestPath=generalHeader.split(" ")[1];

                    System.out.println("requestPath = " + requestPath);
                    System.out.println("requestMethod = " + requestMethod);

                    Map<String,String> requestHeader=new HashMap<>();
                    String line;

                    while ((line=reader.readLine())!=null){
                        if(line.isBlank()){
                            break;
                        }
                        String key=line.split(": ")[0];
                        String value=line.split(": ")[1];

                        requestHeader.put(key,value);
                    }
                    System.out.println("requestHeader = " + requestHeader);

                    String body=null;
                    if("POST".equals(requestMethod)){
                        int contentLength=Integer.parseInt(requestHeader.get("Content-Length"));
                        char[] temp=new char[contentLength];
                        reader.read(temp);
                        body=new String(temp).trim();
                    }
                    System.out.println("body = " + body);

                    StringBuilder responseText=new StringBuilder();
                    if("/".equals(requestPath) && "GET".equals(requestMethod)){
                        String responseGeneralHeader="HTTP/1.1 200 OK\r\n";
                        String contentType= "Content-Type:text/html; charset=UTF-8\r\n";
                        String whiteLine="\r\n";

                        responseText.append(responseGeneralHeader);
                        responseText.append(contentType);
                        responseText.append(whiteLine);

                        BufferedReader br= new BufferedReader(new FileReader("src/main/resources/index.html"));
                        String htmlLine=null;
                        while((htmlLine=br.readLine())!=null){
                            responseText.append(htmlLine);
                        }
                        responseText.append(whiteLine);

                    }
                    System.out.println("responseText = " + responseText);
                    OutputStream out=finalClient.getOutputStream();
                    out.write(responseText.toString().getBytes());

                    out.flush();
                    out.close();

                    reader.close();
                    finalClient.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }
}
