class PlaceHold {
  protected void doBuild(
      final ProjectEngine engine, final Project project, final ArrayList targets) {
    try {
      final int targetCount = targets.size();
      if (0 == targetCount) {
        engine.execute(project, project.getDefaultTargetName());
      } else {
        for (int i = 0; i < targetCount; i++) {
          engine.execute(project, ((String) (targets.get(i))));
        }
      }
    } catch (final AntException ae) {
      getLogger().error("BUILD FAILED");
      getLogger().error("Reason:\n" + StringUtil.printStackTrace(ae, 5, true));
    }
  }
}
