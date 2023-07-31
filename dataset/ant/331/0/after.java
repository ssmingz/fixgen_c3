class NewProjectEvent {
  public NewProjectEvent(AppContext context, ACSProjectElement project) {
    super(context, project);
    if (project == null) {
      throw new IllegalArgumentException("A new project can't be null.");
    }
  }
}
