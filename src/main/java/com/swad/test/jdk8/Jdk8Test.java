package com.swad.test.jdk8;

import java.time.Clock;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Jdk8Test {
	public static void main(String[] args) throws Exception {
//		final Car car = Car.create(Car::new);
//		final List<Car> cars = Arrays.asList(car);
//		final Car police = Car.create(Car::new);
//		cars.forEach(police::follow);

//		Optional<String> fullName = Optional.ofNullable(null);
//		System.out.println("Full Name is set? " + fullName.isPresent());
//		System.out.println("Full Name: " + fullName.orElseGet(() -> "[none]"));
//		System.out.println(fullName.map(s -> "Hey " + s + "!").orElse("Hey Stranger!"));
//		fullName.map(s -> "Hey " + s + "!").orElse("aaa");

//		final Clock clock = Clock.systemUTC();
//		System.out.println(clock.instant());
//		System.out.println(clock.millis());
//		System.out.println(clock.instant());

//		ScriptEngineManager manager = new ScriptEngineManager();
//		ScriptEngine engine = manager.getEngineByName( "JavaScript" );
//		System.out.println( engine.getClass().getName() );
//		System.out.println( "Result:" + engine.eval( "function f() { return 1; }; f() + 1;" ) );

		long[] arrayOfLong = new long[20000];

		Arrays.parallelSetAll(arrayOfLong, index -> ThreadLocalRandom.current().nextInt(1000000));
		Arrays.stream(arrayOfLong).limit(10).forEach(i -> System.out.print(i + " "));
		System.out.println();

		Arrays.parallelSort(arrayOfLong);
		Arrays.stream(arrayOfLong).limit(10).forEach(i -> System.out.print(i + " "));
		System.out.println();
	}

}
