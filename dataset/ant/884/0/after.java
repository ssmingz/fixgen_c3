class PlaceHold {
  public Workspace createWorkspace(final Parameters parameters) throws Exception {
    final String component = getParameter(ROLE);
    final Workspace workspace = ((Workspace) (createComponent(component, Workspace.class)));
    setupObject(workspace, parameters);
    return workspace;
  }
}
