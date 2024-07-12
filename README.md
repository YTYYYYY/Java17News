# Java 17 新特性



## 1.Lambda表达式

 ### 1.1 基本格式

例1：

~~~java
Runnable r1 = new Runnable() {
    @Override
    public void run() {
    System.out.println("r1 is running");
    }
};
~~~

~~~java
//Lambda表达式写法
Runnable r2 = () -> {
    System.out.println("r2 is running");
};
~~~

例2：

~~~java
Comparator<Integer> com1 = new Comparator<Integer>() {
    @Override
    public int compare(Integer o1, Integer o2) {
    	return Integer.compare(o1,o2);
    }
};
~~~

~~~java
//Lambda写法
Comparator<Integer> com2 = (o1,o2) -> Integer.compare(o1,o2);
~~~

~~~java
//方法引用
Comparator<Integer> com3 = Integer::compare;
~~~

### 1.2 类型推断

数据类型省略

~~~java
Consumer<String> com1 = new Consumer<String>() {
    @Override
    public void accept(String s) {
    	System.out.println(s);
    }
};
~~~

~~~java
//Lambda写法
Consumer<String> com2 = (s) -> {
	System.out.println(s);
};
//若形参只有一个可省去小括号：
//Consumer<String> com2 = s -> { System.out.println(s); };
~~~

### 1.3 四大核心函数式接口

| 函数式接口    | 名称       | 参数类型 | 作用                                                         |
| ------------- | ---------- | -------- | ------------------------------------------------------------ |
| Consumer<T>   | 消费型接口 | T        | 对类型为T的对象应用操作，包括方法：void accept(T t)          |
| Supplier<T>   | 供给型接口 | 无       | 返回类型为T的对象，包括方法： T get()                        |
| Function<T,R> | 函数型接口 | T        | 对类型为T的对象应用操作，并返回结果。结果是R类型的对象，包括方法：R apply(T t) |
| Predicate<T>  | 判断型接口 | T        | 确定类型为T的对象是否满足某约束条件，并返回boolean值。包括方法：boolean test(T t) |



## 2.方法引用

> 可以看作是基于Lambda表达式进一步刻画
>
> 当需要提供一个函数式接口实例时，我们可以使用Lambda表达式引用
>
> ​	> 当满足一定的条件下，我们可以使用方法引用或构造器引用来替代Lambda表达式

~~~
//格式：类(或对象) :: 方法名
~~~

### 2.1 对象 :: 实例方法

~~~java
Consumer<String> con1 = new Consumer<String>() {
    @Override
    public void accept(String s) {
        System.out.println(s);
    }
};
~~~

~~~java
//方法引用
Consumer<String> con1 = System.out::println;
~~~

### 2.2 类 :: 静态方法

~~~java
Comparator<Integer> com1 = new Comparator<Integer>() {
    @Override
    public int compare(Integer o1, Integer o2) {
        return Integer.compare(o1,o2);
    }
};
~~~

~~~java
//方法引用
Comparator<Integer> com1 = Integer::compare;
~~~

~~~java
Function<Double,Long> fun1 = new Function<Double, Long>() {
    @Override
    public Long apply(Double aDouble) {
        return Math.round(aDouble);
    }
};
~~~

~~~java
//方法引用
Function<Double, Long> fun1 = Math::round;
~~~

### 2.3 类 :: 实例方法

~~~java
Comparator<String> com1 = new Comparator<String>() {
    @Override
    public int compare(String o1, String o2) {
        return o1.compareTo(o2);
    }
};
~~~

~~~java
//方法引用
Comparator<String> com1 = String::compareTo;
~~~



## 3.Stream API

> Stream API 关注的是多个数据的计算（排序、查找、过滤、映射、遍历等）
>
> 集合关注的是数据的存储，向下存储的。

Stream的执行流程：

1.Stream的实例化 

2.一系列的中间操作

3.执行终止操作

### 3.1 实例化

#### 3.1.1 方式一.通过集合

~~~java
Stream<Integer> stream1 = Arrays.stream(ListData.getArrData());
~~~

#### 3.1.2 方式二.通过数组

~~~java
Stream<String> stream2 = ListData.getListData().stream();
Stream<String> stream3 = ListData.getListData().parallelStream();
~~~

#### 3.1.3 方式三.通过Stream的of()

~~~java
Stream<String> stream4 = Stream.of("a", "b", "c", "d");
~~~

### 3.2 中间操作

#### 3.2.1 筛选与切片

| 方法                | 描述                                                         |
| ------------------- | ------------------------------------------------------------ |
| filter(Predicate p) | 接收Lambda，从流中排除某些元素                               |
| distinct()          | 筛选，通过流所生成元素的hashcode()和equals()去除重复元素     |
| limit(long maxSize) | 截断流，使其元素不超过给定数量                               |
| skip(long n)        | 跳过元素，返回一个扔掉了前n个元素的流<br />若流中元素不足n个，则返回一个空流，与limit(n)互补 |

~~~java
ListData.getListData().stream()
                .filter(data -> data.length()==3)
                .limit(5)
                .distinct()
                .forEach(System.out::println);
~~~

#### 3.2.2 映射

| 方法                            | 描述                                                         |
| ------------------------------- | ------------------------------------------------------------ |
| map(Function f)                 | 接受一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。 |
| mapToDouble(toDoubleFunction f) | 接受一个函数作为参数，该函数会被应用到每个元素上，产生一个新的DoubleStream。 |
| mapToInt(toIntFunction f)       | 接受一个函数作为参数，该函数会被应用到每个元素上，产生一个新的IntStream。 |
| mapToLong(toLongFunction f)     | 接受一个函数作为参数，该函数会被应用到每个元素上，产生一个新的LongStream。 |
| flatMap(Function f)             | 接受一个函数作为参数，将流中的每个值都换成另一个值，然后把所有流连接成一个流。 |

~~~java
ListData.getListData().stream()
                .map(str -> "#"+str)
                .forEach(System.out::println);
~~~

#### 3.2.3 排序

| 方法                   | 描述                                 |
| ---------------------- | ------------------------------------ |
| sorted()               | 产生一个新流，其中按照自然顺序排序   |
| sorted(Comparator com) | 产生一个新流，其中按照比较器顺序排序 |

```java
Arrays.stream(ListData.getArrData())
                .sorted(com1)
                .forEach(System.out::println);
```

### 3.3 终止操作

#### 3.3.1 匹配与查找

| 方法                  | 描述                     |
| --------------------- | ------------------------ |
| allMatch(Predicate p) | 检查是否匹配所有元素     |
| anyMatch(Predicate p) | 检查是否至少匹配一个元素 |
| findFirst()           | 返回第一个元素           |
| count()               | 返回元素个数             |
| max/mix(Comparator c) | 返回最大/最小值          |
| foreach(Consumer c)   | 内部迭代                 |

~~~java
System.out.println(ListData.getListData().stream()
                .allMatch(data -> data.length() == 3));
System.out.println(ListData.getListData().stream()
                .findFirst().get());
~~~

#### 3.3.2 规约

| 方法                               | 描述                                                         |
| ---------------------------------- | ------------------------------------------------------------ |
| reduce(T identity, BinaryOperator) | 可以将流中的元素反复结合起来，得到一个新的值。返回T          |
| reduce(BinaryOperator)             | 可以将流中的元素反复结合起来，得到一个新的值。返回Optional<T> |

~~~java
System.out.println(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).stream()
                .reduce(0, (x1, x2) -> x1 + x2));
~~~

#### 3.3.3 收集

| 方法                 | 描述                                                         |
| -------------------- | ------------------------------------------------------------ |
| collect(Collector c) | 将流转换成其他形式。<br />接收一个Collector接口的实现，用于给Stream中元素做汇总的方法。 |

~~~java
List<Integer> collect = Arrays.stream(ListData.getArrData())
                .sorted()
                .collect(Collectors.toList());
~~~



## 4.新语法结构

### 4.1 Java的REPL工具：jShell命令

~~~shell
cmd> jshell
|  欢迎使用 JShell -- 版本 17.0.9
|  要大致了解该版本, 请键入: /help intro
jshell> System.out.println("Hello World!");
Hello World!
~~~

### 4.2 异常处理之try-catch资源关闭

> jdk7之前：

~~~java
@Test
    public void te1(){
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("text.txt"));
            char[] chs = new char[1024];
            int len;
            while((len = br.read(chs)) != -1){
                System.out.print(String.valueOf(chs,0,len));
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            //关闭BufferedReader
            try {
                if (br != null) br.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
~~~

> jdk8：

~~~java
@Test
public void te1(){
    try(
        //在try中声明自动关闭资源
        BufferedReader br = new BufferedReader(new FileReader("text.txt"))
    ) {
        char[] chs = new char[1024];
        int len;
        while((len = br.read(chs)) != -1){
            System.out.print(String.valueOf(chs,0,len));
        }
    }catch (IOException e){
    	e.printStackTrace();
    }
}
~~~

> jdk9：

~~~java
@Test
public void te1(){
    InputStreamReader isr = new InputStreamReader(System.in);
    //在try中直接声明变量
    try(isr) {
        char[] chs = new char[1024];
        int len;
        while((len = isr.read(chs)) != -1){
            System.out.print(String.valueOf(chs,0,len));
        }
    }catch (IOException e){
        e.printStackTrace();
    }
}
~~~

### 4.3 局部变量类型判断 ( jdk10 )

~~~java
@Test
public void te1(){
    //局部变量实例化
    var list = new ArrayList<String>();
    var map = new LinkedHashMap<>();

    //增强for循环的索引
    for(var i : list){
    	System.out.println(i);
    }

    //传统for循环
    for(var i=0;i<100;i++){
    	System.out.println(i);
    }
}
~~~

### 4.4 instanceof 的模式匹配

~~~java
@Test
public void te1(){
    Object str = new String("Hello");
    if (str instanceof String){
        String s = (String)str; 
        System.out.println(s);
    }
}
~~~

> jdk14:

~~~java
@Test
public void te1(){
    Object str = new String("Hello");
    if (str instanceof String s){
    	System.out.println(s);
    }
}

~~~

### 4.5 switch 表达式

jdk12之前：

~~~java
switch (day){
    case MONDAY: {
        System.out.println(1);
        break;
    }
    case TUESDAY: {
        System.out.println(2);
        break;
    }
    case WEDNESDAY:
    case THURSDAY:
    case FRIDAY:
    case SATURDAY:
    case SUNDAY:{
        System.out.println(7);
        break;
    }
}
~~~

jdk12：

~~~java
//省去了break，避免case穿透
switch (day){
    case MONDAY -> System.out.println(1);
    case TUESDAY -> System.out.println(2);
    case WEDNESDAY,THURSDAY,FRIDAY,SATURDAY,SUNDAY -> System.out.println(7);
}
~~~

~~~java
//还可以用变量接收switch表达式的结果
int res = switch (day){
    case MONDAY -> 1;
    case TUESDAY -> 2;
    case WEDNESDAY,THURSDAY,FRIDAY,SATURDAY,SUNDAY -> 7;
};
~~~

jdk13：

> 引入了人yield关键字，用于返回指定的数据，结束switch结构

~~~java
int res = switch (day){
    case MONDAY -> {yield 1;}
    case TUESDAY -> {yield 2;}
    case WEDNESDAY,THURSDAY,FRIDAY,SATURDAY,SUNDAY -> {yield 7;}
};
~~~

jdk17：模式匹配（预览）



### 4.6 文本块

>用三个引号来表示文本块

~~~java
String str1 = """
        <html>
            <body>
            	<div>hello</div>
            </body>
        </html>
    """;
~~~

 

### 4.7 Record

..

### 4.8 密封类

..



## 5.Optional API

> 为了避免代码中出现空指针异常
>
> 实例化：static <T> Optional<T> ofNullable(T value)

~~~java
String str = null;
//1.实例化
//ofNullable(T value)：用来创建一个Optional实例，value可能是空，也可能是非空
Optional<String> optional = Optional.ofNullable(str);
//orElse(T other)
String otherStr = "other";
String finalStr = optional.orElse(otherStr);
System.out.println(finalStr.toString());
~~~
