class PlaceHold {
  protected boolean isRebuildRequired(File genericJarFile, File weblogicJarFile) {
    boolean rebuild = false;
    JarFile genericJar = null;
    JarFile wlJar = null;
    File newWLJarFile = null;
    JarOutputStream newJarStream = null;
    try {
      log(
          "Checking if weblogic Jar needs to be rebuilt for jar " + weblogicJarFile.getName(),
          MSG_VERBOSE);
      if (((genericJarFile.exists() && genericJarFile.isFile()) && weblogicJarFile.exists())
          && weblogicJarFile.isFile()) {
        genericJar = new JarFile(genericJarFile);
        wlJar = new JarFile(weblogicJarFile);
        Hashtable genericEntries = new Hashtable();
        Hashtable wlEntries = new Hashtable();
        Hashtable replaceEntries = new Hashtable();
        for (Enumeration e = genericJar.entries(); e.hasMoreElements(); ) {
          JarEntry je = ((JarEntry) (e.nextElement()));
          genericEntries.put(je.getName().replace('\\', '/'), je);
        }
        for (Enumeration e = wlJar.entries(); e.hasMoreElements(); ) {
          JarEntry je = ((JarEntry) (e.nextElement()));
          wlEntries.put(je.getName(), je);
        }
        ClassLoader genericLoader = getClassLoaderFromJar(genericJarFile);
        for (Enumeration e = genericEntries.keys(); e.hasMoreElements(); ) {
          String filepath = ((String) (e.nextElement()));
          if (wlEntries.containsKey(filepath)) {
            JarEntry genericEntry = ((JarEntry) (genericEntries.get(filepath)));
            JarEntry wlEntry = ((JarEntry) (wlEntries.get(filepath)));
            if ((genericEntry.getCrc() != wlEntry.getCrc())
                || (genericEntry.getSize() != wlEntry.getSize())) {
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
              } else if (!genericEntry.getName().equals("META-INF/MANIFEST.MF")) {
                log(("Non class file " + genericEntry.getName()) + " has changed", MSG_VERBOSE);
                rebuild = true;
                break;
              }
            }
          } else {
            log(("File " + filepath) + " not present in weblogic jar", MSG_VERBOSE);
            rebuild = true;
            break;
          }
        }
        if (!rebuild) {
          log("No rebuild needed - updating jar", MSG_VERBOSE);
          newWLJarFile = new File(weblogicJarFile.getAbsolutePath() + ".temp");
          if (newWLJarFile.exists()) {
            newWLJarFile.delete();
          }
          newJarStream = new JarOutputStream(new FileOutputStream(newWLJarFile));
          newJarStream.setLevel(0);
          for (Enumeration e = wlEntries.elements(); e.hasMoreElements(); ) {
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
              is = wlJar.getInputStream(je);
            }
            newJarStream.putNextEntry(new JarEntry(je.getName()));
            while ((bytesRead = is.read(buffer)) != (-1)) {
              newJarStream.write(buffer, 0, bytesRead);
            }
            is.close();
          }
        } else {
          log("Weblogic Jar rebuild needed due to changed interface or XML", MSG_VERBOSE);
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
      if (wlJar != null) {
        try {
          wlJar.close();
        } catch (IOException closeException) {
        }
      }
      if (newJarStream != null) {
        try {
          newJarStream.close();
        } catch (IOException closeException) {
        }
        weblogicJarFile.delete();
        newWLJarFile.renameTo(weblogicJarFile);
        if (!weblogicJarFile.exists()) {
          rebuild = true;
        }
      }
    }
    return rebuild;
  }
}
