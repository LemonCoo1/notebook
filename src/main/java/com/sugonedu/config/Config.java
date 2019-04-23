package com.sugonedu.config;

import com.blade.Blade;
import com.blade.ioc.annotation.Bean;
import com.blade.loader.BladeLoader;
import com.blade.mvc.view.template.JetbrickTemplateEngine;

/**
 * @author xucong
 * @date 2019/4/23
 */
@Bean
public class Config implements BladeLoader {
    @Override
    public void load(Blade blade) {
        JetbrickTemplateEngine templateEngine = new JetbrickTemplateEngine();
        blade.templateEngine(templateEngine);
    }
}
