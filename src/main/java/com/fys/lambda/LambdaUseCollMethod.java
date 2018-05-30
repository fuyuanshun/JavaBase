package com.fys.lambda;

import com.fys.bean.People;
import com.fys.bean.PeopleChecker;
import com.fys.util.PeopleListUtil;

import java.util.List;

/**
 * 使用lambda表达式引用容器方法
 */
public class LambdaUseCollMethod {
    public static void main(String[] args) {
        List<People> list;
        list = PeopleListUtil.getInitPeople(10);
        System.out.println("初始化完成!");
        System.out.println(list);

        System.out.println("lambda表达式:");
        fileter(list, people -> people.getAge() < 10 && people.getAge() > 0);
        System.out.println("调用容器中的对象:");
        fileter(list, people -> people.matched());
        System.out.println("直接调用:");
        //matched恰好就是容器中的对象People的方法，所以可以写为:
        fileter(list, People::matched);
    }

    private static boolean checkPeople(People people) {
        return people.getAge() < 10 && people.getAge() > 0;
    }

    private static void fileter(List<People> list, PeopleChecker peopleChecker) {
        for (People people : list) {
            if (peopleChecker.check(people)) {
                System.out.println(people);
            }
        }
    }

}