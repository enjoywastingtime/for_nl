package com;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;

public class ClassMain {
public static void main(String[] args) {
	System.out.println(new SecureRandomNumberGenerator().nextBytes(1));
}
}
