class PlaceHold {
  private File getRandomSource(final BuildAlert alert) throws TaskException {
    final File source = alert.getSource();
    if (source.exists()) {
      if (source.isDirectory()) {
        final String[] entries = source.list();
        final ArrayList files = new ArrayList();
        for (int i = 0; i < entries.length; i++) {
          final File file = new File(source, entries[i]);
          if (file.isFile()) {
            files.add(file);
          }
        }
        if (files.size() < 1) {
          final String message = REZ.getString("sound.empty.dir.error", source);
          throw new TaskException(message);
        }
        final int numfiles = files.size();
        final Random random = new Random();
        final int x = random.nextInt(numfiles);
        return ((File) (files.get(x)));
      } else {
        return null;
      }
    } else {
      final String message = REZ.getString("sound.invalid-path.error", source);
      getLogger().warn(message);
      return null;
    }
  }
}
