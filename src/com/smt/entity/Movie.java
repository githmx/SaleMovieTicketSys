package com.smt.entity;
import java.util.Date;

//µÁ”∞
public class Movie {
	private Integer id;
	private String name;
	private String actors;
	private Double duration;
	private String type;
	private Date releaseTime;
	private Integer score;
	private String summary;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getActors() {
		return actors;
	}
	public void setActors(String actors) {
		this.actors = actors;
	}
	public Double getDuration() {
		return duration;
	}
	public void setDuration(Double duration) {
		this.duration = duration;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Movie(String name, String actors, Double duration, String type, Date releaseTime, Integer score,
			String summary) {
		super();
		this.name = name;
		this.actors = actors;
		this.duration = duration;
		this.type = type;
		this.releaseTime = releaseTime;
		this.score = score;
		this.summary = summary;
	}
	public Movie() {
		super();
	}
	@Override
	public String toString() {
		return "movie [id=" + id + ", name=" + name + ", actors=" + actors + ", duration=" + duration + ", type=" + type
				+ ", releaseTime=" + releaseTime + ", score=" + score + ", summary=" + summary + "]";
	}
	
	
	
}
