class PlaceHold {
  protected void fireMessageLogged(Project project, String message, int priority) {
    fireMessageLogged(project, message, null, priority);
  }
}
