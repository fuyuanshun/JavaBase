package com.fys.lambda;

import com.fys.bean.People;
import com.fys.bean.PeopleChecker;
import com.fys.util.PeopleListUtil;

import java.util.List;

/**
 * 使用lambda表达式引用对象方法
 */
public class LambdaUseObjectMethod {
    public static void main(String[] args) {
        List<People> list;
        list = PeopleListUtil.getInitPeople(10);
        System.out.println("list初始化完成:");
        System.out.println(list);

        //使用引用对象方法
        LambdaUseObjectMethod lambdaUseObjectMethod = new LambdaUseObjectMethod();
        filter(list, lambdaUseObjectMethod::checkPeople);
    }

    private boolean checkPeople(People people) {
        return people.getAge() < 10 && people.getAge() > 0;
    }

    private static void filter(List<People> list, PeopleChecker peopleChecker) {
        for (People people : list) {
            if (peopleChecker.check(people)) {
                System.out.println(people);
            }
        }
    }

}