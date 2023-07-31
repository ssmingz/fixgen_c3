class PlaceHold {
  protected boolean touchFile(File file, long timemillis) throws BuildException {
    if (project.getJavaVersion() != Project.JAVA_1_1) {
      Touch touch = ((Touch) (project.createTask("touch")));
      touch.setOwningTarget(target);
      touch.setTaskName(getTaskName());
      touch.setLocation(getLocation());
      touch.setFile(file);
      touch.setMillis(timemillis);
      touch.touch();
      return true;
    } else {
      return false;
    }
  }
}
