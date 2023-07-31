class PlaceHold {
  public void execute() throws BuildException {
    File savedDir = dir;
    String savedAntFile = antFile;
    Vector locals = new VectorSet(targets);
    try {
      getNewProject();
      if ((dir == null) && inheritAll) {
        dir = getProject().getBaseDir();
      }
      initializeProject();
      if (dir != null) {
        if (!useNativeBasedir) {
          newProject.setBaseDir(dir);
          if (savedDir != null) {
            newProject.setInheritedProperty(PROJECT_BASEDIR, dir.getAbsolutePath());
          }
        }
      } else {
        dir = getProject().getBaseDir();
      }
      overrideProperties();
      if (antFile == null) {
        antFile = getDefaultBuildFile();
      }
      File file = FILE_UTILS.resolveFile(dir, antFile);
      antFile = file.getAbsolutePath();
      log(
          (("calling target(s) " + (locals.size() > 0 ? locals.toString() : "[default]"))
                  + " in build file ")
              + antFile,
          MSG_VERBOSE);
      newProject.setUserProperty(ANT_FILE, antFile);
      String thisAntFile = getProject().getProperty(ANT_FILE);
      if (((thisAntFile != null) && file.equals(getProject().resolveFile(thisAntFile)))
          && (getOwningTarget() != null)) {
        if (getOwningTarget().getName().equals("")) {
          if (getTaskName().equals("antcall")) {
            throw new BuildException("antcall must not be used at" + " the top level.");
          }
          throw new BuildException(
              ((getTaskName() + " task at the") + " top level must not invoke")
                  + " its own build file.");
        }
      }
      try {
        ProjectHelper.configureProject(newProject, file);
      } catch (BuildException ex) {
        throw ProjectHelper.addLocationToBuildException(ex, getLocation());
      }
      if (locals.size() == 0) {
        String defaultTarget = newProject.getDefaultTarget();
        if (defaultTarget != null) {
          locals.add(defaultTarget);
        }
      }
      if (newProject.getProperty(ANT_FILE).equals(getProject().getProperty(ANT_FILE))
          && (getOwningTarget() != null)) {
        String owningTargetName = getOwningTarget().getName();
        if (locals.contains(owningTargetName)) {
          throw new BuildException((getTaskName() + " task calling ") + "its own parent target.");
        }
        boolean circular = false;
        for (Iterator it = locals.iterator(); (!circular) && it.hasNext(); ) {
          Target other = ((Target) (getProject().getTargets().get(it.next())));
          circular |= (other != null) && other.dependsOn(owningTargetName);
        }
        if (circular) {
          throw new BuildException(
              ((((getTaskName() + " task calling a target") + " that depends on")
                          + " its parent target \'")
                      + owningTargetName)
                  + "\'.");
        }
      }
      addReferences();
      if ((locals.size() > 0) && (!((locals.size() == 1) && "".equals(locals.get(0))))) {
        BuildException be = null;
        try {
          log(("Entering " + antFile) + "...", MSG_VERBOSE);
          newProject.fireSubBuildStarted();
          newProject.executeTargets(locals);
        } catch (BuildException ex) {
          be = ProjectHelper.addLocationToBuildException(ex, getLocation());
          throw be;
        } finally {
          log(("Exiting " + antFile) + ".", MSG_VERBOSE);
          newProject.fireSubBuildFinished(be);
        }
      }
    } finally {
      newProject = null;
      Enumeration e = properties.elements();
      while (e.hasMoreElements()) {
        Property p = ((Property) (e.nextElement()));
        p.setProject(null);
      }
      if ((output != null) && (out != null)) {
        try {
          out.close();
        } catch (final Exception ex) {
        }
      }
      dir = savedDir;
      antFile = savedAntFile;
    }
  }
}
