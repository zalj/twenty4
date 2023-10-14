package designpattern.observer.service.my;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

import java.math.BigInteger;

public class Room {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Room.class);
        enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
            long start = System.currentTimeMillis();
            Object res = methodProxy.invokeSuper(o, objects);
            long end = System.currentTimeMillis();
            System.out.println("cost " + (end - start) + "ms");
            return res;
        });
        Room r = (Room) enhancer.create();
        String s = r.extracted(240);
        r.pow2(s);
    }

    public String extracted(int pow) {
        BigInteger b = new BigInteger("1");
        for (int i = 0; i < pow; i++) {
            b = b.multiply(new BigInteger("2"));
        }
        return b.toString();
    }

    public String pow2(String s) {
        BigInteger res = new BigInteger(s).pow(2);
        return res.toString();
    }


}
