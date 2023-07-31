class PlaceHold {
  protected void jar(
      String basedir,
      String jarFile,
      String metaInfDir,
      String metaInfIncludes,
      String classpath,
      String mainClass) {
    try {
      File base = new File(resolve(basedir));
      File jar = new File(resolve(jarFile));
      System.out.println("        [jar] Creating jar " + jar);
      Manifest manifest = new Manifest();
      Attributes attributes = manifest.getMainAttributes();
      attributes.putValue("Manifest-Version", "1.0");
      attributes.putValue("Created-By", "Mutant Bootstrap");
      if (classpath != null) {
        attributes.putValue("Class-Path", classpath);
      }
      if (mainClass != null) {
        attributes.putValue("Main-Class", mainClass);
      }
      JarOutputStream jos = new JarOutputStream(new FileOutputStream(jar), manifest);
      addToJar(jos, base, null);
      if (metaInfDir != null) {
        File[] metaFileSet = buildFileSet(metaInfDir, metaInfIncludes);
        addFilesToJar(jos, new File(resolve(metaInfDir)), metaFileSet, "META-INF");
      }
      jos.close();
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException("Unable to Jar file");
    }
  }
}
