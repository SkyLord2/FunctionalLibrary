# Java Functional Utilities

轻量级 Java 函数式编程工具库，提供优雅的条件分支、函数组合等操作，适用于 Java 8+ 环境。

[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
![Java Version](https://img.shields.io/badge/Java-8+-orange)

## 功能特性

- 🎯 &zwnj;**条件分支**&zwnj;：`ifElse()` 根据布尔值或谓词选择执行路径
- 🛠️ &zwnj;**函数组合**&zwnj;：`pipe()` 实现类型安全的函数流水线
- 🔀 &zwnj;**条件链**&zwnj;：`cond()` 实现类似 switch-case 的条件匹配逻辑
- 🧩 &zwnj;**记录类支持**&zwnj;：`CondWrap` 清晰定义条件-操作对

## 快速开始
### 条件分支
```java
Function<Integer, String> numClassifier = ifElse(
    n -> n > 0,
    n -> "Positive",
    n -> "Non-positive"
);
System.out.println(numClassifier.apply(5));
```
### 函数流水线
```java
Function<String, String> pipeline = pipe(
    String::toUpperCase,
    s -> s.repeat(2),
    s -> s + "!"
);
System.out.println(pipeline.apply("hello"));  // 输出 "HELLOHELLO!"
```
### 条件链匹配
```java
List<CondWrap<Integer, String>> rules = List.of(
    new CondWrap<>(n -> n > 90, n -> "A"),
    new CondWrap<>(n -> n > 80, n -> "B")
);

Function<Integer, String> grader = cond(
    rules
);
System.out.println(grader.apply(85));
```
