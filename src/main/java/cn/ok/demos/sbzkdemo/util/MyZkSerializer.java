package cn.ok.demos.sbzkdemo.util;

import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.Charset;

/**
 * cn.ok.demos.sbzkdemo.util
 *
 * @author kyou on 2019-03-15 09:43
 */
@Configuration
public class MyZkSerializer implements ZkSerializer {
    public Object deserialize(byte[] bytes) throws ZkMarshallingError {

        return new String(bytes, Charset.forName("UTF-8"));
    }

    public byte[] serialize(Object obj) throws ZkMarshallingError {
        return String.valueOf(obj).getBytes(Charset.forName("UTF-8"));
    }
}
