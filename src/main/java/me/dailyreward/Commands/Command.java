package me.dailyreward.Commands;

import org.bukkit.configuration.ConfigurationSection;

import java.lang.annotation.*;

/**
 * Command Framework - Command <br>
 * The command annotation used to designate methods as commands. All methods
 * should have a single CommandArgs argument
 *
 * @author minnymin3
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {

	public String name();

	public String permission() default "";

	public String[] aliases() default {};

	public String description() default "";

	public String usage() default "";

	public boolean isGameOnly() default true;

	public boolean isAdminOnly() default false;

	public boolean isConsoleOnly() default false;
}