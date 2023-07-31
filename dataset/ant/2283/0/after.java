class PlaceHold {
  public void execute() throws BuildException {
    if ((descriptorDirectory == null) || (!descriptorDirectory.isDirectory())) {
      throw new BuildException(
          ("descriptors directory " + descriptorDirectory.getPath()) + " is not valid");
    }
    if ((generatedFilesDirectory == null) || (!generatedFilesDirectory.isDirectory())) {
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
    String execClassPath = Project.translatePath((systemClassPath + ":") + classpath);
    Java ddCreatorTask = new Java(this);
    ddCreatorTask.setFork(true);
    ddCreatorTask.setClassname("org.apache.tools.ant.taskdefs.optional.ejb.DDCreatorHelper");
    Commandline.Argument arguments = ddCreatorTask.createArg();
    arguments.setLine(args);
    ddCreatorTask.setClasspath(new Path(getProject(), execClassPath));
    if (ddCreatorTask.executeJava() != 0) {
      throw new BuildException("Execution of ddcreator helper failed");
    }
  }
}
