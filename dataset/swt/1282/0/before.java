class PlaceHold {
  int createHeaderTemplate(int columnJniRef) {
    int template = OS.gcnew_DataTemplate();
    int stackPanelType = OS.StackPanel_typeid();
    int stackPanelName = createDotNetString(STACKPANEL_PART_NAME, false);
    int stackPanelNode = OS.gcnew_FrameworkElementFactory(stackPanelType, stackPanelName);
    OS.GCHandle_Free(stackPanelName);
    OS.GCHandle_Free(stackPanelType);
    int textType = OS.TextBlock_typeid();
    int textName = createDotNetString(TEXT_PART_NAME, false);
    int textNode = OS.gcnew_FrameworkElementFactory(textType, textName);
    OS.GCHandle_Free(textName);
    OS.GCHandle_Free(textType);
    int imageType = OS.Image_typeid();
    int imageName = createDotNetString(IMAGE_PART_NAME, false);
    int imageNode = OS.gcnew_FrameworkElementFactory(imageType, imageName);
    OS.GCHandle_Free(imageName);
    OS.GCHandle_Free(imageType);
    int marginProperty = OS.FrameworkElement_MarginProperty();
    int thickness = OS.gcnew_Thickness(0, 0, 4, 0);
    OS.FrameworkElementFactory_SetValue(imageNode, marginProperty, thickness);
    OS.GCHandle_Free(thickness);
    OS.GCHandle_Free(marginProperty);
    int orientationProperty = OS.StackPanel_OrientationProperty();
    OS.FrameworkElementFactory_SetValueOrientation(
        stackPanelNode, orientationProperty, Orientation_Horizontal);
    OS.GCHandle_Free(orientationProperty);
    int stretchProperty = OS.Image_StretchProperty();
    OS.FrameworkElementFactory_SetValueStretch(imageNode, stretchProperty, Stretch_None);
    OS.GCHandle_Free(stretchProperty);
    OS.FrameworkElementFactory_AppendChild(stackPanelNode, imageNode);
    OS.GCHandle_Free(imageNode);
    OS.FrameworkElementFactory_AppendChild(stackPanelNode, textNode);
    OS.GCHandle_Free(textNode);
    OS.FrameworkTemplate_VisualTree(template, stackPanelNode);
    OS.GCHandle_Free(stackPanelNode);
    return template;
  }
}
