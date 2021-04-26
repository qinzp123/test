package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.*;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void timeTest() {
        long now = Instant.now().toEpochMilli();
        long offset = Duration.ofDays(1).toMillis();

        long toNanos = Duration.ofDays(1).toNanos();

        System.out.println(now);
        System.out.println(offset);
        System.out.println(toNanos);
    }

    @Test
    private  void replaceTest() {
        String ss = "已推送(在线)";
        System.out.println(ss.substring(0,ss.indexOf("(")));
    }

/*    @Test
    private static void EnumTest() {
        List<TypeEnum> aLl = EnumUtil.getALl(TypeEnum.class);
        System.out.println(aLl);
        Integer s = -1;
        System.out.println(s);
    }*/

    @Test
    public void testStream() {
        List<UserBean> userBeanList = Arrays.asList(new UserBean(1, "张三", 10, "男"),
                new UserBean(2, "李四", 15, "男"),
                new UserBean(3, "王五", 18, "女"),
                new UserBean(4, "赵柳", 15, "男"));
        List<UserBean> beanList = userBeanList.stream().filter(userBean ->
                userBean.getAge() > 10 && userBean.getAge() < 18
        ).collect(toList());
        beanList.forEach((t) -> System.out.println(t.toString()));

//        String string = userBeanList.stream().filter(userBean -> userBean.getAge() == 11).findAny().orElseGet(() -> ss("a")).toString();
        UserBean string = userBeanList.stream().filter(userBean -> userBean.getAge() == 12).findAny().orElse(null);
        System.out.println(string);
//        userBeanList.stream().map(JacksonUtil::toJson).forEach(System.out::println);

        Map<Integer, Long> list1 = userBeanList.stream().collect(groupingBy(UserBean::getAge, Collectors.counting()));
        System.out.println("list1"+list1);
        Map<Integer, Integer> list2 = userBeanList.stream().collect(groupingBy(UserBean::getAge, Collectors.summingInt(UserBean::getId)));
        System.out.println("list2"+list2);
        Map<Integer, List<UserBean>> list3 = userBeanList.stream().collect(groupingBy(UserBean::getAge));
        System.out.println("list3"+list3);
        Map<Integer, Set<String>> list4 = userBeanList.stream().collect(groupingBy(UserBean::getAge, Collectors.mapping(UserBean::getName, Collectors.toSet())));
        System.out.println("list4"+list4);

        Map<Integer, List<String>> list5 = userBeanList.stream().collect(groupingBy(
                UserBean::getAge,
                collectingAndThen(toList(), list -> list.stream().map(UserBean::getSex).collect(toList()))));
        System.out.println("list5"+list5);

    }

    public static UserBean ss(String name) {
        System.out.println(name + "执行了方法");
        return new UserBean(5, "haha", 15, "男");
    }


    private static void testTwoParaSum(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        int rel = list.stream().reduce(20,Integer::sum);
//        int rel = list.stream().reduce(Integer::sum).get();
        System.out.println(rel);
    }

    public static void testTime() {
    /*    Long pvPlanPlusValue=50000L;
        Long startTime = 1592203610696L;
        Long endTime = 1593499610696L;
        long pvPlusValue = pvPlanPlusValue * (2000L / (endTime - startTime));
        System.out.println(2000L / (endTime - startTime));
        double a = new BigDecimal(((float)2000 / (endTime - startTime))).setScale(8, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(a*pvPlanPlusValue);
        System.out.println(startTime/1000*1000);*/

        Long now = 1593590390000L;
        Long startTime = 1592294391971L;
        Long endTime = 1593590391000L;
        Long pvPlanPlusValue = 5000000L;
        long pvPlusValue = (now - startTime) * pvPlanPlusValue / (endTime - startTime);
        System.out.println(pvPlusValue);
    }

    public static class UserBean {
        private Integer id;
        private String Name;
        private Integer age;
        private String sex;

        public UserBean(Integer id, String name, Integer age, String sex) {
            this.id = id;
            Name = name;
            this.age = age;
            this.sex = sex;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        @Override
        public String toString() {
            return "UserBean{" +
                    "id=" + id +
                    ", Name='" + Name + '\'' +
                    ", age=" + age +
                    ", sex='" + sex + '\'' +
                    '}';
        }
    }



    @Test
    public void whenCheckIfPresent_thenOk() {
        UserBean user = new UserBean(1, "zhangsan",12,"男");
        Optional<UserBean> opt = Optional.ofNullable(user);
   /*     assertTrue(opt.isPresent());

        assertEquals(new Integer(11), opt.get().getAge());*/

        opt.ifPresent(userBean -> System.out.println(userBean));
    }

    @Test
    public void flatMapTest() {
        List<Integer> integers = new ArrayList<>();
        integers.addAll(Arrays.asList(7, 8, 9));
        // 添加数据略
        integers.stream().map(i -> i + 1).forEach(System.out::println);
        // 而使用flatmap使我们能够操作更深一层的数据，如下：

        List<List<Integer>> outer = new ArrayList<>();

        List<Integer> inner1 = new ArrayList<>();

        inner1.add(1);
        inner1.add(2);
        inner1.add(3);
        inner1.add(4);
        inner1.add(5);

        List<Integer> inner2 = new ArrayList<>();
        List<Integer> inner3 = new ArrayList<>();
        List<Integer> inner4 = new ArrayList<>();
        List<Integer> inner5 = new ArrayList<>();

        outer.add(inner1);
        outer.add(inner2);
        outer.add(inner3);
        outer.add(inner4);
        outer.add(inner5);

        List<Integer> result = outer.stream().flatMap(inner -> inner.stream().map(i -> i + 1)).collect(toList());

        System.out.println(result);
    }

    @Test
    public void testRegx() {
        String str = "这是标题Qa11 !@###&)(*&^%$#%^{~^:<>?}{+\uD83D\uDE1D";
        Pattern pattern = Pattern.compile("[^\\u0000-\\uFFFF]");
        Matcher matcher = pattern.matcher(str);
        System.out.println(matcher.find());
    }

    @Test
    public void testDuplicateElement() {
        //取出重复的数据
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(1);
        list.add(2);
        Map<Integer, Long> countMap = list.stream().collect(groupingBy(Function.identity(), Collectors.counting()));
        List<Integer> flag = countMap.keySet().stream().filter(key -> countMap.get(key) > 1).collect(Collectors.toList());
        System.out.println(flag);
    }

    @Test
    public void testReplace() {
        String ss = "1223.000";
        String replace = ss.replaceAll("\\.0*", "");
        System.out.println(replace);
    }

    @Test
    public void testList() {
        List<UserBean> userBeanList = new ArrayList<>();
//        userBeanList.add(new UserBean(1, "zhangsan",12,"男"));
        System.out.println(userBeanList.get(0));
    }

   /* @Test
    public void desensitization() {
        String idCardName = "万云";
        String idCardNo = "410603199111082512";
        String businessName = "北京哈哈哈信息技术公司";
        String taxpayerCode = "1222A";
        idCardName = StringUtils.rightPad(StringUtils.left(idCardName, 1), StringUtils.length(idCardName), "*");
        System.out.println(idCardName);

        String right = StringUtils.right(idCardNo, 1);
        String left = StringUtils.rightPad(StringUtils.left(idCardNo, 1), StringUtils.length(idCardNo)-1, "*");
        idCardNo = left.concat(right);
        System.out.println(idCardNo);

        businessName = StringUtils.rightPad(StringUtils.left(businessName, 4), StringUtils.length(businessName), "*");
        System.out.println(businessName);

        StringBuilder tax = new StringBuilder();
        for (int i = 0; i < taxpayerCode.length(); i++) {
            if (i == 0 || i == taxpayerCode.length() - 1) {
                tax.append(taxpayerCode.charAt(i));
            } else {
                tax.append("*");
            }
        }
        System.out.println(tax);
    }*/

    @Test
    public void iterTest() {
        int count = 0;
        int pageSize = 20;
        int pageAll = count / pageSize;

        for (int pageNo = 1; pageNo <= pageAll; pageNo++) {
            System.out.println(pageNo);
        }
    }

    @Test
    public void testCron() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("任务启动："+now);
    }

    @Test
    public void testReduce1(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list.stream().reduce(Integer::sum).get()); // Optional[1]
    }

}
