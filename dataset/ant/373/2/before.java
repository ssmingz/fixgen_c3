class PlaceHold {
  public Workspace createWorkspace(final Map properties) throws Exception {
    final Workspace workspace =
        ((Workspace) (createService(Workspace.class, PREFIX + "workspace.DefaultWorkspace")));
    setupObject(workspace, m_workspaceServiceManager, m_parameters);
    final PropertyStore propStore = createBaseStore(properties);
    final ExecutionFrame frame =
        new DefaultExecutionFrame(getLogger(), propStore, m_workspaceServiceManager);
    ((ExecutionContainer) (workspace)).setRootExecutionFrame(frame);
    return workspace;
  }
}
