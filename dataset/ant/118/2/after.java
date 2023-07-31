class PlaceHold {
  public void execute() throws BuildException {
    if ("expand".equals(taskType)) {
      log("!! expand is deprecated. Use unzip instead. !!");
    }
    Touch touch = ((Touch) (project.createTask("touch")));
    touch.setOwningTarget(target);
    touch.setTaskName(getTaskName());
    touch.setLocation(getLocation());
    if (source == null) {
      throw new BuildException("Source attribute must be specified");
    }
    if (dest == null) {
      throw new BuildException("Dest attribute must be specified");
    }
    if (source.isDirectory()) {
      DirectoryScanner ds = super.getDirectoryScanner(source);
      String[] files = ds.getIncludedFiles();
      for (int i = 0; i < files.length; ++i) {
        File file = new File(source, files[i]);
        expandFile(touch, file, dest);
      }
    } else {
      expandFile(touch, source, dest);
    }
  }
}
