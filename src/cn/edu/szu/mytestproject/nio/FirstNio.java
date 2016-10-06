package cn.edu.szu.mytestproject.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Author : hengyilin
 * Date   : 16-10-1
 * Version: 1.0
 * Email  : hylin601@126.com
 * Github : www.github.com/hengyilin
 */
public class FirstNio {
    public FirstNio() throws FileNotFoundException {
    }

    public static void main(String[] args) {
        try {
            RandomAccessFile file = new RandomAccessFile("/home/hengyilin/StopThread.java", "rw");
            FileChannel channel = file.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(48);
            int byteRead = channel.read(buffer);
            while (byteRead != -1) {
                System.out.println("Read:" + byteRead);
                buffer.flip();
                while (buffer.hasRemaining()) {
                    System.out.println((char) buffer.get());
                }
                buffer.clear();
                byteRead = channel.read(buffer);
            }
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
