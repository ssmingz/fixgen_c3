class PlaceHold {
  public void execute() throws TaskException {
    validate();
    final long sleepTime = getSleepTime();
    final String message = REZ.getString("sleep.duration.notice", new Long(sleepTime));
    getContext().debug(message);
    doSleep(sleepTime);
  }
}
