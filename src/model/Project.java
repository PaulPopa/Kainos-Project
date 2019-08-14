package model;

public class Project {
	private int projectId;
	private String projectName;
	private String leaderId;

	public Project() {}
	
	public Project(int projectId, String projectName, String leaderId) {
		this(projectName, leaderId);
		setProjectId(projectId);
	}
	
	public Project(String projectName, String leaderId) {
		this();
		setProjectName(projectName);
		setLeaderId(leaderId);
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getLeaderId() {
		return leaderId;
	}

	public void setLeaderId(String leaderId) {
		this.leaderId = leaderId;
	}
}
