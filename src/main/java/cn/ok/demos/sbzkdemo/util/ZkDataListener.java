package cn.ok.demos.sbzkdemo.util;

import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.IZkDataListener;
import org.springframework.context.annotation.Configuration;

/**
 * cn.ok.demos.sbzkdemo.util
 *
 * @author kyou on 2019-03-15 11:03
 */
@Slf4j
@Configuration
public class ZkDataListener implements IZkDataListener {
    @Override
    public void handleDataChange(String dataPath, Object data) {
        log.info("Path({}) 's value has been changed to {} ", dataPath, data);
    }

    @Override
    public void handleDataDeleted(String dataPath) {
        log.info("Path({}) has been deleted", dataPath);
    }
}
