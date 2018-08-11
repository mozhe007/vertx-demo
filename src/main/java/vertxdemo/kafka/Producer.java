package vertxdemo.kafka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Producer {

    public static final String TOPIC = "test";
    private static Properties properties;

    static {
        properties = new Properties();
        String path = Producer.class.getResource("/config/kafka.properties").getPath();
        try {
            FileInputStream fis = new FileInputStream(new File(path));
            properties.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送消息
     *
     * @param topic
     * @param key
     * @param value
     */
    public void sendMsg(String topic, byte[] key, byte[] value) {
        // 实例化produce
        KafkaProducer<byte[], byte[]> kp = new KafkaProducer<byte[], byte[]>(
                properties);
        // 消息封装
        ProducerRecord<byte[], byte[]> pr = new ProducerRecord<byte[], byte[]>(
                topic, key, value);
        System.out.println("发送数据");
        // 发送数据
        kp.send(pr, new Callback() {
            // 回调函数
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if (null != exception) {
                    System.out.println("记录的offset在:" + metadata.offset());
                    System.out.println(exception.getMessage() + exception);
                }
            }
        });
        // 关闭produce
        kp.close();
    }

    public static void main(String[] args) {
        Producer producer = new Producer();
        producer.sendMsg(Producer.TOPIC, "test111111".getBytes(), "testv11111111".getBytes());
    }
}
