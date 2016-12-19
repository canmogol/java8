package com.fererlab.control.expressional;

import org.junit.Test;

import java.util.Optional;

import static com.fererlab.control.expressional.Match.Match;

public class MatchTest {

    @Test
    public void condMatchTest() {
	String tripleA = "AAA";
	Optional resultOptional = Match()
		.check("AAA", () -> "string with three A chars")
		.check(Boolean.FALSE, () -> Boolean.FALSE)
		.check(99, () -> 99.99)
		.otherwise(() -> "something else")
		.error((e) -> System.out.println("got exception: " + e))
		.eval(tripleA);

	System.out.println(resultOptional.get());
    }

    @Test
    public void condMultiMatchTest() {
	String tripleA = "AAA";
	boolean aFalse = false;
	int ninetyNine = 99;

	Match match = Match()
		.check("AAA",   () -> "string with three 'A's")
		.check(Boolean.FALSE, () -> Boolean.FALSE)
		.check(99, () -> 99.99)
		.otherwise(() -> "something else")
		.error((e) -> System.out.println("got exception: " + e));

	System.out.println(match.eval(tripleA).get());
	System.out.println(match.eval(aFalse).get());
	System.out.println(match.eval(ninetyNine).get());
    }

}