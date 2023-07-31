class PlaceHold {
  public void execute() throws BuildException {
    File savedDir = dir;
    String savedAntFile = antFile;
    Vector locals = new Vector(targets);
    try {
      if (newProject == null) {
        reinit();
      }
      if ((dir == null) && inheritAll) {
        dir = getProject().getBaseDir();
      }
      initializeProject();
      if (dir != null) {
        newProject.setBaseDir(dir);
        if (savedDir != null) {
          newProject.setInheritedProperty("basedir", dir.getAbsolutePath());
        }
      } else {
        dir = getProject().getBaseDir();
      }
      overrideProperties();
      if (antFile == null) {
        antFile = "build.xml";
      }
      File file = FileUtils.newFileUtils().resolveFile(dir, antFile);
      antFile = file.getAbsolutePath();
      log(
          (("calling target(s) " + (locals.size() > 0 ? locals.toString() : "[default]"))
                  + " in build file ")
              + antFile,
          MSG_VERBOSE);
      newProject.setUserProperty("ant.file", antFile);
      String thisAntFile = getProject().getProperty("ant.file");
      if (((thisAntFile != null)
              && newProject
                  .resolveFile(newProject.getProperty("ant.file"))
                  .equals(getProject().resolveFile(thisAntFile)))
          && (getOwningTarget() != null)) {
        if (getOwningTarget().getName().equals("")) {
          if (getTaskName().equals("antcall")) {
            throw new BuildException("antcall must not be used at" + " the top level.");
          } else {
            throw new BuildException(
                ((getTaskName() + " task at the") + " top level must not invoke")
                    + " its own build file.");
          }
        }
      }
      try {
        ProjectHelper.configureProject(newProject, new File(antFile));
      } catch (BuildException ex) {
        throw ProjectHelper.addLocationToBuildException(ex, getLocation());
      }
      if (locals.size() == 0) {
        String defaultTarget = newProject.getDefaultTarget();
        if (defaultTarget != null) {
          locals.add(defaultTarget);
        }
      }
      if (newProject.getProperty("ant.file").equals(getProject().getProperty("ant.file"))
          && (getOwningTarget() != null)) {
        String owningTargetName = getOwningTarget().getName();
        if (locals.contains(owningTargetName)) {
          throw new BuildException((getTaskName() + " task calling ") + "its own parent target.");
        } else {
          boolean circular = false;
          for (Iterator it = locals.iterator(); (!circular) && it.hasNext(); ) {
            Target other = ((Target) (getProject().getTargets().get(((String) (it.next())))));
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
      }
      addReferences();
      if ((locals.size() > 0) && (!((locals.size() == 1) && (locals.get(0) == "")))) {
        Throwable t = null;
        try {
          log(("Entering " + antFile) + "...", MSG_VERBOSE);
          newProject.fireSubBuildStarted();
          EXECUTOR.executeTargets(
              newProject, ((String[]) (locals.toArray(new String[locals.size()]))));
        } catch (BuildException ex) {
          t = ProjectHelper.addLocationToBuildException(ex, getLocation());
          throw ((BuildException) (t));
        } finally {
          log(("Exiting " + antFile) + ".", MSG_VERBOSE);
          newProject.fireSubBuildFinished(t);
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
