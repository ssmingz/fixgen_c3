class PlaceHold {
  void updateBackground(int index) {
    int panel = findPart(index, DOCKPANEL_PART_NAME);
    if (panel != 0) {
      if ((cellBackground != null) && (cellBackground[index] != null)) {
        int brush = OS.gcnew_SolidColorBrush(cellBackground[index].handle);
        int current = OS.Panel_Background(panel);
        if (!OS.Object_Equals(brush, current)) {
          OS.Panel_Background(panel, brush);
        }
        OS.GCHandle_Free(current);
        OS.GCHandle_Free(brush);
      } else {
        int property = OS.Panel_BackgroundProperty();
        OS.DependencyObject_ClearValue(panel, property);
        OS.GCHandle_Free(property);
      }
      OS.GCHandle_Free(panel);
    }
  }
}
