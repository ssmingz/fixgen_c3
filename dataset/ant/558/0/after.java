class PlaceHold {
  public void processDescriptor(String descriptorFileName, SAXParser saxParser) {
    FileInputStream descriptorStream = null;
    try {
      DescriptorHandler handler = getDescriptorHandler(srcDir);
      descriptorStream = new FileInputStream(new File(getDescriptorDir(), descriptorFileName));
      saxParser.parse(new InputSource(descriptorStream), handler);
      Hashtable ejbFiles = handler.getFiles();
      String baseName = "";
      if (baseJarName != null) {
        baseName = baseJarName;
      } else {
        int lastSeparatorIndex = descriptorFileName.lastIndexOf(File.separator);
        int endBaseName = -1;
        if (lastSeparatorIndex != (-1)) {
          endBaseName = descriptorFileName.indexOf(baseNameTerminator, lastSeparatorIndex);
        } else {
          endBaseName = descriptorFileName.indexOf(baseNameTerminator);
        }
        if (endBaseName != (-1)) {
          baseName = descriptorFileName.substring(0, endBaseName);
        }
        baseName = descriptorFileName.substring(0, endBaseName);
      }
      ejbFiles.put(META_DIR + EJB_DD, new File(getDescriptorDir(), descriptorFileName));
      addVendorFiles(ejbFiles, baseName);
      checkAndAddInherited(ejbFiles);
      if (flatDestDir && (baseName.length() != 0)) {
        int startName = baseName.lastIndexOf(File.separator);
        if (startName == (-1)) {
          startName = 0;
        }
        int endName = baseName.length();
        baseName = baseName.substring(startName, endName);
      }
      File jarFile = getVendorOutputJarFile(baseName);
      boolean needBuild = true;
      if (jarFile.exists()) {
        long lastBuild = jarFile.lastModified();
        Iterator fileIter = ejbFiles.values().iterator();
        needBuild = false;
        while ((needBuild == false) && fileIter.hasNext()) {
          File currentFile = ((File) (fileIter.next()));
          needBuild = lastBuild < currentFile.lastModified();
          if (needBuild) {
            log(("Build needed because " + currentFile.getPath()) + " is out of date", MSG_VERBOSE);
          }
        }
      }
      if (needBuild) {
        log(
            ((("building " + jarFile.getName()) + " with ") + String.valueOf(ejbFiles.size()))
                + " files",
            MSG_INFO);
        writeJar(baseName, jarFile, ejbFiles);
      } else {
        log(jarFile.toString() + " is up to date.", MSG_VERBOSE);
      }
    } catch (SAXException se) {
      String msg =
          ((("SAXException while parsing '" + descriptorFileName.toString())
                      + "'. This probably indicates badly-formed XML.")
                  + "  Details: ")
              + se.getMessage();
      throw new BuildException(msg, se);
    } catch (IOException ioe) {
      String msg =
          ((("IOException while parsing'" + descriptorFileName.toString())
                      + "'.  This probably indicates that the descriptor")
                  + " doesn't exist. Details: ")
              + ioe.getMessage();
      throw new BuildException(msg, ioe);
    } finally {
      if (descriptorStream != null) {
        try {
          descriptorStream.close();
        } catch (IOException closeException) {
        }
      }
    }
  }
}
