class PlaceHold {
  public void execute() throws BuildException {
    if ("javadoc2".equals(taskType)) {
      log("!! javadoc2 is deprecated. Use javadoc instead. !!");
    }
    if (sourcePath == null) {
      String msg = "sourcePath attribute must be set!";
      throw new BuildException(msg);
    }
    log("Generating Javadoc", MSG_INFO);
    Commandline toExecute = ((Commandline) (cmd.clone()));
    toExecute.setExecutable("javadoc");
    if (classpath == null) {
      classpath = Path.systemClasspath;
    } else {
      classpath = classpath.concatSystemClasspath("ignore");
    }
    if (!javadoc1) {
      toExecute.createArgument().setValue("-classpath");
      toExecute.createArgument().setPath(classpath);
      toExecute.createArgument().setValue("-sourcepath");
      toExecute.createArgument().setPath(sourcePath);
    } else {
      toExecute.createArgument().setValue("-classpath");
      toExecute
          .createArgument()
          .setValue(
              (sourcePath.toString() + System.getProperty("path.separator"))
                  + classpath.toString());
    }
    if (version && (doclet == null)) {
      toExecute.createArgument().setValue("-version");
    }
    if (author && (doclet == null)) {
      toExecute.createArgument().setValue("-author");
    }
    if (javadoc1 || (doclet == null)) {
      if (destDir == null) {
        String msg = "destDir attribute must be set!";
        throw new BuildException(msg);
      }
    }
    if (!javadoc1) {
      if (doclet != null) {
        if (doclet.getName() == null) {
          throw new BuildException("The doclet name must be specified.", location);
        } else {
          toExecute.createArgument().setValue("-doclet");
          toExecute.createArgument().setValue(doclet.getName());
          if (doclet.getPath() != null) {
            toExecute.createArgument().setValue("-docletpath");
            toExecute.createArgument().setPath(doclet.getPath());
          }
          for (Enumeration e = doclet.getParams(); e.hasMoreElements(); ) {
            DocletParam param = ((DocletParam) (e.nextElement()));
            if (param.getName() == null) {
              throw new BuildException("Doclet parameters must have a name");
            }
            toExecute.createArgument().setValue(param.getName());
            if (param.getValue() != null) {
              toExecute.createArgument().setValue(param.getValue());
            }
          }
        }
      }
      if (bootclasspath != null) {
        toExecute.createArgument().setValue("-bootclasspath");
        toExecute.createArgument().setPath(bootclasspath);
      }
      if (links.size() != 0) {
        for (Enumeration e = links.elements(); e.hasMoreElements(); ) {
          LinkArgument la = ((LinkArgument) (e.nextElement()));
          if (la.getHref() == null) {
            throw new BuildException(
                "Links must provide the URL to the external class documentation.");
          }
          if (la.isLinkOffline()) {
            String packageListLocation = la.getPackagelistLoc();
            if (packageListLocation == null) {
              throw new BuildException(
                  ("The package list location for link " + la.getHref())
                      + " must be provided because the link is offline");
            }
            toExecute.createArgument().setValue("-linkoffline");
            toExecute.createArgument().setValue(la.getHref());
            toExecute.createArgument().setValue(packageListLocation);
          } else {
            toExecute.createArgument().setValue("-link");
            toExecute.createArgument().setValue(la.getHref());
          }
        }
      }
      if (group != null) {
        StringTokenizer tok = new StringTokenizer(group, ",", false);
        while (tok.hasMoreTokens()) {
          String grp = tok.nextToken().trim();
          int space = grp.indexOf(" ");
          if (space > 0) {
            String name = grp.substring(0, space);
            String pkgList = grp.substring(space + 1);
            toExecute.createArgument().setValue("-group");
            toExecute.createArgument().setValue(name);
            toExecute.createArgument().setValue(pkgList);
          }
        }
      }
      if (groups.size() != 0) {
        for (Enumeration e = groups.elements(); e.hasMoreElements(); ) {
          GroupArgument ga = ((GroupArgument) (e.nextElement()));
          String title = ga.getTitle();
          String packages = ga.getPackages();
          if ((title == null) || (packages == null)) {
            throw new BuildException(
                "The title and packages must be specified for group elements.");
          }
          toExecute.createArgument().setValue("-group");
          toExecute.createArgument().setValue(title);
          toExecute.createArgument().setValue(packages);
        }
      }
    }
    if ((packageNames != null) && (packageNames.length() > 0)) {
      Vector packages = new Vector();
      StringTokenizer tok = new StringTokenizer(packageNames, ",", false);
      while (tok.hasMoreTokens()) {
        String name = tok.nextToken().trim();
        if (name.endsWith(".*")) {
          packages.addElement(name);
        } else {
          toExecute.createArgument().setValue(name);
        }
      }
      if (packages.size() > 0) {
        evaluatePackages(toExecute, sourcePath, packages);
      }
    }
    if ((sourceFiles != null) && (sourceFiles.length() > 0)) {
      StringTokenizer tok = new StringTokenizer(sourceFiles, ",", false);
      while (tok.hasMoreTokens()) {
        toExecute.createArgument().setValue(tok.nextToken().trim());
      }
    }
    if (packageList != null) {
      toExecute.createArgument().setValue("@" + packageList);
    }
    log("Javadoc args: " + toExecute, MSG_VERBOSE);
    log("Javadoc execution", MSG_INFO);
    JavadocOutputStream out = new JavadocOutputStream(Project.MSG_INFO);
    JavadocOutputStream err = new JavadocOutputStream(Project.MSG_WARN);
    Execute exe = new Execute(new PumpStreamHandler(out, err));
    exe.setAntRun(project);
    exe.setWorkingDirectory(project.getBaseDir());
    try {
      exe.setCommandline(toExecute.getCommandline());
      int ret = exe.execute();
      if ((ret != 0) && failOnError) {
        throw new BuildException("Javadoc returned " + ret, location);
      }
    } catch (IOException e) {
      throw new BuildException("Javadoc failed: " + e, e, location);
    } finally {
      out.logFlush();
      err.logFlush();
      try {
        out.close();
        err.close();
      } catch (IOException e) {
      }
    }
  }
}
