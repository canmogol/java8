package com.fererlab.conditional;

import org.junit.Test;

public class CondTest {

	@Test
	public void condTest() {
		Cond
			.create()
			.check(() -> "123".startsWith("a"),
				() -> System.out.println("first check, should pass"))
			.check(() -> "-*/".startsWith("a"),
				() -> System.out.println("second check, should pass"))
			.check(() -> "abc".startsWith("a"),
				() -> System.out.println("start with a!"))
			.otherwise(() -> System.out.println("something else"))
			.error((e) -> System.out.println("got exception: " + e));
	}

}
