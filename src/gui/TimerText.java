package gui;

import java.time.Duration;
import java.time.LocalDateTime;

import javafx.scene.text.Text;

public class TimerText extends Text implements Runnable {
	private LocalDateTime startTime;
	private int updateMillis = 100;
	private Thread t;
	private boolean running = false;
	
	public TimerText(){
		super("Time: 0:0.0");
		startTime = LocalDateTime.now();
	}
	
	public void run(){
		startTime = LocalDateTime.now();
		while (running){
			update();
			try {
				Thread.sleep(updateMillis);
			} catch (InterruptedException e) {
				//DO Nothing
			}
		}
	}
	
	public void start(){
		System.out.println("Starting Timer");
		if (t == null){
			t = new Thread(this, "Timer");
			running = true;
			t.start();
		}
	}
	
	public void stop(){
		running = false;
		if (t != null){
			t.interrupt();
			t = null;
		}
	}
	
	public void reset(){
		startTime = LocalDateTime.now();
	}
	
	public String toString(){
		return durationToString(duration());
	}
	
	public LocalDateTime difference(LocalDateTime a, LocalDateTime b){
		LocalDateTime result;
		if (b.isAfter(a)){
			result = LocalDateTime.of(b.getYear()-a.getYear(),
					b.getMonthValue()-a.getMonthValue(), b.getDayOfMonth()-a.getDayOfMonth(),
					b.getHour()-a.getHour(), b.getMinute()-a.getMinute(),
					b.getSecond()-a.getSecond(), b.getNano()-a.getNano());
		} else {
			result = LocalDateTime.of(a.getYear()-b.getYear(),
					a.getMonthValue()-b.getMonthValue(), a.getDayOfMonth()-b.getDayOfMonth(),
					a.getHour()-b.getHour(), a.getMinute()-b.getMinute(),
					a.getSecond()-b.getSecond(), a.getNano()-b.getNano());
		}
		return result;
	}
	
	public Duration duration(LocalDateTime a, LocalDateTime b){
		return Duration.between(a, b);
	}
	
	public Duration duration(){
		return Duration.between(startTime, LocalDateTime.now());
	}
	
	public LocalDateTime startTime(){
		return startTime;
	}
	
	public String durationToString(Duration duration){
		return duration.toMinutes() + ":" + duration.getSeconds() % 60 + "." + String.valueOf(duration.getNano()).substring(0, 1);
	}
	
	public void update(){
		this.setText("Time: " + this.toString());
	}
}
