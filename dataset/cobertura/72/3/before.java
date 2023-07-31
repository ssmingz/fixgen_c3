class PlaceHold {
  private void runTest() throws Exception {
    File tempDir = TestUtils.getTempDir();
    File srcDir = new File(tempDir, "src");
    File instrumentDir = new File(tempDir, "instrument");
    File mainSourceFile = new File(srcDir, "mypackage/Main.java");
    File datafile = new File(srcDir, "cobertura.ser");
    mainSourceFile.getParentFile().mkdirs();
    BufferedWriter bw = null;
    try {
      bw = new BufferedWriter(new FileWriter(mainSourceFile));
      bw.write(getMainCode(instrumentDir));
    } catch (IOException e) {
      e.printStackTrace();
      fail();
    } finally {
      IOUtils.closeQuietly(bw);
    }
    File calledSourceFile = new File(srcDir, "mypackage/Called.java");
    try {
      bw = new BufferedWriter(new FileWriter(calledSourceFile));
      bw.write(CALLED_CODE);
    } catch (IOException e) {
      e.printStackTrace();
      fail();
    } finally {
      IOUtils.closeQuietly(bw);
    }
    TestUtils.compileSource(ant, srcDir);
    TestUtils.instrumentClasses(ant, srcDir, datafile, instrumentDir);
    DirSet dirSet = new DirSet();
    dirSet.setDir(srcDir);
    dirSet.setProject(project);
    Path classpath = new Path(TestUtils.project);
    classpath.addDirset(dirSet);
    classpath.addDirset(TestUtils.getCoberturaClassDirSet());
    Java java = new Java();
    java.setProject(project);
    java.setClassname("mypackage.Main");
    java.setDir(srcDir);
    java.setFork(true);
    java.setFailonerror(true);
    java.setClasspath(classpath);
    java.execute();
    ReportTask reportTask = new ReportTask();
    reportTask.setProject(project);
    reportTask.setDataFile(datafile.getAbsolutePath());
    reportTask.setFormat("xml");
    reportTask.setDestDir(srcDir);
    reportTask.execute();
    Node dom = TestUtils.getXMLReportDOM(srcDir.getAbsolutePath() + "/coverage.xml");
    List<Node> lines = TestUtils.getLineCounts(dom, "mypackage.Called", "callThis", null);
    assertEquals(1, lines.size());
    for (int i = 0; i < lines.size(); i++) {
      assertEquals("hit count incorrect", "2", lines.get(i).attribute("hits"));
    }
  }
}
