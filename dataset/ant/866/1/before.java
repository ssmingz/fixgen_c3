class PlaceHold {
  public void execute() throws TaskException {
    try {
      getProject().setLogger(getLogger());
      getProject().contextualize(getContext());
      getProject().init();
      getTask().init();
      getTask().execute();
    } catch (final Exception e) {
      throw new TaskException(e.getMessage(), e);
    }
  }
}
