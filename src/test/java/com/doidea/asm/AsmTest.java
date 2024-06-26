package com.doidea.asm;

import com.doidea.core.utils.CommonUtil;
import com.doidea.core.utils.FileUtil;

import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public class AsmTest {
    public static void main(String[] args) {

        //Stream.of(Thread.currentThread().getStackTrace()).forEach(System.out::println);
        //new RuntimeException(">>>> Print stacktrace: \n").printStackTrace();
        //System.out.println(UUID.randomUUID().toString()); // 1d1d4ae4-6718-442f-80a0-9006d48dc3f7
        //testShowDialog(null, null, "trial has expired");
        //new JDialog().setTitle("Licenses");
        //testReadConfig();
        //testGetClassLoader();
        //testStackTrace(null);


        String title = "Licenses";
        // title = title.trim(); // 尽可能不改动原 title
        if (title.trim().equalsIgnoreCase("Licenses") || title.trim().equalsIgnoreCase("许可证")) {
            System.out.println(title);
            throw new RuntimeException();
        }
    }

    public static byte[] testUserName(byte[] bArr, long j) {
        System.out.println(new String(bArr));
        System.out.println(j);

        String arg1 = "guest";
        bArr = arg1.getBytes(StandardCharsets.UTF_8);
        System.out.println(new String(bArr));
        return bArr;
    }

    public static byte[] testUserName2(byte[] bArr, long j) {
        System.out.println(new String(bArr));
        System.out.println(j);
        // 每月 14 号变换一次
        LocalDateTime now = LocalDateTime.now();
        //now = LocalDateTime.of(2024, 6, 1, 23, 59, 59, 11);
        int y = now.getYear();
        int m = now.getMonthValue();
        int d = now.getDayOfMonth();
        String arg1 = "MAC-" + y + m + 14;
        if (d >= 14) {
            arg1 = "MAC-" + y + m + 28;
        }
        bArr = arg1.getBytes(StandardCharsets.UTF_8);
        System.out.println(new String(bArr));

        return bArr;
    }

    public static String testMachineId(int r8, int r9) {
        System.out.println(r8);
        System.out.println(r9);
        r8 += 111;
        r9 += 11111;

        String machineId = UUID.randomUUID().toString();
        return machineId;
    }

    public static int testShowDialog(Object obj, String msg, String title) {
        // title = title.trim(); // 尽可能不改动原 title
        if (title.trim().contains("trial has expired") || title.trim().contains("试用已到期")) {
            System.out.println(title);
            return 0;
        }
        return 1;
    }

    public static void testReadConfig() {
        Map<String, String> configMap = FileUtil.readPropConfig("src/main/resources/doidea.properties");
        for (Map.Entry<String, String> entry : configMap.entrySet()) {
            System.out.println("Key=" + entry.getKey() + ", Value=" + entry.getValue());
        }
    }

    public static void testGetClassLoader() {
        try {
            Class<?> aClass = Class.forName("java.lang.Throwable");
            ClassLoader classLoader = aClass.getClassLoader(); // null
            //if (null == classLoader) classLoader = ClassLoader.getSystemClassLoader(); // jdk.internal.loader.ClassLoaders$AppClassLoader@63947c6b
            if (null == classLoader)
                classLoader = ClassLoader.getSystemClassLoader().getParent(); // jdk.internal.loader.ClassLoaders$PlatformClassLoader@776ec8df
            //if (null == classLoader) classLoader = ClassLoader.getSystemClassLoader().getParent().getParent(); // null
            System.out.println(classLoader);
            aClass = Class.forName("java.lang.Class");
            classLoader = aClass.getClassLoader(); // null
            if (null == classLoader) classLoader = ClassLoader.getSystemClassLoader();
            System.out.println(classLoader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testStackTrace(StackTraceElement[] stackTrace) {
        Throwable t = CommonUtil.mixExceptionStackTrace(new SocketTimeoutException(), "com.doidea.");
        t.printStackTrace();
    }
}
