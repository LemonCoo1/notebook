package com.sugonedu;

import com.blade.Blade;
import com.blade.ioc.annotation.Bean;
import com.blade.loader.BladeLoader;
import com.blade.mvc.view.template.JetbrickTemplateEngine;

public class App {
    public static void main(String[] args) {
        Blade.of().start(App.class, args);
        //Blade.of().get("/", ctx -> ctx.text("HELLO WORLD")).start(App.class, args);
        //Blade.of().get("/users", getting).start();
    }
}
