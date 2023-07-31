class PlaceHold {
  public static Iterable<JarJDKToolsLibrary> search(GlobalModel model) {
    String javaHome = System.getProperty("java.home");
    String envJavaHome = null;
    String programFiles = null;
    String systemDrive = null;
    if (CURRENT.supports(JAVA_5)) {
      envJavaHome = System.getenv("JAVA_HOME");
      programFiles = System.getenv("ProgramFiles");
      systemDrive = System.getenv("SystemDrive");
    }
    LinkedHashSet<File> roots = new LinkedHashSet<File>();
    if (javaHome != null) {
      addIfDir(new File(javaHome), roots);
      addIfDir(new File(javaHome, ".."), roots);
      addIfDir(new File(javaHome, "../.."), roots);
    }
    if (envJavaHome != null) {
      addIfDir(new File(envJavaHome), roots);
      addIfDir(new File(envJavaHome, ".."), roots);
      addIfDir(new File(envJavaHome, "../.."), roots);
    }
    if (programFiles != null) {
      addIfDir(new File(programFiles, "Java"), roots);
      addIfDir(new File(programFiles), roots);
    }
    addIfDir(new File("/C:/Program Files/Java"), roots);
    addIfDir(new File("/C:/Program Files"), roots);
    if (systemDrive != null) {
      addIfDir(new File(systemDrive, "Java"), roots);
      addIfDir(new File(systemDrive), roots);
    }
    addIfDir(new File("/C:/Java"), roots);
    addIfDir(new File("/C:"), roots);
    addIfDir(new File("/System/Library/Frameworks/JavaVM.framework/Versions"), roots);
    addIfDir(new File("/usr/java"), roots);
    addIfDir(new File("/usr/j2se"), roots);
    addIfDir(new File("/usr"), roots);
    addIfDir(new File("/usr/local/java"), roots);
    addIfDir(new File("/usr/local/j2se"), roots);
    addIfDir(new File("/usr/local"), roots);
    LinkedHashSet<File> jars = new LinkedHashSet<File>();
    Predicate<File> subdirFilter =
        LambdaUtil.or(
            IOUtil.regexpCanonicalCaseFilePredicate("j2sdk.*"),
            IOUtil.regexpCanonicalCaseFilePredicate("jdk.*"),
            IOUtil.regexpCanonicalCaseFilePredicate("\\d+\\.\\d+\\.\\d+"));
    for (File root : roots) {
      for (File subdir : IOUtil.attemptListFilesAsIterable(root, subdirFilter)) {
        addIfFile(new File(subdir, "lib/tools.jar"), jars);
        addIfFile(new File(subdir, "Classes/classes.jar"), jars);
      }
    }
    Map<FullVersion, Iterable<JarJDKToolsLibrary>> results =
        new TreeMap<FullVersion, Iterable<JarJDKToolsLibrary>>();
    for (File jar : jars) {
      JarJDKToolsLibrary lib = makeFromFile(jar, model);
      if (lib.isValid()) {
        FullVersion v = lib.version();
        if (results.containsKey(v)) {
          results.put(v, IterUtil.compose(lib, results.get(v)));
        } else {
          results.put(v, IterUtil.singleton(lib));
        }
      }
    }
    return ReverseIterable.make(IterUtil.collapse(results.values()));
  }
}
