package cn.ok.demos.sbzkdemo.controller;

import cn.ok.demos.sbzkdemo.util.ZkDataListener;
import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 操作zookeeper节点
 *
 * @author kyou on 2019-03-15 08:14
 */
@Slf4j
@RestController
public class DemoController {

    private final String nodePath = "/cn/ok/demos/zkDemo";
    private final ZkClient zkc;
    private final ZkDataListener zkDataListener;

    @Autowired
    public DemoController(ZkClient zkc, ZkDataListener zkDataListener) {
        this.zkc = zkc;
        this.zkDataListener = zkDataListener;
    }

    /**
     * 读取节点存储的数据
     *
     * @param nodePath 节点名称
     * @return 节点存储的数据
     */
    @GetMapping("/get/{nodePath}")
    public String getZkNodeValue(@PathVariable("nodePath") String nodePath) {
        if (!nodePath.startsWith("/")) {
            nodePath += "/";
        }
        return readNode(nodePath);
    }

    private String readNode(String nodePath) {
        if (!zkc.exists(nodePath)) {
            zkc.createPersistent(nodePath, "Default");
        }

        return zkc.readData(nodePath);
    }

    @GetMapping("/addNote")
    public String addNode() {
        zkc.createPersistent(nodePath, true);
        zkc.subscribeDataChanges(nodePath, zkDataListener);
        zkc.writeData(nodePath, "12345678");
        return readNode(nodePath);
    }


    @GetMapping("/updateNode")
    public String updateNode() {
        zkc.writeData(nodePath, "iuytrewq");
        return "Done!";
    }

    @GetMapping("/rmr/{rootNode}")
    public boolean rmr(@PathVariable("rootNode") String rootNode) {
        return zkc.deleteRecursive("/" + rootNode);
    }

    @GetMapping("/addTmpNode")
    public String addTmpNode() {
        String path = "/tmp";

        // 创建的临时目录，当本进程终止后，zookeeper自动删除临时目录。该特性可以用于分布式事务。
        zkc.createEphemeral(path,"TMP_VALUE");
        zkc.subscribeDataChanges(path, zkDataListener);

        return readNode(path);
    }
}
