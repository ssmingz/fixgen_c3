class PlaceHold {
  public void execute() throws TaskException {
    if ((descriptorDirectory == null) || (!descriptorDirectory.isDirectory())) {
      throw new TaskException(
          ("descriptors directory " + descriptorDirectory.getPath()) + " is not valid");
    }
    if ((generatedFilesDirectory == null) || (!generatedFilesDirectory.isDirectory())) {
      throw new TaskException(
          ("dest directory " + generatedFilesDirectory.getPath()) + " is not valid");
    }
    String args = (descriptorDirectory + " ") + generatedFilesDirectory;
    DirectoryScanner ds = super.getDirectoryScanner(descriptorDirectory);
    String[] files = ds.getIncludedFiles();
    for (int i = 0; i < files.length; ++i) {
      args += " " + files[i];
    }
    String systemClassPath = System.getProperty("java.class.path");
    String execClassPath = getProject().translatePath((systemClassPath + ":") + classpath);
    Java ddCreatorTask = ((Java) (getProject().createTask("java")));
    ddCreatorTask.setFork(true);
    ddCreatorTask.setClassname("org.apache.tools.ant.taskdefs.optional.ejb.DDCreatorHelper");
    Commandline.Argument arguments = ddCreatorTask.createArg();
    arguments.setLine(args);
    ddCreatorTask.setClasspath(new Path(getProject(), execClassPath));
    if (ddCreatorTask.executeJava() != 0) {
      throw new TaskException("Execution of ddcreator helper failed");
    }
  }
}
