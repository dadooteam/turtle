package im.dadoo.turtle;

import im.dadoo.turtle.container.Container;
import im.dadoo.turtle.container.UndertowContainer;
import im.dadoo.turtle.context.TurtleContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(final String[] args) throws Exception {
        List<Class<?>> contexts = new ArrayList<>();
        contexts.add(TurtleContext.class);
        Container container = new UndertowContainer(contexts);
        container.start();
    }
}
