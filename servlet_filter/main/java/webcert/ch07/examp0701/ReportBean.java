package webcert.ch07.examp0701;

import java.util.List;

public class ReportBean {

	private String user;
	private String password;
	private String hidden;
	private List<String> weatherType;
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHidden() {
		return hidden;
	}
	public void setHidden(String hidden) {
		this.hidden = hidden;
	}
	public List<String> getWeatherType() {
		return weatherType;
	}
	public void setWeatherType(List<String> weatherType) {
		this.weatherType = weatherType;
	}
	
}
