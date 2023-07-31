class PlaceHold {
  public void testCreateJar() {
    File f = new File("test.jar");
    f.deleteOnExit();
    File add = null;
    try {
      String fileContents =
          ((("public class JarTest {" + "\tpublic String getClassName() {")
                      + "\t\treturn \"JarTest\";")
                  + "\t}")
              + "}";
      byte[] b = new byte[fileContents.getBytes("UTF-8").length];
      add = File.createTempFile("JarTest", ".java");
      add.deleteOnExit();
      PrintWriter pw = new PrintWriter(new FileOutputStream(add));
      pw.write(fileContents);
      pw.close();
      JarBuilder jb = new JarBuilder(f);
      jb.addFile(add, "", "JarTest.java");
      jb.addFile(add, "dir", "JarTest.java");
      jb.close();
      testArchive(
          f, new TreeSet<String>(Arrays.asList(new String[] {"JarTest.java", "dir/JarTest.java"})));
      JarInputStream jarStream = new JarInputStream(new FileInputStream(f), true);
      JarEntry ent = jarStream.getNextJarEntry();
      assertTrue("should have JarTest", ent != null);
      assertEquals("names should match", "JarTest.java", ent.getName());
      ent = jarStream.getNextJarEntry();
      assertTrue("should have JarTest", ent != null);
      assertEquals("names should match", "dir/JarTest.java", ent.getName());
    } catch (IOException e) {
      e.printStackTrace();
      fail("failed test");
    }
  }
}
