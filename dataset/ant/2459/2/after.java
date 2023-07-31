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
    if ((sourceDirectory == null) || (!sourceDirectory.isDirectory())) {
      throw new TaskException(("src directory " + sourceDirectory.getPath()) + " is not valid");
    }
    String systemClassPath = System.getProperty("java.class.path");
    String execClassPath =
        getProject()
            .translatePath((((systemClassPath + ":") + classpath) + ":") + generatedFilesDirectory);
    DirectoryScanner ds = super.getDirectoryScanner(descriptorDirectory);
    String[] files = ds.getIncludedFiles();
    Java helperTask = ((Java) (getProject().createTask("java")));
    helperTask.setFork(true);
    helperTask.setClassname("org.apache.tools.ant.taskdefs.optional.ejb.EjbcHelper");
    String args = "";
    args += " " + descriptorDirectory;
    args += " " + generatedFilesDirectory;
    args += " " + sourceDirectory;
    args += " " + generatedManifestFile;
    args += " " + keepgenerated;
    for (int i = 0; i < files.length; ++i) {
      args += " " + files[i];
    }
    Argument arguments = helperTask.createArg();
    arguments.setLine(args);
    helperTask.setClasspath(new Path(execClassPath));
    if (helperTask.executeJava() != 0) {
      throw new TaskException("Execution of ejbc helper failed");
    }
  }
}
