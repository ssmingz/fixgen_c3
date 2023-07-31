class PlaceHold {
  protected boolean isRebuildRequired(File genericJarFile, File websphereJarFile) {
    boolean rebuild = false;
    JarFile genericJar = null;
    JarFile wasJar = null;
    File newwasJarFile = null;
    JarOutputStream newJarStream = null;
    try {
      log(
          "Checking if websphere Jar needs to be rebuilt for jar " + websphereJarFile.getName(),
          MSG_VERBOSE);
      if (((genericJarFile.exists() && genericJarFile.isFile()) && websphereJarFile.exists())
          && websphereJarFile.isFile()) {
        genericJar = new JarFile(genericJarFile);
        wasJar = new JarFile(websphereJarFile);
        Hashtable genericEntries = new Hashtable();
        Hashtable wasEntries = new Hashtable();
        Hashtable replaceEntries = new Hashtable();
        for (Enumeration e = genericJar.entries(); e.hasMoreElements(); ) {
          JarEntry je = ((JarEntry) (e.nextElement()));
          genericEntries.put(je.getName().replace('\\', '/'), je);
        }
        for (Enumeration e = wasJar.entries(); e.hasMoreElements(); ) {
          JarEntry je = ((JarEntry) (e.nextElement()));
          wasEntries.put(je.getName(), je);
        }
        ClassLoader genericLoader = getClassLoaderFromJar(genericJarFile);
        for (Enumeration e = genericEntries.keys(); e.hasMoreElements(); ) {
          String filepath = ((String) (e.nextElement()));
          if (wasEntries.containsKey(filepath)) {
            JarEntry genericEntry = ((JarEntry) (genericEntries.get(filepath)));
            JarEntry wasEntry = ((JarEntry) (wasEntries.get(filepath)));
            if ((genericEntry.getCrc() != wasEntry.getCrc())
                || (genericEntry.getSize() != wasEntry.getSize())) {
              if (genericEntry.getName().endsWith(".class")) {
                String classname = genericEntry.getName().replace(File.separatorChar, '.');
                classname = classname.substring(0, classname.lastIndexOf(".class"));
                Class genclass = genericLoader.loadClass(classname);
                if (genclass.isInterface()) {
                  log(("Interface " + genclass.getName()) + " has changed", MSG_VERBOSE);
                  rebuild = true;
                  break;
                } else {
                  replaceEntries.put(filepath, genericEntry);
                }
              } else {
                if (!genericEntry.getName().equals("META-INF/MANIFEST.MF")) {
                  log(("Non class file " + genericEntry.getName()) + " has changed", MSG_VERBOSE);
                  rebuild = true;
                }
                break;
              }
            }
          } else {
            log(("File " + filepath) + " not present in websphere jar", MSG_VERBOSE);
            rebuild = true;
            break;
          }
        }
        if (!rebuild) {
          log("No rebuild needed - updating jar", MSG_VERBOSE);
          newwasJarFile = new File(websphereJarFile.getAbsolutePath() + ".temp");
          if (newwasJarFile.exists()) {
            newwasJarFile.delete();
          }
          newJarStream = new JarOutputStream(new FileOutputStream(newwasJarFile));
          newJarStream.setLevel(0);
          for (Enumeration e = wasEntries.elements(); e.hasMoreElements(); ) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            InputStream is;
            JarEntry je = ((JarEntry) (e.nextElement()));
            if ((je.getCompressedSize() == (-1)) || (je.getCompressedSize() == je.getSize())) {
              newJarStream.setLevel(0);
            } else {
              newJarStream.setLevel(9);
            }
            if (replaceEntries.containsKey(je.getName())) {
              log("Updating Bean class from generic Jar " + je.getName(), MSG_VERBOSE);
              je = ((JarEntry) (replaceEntries.get(je.getName())));
              is = genericJar.getInputStream(je);
            } else {
              is = wasJar.getInputStream(je);
            }
            newJarStream.putNextEntry(new JarEntry(je.getName()));
            while ((bytesRead = is.read(buffer)) != (-1)) {
              newJarStream.write(buffer, 0, bytesRead);
            }
            is.close();
          }
        } else {
          log("websphere Jar rebuild needed due to changed " + "interface or XML", MSG_VERBOSE);
        }
      } else {
        rebuild = true;
      }
    } catch (ClassNotFoundException cnfe) {
      String cnfmsg =
          ("ClassNotFoundException while processing ejb-jar file" + ". Details: ")
              + cnfe.getMessage();
      throw new BuildException(cnfmsg, cnfe);
    } catch (IOException ioe) {
      String msg =
          ("IOException while processing ejb-jar file " + ". Details: ") + ioe.getMessage();
      throw new BuildException(msg, ioe);
    } finally {
      if (genericJar != null) {
        try {
          genericJar.close();
        } catch (IOException closeException) {
        }
      }
      if (wasJar != null) {
        try {
          wasJar.close();
        } catch (IOException closeException) {
        }
      }
      if (newJarStream != null) {
        try {
          newJarStream.close();
        } catch (IOException closeException) {
        }
        try {
          FileUtils.newFileUtils().rename(newwasJarFile, websphereJarFile);
        } catch (IOException renameException) {
          log(renameException.getMessage(), MSG_WARN);
          rebuild = true;
        }
      }
    }
    return rebuild;
  }
}
