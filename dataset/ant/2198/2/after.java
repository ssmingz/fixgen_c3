class PlaceHold {
  public void execute() throws BuildException {
    if (!descriptorDirectory.isDirectory()) {
      throw new BuildException(
          ("descriptors directory " + descriptorDirectory.getPath()) + " is not valid");
    }
    if (!generatedFilesDirectory.isDirectory()) {
      throw new BuildException(
          ("dest directory " + generatedFilesDirectory.getPath()) + " is not valid");
    }
    String args = (descriptorDirectory + " ") + generatedFilesDirectory;
    DirectoryScanner ds = super.getDirectoryScanner(descriptorDirectory);
    String[] files = ds.getIncludedFiles();
    for (int i = 0; i < files.length; ++i) {
      args += " " + files[i];
    }
    String systemClassPath = System.getProperty("java.class.path");
    String execClassPath = project.translatePath((systemClassPath + ":") + classpath);
    Java ddCreatorTask = ((Java) (project.createTask("java")));
    ddCreatorTask.setFork("yes");
    ddCreatorTask.setClassname("org.apache.tools.ant.taskdefs.optional.ejb.DDCreatorHelper");
    ddCreatorTask.setArgs(args);
    ddCreatorTask.setClasspath(new Path(execClassPath));
    if (ddCreatorTask.executeJava() != 0) {
      throw new BuildException("Execution of ddcreator helper failed");
    }
  }
}
