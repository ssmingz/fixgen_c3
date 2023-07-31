class PlaceHold {
  public void execute(final Project project, final String target) throws AntException {
    m_componentManager.put("org.apache.ant.project.Project", project);
    final TaskletContext context = project.getContext();
    final String projectName = ((String) (context.getProperty(PROJECT)));
    m_listenerSupport.projectStarted(projectName);
    executeTargetWork("<init>", project.getImplicitTarget(), context);
    execute(project, target, context);
    m_listenerSupport.projectFinished();
  }
}
