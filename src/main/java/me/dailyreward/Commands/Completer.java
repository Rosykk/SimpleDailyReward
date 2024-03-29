package me.dailyreward.Commands;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Completer {

	String name();

	String[] aliases() default {};

}
