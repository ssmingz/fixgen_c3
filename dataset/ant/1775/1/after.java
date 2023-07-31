class PlaceHold {
  public void rename(File from, File to) throws IOException {
    if (to.exists() && (!to.delete())) {
      throw new IOException((("Failed to delete " + to) + " while trying to rename ") + from);
    }
    File parent = getParentFile(to);
    if (((parent != null) && (!parent.exists())) && (!parent.mkdirs())) {
      throw new IOException(
          (("Failed to create directory " + parent) + " while trying to rename ") + from);
    }
    if (!from.renameTo(to)) {
      copyFile(from, to);
      if (!from.delete()) {
        throw new IOException(("Failed to delete " + from) + " while trying to rename it.");
      }
    }
  }
}
