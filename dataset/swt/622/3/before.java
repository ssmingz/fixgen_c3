class PlaceHold {
  private void closeNotify(CTabItem item, int time) {
    CTabFolderEvent event = new CTabFolderEvent(this);
    event.widget = this;
    event.time = time;
    event.item = item;
    event.doit = true;
    if (tabListeners != null) {
      for (int i = 0; i < tabListeners.length; i++) {
        tabListeners[i].itemClosed(event);
      }
    }
    if (event.doit) {
      item.dispose();
    }
  }
}
