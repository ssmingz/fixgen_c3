class PlaceHold {
  private void runBuild(ClassLoader coreLoader) throws BuildException {
    if (!readyToRun) {
      return;
    }
    final Project project = new Project();
    project.setCoreLoader(coreLoader);
    Throwable error = null;
    try {
      addBuildListeners(project);
      addInputHandler(project);
      PrintStream savedErr = System.err;
      PrintStream savedOut = System.out;
      InputStream savedIn = System.in;
      SecurityManager oldsm = null;
      oldsm = System.getSecurityManager();
      try {
        if (allowInput) {
          project.setDefaultInputStream(System.in);
        }
        System.setIn(new DemuxInputStream(project));
        System.setOut(new PrintStream(new DemuxOutputStream(project, false)));
        System.setErr(new PrintStream(new DemuxOutputStream(project, true)));
        if (!projectHelp) {
          project.fireBuildStarted();
        }
        if (threadPriority != null) {
          try {
            project.log("Setting Ant's thread priority to " + threadPriority, MSG_VERBOSE);
            Thread.currentThread().setPriority(threadPriority.intValue());
          } catch (SecurityException swallowed) {
            project.log("A security manager refused to set the -nice value");
          }
        }
        project.init();
        PropertyHelper propertyHelper =
            ((PropertyHelper) (PropertyHelper.getPropertyHelper(project)));
        HashMap props = new HashMap(definedProps);
        new ResolvePropertyMap(project, propertyHelper, propertyHelper.getExpanders())
            .resolveAllProperties(props, null);
        for (Iterator e = props.entrySet().iterator(); e.hasNext(); ) {
          Map.Entry ent = ((Map.Entry) (e.next()));
          String arg = ((String) (ent.getKey()));
          Object value = ent.getValue();
          project.setUserProperty(arg, String.valueOf(value));
        }
        project.setUserProperty(ANT_FILE, buildFile.getAbsolutePath());
        project.setUserProperty(ANT_FILE_TYPE, ANT_FILE_TYPE_FILE);
        project.setKeepGoingMode(keepGoingMode);
        if (proxy) {
          ProxySetup proxySetup = new ProxySetup(project);
          proxySetup.enableProxies();
        }
        ProjectHelper.configureProject(project, buildFile);
        if (projectHelp) {
          printDescription(project);
          printTargets(project, msgOutputLevel > Project.MSG_INFO);
          return;
        }
        if (targets.size() == 0) {
          if (project.getDefaultTarget() != null) {
            targets.addElement(project.getDefaultTarget());
          }
        }
        project.executeTargets(targets);
      } finally {
        if (oldsm != null) {
          System.setSecurityManager(oldsm);
        }
        System.setOut(savedOut);
        System.setErr(savedErr);
        System.setIn(savedIn);
      }
    } catch (RuntimeException exc) {
      error = exc;
      throw exc;
    } catch (Error e) {
      error = e;
      throw e;
    } finally {
      if (!projectHelp) {
        try {
          project.fireBuildFinished(error);
        } catch (Throwable t) {
          System.err.println(
              "Caught an exception while logging the" + " end of the build.  Exception was:");
          t.printStackTrace();
          if (error != null) {
            System.err.println("There has been an error prior to" + " that:");
            error.printStackTrace();
          }
          throw new BuildException(t);
        }
      } else if (error != null) {
        project.log(error.toString(), MSG_ERR);
      }
    }
  }
}
