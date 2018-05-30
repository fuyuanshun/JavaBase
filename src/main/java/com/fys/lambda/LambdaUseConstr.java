package com.fys.lambda;

import com.fys.bean.People;
import com.fys.bean.PeopleChecker;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * 使用Lambda表达式引用构造器
 */
public class LambdaUseConstr {
    public static void main(String[] args) {
        System.out.println("匿名类调用getList方法");
        Supplier<List> listSupplier = new Supplier<List>() {
            @Override
            public List get() {
                return new ArrayList();
            }
        };
        List list1 = getList(listSupplier);
        System.out.println("Lambda表达式调用方法:");
        List list2 = getList(()-> new ArrayList<>());
        System.out.println("引用构造器:");
        List list3 = getList(ArrayList::new);
    }

    /**
     * 获得一个List对象
     * @param listSupplier
     * @return
     */
    private static List getList(Supplier<List> listSupplier) {
        return listSupplier.get();
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