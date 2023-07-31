class PlaceHold {
  private JMenu _setUpProjectMenu(int mask) {
    JMenu projectMenu = new JMenu("Project");
    ONLY.setMnemonic(projectMenu, KeyEvent.VK_P);
    projectMenu.add(_newProjectAction);
    _addMenuItem(projectMenu, _openProjectAction, KEY_OPEN_PROJECT);
    projectMenu.add(_saveProjectAction);
    projectMenu.add(_saveProjectAsAction);
    _addMenuItem(projectMenu, _closeProjectAction, KEY_CLOSE_PROJECT);
    projectMenu.addSeparator();
    projectMenu.add(_compileProjectAction);
    projectMenu.add(_junitProjectAction);
    projectMenu.add(_runProjectAction);
    projectMenu.add(_cleanAction);
    projectMenu.add(_autoRefreshAction);
    projectMenu.add(_jarProjectAction);
    projectMenu.addSeparator();
    projectMenu.add(_projectPropertiesAction);
    return projectMenu;
  }
}
