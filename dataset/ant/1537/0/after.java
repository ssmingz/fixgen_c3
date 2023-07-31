class PlaceHold {
  private void processArgs(String[] args) {
    String searchForThis = null;
    PrintStream logTo = null;
    for (int i = 0; i < args.length; i++) {
      String arg = args[i];
      if (arg.equals("-help") || arg.equals("-h")) {
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
      } else if (arg.equals("-debug") || arg.equals("-d")) {
        printVersion();
        msgOutputLevel = Project.MSG_DEBUG;
      } else if (arg.equals("-noinput")) {
        allowInput = false;
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
        i = handleArgBuildFile(args, i);
      } else if (arg.equals("-listener")) {
        i = handleArgListener(args, i);
      } else if (arg.startsWith("-D")) {
        i = handleArgDefine(args, i);
      } else if (arg.equals("-logger")) {
        i = handleArgLogger(args, i);
      } else if (arg.equals("-inputhandler")) {
        i = handleArgInputHandler(args, i);
      } else if (arg.equals("-emacs") || arg.equals("-e")) {
        emacsMode = true;
      } else if (arg.equals("-projecthelp") || arg.equals("-p")) {
        projectHelp = true;
      } else if (arg.equals("-find") || arg.equals("-s")) {
        if (i < (args.length - 1)) {
          searchForThis = args[++i];
        } else {
          searchForThis = DEFAULT_BUILD_FILENAME;
        }
      } else if (arg.startsWith("-propertyfile")) {
        i = handleArgPropertyFile(args, i);
      } else if (arg.equals("-k") || arg.equals("-keep-going")) {
        keepGoingMode = true;
      } else if (arg.equals("-nice")) {
        i = handleArgNice(args, i);
      } else if (LAUNCH_COMMANDS.contains(arg)) {
        String msg =
            (((("Ant's Main method is being handed " + "an option ") + arg)
                        + " that is only for the launcher class.")
                    + "\nThis can be caused by a version mismatch between ")
                + "the ant script/.bat file and Ant itself.";
        throw new BuildException(msg);
      } else if (arg.equals("-autoproxy")) {
        proxy = true;
      } else if (arg.startsWith("-")) {
        String msg = "Unknown argument: " + arg;
        System.err.println(msg);
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
    loadPropertyFiles();
    if (msgOutputLevel >= Project.MSG_INFO) {
      System.out.println("Buildfile: " + buildFile);
    }
    if (logTo != null) {
      out = logTo;
      err = logTo;
      System.setOut(out);
      System.setErr(err);
    }
    readyToRun = true;
  }
}
