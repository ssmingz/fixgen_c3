class PlaceHold {
  public String[] restrict(String[] files, File srcDir, File destDir, FileNameMapper mapper)
      throws TaskException {
    long now = new Date().getTime();
    StringBuffer targetList = new StringBuffer();
    if (Os.isFamily(OS_FAMILY_WINDOWS)) {
      now += 2000;
    }
    final ArrayList v = new ArrayList();
    for (int i = 0; i < files.length; i++) {
      final String[] targets = mapper.mapFileName(files[i]);
      if ((targets == null) || (targets.length == 0)) {
        final String message = files[i] + " skipped - don\'t know how to handle it";
        getLogger().debug(message);
        continue;
      }
      final File src = FileUtil.resolveFile(srcDir, files[i]);
      if (src.lastModified() > now) {
        final String message = ("Warning: " + files[i]) + " modified in the future.";
        getLogger().warn(message);
      }
      boolean added = false;
      targetList.setLength(0);
      for (int j = 0; (!added) && (j < targets.length); j++) {
        File dest = FileUtil.resolveFile(destDir, targets[j]);
        if (!dest.exists()) {
          final String message =
              ((files[i] + " added as ") + dest.getAbsolutePath()) + " doesn\'t exist.";
          getLogger().debug(message);
          v.add(files[i]);
          added = true;
        } else if (src.lastModified() > dest.lastModified()) {
          final String message =
              ((files[i] + " added as ") + dest.getAbsolutePath()) + " is outdated.";
          getLogger().debug(message);
          v.add(files[i]);
          added = true;
        } else {
          if (targetList.length() > 0) {
            targetList.append(", ");
          }
          targetList.append(dest.getAbsolutePath());
        }
      }
      if (!added) {
        final String message =
            (((files[i] + " omitted as ") + targetList.toString())
                    + (targets.length == 1 ? " is" : " are "))
                + " up to date.";
        getLogger().debug(message);
      }
    }
    final String[] result = new String[v.size()];
    return ((String[]) (v.toArray(result)));
  }
}
