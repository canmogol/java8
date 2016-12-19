package com.fererlab.java8;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Java8 {

    public static void main(String args[]) {
//        System.out.println(new Date());
//        System.out.println(Calendar.getInstance().getTimeZone());

//        byte i;
//        for(i = 0; i < 128; i++){
//            System.out.println("i = " + i);
//        }
//        Java8 java8 = new Java8();
//        System.out.println("----- main -----");
//        java8.streamTest5();
    }

    public void streamTest5() {
	Stream<String> stream = Stream.of("one", "two", "three", "four", "five");
	Predicate<String> p1 = s -> s.length() > 3;
	Predicate<String> p2 = Predicate.isEqual("two");
	Predicate<String> p3 = Predicate.isEqual("three");
	stream.filter(p1.and(p2.or(p3)))
		.forEach(System.out::println);
    }

    private void streamTest4() {
	PersonFactory<Person> personFactory/*compiler automatically chooses the right constructor that matches signature of single abstract method "create"*/
		= Person::new/*a reference to the Person constructor*/;
	Person person = personFactory.create("jack", 21, Boolean.TRUE);
    }

    private void streamTest3() {
	Stream.of(new Person("jack", 21, Boolean.TRUE), new Person("mike", 32, Boolean.TRUE), new Person("jane", 23, Boolean.FALSE))
		.map(Person::getAge)
		.filter(p -> p > 21)
		.forEach(System.out::println);
    }

    private void streamTest2() {
	Predicate<String> two = "two"::equals;
	Stream.of("one", "two", "three").filter(two::test);
	Optional<Integer> integers = Stream.of(1, 2, 3, 4).map(integer -> {
	    System.out.println("map integer = " + integer);
	    return integer * 2;
	}).filter(integer -> {
	    System.out.println("fil integer = " + integer);
	    return integer > 5;
	}).reduce((first, second) -> {
	    System.out.println("red first = " + first + " second = " + second);
	    return first > second ? first : second;
	});
	System.out.println("integers.get() = " + integers.get());
    }

    public void streamTest() {
	List<Person> personList = new ArrayList<>();
	personList.add(new Person("John", 21, Boolean.TRUE));
	personList.add(new Person("Mike", 32, Boolean.TRUE));
	Stream<Person> personStream = personList.stream();
	Consumer<Person> personConsumer1 = new Consumer<Person>() {
	    @Override
	    public void accept(Person person) {
		System.out.println("1: " + person);
	    }
	};
	Consumer<Person> personConsumer2 = new Consumer<Person>() {
	    @Override
	    public void accept(Person person) {
		System.out.println("2: " + person);
	    }
	};
	Consumer<Person> personConsumer3 = new Consumer<Person>() {
	    @Override
	    public void accept(Person person) {
		System.out.println("3: " + person);
	    }
	};
	Consumer<Person> personConsumer4 = new Consumer<Person>() {
	    @Override
	    public void accept(Person person) {
		System.out.println("4: " + person);
	    }
	};
	Consumer<Person> personConsumer5 = new Consumer<Person>() {
	    @Override
	    public void accept(Person person) {
		System.out.println("5: " + person);
	    }
	};
	personStream.forEach(
		personConsumer1.andThen(
			personConsumer2.andThen(personConsumer3).andThen(personConsumer4)
		).andThen(personConsumer5)
	);
	personList.stream().forEach(((Consumer<Person>) p -> System.out.println("1:" + p)).andThen(p -> System.out.println("p = " + p)));
    }

    public void test() {
	FileFilter fileFilterAnonymous = new FileFilter() {
	    public boolean accept(File file) {
		return file.getName().endsWith(".java");
	    }
	};
	System.out.println("anonymous file filter: " + fileFilterAnonymous);
	FileFilter fileFilterLambda = (File file) -> file.getName().endsWith(".java");
	System.out.println("lambda file filter: " + fileFilterLambda);

    }
}

