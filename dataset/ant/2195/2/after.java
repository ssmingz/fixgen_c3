class PlaceHold {
  protected void setupProjectContext(final Project project, final HashMap defines)
      throws AntException {
    final TaskContext context = project.getContext();
    addToContext(context, defines);
    addToContext(context, System.getProperties());
  }
}
