package site.blmdz.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

	static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
//	@Scheduled(fixedRate = 2000)
//	@Scheduled(cron = "*/2 * * * * ?")
	public void test1() {
		System.out.println("current time is: " + sdf.format(new Date()));
	}
	
}
