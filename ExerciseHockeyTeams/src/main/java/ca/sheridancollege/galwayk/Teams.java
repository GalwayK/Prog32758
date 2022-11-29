package ca.sheridancollege.galwayk;

public enum Teams 
{
	MONTREAL_CANADIENS("Montreal Canadiens"), MAPLE_LEAFS("Maple Leafs");
	
	private String teamName;
	
	private Teams(String teamName)
	{
		this.teamName = teamName;
	}
	
	public String getTeamName()
	{
		return teamName;
	}
}
