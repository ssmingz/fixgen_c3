class PlaceHold {
  private void _rawJUnitOpenDefDocs(List<OpenDefinitionsDocument> lod, boolean allTests) {
    File buildDir = _model.getBuildDirectory();
    HashSet<String> openDocFiles = new HashSet<String>();
    HashMap<File, File> classDirsAndRoots = new HashMap<File, File>();
    for (OpenDefinitionsDocument doc : lod) {
      if (doc.isSourceFile()) {
        try {
          File sourceRoot = doc.getSourceRoot();
          openDocFiles.add(doc.getCanonicalPath());
          String packagePath = doc.getPackageName().replace('.', File.separatorChar);
          File buildRoot = (buildDir == FileOps.NULL_FILE) ? sourceRoot : buildDir;
          File classFileDir = new File(IOUtil.attemptCanonicalFile(buildRoot), packagePath);
          File sourceDir =
              (buildDir == FileOps.NULL_FILE)
                  ? classFileDir
                  : new File(IOUtil.attemptCanonicalFile(sourceRoot), packagePath);
          if (!classDirsAndRoots.containsKey(classFileDir)) {
            classDirsAndRoots.put(classFileDir, sourceDir);
          }
        } catch (InvalidPackageException e) {
        }
      }
    }
    Set<File> classDirs = classDirsAndRoots.keySet();
    ArrayList<String> classNames = new ArrayList<String>();
    ArrayList<File> files = new ArrayList<File>();
    boolean isProject = _model.isProjectActive();
    try {
      for (File dir : classDirs) {
        File[] listing = dir.listFiles();
        if (listing != null) {
          for (File entry : listing) {
            String name = entry.getName();
            if (!name.endsWith(".class")) {
              continue;
            }
            if (_forceTestSuffix) {
              String noExtName = name.substring(0, name.length() - 6);
              int indexOfLastDot = noExtName.lastIndexOf('.');
              String simpleClassName = noExtName.substring(indexOfLastDot + 1);
              if (isProject && (!simpleClassName.endsWith("Test"))) {
                continue;
              }
            }
            if (!entry.isFile()) {
              continue;
            }
            try {
              final Box<String> className = new SimpleBox<String>();
              final Box<String> sourceName = new SimpleBox<String>();
              new ClassReader(IOUtil.toByteArray(entry))
                  .accept(
                      new ClassVisitor() {
                        public void visit(
                            int version,
                            int access,
                            String name,
                            String sig,
                            String sup,
                            String[] inters) {
                          className.set(name.replace('/', '.'));
                        }

                        public void visitSource(String source, String debug) {
                          sourceName.set(source);
                        }

                        public void visitOuterClass(String owner, String name, String desc) {}

                        public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
                          return null;
                        }

                        public void visitAttribute(Attribute attr) {}

                        public void visitInnerClass(
                            String name, String out, String in, int access) {}

                        public FieldVisitor visitField(
                            int a, String n, String d, String s, Object v) {
                          return null;
                        }

                        public MethodVisitor visitMethod(
                            int a, String n, String d, String s, String[] e) {
                          return null;
                        }

                        public void visitEnd() {}
                      },
                      0);
              File rootDir = classDirsAndRoots.get(dir);
              String javaSourceFileName =
                  (getCanonicalPath(rootDir) + File.separator) + sourceName.value();
              int indexOfExtDot = javaSourceFileName.lastIndexOf('.');
              if (indexOfExtDot == (-1)) {
                continue;
              }
              String strippedName = javaSourceFileName.substring(0, indexOfExtDot);
              String sourceFileName;
              if (openDocFiles.contains(javaSourceFileName)) {
                sourceFileName = javaSourceFileName;
              } else if (openDocFiles.contains(strippedName + ".dj0")) {
                sourceFileName = strippedName + ".dj0";
              } else if (openDocFiles.contains(strippedName + ".dj1")) {
                sourceFileName = strippedName + ".dj1";
              } else if (openDocFiles.contains(strippedName + ".dj2")) {
                sourceFileName = strippedName + ".dj2";
              } else {
                continue;
              }
              File sourceFile = new File(sourceFileName);
              classNames.add(className.value());
              files.add(sourceFile);
            } catch (IOException e) {
            }
          }
        }
      }
    } catch (Exception e) {
      throw new UnexpectedException(e);
    }
    synchronized (_compilerModel.getCompilerLock()) {
      List<String> tests;
      try {
        tests = _jvm.findTestClasses(classNames, files);
      } catch (IOException e) {
        throw new UnexpectedException(e);
      }
      if ((tests == null) || tests.isEmpty()) {
        nonTestCase(allTests);
        return;
      }
      try {
        _testInProgress = true;
        Utilities.invokeLater(
            new Runnable() {
              public void run() {
                _notifier.junitStarted();
              }
            });
        _jvm.runTestSuite();
      } catch (Exception e) {
        _notifier.junitEnded();
        _testInProgress = false;
        throw new UnexpectedException(e);
      }
    }
  }
}
