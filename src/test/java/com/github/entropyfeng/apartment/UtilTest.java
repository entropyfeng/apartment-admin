package com.github.entropyfeng.apartment;

import com.github.entropyfeng.apartment.config.AuthProperties;
import com.github.entropyfeng.common.util.JWTUtil;
import com.github.entropyfeng.common.util.JwtAccount;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.loader.JarLauncher;
import org.springframework.boot.loader.archive.Archive;
import org.springframework.boot.loader.archive.JarFileArchive;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.zip.ZipEntry;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UtilTest {

    @Autowired
    AuthProperties authProperties;

    @Test
    public void test(){
      JwtAccount account= JWTUtil.parseJwt("eyJhbGciOiJIUzM4NCIsInppcCI6IkRFRiJ9.eNpMjcEKwjAQRP9lzw1skppIP8KLR9tDttlApdXSJCCI_-7WXpzTzGOGecO9TNCBS62z5pTUaJBUGxMqCiMp8g5Hpw1Z8tBAriRlI27KWVzkFOpc9hwKdNrps0W0Hhvg1yoA8Z9tz5n32a2HXGrkR-lhkPHK2_Ljw_FxCQtLvOIhjfD5AgAA__8.4Vpsbb2ChGf9SKCzrX0jj8g6PLYMaKtjCa0zq1ciFUv_xEmKJNZyXcPlVmaWrmyQ",authProperties.getJwtSecretKey());
      account.getRoles();

    }

    static class FileClassLoader extends ClassLoader{


        public FileClassLoader(byte[] bytes){
            this.bytes=bytes;
        }

        private byte[] bytes;
        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {

            return defineClass(name,bytes,0, bytes.length);

        }

        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            return super.loadClass(name);
        }
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        System.out.println("hello");


        JarFile jarFile = new JarFile("C:\\Users\\lxf\\Desktop\\demo-springboot.jar");
/*        JarEntry temp;
        Enumeration enumeration=jarFile.entries();
        while (enumeration.hasMoreElements()){
            temp=(JarEntry) enumeration.nextElement();
            System.out.println(temp.getName());
            if (temp.getName().startsWith("BOOT-INF/classes/com/example/demo/HelloController")){

               InputStream inputStream= jarFile.getInputStream(temp);

                System.out.println("qq");
            }
        }*/

        File file=new File("C:\\Users\\lxf\\Desktop\\demo-springboot.jar");

        org.springframework.boot.loader.jar.JarFile jarFile1=new org.springframework.boot.loader.jar.JarFile(file);

        Enumeration<JarEntry> enumeration= jarFile1.entries();
        while (enumeration.hasMoreElements()){
            System.out.println(enumeration.nextElement().getName());
        }
        ZipEntry xx= jarFile1.getEntry("BOOT-INF/classes/com/example/demo/HelloController.class");

        InputStream inputStream= jarFile1.getInputStream(xx);

        int av=inputStream.available();
        byte[] bytes=new byte[(int)xx.getSize()];
        inputStream.read(bytes);
        File outFile=new File("Haha.class");
        FileOutputStream fileOutputStream=new FileOutputStream(outFile);
        fileOutputStream.write(bytes);
        fileOutputStream.close();
        FileClassLoader fileClassLoader=new FileClassLoader(bytes);
       Class<?> clazz=  fileClassLoader.loadClass("com.example.demo.HelloController");
      boolean isLoaderClass= clazz.isLocalClass();


        ZipEntry zipEntry= jarFile1.getEntry("BOOT-INF/classes/com/example/demo/HelloController");

        JarFileArchive jarFileArchive=new JarFileArchive(jarFile1);
        Manifest manifest=jarFileArchive.getManifest();
        Archive.EntryFilter filter=new Archive.EntryFilter() {
            @Override
            public boolean matches(Archive.Entry entry) {
             return    entry.getName().startsWith("BOOT-INF/classes/com/example/demo/DemoApplication.class");

            }
        };

        List<Archive> archiveList= jarFileArchive.getNestedArchives(filter);
        for (Archive.Entry entry : jarFileArchive) {
            System.out.println(entry.getName());
        }
        URL[] urls = new URL[1];
        urls[0] = file.toURI().toURL();
        URLClassLoader urlClassLoader = new URLClassLoader(urls);



        ProtectionDomain protectionDomain=UtilTest.class.getProtectionDomain();
        CodeSource codeSource=protectionDomain.getCodeSource();
        URL url= codeSource.getLocation();
        String className = "com.example.demo.HelloController";
        Method method = urlClassLoader.loadClass(className).getDeclaredMethod("echo");

        Object obj= urlClassLoader.loadClass(className).newInstance();
        method.invoke(obj);





        System.out.println("qeqrwe");
    }
}
