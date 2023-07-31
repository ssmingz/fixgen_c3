class PlaceHold {
  public void deploy() {
    Java java = ((Java) (getTask().getProject().createTask("java")));
    java.setFork(true);
    java.setFailonerror(true);
    java.setClasspath(getClasspath());
    java.setClassname(WEBLOGIC_DEPLOY_CLASS_NAME);
    java.createArg().setLine(getArguments());
    java.execute();
  }
}
