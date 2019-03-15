package cn.ok.demos.sbzkdemo.config;

import cn.ok.demos.sbzkdemo.util.MyZkSerializer;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * cn.ok.demos.sbzkdemo.config
 *
 * @author kyou on 2019-03-15 10:15
 */
@Configuration
public class ZkConfig {
    private final MyZkSerializer zkSerializer;

    @Value("${zkAddrs}")
    String zkAddrs;

    @Autowired
    public ZkConfig(MyZkSerializer zkSerializer) {
        this.zkSerializer = zkSerializer;
    }

    @Bean
    ZkClient zkClient() {
        return new ZkClient(zkAddrs, 30000, 30000, zkSerializer);
    }
}
