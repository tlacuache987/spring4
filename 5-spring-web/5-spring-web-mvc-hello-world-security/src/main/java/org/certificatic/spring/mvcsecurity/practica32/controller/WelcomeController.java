package org.certificatic.spring.mvcsecurity.practica32.controller;

import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller("controllerBean")
@RequestMapping("/welcome")
public class WelcomeController implements BeanFactoryAware, InitializingBean {

	private @Getter String name = "Ivan";

	private @Setter BeanFactory beanFactory;

	private StandardEvaluationContext context = new StandardEvaluationContext();

	public void afterPropertiesSet() {
		context.setBeanResolver(new BeanFactoryResolver(this.beanFactory));
	}

	@RequestMapping(method = RequestMethod.GET)
	public String showWelcomePage(Model model) {

		log.info("show welcome page ------------------");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();

		@SuppressWarnings("unchecked")
		List<GrantedAuthority> authorities = (List<GrantedAuthority>) auth.getAuthorities();

		log.info("username : {}", username);
		log.info("authorities : {}", authorities);

		final ExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser.parseExpression("@controllerBean.name");
		final String value = expression.getValue(context, String.class);
		log.info("bean name : {}", value);

		model.addAttribute("currentSecc", "welcome");

		return "secure/view_welcome";
	}

}
