class PlaceHold {
  protected Main(String[] args) throws BuildException {
    String searchForThis = null;
    PrintStream logTo = null;
    for (int i = 0; i < args.length; i++) {
      String arg = args[i];
      if (arg.equals("-help")) {
        printUsage();
        return;
      } else if (arg.equals("-version")) {
        printVersion();
        return;
      } else if (arg.equals("-diagnostics")) {
        Diagnostics.doReport(System.out);
        return;
      } else if (arg.equals("-quiet") || arg.equals("-q")) {
        msgOutputLevel = Project.MSG_WARN;
      } else if (arg.equals("-verbose") || arg.equals("-v")) {
        printVersion();
        msgOutputLevel = Project.MSG_VERBOSE;
      } else if (arg.equals("-debug")) {
        printVersion();
        msgOutputLevel = Project.MSG_DEBUG;
      } else if (arg.equals("-logfile") || arg.equals("-l")) {
        try {
          File logFile = new File(args[i + 1]);
          i++;
          logTo = new PrintStream(new FileOutputStream(logFile));
          isLogFileUsed = true;
        } catch (IOException ioe) {
          String msg =
              ("Cannot write on the specified log file. "
                      + "Make sure the path exists and you have write ")
                  + "permissions.";
          throw new BuildException(msg);
        } catch (ArrayIndexOutOfBoundsException aioobe) {
          String msg = "You must specify a log file when " + "using the -log argument";
          throw new BuildException(msg);
        }
      } else if ((arg.equals("-buildfile") || arg.equals("-file")) || arg.equals("-f")) {
        try {
          buildFile = new File(args[i + 1]);
          i++;
        } catch (ArrayIndexOutOfBoundsException aioobe) {
          String msg = "You must specify a buildfile when " + "using the -buildfile argument";
          throw new BuildException(msg);
        }
      } else if (arg.equals("-listener")) {
        try {
          listeners.addElement(args[i + 1]);
          i++;
        } catch (ArrayIndexOutOfBoundsException aioobe) {
          String msg = "You must specify a classname when " + "using the -listener argument";
          throw new BuildException(msg);
        }
      } else if (arg.startsWith("-D")) {
        String name = arg.substring(2, arg.length());
        String value = null;
        int posEq = name.indexOf("=");
        if (posEq > 0) {
          value = name.substring(posEq + 1);
          name = name.substring(0, posEq);
        } else if (i < (args.length - 1)) {
          value = args[++i];
        }
        definedProps.put(name, value);
      } else if (arg.equals("-logger")) {
        if (loggerClassname != null) {
          throw new BuildException("Only one logger class may " + " be specified.");
        }
        try {
          loggerClassname = args[++i];
        } catch (ArrayIndexOutOfBoundsException aioobe) {
          throw new BuildException(
              "You must specify a classname when" + " using the -logger argument");
        }
      } else if (arg.equals("-inputhandler")) {
        if (inputHandlerClassname != null) {
          throw new BuildException("Only one input handler class may " + "be specified.");
        }
        try {
          inputHandlerClassname = args[++i];
        } catch (ArrayIndexOutOfBoundsException aioobe) {
          throw new BuildException(
              ("You must specify a classname when" + " using the -inputhandler") + " argument");
        }
      } else if (arg.equals("-emacs")) {
        emacsMode = true;
      } else if (arg.equals("-projecthelp")) {
        projectHelp = true;
      } else if (arg.equals("-find")) {
        if (i < (args.length - 1)) {
          searchForThis = args[++i];
        } else {
          searchForThis = DEFAULT_BUILD_FILENAME;
        }
      } else if (arg.startsWith("-propertyfile")) {
        try {
          propertyFiles.addElement(args[i + 1]);
          i++;
        } catch (ArrayIndexOutOfBoundsException aioobe) {
          String msg =
              "You must specify a property filename when " + "using the -propertyfile argument";
          throw new BuildException(msg);
        }
      } else if (arg.startsWith("-")) {
        String msg = "Unknown argument: " + arg;
        System.out.println(msg);
        printUsage();
        throw new BuildException("");
      } else {
        targets.addElement(arg);
      }
    }
    if (buildFile == null) {
      if (searchForThis != null) {
        buildFile = findBuildFile(System.getProperty("user.dir"), searchForThis);
      } else {
        buildFile = new File(DEFAULT_BUILD_FILENAME);
      }
    }
    if (!buildFile.exists()) {
      System.out.println(("Buildfile: " + buildFile) + " does not exist!");
      throw new BuildException("Build failed");
    }
    if (buildFile.isDirectory()) {
      System.out.println(("What? Buildfile: " + buildFile) + " is a dir!");
      throw new BuildException("Build failed");
    }
    for (int propertyFileIndex = 0; propertyFileIndex < propertyFiles.size(); propertyFileIndex++) {
      String filename = ((String) (propertyFiles.elementAt(propertyFileIndex)));
      Properties props = new Properties();
      FileInputStream fis = null;
      try {
        fis = new FileInputStream(filename);
        props.load(fis);
      } catch (IOException e) {
        System.out.println((("Could not load property file " + filename) + ": ") + e.getMessage());
      } finally {
        if (fis != null) {
          try {
            fis.close();
          } catch (IOException e) {
          }
        }
      }
      Enumeration propertyNames = props.propertyNames();
      while (propertyNames.hasMoreElements()) {
        String name = ((String) (propertyNames.nextElement()));
        if (definedProps.getProperty(name) == null) {
          definedProps.put(name, props.getProperty(name));
        }
      }
    }
    if (msgOutputLevel >= Project.MSG_INFO) {
      System.out.println("Buildfile: " + buildFile);
    }
    if (logTo != null) {
      out = err = logTo;
      System.setOut(out);
      System.setErr(out);
    }
    readyToRun = true;
  }
}
