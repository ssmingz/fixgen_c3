class PlaceHold {
  private File _getSourceRoot(String packageName) throws InvalidPackageException {
    File sourceFile;
    try {
      sourceFile = _doc.getFile();
    } catch (IllegalStateException ise) {
      throw new InvalidPackageException(
          -1, "Can not get source root for " + "unsaved file. Please save.");
    } catch (FileMovedException fme) {
      throw new InvalidPackageException(
          -1, "File has been moved or deleted " + "from its previous location. Please save.");
    }
    if (packageName.equals("")) {
      return sourceFile.getParentFile();
    }
    Stack packageStack = new Stack();
    int dotIndex = packageName.indexOf('.');
    int curPartBegins = 0;
    while (dotIndex != (-1)) {
      packageStack.push(packageName.substring(curPartBegins, dotIndex));
      curPartBegins = dotIndex + 1;
      dotIndex = packageName.indexOf('.', dotIndex + 1);
    }
    packageStack.push(packageName.substring(curPartBegins));
    File parentDir = sourceFile;
    while (!packageStack.empty()) {
      String part = ((String) (packageStack.pop()));
      parentDir = parentDir.getParentFile();
      if (parentDir == null) {
        throw new RuntimeException("parent dir is null?!");
      }
      if (!part.equals(parentDir.getName())) {
        String msg =
            (((((("The source file " + sourceFile.getAbsolutePath())
                                    + " is in the wrong directory or in the wrong package. ")
                                + "The directory name ")
                            + parentDir.getName())
                        + " does not match the package component ")
                    + part)
                + ".";
        throw new InvalidPackageException(-1, msg);
      }
    }
    parentDir = parentDir.getParentFile();
    if (parentDir == null) {
      throw new RuntimeException("parent dir of first component is null?!");
    }
    return parentDir;
  }
}
