package com.sunyk.servlet;

import com.sunyk.annotation.Autowired;
import com.sunyk.annotation.Controller;
import com.sunyk.annotation.Service;
import com.sunyk.demo.controller.DemoController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 启动核心Servlet
 *
 * Create by sunyang on 2019/3/3 17:02
 * For me:One handred lines of code every day,make myself stronger.
 */
public class DispatchServlet extends HttpServlet {

    private Properties contextConfig = new Properties();

    private Map<String, Object> beanMap = new ConcurrentHashMap<String, Object>();

    private List<String> classNames = new ArrayList<String>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("--------- doPost START ---------------");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {

        //定位
        doLoadConfig(config.getInitParameter("contextConfigLocation"));

        //加载
        doScan(contextConfig.getProperty("scanPackage"));

        //注册
        doRegister();

        //自动依赖注入
        doAutowired();

        DemoController demoController = (DemoController) beanMap.get("demoController");
        demoController.index(null,null,"sunyk");
        //handerMapping 映射 URL关系

    }

    private void doLoadConfig(String location) {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(location.replace("classpath:", ""));
        try {
            contextConfig.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 自动依赖注入
     * 如果IOC容器是空直接返回
     * 迭代beanMap ，如果不是autowired，继续
     * 如果是，取beanName，如果是空，则取类型的名称
     * 赋值，把当前的value当key，beanMap中取beanName当value
     */
    private void doAutowired() {
        if (beanMap.isEmpty()){
            return;
        }
        try {
            for (Map.Entry<String, Object> entry : beanMap.entrySet()){
                Field[] fields = entry.getValue().getClass().getDeclaredFields();

                for (Field field : fields){
                    if (!field.isAnnotationPresent(Autowired.class)){
                        continue;
                    }
                    Autowired autowired = field.getAnnotation(Autowired.class);
                    String beanName = autowired.value().trim();
                    if ("".equals(beanName)){
                        beanName = field.getType().getName();
                    }
                    field.setAccessible(true);

                    field.set(entry.getValue(), beanMap.get(beanName));
                }

            /*if (!"autowired".equals(entry.getValue().getClass().getAnnotation(Autowired.class))){
                continue;
            }
            Field[] fields = entry.getClass().getDeclaredFields();
            for (Field field : fields){

                if ("".equals(field.getName().trim())){
                    String beanName = String.valueOf(field.getType());
                }
            }*/


            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    /**
     * 把扫到的类开始进行注册
     * 根据类型去添加beanMap
     * 是controller和server 首字母小写添加，如果有自定义的有限使用自定义
     * 是接口的话，接口名添加
     *
     */
    private void doRegister() {
        if (classNames.isEmpty()){
            return;
        }
        try {
            for (String clazz :classNames){
                Class<?> clazzName = Class.forName(clazz);
                if (clazzName.isAnnotationPresent(Controller.class)){

                    String beanName = lowChangeFirst(clazzName.getSimpleName());
                    beanMap.put(beanName, clazzName.newInstance());
                }else if (clazzName.isAnnotationPresent(Service.class)){
                    Service service = clazzName.getAnnotation(Service.class);
                    String beanName = service.value();
                    if ("".equals(beanName)){
                        beanName = lowChangeFirst(clazzName.getSimpleName());
                    }
                    Object instance = clazzName.newInstance();
                    beanMap.put(beanName,instance);
                    Class<?>[] interfaces = clazzName.getInterfaces();
                    for (Class<?> i : interfaces){
                        beanMap.put(i.getName(), instance);
                    }
                }else{
                    continue;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    private String lowChangeFirst(String simpleName) {
        char[] chars = simpleName.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    private void doScan(String packageName) {
        if ("".equals(packageName)){
            return;
        }
        /**
         * 扫描包的路径
         * 需要要组装路径
         * 通过文件取出所有目录
         * 取出所有的路径
         * 递归判断是否是目录
         * 添加要扫描的包名
         */
        URL url = this.getClass().getClassLoader().getResource("/" + packageName.replaceAll("\\.","/"));
        File classDir = new File(url.getFile());
        File[] classDirs = classDir.listFiles();
        for (File file : classDirs){
            if (file.isDirectory()){
                doScan(packageName + "." + file.getName());
            }else{
                classNames.add(packageName + "." + file.getName().replace(".class", ""));
            }
        }
    }


}
