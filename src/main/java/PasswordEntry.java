public class PasswordEntry {

    private String pass;
    private String log;
    private String description;

    public PasswordEntry(String description, String log, String pass) {
        this.pass = pass;
        this.log = log;
        this.description = description;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
