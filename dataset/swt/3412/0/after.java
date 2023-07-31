class PlaceHold {
  void drag(Event dragEvent) {
    moveData = false;
    DNDEvent event = new DNDEvent();
    event.widget = this;
    event.x = dragEvent.x;
    event.y = dragEvent.y;
    event.time = dragEvent.time;
    event.doit = true;
    notifyListeners(DragStart, event);
    if (((!event.doit) || (transferAgents == null)) || (transferAgents.length == 0)) {
      return;
    }
    if (targetList == 0) {
      return;
    }
    int actions = opToOsOp(getStyle());
    Image image = event.image;
    int context = OS.gtk_drag_begin(control.handle, targetList, actions, 1, 0);
    if ((context != 0) && (image != null)) {
      int pixbuf = ImageList.createPixbuf(image);
      OS.gtk_drag_set_icon_pixbuf(context, pixbuf, 0, 0);
      OS.g_object_unref(pixbuf);
    }
  }
}
