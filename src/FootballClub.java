import java.io.Serializable;

public class FootballClub extends SportsClub implements Serializable {
    private int winCount;
    private int defeatCount;
    private int drawCount;
    private int scoredGoalsCount;
    private int receivedGoalCount;
    private int points;
    private int matchesPlayed;

    public int getDefeatCount() {
        return defeatCount;
    }

    public int getWinCount() {
        return winCount;
    }

    public int getDrawCount() {
        return drawCount;
    }

    public int getReceivedGoalCount() {
        return receivedGoalCount;
    }

    public int getScoredGoalsCount() {
        return scoredGoalsCount;
    }

    public int getPoints() {
        return points;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setDrawCount(int drawCount) {
        this.drawCount = drawCount;
    }

    public void setWinCount(int winCount) {
        this.winCount = winCount;
    }

    public void setDefeatCount(int defeatCount) {
        this.defeatCount = defeatCount;
    }

    public void setScoredGoalsCount(int scoredGoalsCount) {
        this.scoredGoalsCount = scoredGoalsCount;
    }

    public void setReceivedGoalCount(int receivedGoalCount) {
        this.receivedGoalCount = receivedGoalCount;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }
}
