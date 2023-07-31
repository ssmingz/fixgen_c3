class PlaceHold {
  private boolean validateAndExecute() throws BuildException {
    String savedFileExt = fileext;
    if ((file == null) && ((resources == null) || (resources.size() == 0))) {
      throw new BuildException("Specify at least one source - a file or a resource collection.");
    }
    if (!((resources == null) || resources.isFilesystemOnly())) {
      throw new BuildException("Can only calculate checksums for file-based resources.");
    }
    if (((file != null) && file.exists()) && file.isDirectory()) {
      throw new BuildException("Checksum cannot be generated for directories");
    }
    if ((file != null) && (totalproperty != null)) {
      throw new BuildException("File and Totalproperty cannot co-exist.");
    }
    if ((property != null) && (fileext != null)) {
      throw new BuildException("Property and FileExt cannot co-exist.");
    }
    if (property != null) {
      if (forceOverwrite) {
        throw new BuildException("ForceOverwrite cannot be used when Property is specified");
      }
      int ct = 0;
      if (resources != null) {
        ct += resources.size();
      }
      if (file != null) {
        ct++;
      }
      if (ct > 1) {
        throw new BuildException("Multiple files cannot be used when Property is specified");
      }
    }
    if (verifyProperty != null) {
      isCondition = true;
    }
    if ((verifyProperty != null) && forceOverwrite) {
      throw new BuildException("VerifyProperty and ForceOverwrite cannot co-exist.");
    }
    if (isCondition && forceOverwrite) {
      throw new BuildException("ForceOverwrite cannot be used when conditions are being used.");
    }
    messageDigest = null;
    if (provider != null) {
      try {
        messageDigest = MessageDigest.getInstance(algorithm, provider);
      } catch (NoSuchAlgorithmException noalgo) {
        throw new BuildException(noalgo, getLocation());
      } catch (NoSuchProviderException noprovider) {
        throw new BuildException(noprovider, getLocation());
      }
    } else {
      try {
        messageDigest = MessageDigest.getInstance(algorithm);
      } catch (NoSuchAlgorithmException noalgo) {
        throw new BuildException(noalgo, getLocation());
      }
    }
    if (messageDigest == null) {
      throw new BuildException("Unable to create Message Digest", getLocation());
    }
    if (fileext == null) {
      fileext = "." + algorithm;
    } else if (fileext.trim().length() == 0) {
      throw new BuildException("File extension when specified must not be an empty string");
    }
    try {
      if (resources != null) {
        for (Iterator i = resources.iterator(); i.hasNext(); ) {
          Resource r = ((Resource) (i.next()));
          File src = ((FileProvider) (r)).getFile();
          if ((totalproperty != null) || (todir != null)) {
            relativeFilePaths.put(src, r.getName().replace(File.separatorChar, '/'));
          }
          addToIncludeFileMap(src);
        }
      }
      if (file != null) {
        if ((totalproperty != null) || (todir != null)) {
          relativeFilePaths.put(file, file.getName().replace(File.separatorChar, '/'));
        }
        addToIncludeFileMap(file);
      }
      return generateChecksums();
    } finally {
      fileext = savedFileExt;
      includeFileMap.clear();
    }
  }
}
