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
    if ((sourceDirectory == null) || (!sourceDirectory.isDirectory())) {
      throw new BuildException(("src directory " + sourceDirectory.getPath()) + " is not valid");
    }
    String systemClassPath = System.getProperty("java.class.path");
    String execClassPath =
        project.translatePath(
            (((systemClassPath + ":") + classpath) + ":") + generatedFilesDirectory);
    DirectoryScanner ds = super.getDirectoryScanner(descriptorDirectory);
    String[] files = ds.getIncludedFiles();
    Java helperTask = ((Java) (project.createTask("java")));
    helperTask.setTaskName(getTaskName());
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
    Commandline.Argument arguments = helperTask.createArg();
    arguments.setLine(args);
    helperTask.setClasspath(new Path(project, execClassPath));
    if (helperTask.executeJava() != 0) {
      throw new BuildException("Execution of ejbc helper failed");
    }
  }
}
