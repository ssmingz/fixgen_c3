class PlaceHold {
  private void run(String[] args) throws LaunchException, MalformedURLException {
    String antHomeProperty = System.getProperty(ANTHOME_PROPERTY);
    File antHome = null;
    File sourceJar = Locator.getClassSource(getClass());
    File jarDir = sourceJar.getParentFile();
    if (antHomeProperty != null) {
      antHome = new File(antHomeProperty);
    }
    if ((antHome == null) || (!antHome.exists())) {
      antHome = jarDir.getParentFile();
      System.setProperty(ANTHOME_PROPERTY, antHome.getAbsolutePath());
    }
    if (!antHome.exists()) {
      throw new LaunchException("Ant home is set incorrectly or " + "ant could not be located");
    }
    List libPaths = new ArrayList();
    String cpString = null;
    List argList = new ArrayList();
    String[] newArgs;
    boolean noUserLib = false;
    boolean noClassPath = false;
    for (int i = 0; i < args.length; ++i) {
      if (args[i].equals("-lib")) {
        if (i == (args.length - 1)) {
          throw new LaunchException(
              "The -lib argument must " + "be followed by a library location");
        }
        libPaths.add(args[++i]);
      } else if (args[i].equals("-cp")) {
        if (i == (args.length - 1)) {
          throw new LaunchException(
              "The -cp argument must " + "be followed by a classpath expression");
        }
        if (cpString != null) {
          throw new LaunchException("The -cp argument must " + "not be repeated");
        }
        cpString = args[++i];
      } else if (args[i].equals("--nouserlib") || args[i].equals("-nouserlib")) {
        noUserLib = true;
      } else if (args[i].equals("--noclasspath") || args[i].equals("-noclasspath")) {
        noClassPath = true;
      } else {
        argList.add(args[i]);
      }
    }
    if ((libPaths.size() == 0) && (cpString == null)) {
      newArgs = args;
    } else {
      newArgs = ((String[]) (argList.toArray(new String[0])));
    }
    List libPathURLs = new ArrayList();
    if ((cpString != null) && (!noClassPath)) {
      addPath(cpString, false, libPathURLs);
    }
    for (Iterator i = libPaths.iterator(); i.hasNext(); ) {
      String libPath = ((String) (i.next()));
      addPath(libPath, true, libPathURLs);
    }
    URL[] libJars = ((URL[]) (libPathURLs.toArray(new URL[0])));
    File toolsJar = Locator.getToolsJar();
    File antLibDir = null;
    String antLibDirProperty = System.getProperty(ANTLIBDIR_PROPERTY);
    if (antLibDirProperty != null) {
      antLibDir = new File(antLibDirProperty);
    }
    if ((antLibDir == null) || (!antLibDir.exists())) {
      antLibDir = jarDir;
      System.setProperty(ANTLIBDIR_PROPERTY, antLibDir.getAbsolutePath());
    }
    URL[] systemJars = Locator.getLocationURLs(antLibDir);
    File userLibDir = new File(System.getProperty(USER_HOMEDIR), USER_LIBDIR);
    URL[] userJars = (noUserLib) ? new URL[0] : Locator.getLocationURLs(userLibDir);
    int numJars = (libJars.length + userJars.length) + systemJars.length;
    if (toolsJar != null) {
      numJars++;
    }
    URL[] jars = new URL[numJars];
    System.arraycopy(libJars, 0, jars, 0, libJars.length);
    System.arraycopy(userJars, 0, jars, libJars.length, userJars.length);
    System.arraycopy(systemJars, 0, jars, userJars.length + libJars.length, systemJars.length);
    if (toolsJar != null) {
      jars[jars.length - 1] = toolsJar.toURL();
    }
    StringBuffer baseClassPath = new StringBuffer(System.getProperty("java.class.path"));
    if (baseClassPath.charAt(baseClassPath.length() - 1) == File.pathSeparatorChar) {
      baseClassPath.setLength(baseClassPath.length() - 1);
    }
    for (int i = 0; i < jars.length; ++i) {
      baseClassPath.append(File.pathSeparatorChar);
      baseClassPath.append(Locator.fromURI(jars[i].toString()));
    }
    System.setProperty("java.class.path", baseClassPath.toString());
    URLClassLoader loader = new URLClassLoader(jars);
    Thread.currentThread().setContextClassLoader(loader);
    Class mainClass = null;
    try {
      mainClass = loader.loadClass(MAIN_CLASS);
      AntMain main = ((AntMain) (mainClass.newInstance()));
      main.startAnt(newArgs, null, null);
    } catch (InstantiationException ex) {
      System.out.println("Incompatible version of org.apache.tools.ant detected");
      File mainJar = Locator.getClassSource(mainClass);
      System.out.println("Location of this class " + mainJar);
    } catch (Throwable t) {
      t.printStackTrace();
    }
  }
}
