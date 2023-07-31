class PlaceHold {
  private void runTest(String code) throws Exception {
    File tempDir = TestUtils.getTempDir();
    final File srcDir = new File(tempDir, "src");
    File sourceFile = new File(srcDir, "mypackage/MyThreads.java");
    final File datafile = new File(srcDir, "cobertura.ser");
    sourceFile.getParentFile().mkdirs();
    BufferedWriter bw = null;
    try {
      bw = new BufferedWriter(new FileWriter(sourceFile));
      bw.write(getThreadedCode(code));
    } finally {
      bw.close();
    }
    compileSource(srcDir);
    instrumentClasses(srcDir, datafile);
    Path p = new Path(TestUtils.project);
    DirSet dirSet = new DirSet();
    FileSet fileSet = new FileSet();
    dirSet.setDir(srcDir);
    fileSet.setDir(new File("src/test/resources/antLibrary/common/groovy"));
    fileSet.setIncludes("*.jar");
    p.addFileset(fileSet);
    p.addDirset(dirSet);
    p.setProject(project);
    p.addDirset(TestUtils.getCoberturaClassDirSet());
    p.add(TestUtils.createDependencyPath("org.slf4j", "slf4j-api", "1.7.5"));
    for (int i = 0; i < numberOfRetries; i++) {
      System.out.println("Executing build: " + i);
      Java java = new Java();
      java.setClassname("mypackage.MyThreads");
      java.setDir(srcDir);
      java.setFork(true);
      java.setProject(project);
      java.setFailonerror(true);
      java.setClasspath(p);
      java.execute();
    }
    System.out.println("Starting reporting task.");
    ReportTask reportTask = new ReportTask();
    reportTask.setProject(project);
    reportTask.setDataFile(datafile.getAbsolutePath());
    reportTask.setFormat("xml");
    reportTask.setDestDir(srcDir);
    reportTask.execute();
    System.out.println("Finish reporting task.");
    Node dom = TestUtils.getXMLReportDOM(srcDir.getAbsolutePath() + "/coverage.xml");
    int hitCount = TestUtils.getHitCount(dom, "mypackage.MyThreads", "acall");
    assertEquals(
        "hit count incorrect", (numberOfRetries * numberOfThreads) * numberOfCalls, hitCount);
  }
}
