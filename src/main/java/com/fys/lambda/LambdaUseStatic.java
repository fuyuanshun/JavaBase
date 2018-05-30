package com.fys.lambda;

import com.fys.bean.People;
import com.fys.bean.PeopleChecker;
import com.fys.util.PeopleListUtil;

import java.util.List;

/**
 * 使用lambda表达式引用静态方法
 */
public class LambdaUseStatic {
    public static void main(String[] args) {
        List<People> list;
        list = PeopleListUtil.getInitPeople(10);
        System.out.println("获得的集合为:");
        System.out.println(list);
        PeopleChecker peopleChecker = new PeopleChecker() {
            @Override
            public boolean check(People people) {
                return people.getAge() < 10 && people.getAge() > 0;
            }
        };

        System.out.println("使用匿名类:");
        filter(list, peopleChecker);
        System.out.println("使用lambda表达式:");
        filter(list, people->people.getAge() < 10 && people.getAge() > 0);
        System.out.println("在lambda表达式中使用静态方法:");
        filter(list, people->LambdaUseStatic.checkPeople(people));

        System.out.println("直接使用lambda静态方法");
        filter(list, LambdaUseStatic::checkPeople);
    }

    private static boolean checkPeople(People people) {
        return people.getAge() < 10 && people.getAge() > 0;
    }

    /**
     * 过滤
     * @param list
     * @param peopleChecker
     */
    private static void filter(List<People> list, PeopleChecker peopleChecker) {
        for (People people : list) {
            if (peopleChecker.check(people)) {
                System.out.println(people);
            }
        }
    }
}