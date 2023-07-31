class PlaceHold {
  private boolean checkFile() {
    if (filepath == null) {
      return checkFile(project.resolveFile(file), file);
    } else {
      String[] paths = filepath.list();
      for (int i = 0; i < paths.length; ++i) {
        log("Searching " + paths[i], MSG_DEBUG);
        File path = new File(paths[i]);
        if (path.exists() && file.equals(paths[i])) {
          if (type == null) {
            log("Found: " + path, MSG_VERBOSE);
            return true;
          } else if (type.isDir() && path.isDirectory()) {
            log("Found directory: " + path, MSG_VERBOSE);
            return true;
          } else if (type.isFile() && path.isFile()) {
            log("Found file: " + path, MSG_VERBOSE);
            return true;
          }
          return false;
        }
        FileUtils fileUtils = FileUtils.newFileUtils();
        File parent = fileUtils.getParentFile(path);
        if (((parent != null) && parent.exists()) && file.equals(parent.getAbsolutePath())) {
          if (type == null) {
            log("Found: " + parent, MSG_VERBOSE);
            return true;
          } else if (type.isDir()) {
            log("Found directory: " + parent, MSG_VERBOSE);
            return true;
          }
          return false;
        }
        if (path.exists() && path.isDirectory()) {
          if (checkFile(new File(path, file), (file + " in ") + path)) {
            return true;
          }
        }
        if ((parent != null) && parent.exists()) {
          if (checkFile(new File(parent, file), (file + " in ") + parent)) {
            return true;
          }
        }
        if (parent != null) {
          File grandParent = fileUtils.getParentFile(parent);
          if ((grandParent != null) && grandParent.exists()) {
            if (checkFile(new File(grandParent, file), (file + " in ") + grandParent)) {
              return true;
            }
          }
        }
      }
    }
    return false;
  }
}
