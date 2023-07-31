class PlaceHold {
  public Workspace createWorkspace(final Parameters parameters) throws Exception {
    final String component = getParameter(ROLE);
    final Workspace workspace = ((Workspace) (createComponent(component, Workspace.class)));
    setupLogger(workspace);
    if (workspace instanceof Composable) {
      ((Composable) (workspace)).compose(m_componentManager);
    }
    if (workspace instanceof Parameterizable) {
      ((Parameterizable) (workspace)).parameterize(parameters);
    }
    if (workspace instanceof Initializable) {
      ((Initializable) (workspace)).initialize();
    }
    return workspace;
  }
}
