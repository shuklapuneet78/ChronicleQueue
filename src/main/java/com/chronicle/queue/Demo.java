package com.chronicle.queue;

import net.openhft.chronicle.Chronicle;
import net.openhft.chronicle.ChronicleQueueBuilder;
import net.openhft.chronicle.ExcerptAppender;
import net.openhft.chronicle.ExcerptTailer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Demo {
    public static void main(String[] args) {

        File queueDir = null;
        Chronicle chronicle = null;
        try {
            queueDir = Files.createTempDirectory("chronicle-queue").toFile();
            chronicle = ChronicleQueueBuilder.indexed(queueDir).build();

            ExcerptAppender appender = chronicle.createAppender();
            appender.startExcerpt();

            String stringValue = "Hello World";
            int intValue = 101;
            long longValue = System.currentTimeMillis();
            double doubleValue = 90.00192091d;

            appender.writeUTF(stringValue);
            appender.writeInt(intValue);
            appender.writeLong(longValue);
            appender.writeDouble(doubleValue);
            appender.finish();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Wrote to file. Now to read...");
        ExcerptTailer tailer = null;
        try {
            tailer = chronicle.createTailer();
            while (tailer.nextIndex()) {
                System.out.println(tailer.readUTF());
                System.out.println(tailer.readInt());
                System.out.println(tailer.readLong());
                System.out.println(tailer.readDouble());
            }
            tailer.finish();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
