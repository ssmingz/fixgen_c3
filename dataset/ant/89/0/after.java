class PlaceHold{
public void execute() throws BuildException {
    try {
        validate();
        long sleepTime = getSleepTime();
        log(("sleeping for " + sleepTime) + " milliseconds", MSG_VERBOSE);
        doSleep(sleepTime);
    } catch (Exception e) {
        if (failOnError) {
            throw new BuildException("Error", e);
        } else {
            String text = e.toString();
            log(text, MSG_ERR);
        }
    }
}
}