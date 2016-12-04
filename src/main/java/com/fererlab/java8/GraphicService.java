package com.fererlab.java8;

import java.util.Collection;

public interface GraphicService {

    void addPanel(Panel parent, Panel child);

    Collection<Panel> getPanels(Panel parent);

    Panel removePanel(Panel parent, String title);

}