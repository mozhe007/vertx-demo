package vertxdemo.kafka;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Properties;

public class Consumer {

    private final static String TOPIC = "test";

    private static Properties properties;

    static {
        properties = new Properties();
        String path = Consumer.class.getResource("/config/kafka.properties").getPath();
        try {
            FileInputStream fis = new FileInputStream(new File(path));
            properties.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取消息
     *
     * @throws Exception
     */
    public void getMsg() {
        // 实例化produce
        KafkaConsumer<byte[], byte[]> kc = new KafkaConsumer<byte[], byte[]>(
                properties);
        kc.subscribe(Arrays.asList(Producer.TOPIC));
        while (true) {
            ConsumerRecords<byte[], byte[]> records = kc.poll(100);
            for (ConsumerRecord<byte[], byte[]> record : records)
                System.out.printf("记录消费 offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
        }
    }

    public static void main(String[] args) {
        Consumer consumer = new Consumer();
        consumer.getMsg();
    }
}