package com.lab;

public class ShippingCalculator {
    public double calculate(double weight, String type) {
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be positive");
        }
        if (type.equals("EXPRESS")) return weight * 5000 + 20000;
        if (type.equals("STANDARD")) return weight * 3000;
        throw new IllegalArgumentException("Unknown type: " + type);
    }
}
/*
**Error:**  The goal you specified requires a project to execute but there is no POM in this directory (/home/runner/work/BuiThiHuyenLy_25021861_week09/BuiThiHuyenLy_25021861_week09). Please verify you invoked Maven from the correct directory. -> [Help 1]
1. ⇒ thiếu checkout  nên github actions không tải code xuống

⇒ thư mục trống ⇒ không tìm thấy pom

lỗi nằm ở file bai10.yml, dòng 9,10 ( đã sửa)

1. ⇒ sai đường dẫn
- file bai10.yml
- do project có nhiều module nên maven không biết build cái nào nên phải chỉ rõ bai 10

**Error:**  Failed to execute goal on project bai10: Could not resolve dependencies for project org.example:bai10:jar:1.0-SNAPSHOT

**Error:**  dependency: ch.qos.logback:logback-classic:jar:9.9.9 (compile)

**Error:**  	Could not find artifact ch.qos.logback:logback-classic:jar:9.9.9 in central (https://repo.maven.apache.org/maven2)

⇒ logback version 9.9.9 không tồn tại

⇒ maven cố tải nhưng version không tồn tại nên không resolve

- file: bai10/pom.xml
- dòng 20

**Error:**  Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.15.0:compile (default-compile) on project bai10: Fatal error compiling: error: invalid target release: 25 -> [Help 1]

3. => sai java version ( không tồn tại)
- file: pom.xml
- dòng 10/11

4. lỗi tự nghĩ :

Run actions/setup-java@v3

Installed distributions

**Error:** No supported distribution was found for input abc

- lỗi ở distribution: nhà phân phối
- file bai 10.yml
 */

