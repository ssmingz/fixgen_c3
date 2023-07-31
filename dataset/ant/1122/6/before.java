class PlaceHold {
  protected void setupJUnitDelegate() {
    final ClassLoader myLoader = JUnitTask.class.getClassLoader();
    if (splitJUnit) {
      final Path path = new Path(getProject());
      path.add(antRuntimeClasses);
      Path extra = getCommandline().getClasspath();
      if (extra != null) {
        path.add(extra);
      }
      mirrorLoader =
          ((ClassLoader)
              (AccessController.doPrivileged(
                  new PrivilegedAction() {
                    @Override
                    public Object run() {
                      return new SplitClassLoader(
                          myLoader,
                          path,
                          getProject(),
                          new String[] {
                            "BriefJUnitResultFormatter",
                            "JUnit4TestMethodAdapter",
                            "JUnitResultFormatter",
                            "JUnitTaskMirrorImpl",
                            "JUnitTestRunner",
                            "JUnitVersionHelper",
                            "OutErrSummaryJUnitResultFormatter",
                            "PlainJUnitResultFormatter",
                            "SummaryJUnitResultFormatter",
                            "TearDownOnVmCrash",
                            "XMLJUnitResultFormatter",
                            "IgnoredTestListener",
                            "IgnoredTestResult",
                            "CustomJUnit4TestAdapterCache",
                            "TestListenerWrapper"
                          });
                    }
                  })));
    } else {
      mirrorLoader = myLoader;
    }
    delegate = createMirror(this, mirrorLoader);
  }
}
