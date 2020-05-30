package com.hsuhau.selector;

import com.hsuhau.annotation.EnableServer;
import com.hsuhau.service.Server;
import com.hsuhau.service.impl.FtpServer;
import com.hsuhau.service.impl.HttpServer;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * @author hsuhau
 */
public class ServerImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        // 读取 EnableServer 中所有的属性方法，本例中仅有type()属性方法
        // 其中key为属性方法的名称，value为属性方法的返回对象
        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(EnableServer.class.getName());
        // 获取名为"type"的属性方法，并且强制转换为Server.Type类型
        Server.Type type = (Server.Type) annotationAttributes.get("type");
        // 导入的类名称数组
        String[] importClassNames = new String[0];
        switch (type) {
            // 当设置HTTP服务器类型时，返回HttpServer组件
            case HTTP:
                importClassNames = new String[]{HttpServer.class.getName()};
                break;
            // 当设置FTP服务器类型时，返回FTPServer组件
            case FTP:
                importClassNames = new String[]{FtpServer.class.getName()};
                break;
            default:
                break;
        }
        return importClassNames;
    }
}
