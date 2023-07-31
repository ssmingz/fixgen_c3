class PlaceHold {
  public void execute() {
    if (_name == null) {
      throw new BuildException("Missing 'property' attribute!");
    }
    if (_dir == null) {
      throw new BuildException("Missing 'jarfile' attribute!");
    }
    if (getProject().getProperty(_name) != null) {
      throw new BuildException(("Property '" + _name) + "' already set!");
    }
    if (_path == null) {
      throw new BuildException("Missing nested <classpath>!");
    }
    final FileUtils fileUtils = FileUtils.getFileUtils();
    _dir = fileUtils.normalize(_dir.getAbsolutePath());
    File currDir = _dir;
    String[] dirs = new String[_maxParentLevels + 1];
    for (int i = 0; i < (_maxParentLevels + 1); ++i) {
      dirs[i] = currDir.getAbsolutePath() + File.separatorChar;
      currDir = currDir.getParentFile();
      if (currDir == null) {
        _maxParentLevels = i + 1;
        break;
      }
    }
    String[] elements = _path.list();
    StringBuffer buffer = new StringBuffer();
    StringBuffer element = new StringBuffer();
    for (int i = 0; i < elements.length; ++i) {
      File pathEntry = new File(elements[i]);
      pathEntry = fileUtils.normalize(pathEntry.getAbsolutePath());
      String fullPath = pathEntry.getAbsolutePath();
      String relPath = null;
      for (int j = 0; j <= _maxParentLevels; ++j) {
        String dir = dirs[j];
        if (!fullPath.startsWith(dir)) {
          continue;
        }
        element.setLength(0);
        for (int k = 0; k < j; ++k) {
          element.append("..");
          element.append(File.separatorChar);
        }
        element.append(fullPath.substring(dir.length()));
        relPath = element.toString();
        break;
      }
      if (relPath == null) {
        throw new BuildException((("No suitable relative path from " + _dir) + " to ") + fullPath);
      }
      if (File.separatorChar != '/') {
        relPath = relPath.replace(File.separatorChar, '/');
      }
      if (pathEntry.isDirectory()) {
        relPath = relPath + '/';
      }
      try {
        relPath = Locator.encodeURI(relPath);
      } catch (UnsupportedEncodingException exc) {
        throw new BuildException(exc);
      }
      buffer.append(relPath);
      buffer.append(' ');
    }
    if (buffer.length() > 0) {
      buffer.setLength(buffer.length() - 1);
    }
    getProject().setNewProperty(_name, buffer.toString());
  }
}
