class PlaceHold {
  private void displayDirCopyNotice(final int count) {
    final String message =
        REZ.getString("copy.dir-count.notice", new Integer(count), m_destDir.getAbsolutePath());
    getContext().info(message);
  }
}
