class PlaceHold {
  private boolean validateAndExecute() throws TaskException {
    if ((file == null) && (filesets.size() == 0)) {
      throw new TaskException("Specify at least one source - a file or a fileset.");
    }
    if (((file != null) && file.exists()) && file.isDirectory()) {
      throw new TaskException("Checksum cannot be generated for directories");
    }
    if ((property != null) && (fileext != null)) {
      throw new TaskException("Property and FileExt cannot co-exist.");
    }
    if (property != null) {
      if (forceOverwrite) {
        throw new TaskException("ForceOverwrite cannot be used when Property is specified");
      }
      if (file != null) {
        if (filesets.size() > 0) {
          throw new TaskException("Multiple files cannot be used when Property is specified");
        }
      } else if (filesets.size() > 1) {
        throw new TaskException("Multiple files cannot be used when Property is specified");
      }
    }
    if (verifyProperty != null) {
      isCondition = true;
    }
    if ((verifyProperty != null) && forceOverwrite) {
      throw new TaskException("VerifyProperty and ForceOverwrite cannot co-exist.");
    }
    if (isCondition && forceOverwrite) {
      throw new TaskException("ForceOverwrite cannot be used when conditions are being used.");
    }
    if (fileext == null) {
      fileext = "." + algorithm;
    } else if (fileext.trim().length() == 0) {
      throw new TaskException("File extension when specified must not be an empty string");
    }
    messageDigest = null;
    if (provider != null) {
      try {
        messageDigest = MessageDigest.getInstance(algorithm, provider);
      } catch (NoSuchAlgorithmException noalgo) {
        throw new TaskException(noalgo.toString(), noalgo);
      } catch (NoSuchProviderException noprovider) {
        throw new TaskException(noprovider.toString(), noprovider);
      }
    } else {
      try {
        messageDigest = MessageDigest.getInstance(algorithm);
      } catch (NoSuchAlgorithmException noalgo) {
        throw new TaskException(noalgo.toString(), noalgo);
      }
    }
    if (messageDigest == null) {
      throw new TaskException("Unable to create Message Digest");
    }
    addToIncludeFileMap(file);
    int sizeofFileSet = filesets.size();
    for (int i = 0; i < sizeofFileSet; i++) {
      FileSet fs = ((FileSet) (filesets.elementAt(i)));
      DirectoryScanner ds = fs.getDirectoryScanner(getProject());
      String[] srcFiles = ds.getIncludedFiles();
      for (int j = 0; j < srcFiles.length; j++) {
        File src = new File(fs.getDir(getProject()), srcFiles[j]);
        addToIncludeFileMap(src);
      }
    }
    return generateChecksums();
  }
}
