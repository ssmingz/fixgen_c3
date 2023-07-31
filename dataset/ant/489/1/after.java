class PlaceHold {
  private void execute(final Project project, final String targetName, final ProjectEntry entry)
      throws TaskException {
    final int index = targetName.indexOf("->");
    if ((-1) != index) {
      final String name = targetName.substring(0, index);
      final String otherTargetName = targetName.substring(index + 2);
      final Project otherProject = getProject(name, project);
      final ProjectEntry otherEntry = getProjectEntry(otherProject);
      execute(otherProject, otherTargetName, otherEntry);
      return;
    }
    final Target target = project.getTarget(targetName);
    if (null == target) {
      final String message = REZ.getString("no-target.error", targetName);
      throw new TaskException(message);
    }
    entry.completeTarget(targetName);
    final String[] dependencies = target.getDependencies();
    for (int i = 0; i < dependencies.length; i++) {
      if (!entry.isTargetCompleted(dependencies[i])) {
        execute(project, dependencies[i], entry);
      }
    }
    m_listenerSupport.targetStarted(targetName);
    executeTarget(targetName, target, entry.getFrame());
    m_listenerSupport.targetFinished();
  }
}
