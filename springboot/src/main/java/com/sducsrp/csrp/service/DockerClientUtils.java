package com.sducsrp.csrp.service;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.Ports;
import com.github.dockerjava.core.DockerClientBuilder;
import org.springframework.stereotype.Service;


@Service
public class DockerClientUtils {

    /**
     * 连接Docker服务器
     * @return
     */
    public DockerClient connectDocker(String dockerInstance){
        DockerClient dockerClient = DockerClientBuilder.getInstance(dockerInstance).build();
        dockerClient.infoCmd().exec();
        return dockerClient;
    }

    /**
     * 创建容器
     * @param client
     * @return

    public CreateContainerResponse createContainers(DockerClient client, String containerName, String imageName){

        CreateContainerResponse container = client.createContainerCmd(imageName)
                .withName(containerName)
                .exec();

        return container;

    }
     */
    /**
     * 创建容器
     *
     * @param client
     * @param containerName
     * @param imageName
     * @param exposedTcpPort
     * @param bindTcpPort
     * @return
     */
    public CreateContainerResponse createContainer(DockerClient client, String containerName, String imageName,
                                                   int exposedTcpPort, int bindTcpPort) {
        ExposedPort exposedPort = ExposedPort.tcp(exposedTcpPort);
        Ports portBindings = new Ports();
        portBindings.bind(exposedPort, Ports.Binding.bindPort(bindTcpPort));

        CreateContainerResponse container = client.createContainerCmd(imageName)
                .withName(containerName)
                .withHostConfig(HostConfig.newHostConfig().withPortBindings(portBindings))
                .withExposedPorts(exposedPort).exec()
                ;
        return container;
    }


    /**
     * 启动容器
     * @param client
     * @param containerId
     */
    public void startContainer(DockerClient client,String containerId){
        client.startContainerCmd(containerId).exec();
    }

    /**
     * 停止容器
     * @param client
     * @param containerId
     */
    public void stopContainer(DockerClient client,String containerId){
        client.stopContainerCmd(containerId).exec();
    }

    /**
     * 删除容器
     * @param client
     * @param containerId
     */
    public void removeContainer(DockerClient client,String containerId){
        client.removeContainerCmd(containerId).exec();
    }
/*
    public static void main(String[] args) {
        DockerClientUtils dockerClientUtils = new DockerClientUtils();
        //连接Docker服务器
        DockerClient client = dockerClientUtils.connectDocker("tcp://82.157.124.157:2275");
        //创建容器
        CreateContainerResponse container = dockerClientUtils.createContainers(client, "sny_hello1", "hello-world");
        //启动容器
        dockerClientUtils.startContainer(client, container.getId());
        Info info = client.infoCmd().exec();

        String infoStr = JSONObject.toJSONString(info);

        System.out.println(infoStr);
    }
*/
}
