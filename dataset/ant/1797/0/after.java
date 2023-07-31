class PlaceHold {
  protected boolean renameFile(File sourceFile, File destFile, boolean filtering, boolean overwrite)
      throws IOException, BuildException {
    if (((destFile.isDirectory() || filtering) || (getFilterSets().size() > 0))
        || (getFilterChains().size() > 0)) {
      return false;
    }
    if (destFile.isFile() && (!destFile.canWrite())) {
      if (!getForce()) {
        throw new IOException(("can't replace read-only destination " + "file ") + destFile);
      } else if (!getFileUtils().tryHardToDelete(destFile)) {
        throw new IOException(("failed to delete read-only " + "destination file ") + destFile);
      }
    }
    File parent = destFile.getParentFile();
    if ((parent != null) && (!parent.exists())) {
      parent.mkdirs();
    } else if (destFile.isFile()) {
      sourceFile = getFileUtils().normalize(sourceFile.getAbsolutePath()).getCanonicalFile();
      destFile = getFileUtils().normalize(destFile.getAbsolutePath());
      if (destFile.getAbsolutePath().equals(sourceFile.getAbsolutePath())) {
        log(((("Rename of " + sourceFile) + " to ") + destFile) + " is a no-op.", MSG_VERBOSE);
        return true;
      }
      if (!(getFileUtils().areSame(sourceFile, destFile)
          || getFileUtils().tryHardToDelete(destFile, performGc))) {
        throw new BuildException("Unable to remove existing file " + destFile);
      }
    }
    return sourceFile.renameTo(destFile);
  }
}
