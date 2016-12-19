package com.fererlab.java8;

import java.util.Collection;

public class GraphicServiceImpl implements GraphicService {

    @Override
    public void addPanel(Panel parent, Panel child) {
	parent.addChild(child);
    }

    @Override
    public Collection<Panel> getPanels(Panel parent) {
	return parent.getChildren();
    }

    @Override
    public Panel removePanel(Panel parent, String title) {
	return parent.removeChild(title);
    }

}
