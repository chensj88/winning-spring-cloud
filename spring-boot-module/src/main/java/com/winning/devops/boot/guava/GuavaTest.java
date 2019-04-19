package com.winning.devops.boot.guava;

import java.util.Optional;

/**
 * @Author chensj
 * @Description
 * @Date: 2019-04-19 8:56
 */
public class GuavaTest {
/**
 *  Guava用Optional表示可能为null的T类型引用。一个Optional实例可能包含非null的引用
 *（我们称之为引用存在），也可能什么也不包括（称之为引用缺失）。它从不说包含的是null值，
 * 而是用存在或缺失来表示。但Optional从不会包含null值引用。
 *  Optional.of(v2)         创建指定引用的Optional实例
 *  Optional.ofNullable(v1) 创建指定引用的Optional实例，若引用为null则表示缺失
 *  Optional.isPresent()    Optional包含非null的引用（引用存在），返回true
 *  Optional.get()          返回Optional所包含的引用，若引用缺失，则抛出java.lang.IllegalStateException
 *
 *
 *
 *
 *
 */
    public static void main(String[] args){

        Integer v1 = null;
        Integer v2 = 10;
        // 创建指定引用的Optional实例，若引用为null则快速失败返回absent()
        //  absent()创建引用缺失的Optional实例
        Optional<Integer> a = Optional.ofNullable(v1);
        Optional<Integer> b = Optional.of(v2);
        System.out.println(add(a,b));

    }

    public static Integer add(Optional<Integer> a,Optional<Integer> b){
        System.out.println("a is present: "+a.isPresent());
        System.out.println("b is present: "+b.isPresent());
        // isPresent():如果Optional包含非null的引用（引用存在），返回true
        if(!a.isPresent()){
            a = Optional.of(0);
        }
        // 返回Optional所包含的引用,若引用缺失,返回指定的值
        // a.orElse(0);
        if(!b.isPresent()){
            b = Optional.of(0);
        }

        return  a.get() + b.get();
    }
}
