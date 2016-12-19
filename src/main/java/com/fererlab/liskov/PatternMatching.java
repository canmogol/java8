package com.fererlab.liskov;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class PatternMatching {

    public static void main(String[] args) {
	PatternMatching patternMatching = new PatternMatching();
	try {
	    patternMatching.test();
	} catch (NoMatch noMatch) {
	    noMatch.printStackTrace();
	}
    }

    private void test() throws NoMatch {

	User user = new User("john wick", 44);

	Pattern<User> p = new Pattern<>();
	p.match(
		user,
		p.cond(
			u -> u.getAge() > 17,
			u -> {
			    System.out.println("adult = " + u);
			}
		),
		p.cond(
			u -> u.getAge() < 18,
			u -> {
			    System.out.println("child = " + u);
			}
		)
	);


//        match(user) {
//            (user.getAge() < 18) -> {
//                System.out.println("child = " + user);
//            },
//            (user.getAge() > 17) -> {
//                System.out.println("adult = " + user);
//            }
//        }

    }

    class User {
	private String name;
	private Integer age;

	public User(String name, Integer age) {
	    this.name = name;
	    this.age = age;
	}

	public String getName() {
	    return name;
	}

	public Integer getAge() {
	    return age;
	}
    }

    class Pattern<T> {

	public Condition<T> cond(Predicate<T> predicate, Consumer<T> consumer) {
	    return new Condition<>(predicate, consumer);
	}

	@SafeVarargs
	public final void match(T t, Condition<T>... conditions) throws NoMatch {
	    Optional<Condition<T>> optionalCondition = Arrays.asList(conditions).stream().filter(condition -> condition.getPredicate().test(t)).findFirst();
	    if (optionalCondition.isPresent()) {
		optionalCondition.get().getConsumer().accept(t);
	    } else {
		throw new NoMatch();
	    }
	}


    }

    class NoMatch extends Exception {
    }

    class Condition<T> {

	private Predicate<T> predicate;
	private Consumer<T> consumer;

	public Condition(Predicate<T> predicate, Consumer<T> consumer) {
	    this.predicate = predicate;
	    this.consumer = consumer;
	}

	public Predicate<T> getPredicate() {
	    return predicate;
	}

	public Consumer<T> getConsumer() {
	    return consumer;
	}
    }

}


//object MatchTest1 extends App {
//    def matchTest(x: Int): String = x match {
//    case 1 => "one"
//    case 2 => "two"
//    case _ => "many"
//    }
//    println(matchTest(3))
//}