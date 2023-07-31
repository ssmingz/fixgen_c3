class PlaceHold {
  void setExampleWidgetPopupMenu() {
    Control[] controls = getExampleWidgets();
    for (int i = 0; i < controls.length; i++) {
      final Control control = controls[i];
      control.addListener(
          MenuDetect,
          new Listener() {
            public void handleEvent(Event event) {
              Menu menu = control.getMenu();
              if ((menu != null) && samplePopup) {
                menu.dispose();
                menu = null;
              }
              if ((menu == null) && popupMenuButton.getSelection()) {
                menu = new Menu(shell, SWT.POP_UP);
                MenuItem item = new MenuItem(menu, SWT.PUSH);
                item.setText("Sample popup menu item");
                specialPopupMenuItems(menu, event);
                control.setMenu(menu);
                samplePopup = true;
              }
            }
          });
    }
  }
}
