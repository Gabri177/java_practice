package com.yugao.draw.files;

import java.io.*;
import java.util.Properties;

public class create_delete {

    public static void main(String[] args) throws IOException {
        System.out.println("当前工作目录: " + System.getProperty("user.dir"));
        System.out.println("当前类的class" + create_delete.class);
        System.out.println("当前类的class的名字: " + create_delete.class.getName());
        System.out.println("当前类的class的package: " + create_delete.class.getPackage());

        File test = new File("test.properties");
        FileReader fr = new FileReader(test);
        Properties prop = new Properties();
        prop.load(fr);
        fr.close();

        prop.list(System.out);
        prop.setProperty("a", "111111");
        FileWriter nfr =  new FileWriter(test);
        prop.store(nfr, null);
        nfr.close();

        String filePath = "./input.txt";
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
            bw.write("没有这个文件, 创建文件");
            bw.newLine();
            bw.close();
        }

        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line = null;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();

//        File file1 = new File("./Torrente.png");
//        File file2 = new File("./Torrente_copy.png");
//        if (!file1.exists()) {
//            System.out.println("图片不存在");
//        } else {
//            FileInputStream fis = new FileInputStream(file1);
//            FileOutputStream fos = new FileOutputStream(file2);
//
//            while (fis.available() > 0) {
//                fos.write(fis.read());
//            }
//            fis.close();
//            fos.close();
//            System.out.println("图片拷贝完毕");
//
//        }
    }


}
