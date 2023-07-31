class PlaceHold {
  @Test
  public void performanceTest() throws Exception {
    File tempDir = TestUtils.getTempDir();
    FileUtils.deleteDirectory(tempDir);
    File srcDir = new File(tempDir, "src");
    File instrumentDir = new File(tempDir, "instrument");
    File mainSourceFile = new File(srcDir, "mypackage/Main.java");
    File datafile = new File(srcDir, "cobertura.ser");
    mainSourceFile.getParentFile().mkdirs();
    FileUtils.write(
        mainSourceFile,
        ((((((((((((((((("\n package mypackage;" + "\n ") + "\n public class Main extends Thread {")
                                                                        + "\n"
                                                                        + " \tpublic static void"
                                                                        + " main(String[] args) {")
                                                                    + "\n"
                                                                    + " \t\tlong start ="
                                                                    + " System.nanoTime();")
                                                                + "\n \t\tint j = 0;")
                                                            + "\n"
                                                            + " \t\tfor (int i = 0; i < 100000;"
                                                            + " i++) {")
                                                        + "\n \t\t   if (i % 2 == 0) { j+=2; };")
                                                    + "\n \t\t   switch (i % 4) {")
                                                + "\n \t\t      case 0 : ")
                                            + "\n \t\t      case 1 : j++;")
                                        + "\n \t\t      case 2 : j+=2;")
                                    + "\n \t\t      default: j+=3;")
                                + "\n \t\t   } ")
                            + "\n \t\t}")
                        + "\n \t\tlong stop = System.nanoTime();")
                    + "\n"
                    + " \t\tSystem.out.println(\"Test took:\" + (stop - start)/100000.0 + \""
                    + " milis\");")
                + "\n \t}")
            + "\n }");
    TestUtils.compileSource(ant, srcDir);
    System.out.println("Run without instrumentation:\n");
    Path classpath = new Path(TestUtils.project);
    DirSet dirSetSrcDir = new DirSet();
    dirSetSrcDir.setDir(srcDir);
    classpath.addDirset(dirSetSrcDir);
    Java java = new Java();
    java.setProject(project);
    java.setClassname("mypackage.Main");
    java.setDir(srcDir);
    java.setFork(true);
    java.setFailonerror(true);
    java.setClasspath(classpath);
    java.setOutput(new File(tempDir, "PT_uninstrumented.log"));
    java.execute();
    System.out.println(FileUtils.readFileToString(new File(tempDir, "PT_uninstrumented.log")));
    TestUtils.instrumentClasses(ant, srcDir, datafile, instrumentDir);
    System.out.println("Run with instrumentation (not threadsafe-rigorous):\n");
    classpath = new Path(TestUtils.project);
    DirSet dirSetInstrumentDir = new DirSet();
    dirSetSrcDir = new DirSet();
    dirSetInstrumentDir.setDir(instrumentDir);
    dirSetSrcDir.setDir(srcDir);
    classpath.addDirset(dirSetInstrumentDir);
    classpath.addDirset(dirSetSrcDir);
    classpath.addDirset(TestUtils.getCoberturaClassDirSet());
    java = new Java();
    java.setClassname("mypackage.Main");
    java.setDir(srcDir);
    java.setFork(true);
    java.setFailonerror(true);
    java.setClasspath(classpath);
    java.setProject(project);
    java.setOutput(new File(tempDir, "PT_instrumentedNonThreadSafe.log"));
    java.execute();
    System.out.println(
        FileUtils.readFileToString(new File(tempDir, "PT_instrumentedNonThreadSafe.log"))
            .toString());
    TestUtils.compileSource(ant, srcDir);
    TestUtils.instrumentClasses(
        ant,
        srcDir,
        datafile,
        instrumentDir,
        new HashMap() {
          {
            put("threadsafeRigorous", true);
          }
        });
    System.out.println("Run with instrumentation (threadsafe-rigorous):\n");
    java = new Java();
    java.setClassname("mypackage.Main");
    java.setDir(srcDir);
    java.setFork(true);
    java.setFailonerror(true);
    java.setClasspath(classpath);
    java.setProject(project);
    java.setOutput(new File(tempDir, "PT_instrumentedThreadSafe.log"));
    java.execute();
    System.out.println(
        FileUtils.readFileToString(new File(tempDir, "PT_instrumentedThreadSafe.log")));
    ReportTask reportTask = new ReportTask();
    reportTask.setProject(project);
    reportTask.setDataFile(datafile.getAbsolutePath());
    reportTask.setFormat("xml");
    reportTask.setDestDir(srcDir);
    reportTask.execute();
  }
}
