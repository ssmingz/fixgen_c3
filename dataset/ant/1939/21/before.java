class PlaceHold {
  private boolean checkFile() throws TaskException {
    if (m_filepath == null) {
      return checkFile(resolveFile(m_file), m_file);
    } else {
      String[] paths = m_filepath.list();
      for (int i = 0; i < paths.length; ++i) {
        getLogger().debug("Searching " + paths[i]);
        File path = new File(paths[i]);
        if (path.exists() && m_file.equals(paths[i])) {
          if (m_type == null) {
            getLogger().debug("Found: " + path);
            return true;
          } else if (m_type.isDir() && path.isDirectory()) {
            getLogger().debug("Found directory: " + path);
            return true;
          } else if (m_type.isFile() && path.isFile()) {
            getLogger().debug("Found file: " + path);
            return true;
          }
          return false;
        }
        File parent = path.getParentFile();
        if (((parent != null) && parent.exists()) && m_file.equals(parent.getAbsolutePath())) {
          if (m_type == null) {
            getLogger().debug("Found: " + parent);
            return true;
          } else if (m_type.isDir()) {
            getLogger().debug("Found directory: " + parent);
            return true;
          }
          return false;
        }
        if (path.exists() && path.isDirectory()) {
          if (checkFile(new File(path, m_file), (m_file + " in ") + path)) {
            return true;
          }
        }
        if ((parent != null) && parent.exists()) {
          if (checkFile(new File(parent, m_file), (m_file + " in ") + parent)) {
            return true;
          }
        }
        if (parent != null) {
          File grandParent = parent.getParentFile();
          if ((grandParent != null) && grandParent.exists()) {
            if (checkFile(new File(grandParent, m_file), (m_file + " in ") + grandParent)) {
              return true;
            }
          }
        }
      }
    }
    return false;
  }
}
