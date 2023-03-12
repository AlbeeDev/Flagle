import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class JsonData {
	@JsonProperty("version")
    private String version;
	@JsonProperty("userdata")
    private UserDataInfo userdata;
	@JsonProperty("aspect")
    private AspectInfo aspect;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public UserDataInfo getUserData() {
        return userdata;
    }

    public void setUserData(UserDataInfo userData) {
        this.userdata = userData;
    }

    public AspectInfo getAspect() {
        return aspect;
    }

    public void setAspect(AspectInfo aspect) {
        this.aspect = aspect;
    }
}

class UserDataInfo {
	@JsonProperty("username")
    private String username;
	@JsonProperty("difficultyselect")
    private String difficultyselect;
	@JsonProperty("difficulty")
    private Difficulty difficulty;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDifficultyselect() {
        return difficultyselect;
    }

    public void setDifficultyselect(String difficultyselect) {
        this.difficultyselect = difficultyselect;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
}

class Difficulty {
	@JsonProperty("easy")
    private Easy easy;
	@JsonProperty("medium")
    private Medium medium;
	@JsonProperty("hard")
    private Hard hard;

    public Easy getEasy() {
        return easy;
    }

    public void setEasy(Easy easy) {
        this.easy = easy;
    }

    public Medium getMedium() {
        return medium;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    public Hard getHard() {
        return hard;
    }

    public void setHard(Hard hard) {
        this.hard = hard;
    }
}

class Easy {
	@JsonProperty("win")
    private int win;
	@JsonProperty("lost")
    private int lost;
	@JsonProperty("attempts")
    private int attempts;

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }
}

class Medium {
	@JsonProperty("win")
    private int win;
	@JsonProperty("lost")
    private int lost;
	@JsonProperty("attempts")
    private int attempts;

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }
}

class Hard {
	@JsonProperty("win")
    private int win;
	@JsonProperty("lost")
    private int lost;
	@JsonProperty("attempts")
    private int attempts;

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }
}

class AspectInfo {
	@JsonProperty("fullscreen")
    private boolean fullscreen;
	@JsonProperty("theme")
    private String theme;

    public boolean isFullscreen() {
        return fullscreen;
    }

    public void setFullscreen(boolean fullscreen) {
        this.fullscreen = fullscreen;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}