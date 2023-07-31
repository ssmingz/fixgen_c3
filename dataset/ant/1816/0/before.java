class PlaceHold {
  private boolean validateAndExecute() throws BuildException {
    if ((file == null) && (filesets.size() == 0)) {
      throw new BuildException("Specify at least one source - a file or a fileset.");
    }
    if (((file != null) && file.exists()) && file.isDirectory()) {
      throw new BuildException("Checksum cannot be generated for directories");
    }
    if ((property != null) && (fileext != null)) {
      throw new BuildException("Property and FileExt cannot co-exist.");
    }
    if (property != null) {
      if (forceOverwrite) {
        throw new BuildException("ForceOverwrite cannot be used when Property is specified");
      }
      if (file != null) {
        if (filesets.size() > 0) {
          throw new BuildException("Multiple files cannot be used when Property is specified");
        }
      } else if (filesets.size() > 1) {
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
    if (fileext == null) {
      fileext = "." + algorithm;
    } else if (fileext.trim().length() == 0) {
      throw new BuildException("File extension when specified must not be an empty string");
    }
    messageDigest = null;
    if (provider != null) {
      try {
        messageDigest = MessageDigest.getInstance(algorithm, provider);
      } catch (NoSuchAlgorithmException noalgo) {
        throw new BuildException(noalgo, location);
      } catch (NoSuchProviderException noprovider) {
        throw new BuildException(noprovider, location);
      }
    } else {
      try {
        messageDigest = MessageDigest.getInstance(algorithm);
      } catch (NoSuchAlgorithmException noalgo) {
        throw new BuildException(noalgo, location);
      }
    }
    if (messageDigest == null) {
      throw new BuildException("Unable to create Message Digest", location);
    }
    addToIncludeFileMap(file);
    int sizeofFileSet = filesets.size();
    for (int i = 0; i < sizeofFileSet; i++) {
      FileSet fs = ((FileSet) (filesets.elementAt(i)));
      DirectoryScanner ds = fs.getDirectoryScanner(project);
      String[] srcFiles = ds.getIncludedFiles();
      for (int j = 0; j < srcFiles.length; j++) {
        File src = new File(srcFiles[j]);
        addToIncludeFileMap(src);
      }
    }
    return generateChecksums();
  }
}
