class PlaceHold {
  public void execute() throws BuildException {
    if (!destinationDirectory.isDirectory()) {
      throw new BuildException(
          ("destination directory " + destinationDirectory.getPath()) + " is not valid");
    }
    if (!sourceDirectory.isDirectory()) {
      throw new BuildException(("src directory " + sourceDirectory.getPath()) + " is not valid");
    }
    if (destinationPackage == null) {
      throw new BuildException("package attribute must be present.", getLocation());
    }
    pathToPackage = this.destinationPackage.replace('.', File.separatorChar);
    DirectoryScanner ds = super.getDirectoryScanner(sourceDirectory);
    if (compileClasspath == null) {
      compileClasspath = new Path(getProject());
    }
    compileClasspath = compileClasspath.concatSystemClasspath();
    String[] files = ds.getIncludedFiles();
    Java helperTask = new Java(this);
    helperTask.setFork(true);
    helperTask.setClassname("weblogic.jspc");
    helperTask.setTaskName(getTaskName());
    String[] args = new String[12];
    File jspFile = null;
    String parents = "";
    int j = 0;
    args[j++] = "-d";
    args[j++] = destinationDirectory.getAbsolutePath().trim();
    args[j++] = "-docroot";
    args[j++] = sourceDirectory.getAbsolutePath().trim();
    args[j++] = "-keepgenerated";
    args[j++] = "-compilerclass";
    args[j++] = "sun.tools.javac.Main";
    args[j++] = "-classpath";
    args[j++] = compileClasspath.toString();
    this.scanDir(files);
    log(("Compiling " + filesToDo.size()) + " JSP files");
    for (int i = 0; i < filesToDo.size(); i++) {
      String filename = ((String) (filesToDo.elementAt(i)));
      jspFile = new File(filename);
      args[j] = "-package";
      parents = jspFile.getParent();
      if ((parents != null) && (!"".equals(parents))) {
        parents = this.replaceString(parents, File.separator, "_.");
        args[j + 1] = ((destinationPackage + ".") + "_") + parents;
      } else {
        args[j + 1] = destinationPackage;
      }
      args[j + 2] = (sourceDirectory + File.separator) + filename;
      helperTask.clearArgs();
      for (int x = 0; x < (j + 3); x++) {
        helperTask.createArg().setValue(args[x]);
      }
      helperTask.setClasspath(compileClasspath);
      if (helperTask.executeJava() != 0) {
        log(filename + " failed to compile", MSG_WARN);
      }
    }
  }
}
