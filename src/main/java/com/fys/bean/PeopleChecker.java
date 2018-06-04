package com.fys.bean;

@FunctionalInterface
public interface PeopleChecker {
    boolean check(People people);
}