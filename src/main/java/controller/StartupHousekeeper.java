package controller;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import statics.provider.StringUtils;

@Component
public class StartupHousekeeper implements ApplicationListener<ContextRefreshedEvent> {

  @Override
  public void onApplicationEvent(final ContextRefreshedEvent event) {
	  
		for (int i = 33; i < 125; i++) {
			StringUtils.numCharSymbol[i - 33] = (char) i;
		}

  }
}