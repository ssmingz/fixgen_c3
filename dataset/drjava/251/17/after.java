class RecentFileManager {
  public RecentFileManager(int pos, JMenu fileMenu, MainFrame frame) {
    _pos = pos;
    _fileMenu = fileMenu;
    _frame = frame;
    _recentFiles = new Vector<File>();
    _recentMenuItems = new Vector<JMenuItem>();
    Vector<File> files = DrJava.getConfig().getSetting(RECENT_FILES);
    for (int i = files.size() - 1; i >= 0; i--) {
      updateOpenFiles(files.get(i));
    }
  }
}
