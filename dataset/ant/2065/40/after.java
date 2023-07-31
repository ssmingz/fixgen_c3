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
      throw new BuildException("package attribute must be present.");
    }
    String systemClassPath = System.getProperty("java.class.path");
    pathToPackage = this.destinationPackage.replace('.', File.separatorChar);
    DirectoryScanner ds = super.getDirectoryScanner(sourceDirectory);
    if (compileClasspath == null) {
      compileClasspath = new Path(project);
    }
    compileClasspath.append(systemClasspath);
    String[] files = ds.getIncludedFiles();
    Java helperTask = ((Java) (project.createTask("java")));
    helperTask.setFork(true);
    helperTask.setClassname("weblogic.jspc");
    helperTask.setTaskName(getTaskName());
    String[] args = new String[12];
    File jspFile = null;
    String parents = "";
    String arg = "";
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
      jspFile = new File(((String) (filesToDo.elementAt(i))));
      args[j] = "-package";
      parents = jspFile.getParent();
      if ((parents != null) && (!"".equals(parents))) {
        parents = this.replaceString(parents, File.separator, "_.");
        args[j + 1] = ((destinationPackage + ".") + "_") + parents;
      } else {
        args[j + 1] = destinationPackage;
      }
      args[j + 2] = (sourceDirectory + File.separator) + ((String) (filesToDo.elementAt(i)));
      arg = "";
      for (int x = 0; x < 12; x++) {
        arg += " " + args[x];
      }
      System.out.println("arg = " + arg);
      helperTask.clearArgs();
      helperTask.setArgs(arg);
      helperTask.setClasspath(compileClasspath);
      if (helperTask.executeJava() != 0) {
        log(files[i] + " failed to compile", MSG_WARN);
      }
    }
  }
}
